var scoreComponentArray = [[1]]

// const score column
const scoreColumnItem = (i, j) => {
    let removeColumnButton = ""
    if (j !== 0) {
        removeColumnButton = `<button onclick="removeScoreColumn(${i}, ${j})"
                                                type="button"
                                                class="btn btn-sm bg-danger text-white">
                                            <i class="icon-copy fa fa-minus-circle"
                                               aria-hidden="true"></i>
                                        </button>`
    }

    return `<tr id="score-column-${i}-${j}">
                                    <td scope="row">
                                        <input name="scoreComponents[${i}].scoreColumns[${j}].name" 
                                                id="scoreComponents[${i}].scoreColumns[${j}].name"  type="text"
                                               class="form-control">
                                    </td>
                                    <td>
                                        <input name="scoreComponents[${i}].scoreColumns[${j}].weight"
                                                id=name="scoreComponents[${i}].scoreColumns[${j}].weight" 
                                         type="number" min="0.1" max="1" step="0.05"
                                               class="form-control hihi">
                                    </td>
                                    <td class="text-center">
                                       ${removeColumnButton}
                                    </td>
                                </tr>`
}

// const score component
const scoreComponentItem = (i, item = "") => {
    let removeComponentButton = ""
    if (i !== 0) {
        removeComponentButton = `<a href="javascript:;" onclick="removeScoreComponent(${i})"
                                    class="remove-task"  data-toggle="tooltip" data-placement="bottom" title="" 
                                    data-original-title="Remove Task">
                                    <i class="ion-minus-circled"></i>
                                </a>`
    }
    return `<li  id="score-component-${i}">
                 ${removeComponentButton}
                <div class="form-group row">
                    <div class="col-12">
                        <h6 class="text-black-50">Điểm thành phần</h6>
                        <hr/>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="font-weight-bold">Tên thành phần điểm<span
                                    class="text-danger">(*)</span></label>
                            <input name="scoreComponents[${i}].name"
                                    id="scoreComponents[${i}].name"
                                type="text" class="form-control">
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <label class="font-weight-bold">Các cột điểm<span
                                    class="text-danger">(*)</span></label>
                            <div class="pd-20 card-box height-100-p mb-10">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th scope="col" class="col-8">Tên cột điểm</th>
                                        <th scope="col" class="col-3">
                                        Trọng số <span class="font-weight-normal text-danger">(0.1 - 1)</span></th>
                                        <th scope="col" class="col-1">Hành động</th>
                                    </tr>
                                    </thead>
                                    <tbody id="score-columns-${i}">
                                        ${item}
                                    </tbody>
                                </table>
                                <div class="d-flex justify-content-end">
                                    <button onclick="addScoreColumn(${i})"
                                            type="button"
                                            class="btn btn-sm bg-success text-white">
                                            <i class="icon-copy fa fa-plus-circle"
                                            aria-hidden="true"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>`
}

const loadForm = () => {
    let scoreComponentArea = ""

    for (let i = 0; i < scoreComponentArray.length; i++) {
        let scoreColumnArea = ""
        for (let j = 0; j < scoreComponentArray[i].length; j++) {
            scoreColumnArea += scoreColumnItem(i, j);
        }

        scoreComponentArea += scoreComponentItem(i, scoreColumnArea)
    }

    document.getElementById("score-components").innerHTML = scoreComponentArea;
}

const addScoreComponent = () => {
    scoreComponentArray.push([1])
    let lengthCurrent = scoreComponentArray.length

    if (lengthCurrent >= 2) {
        document.getElementById(`score-component-${lengthCurrent - 2}`)
            .insertAdjacentHTML("afterend", scoreComponentItem(lengthCurrent - 1, scoreColumnItem(scoreComponentArray.length - 1, 0)))
    }
}

const addScoreColumn = (i) => {
    scoreComponentArray[i].push(1)

    let lengthCurrent = scoreComponentArray[i].length
    if (lengthCurrent >= 2) {
        document.getElementById(`score-column-${i}-${lengthCurrent - 2}`)
            .insertAdjacentHTML("afterend", scoreColumnItem(i, lengthCurrent - 1))
    }
}

const removeScoreComponent = (i) => {
    scoreComponentArray.splice(i, 1);
    document.getElementById("score-components")
        .removeChild(document.getElementById(`score-component-${i}`))
}

const removeScoreColumn = (i, j) => {
    scoreComponentArray[i].splice(j, 1);
    document.getElementById(`score-columns-${i}`)
        .removeChild(document.getElementById(`score-column-${i}-${j}`));
}


// load modal form
document.addEventListener("DOMContentLoaded", () => {
    loadForm();
});


