const typeMark = {
    Add: 0, Update: 1
}

// load html form update
const loadFormUpdate = (scoreComponents) => {
    let markFormElement = document.getElementById("mark-form");
    let markFormInnerHtml = '';
    let idx = 0;

    scoreComponents.map((scoreComponent, index) => {
        markFormInnerHtml += `<tr>
                                <td colSpan="2" class="font-weight-bold">
                                    ${index + 1}. ${scoreComponent.name}
                                <td/>
                            </tr>`

        scoreComponent.detailedScoreByComponentData.map(scoreDetail => {
            markFormInnerHtml += `<tr>
                                    <td>
                                        ${scoreDetail.scoreColumn.name}
                                    </td>
                                    <td>
                                        ${scoreDetail.scoreColumn.weight * 100}%
                                    </td>
                                    <td>
                                        <input data-score-detail-id="${scoreDetail.id}"
                                         name="scoreDetails[${idx++}].scoreNum" 
                                        id="scoreDetail${scoreDetail.id}" 
                                        value="${scoreDetail.scoreNum}"
                                        type="number" min="0" max="10"
                                        class="form-control form-control-sm">
                                    </td>
                                </tr>`
        })
    })

    markFormElement.innerHTML = markFormInnerHtml;
}

// load html form add
const loadFormAdd = (scoreComponents) => {
    let markFormElement = document.getElementById("mark-form");
    let markFormInnerHtml = '';
    let idx = 0;

    scoreComponents.map((scoreComponent, index) => {
        markFormInnerHtml += `<tr>
                                <td colSpan="2" class="font-weight-bold">
                                    ${index + 1}. ${scoreComponent.name}
                                <td/>
                            </tr>`

        scoreComponent.scoreColumns.map(scoreColumn => {
            markFormInnerHtml += `<tr>
                                    <td>
                                        ${scoreColumn.name}
                                    </td>
                                    <td>
                                        ${scoreColumn.weight * 100}%
                                    </td>
                                    <td>
                                        <input data-score-column-id="${scoreColumn.id}" 
                                        
                                        name="scoreDetails[${idx++}].scoreNum" 
                                        id="scoreColumn${scoreColumn.id}" 
                                        type="number" min="0" max="10" 
                                        class="form-control form-control-sm">
                                    </td>
                                </tr>`
        })
    })

    markFormElement.innerHTML = markFormInnerHtml;
}

// group score with score component
const groupScoreComponent = (data) => {
    let scoreComponentId = new Set();
    let scoreComponentName = new Set();
    let scoreComponent = []

    data.scoreDetails.map(value => {
        scoreComponentId.add(value.scoreColumn.scoreComponent.id)
        scoreComponentName.add(value.scoreColumn.scoreComponent.name)
    });

    scoreComponentId.forEach((value, idx) => scoreComponent.push({
        "id": value, "name": [...scoreComponentName][idx - 1], "detailedScoreByComponentData": data.scoreDetails
            .filter(m => m.scoreColumn.scoreComponent.id === value)
    }))

    return scoreComponent;
}

// load score by thesis id
const loadScoresByThesisId = (appContext, thesisId, councilDetailId, callback) => {
    let endpoint = `${appContext}admin/api/score/?thesisId=${thesisId}&councilDetailId=${councilDetailId}`

    fetch(endpoint, {
        method: 'GET', headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.text()).then(data => {
        let d = data.length === 0 ? null : JSON.parse(data)

        callback(d);
    }).catch(err => {
        errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok")
        console.error(err)
    })
}

const mark = (appContext, thesisId, councilDetailId) => {
    loadScoresByThesisId(appContext, thesisId, councilDetailId, (data) => {
        if (data === null) {
            console.log("KHONG CO DU LIEU!!")
            fetch(`${appContext}admin/api/evaluations-method/1`, {
                method: "GET", headers: {
                    "Content-Type": "application/json"
                }
            }).then(res => res.json()).then(evaluationData => {
                // load form add
                loadFormAdd(evaluationData.scoreComponents)

                // show modal add
                document.getElementById("btn-submit-form").onclick = () => saveChange(appContext, thesisId, councilDetailId, null)
                $('#modal-add-edit-score').modal()
            })
        } else {
            console.log("CO DU LIEU!!")
            loadFormUpdate(groupScoreComponent(data))

            // show modal update
            document.getElementById("btn-submit-form").onclick = () => saveChange(appContext, thesisId, councilDetailId, data.id)
            $('#modal-add-edit-score').modal()
        }
    })
}


// save data
const saveChange = (appContext, thesisId, councilDetailId, scoreId = null) => {
    // get form
    let form = document.getElementById("form-add-edit-score")
    let formInputs = form.getElementsByTagName("input")
    let scoreDetailList = []
    let formData = {}

    $('input').next('span').remove();

    if (scoreId === null) {
        console.log("ADD")
        formData = {
            "thesis": thesisId, "councilDetail": councilDetailId
        }
        for (let i = 0; i < formInputs.length; i++) {
            scoreDetailList.push({
                "scoreNum": formInputs[i].value, "scoreColumn": formInputs[i].getAttribute("data-score-column-id")
            })
        }
        formData["scoreDetails"] = scoreDetailList

        fetch(`${appContext}admin/api/scores`, {
            method: "POST", body: JSON.stringify(formData), headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            switch (res.status){
                case 403:
                    return Promise.reject("Không được phép thêm điểm số!")
                case 500:
                    return Promise.reject("Đã có lỗi trong quá trình thêm dữ liệu!")
                default:
                    return res.json();
            }
        }).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-score').hide();
                successfulAlert("Chấm điểm thành công", "Ok", () => location.reload());
            } else {
                // error
                $.each(data, function (key, value) {
                    document.getElementsByName(key)[0].insertAdjacentHTML("afterend", '<span class="text-danger">' + value + '</span>')
                });
            }
        }).catch(err => {
            errorAlert("Đã có lỗi", err, "Ok")
        })
    } else {
        console.log("UPDATE")
        formData = {
            "thesis": thesisId, "councilDetail": councilDetailId
        }
        for (let i = 0; i < formInputs.length; i++) {
            scoreDetailList.push({
                "scoreNum": formInputs[i].value, "id": formInputs[i].getAttribute("data-score-detail-id")
            })
        }
        formData["scoreDetails"] = scoreDetailList

        fetch(`${appContext}admin/api/scores/${scoreId}`, {
            method: "PATCH", body: JSON.stringify(formData), headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            switch (res.status){
                case 403:
                    return Promise.reject("Không được phép chỉnh sửa!")
                case 500:
                    return Promise.reject("Đã có lỗi trong quá trình thêm dữ liệu!")
                default:
                    return res.json();
            }
        }).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-score').hide();
                successfulAlert("Cập nhật điểm thành công", "Ok", () => location.reload());
            } else {
                // error
                $.each(data, function (key, value) {
                    document.getElementsByName(key)[0].insertAdjacentHTML("afterend", '<span class="text-danger">' + value + '</span>')
                });
            }
        }).catch(err => {
            errorAlert("Đã có lỗi", err, "Ok")
        })
    }
}

// event before hidden modal
$('#modal-add-edit-score').on('hidden.bs.modal', function (e) {
    $('input').next('span').remove();
    document.forms['form-add-edit-score'].reset();
})