const showAddEvaluationMethodModal = (endpoint) => {
    document.getElementById("myModalAddAndEditEvaluationMethod").innerText = "Thêm phương pháp đánh giá"

    document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint)
    $('#modal-add-edit-evaluation-method').modal()
}

const showEditEvaluationMethodModal = (endpoint, evaluationMethodId) => {
    loadEvaluationMethodById(endpoint, (data) => {
        document.getElementById("myModalAddAndEditEvaluationMethod").innerText = "Cập nhật phương pháp đánh giá"

        document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint, evaluationMethodId)
        $('#modal-add-edit-evaluation-method').modal()
        console.log(data)
    })
}

const loadEvaluationMethodById = (endpoint, callback) => {
    fetch(endpoint, {
        method: 'GET', headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        callback(data);
    }).catch(err => {
        errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok")
    })
}

const saveChange = (endpoint, evaluationMethodId = null) => {
    // get form
    let form = $("#form-add-edit-evaluation-method")
    let formDataDic = {}
    form.serializeArray().forEach(item => formDataDic[item.name] = item.value)

    // custom json
    let formData = {}
    let scoreComponents = []
    for (let i = 0; i < scoreComponentArray.length; i++) {
        let scoreColumns = []
        for (let j = 0; j < scoreComponentArray[i].length; j++) {
            scoreColumns.push({
                "name": formDataDic[`scoreComponents[${i}].scoreColumns[${j}].name`],
                "weight": formDataDic[`scoreComponents[${i}].scoreColumns[${j}].weight`]
            })
        }

        scoreComponents.push({
            "name": formDataDic[`scoreComponents[${i}].name`],
            "scoreColumns": scoreColumns
        })
    }
    formData["name"] = document.getElementById("name").value;
    formData["scoreComponents"] = scoreComponents;

    $('input').next('span').remove();

    if (evaluationMethodId === null) {
        // ADD
        fetch(endpoint, {
            method: "POST", body: JSON.stringify(formData),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-evaluation-method').hide();
                successfulAlert("Thêm phương pháp đánh giá thành công", "Ok", () => location.reload());
            } else {
                // error
                if (data["errorTotalWeight"] !== undefined) {
                    errorAlert("Đã có lỗi", "Tổng trọng số các cột điểm không được phép vượt quá 1.0 ~ 100%", "Ok")
                } else {
                    $.each(data, function (key, value) {
                        document.getElementsByName(`${key}`)[0].insertAdjacentHTML('afterend', '<span class="text-danger font-weight-normal">' + value + '</span>');
                    });
                }
            }
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình thêm dữ liệu!", "Ok")
        })
    } else {
        // UPDATE
        // fetch(endpoint, {
        //     method: "PATCH", body: JSON.stringify({
        //         "code": formData.code,
        //         "fullName": formData.fullName,
        //         "email": formData.email,
        //         "phone": formData.phone,
        //         "birthday": formData.birthday,
        //         "gender": formData.gender,
        //         "address": formData.address,
        //         "gpa": formData.gpa,
        //         "schoolYear": formData.schoolYear,
        //         "major": formData.major,
        //         "user": {
        //             "username": formData.code,
        //             "password": formData.password,
        //             "newPassword": formData.newPassword,
        //             "active": formData.active,
        //         }
        //     }), headers: {
        //         "Content-Type": "application/json"
        //     }
        // }).then(res => res.json()).then(data => {
        //     if (Object.keys(data).length === 0) {
        //         // successful
        //         $('#modal-add-edit-student').hide();
        //         successfulAlert("Cập nhật sinh viên thành công", "Ok", () => location.reload());
        //     } else {
        //         // error
        //         $.each(data, function (key, value) {
        //             if (key === "file") {
        //                 console.log("tính sau")
        //             } else if (key === "code" || key === "fullName" || key === "phone" || key === "email" || key === "birthday" || key === "address") {
        //                 $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
        //             }
        //         });
        //     }
        // }).catch(err => {
        //     errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình cập nhật!", "Ok")
        // })
    }
}

const deleteEvaluationMethodItem = (endpoint) => {
    // DELETE
    confirmAlert("Bạn có chắc không?", "Bạn sẽ không thể khôi phục điều này!", "Có, xóa nó", "Không, hủy bỏ", () => {
        fetch(endpoint, {
            method: "DELETE", headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (res) {
            if (res.status === 204) successfulAlert("Xóa phương pháp đánh giá thành công", "Ok", () => location.reload());
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!", "Ok")
        })
    })
}

// event before hidden modal
$('#modal-add-edit-evaluation-method').on('hidden.bs.modal', function (e) {
    $('input').next('span').remove();
    document.forms['form-add-edit-evaluation-method'].reset();
})

