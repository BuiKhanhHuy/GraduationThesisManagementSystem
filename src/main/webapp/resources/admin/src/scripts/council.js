var memberArray = [0]
var lecturersHtml = ''
var isLoadDataOptions = true;

const loadDataOptions = (callback) => {
    if (isLoadDataOptions) {
        fetch("/GraduationThesisManagementSystem/admin/api/thesis-options", {
            method: "GET", headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            let thesesHtml = ''
            data.forEach(d => thesesHtml += `<option value=${d[0]}>${d[1]} - ${d[2]}</option>`)
            document.getElementById("theses").innerHTML = thesesHtml;
        }).then(() => fetch("/GraduationThesisManagementSystem/admin/api/lecturer-options", {
            method: "GET", headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            data.forEach(d => lecturersHtml += `<option value=${d[0]}>${d[1]}</option>`)
            document.getElementById("lecturer-0").innerHTML = lecturersHtml;

            callback();
        })).catch(err => {
            console.log(err)
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok")
        })
        isLoadDataOptions = false;
    } else {
        callback();
    }
}

const memberItem = (i) => {
    let removeMemberButton = `
                             <a href="javascript:;" onclick="removeMemberItem(${i})"
                                class="remove-task text-danger"  data-toggle="tooltip" data-placement="bottom" title="" 
                                data-original-title="Remove member">
                                <i class="ion-minus-circled"></i>
                            </a>`

    if (i === 0) removeMemberButton = "";

    return ` <tr id="member-${i}">
                <th scope="row">
                    <input name="position-${i}" id="position-${i}" type="text" class="form-control"/>
                </th>
                <td>
                     <div>
                        <select class="custom-select form-control"
                                name="lecturer-${i}" id="lecturer-${i}" style="width: 100%; ">
                            ${lecturersHtml}
                        </select>
                    </div>
                </td>
                 <th scope="row" class="text-center">
                   ${removeMemberButton}
                </th>
            </tr>`
}

const loadMemberForm = (memberArray) => {
    let memberAreaElement = document.getElementById("member-area");
    let memberHtml = ''
    memberArray.forEach(value => memberHtml += memberItem(value))

    memberAreaElement.innerHTML = memberHtml + memberAreaElement.innerHTML;
}

// load member form
document.addEventListener("DOMContentLoaded", () => {
    loadMemberForm(memberArray);
});

// add member item
const addMemberItem = () => {
    let itemNumberCurrent = memberArray.at(-1)
    let itemNumberNext = itemNumberCurrent + 1

    console.log(memberArray.length)

    document.getElementById(`member-${itemNumberCurrent}`)
        .insertAdjacentHTML("afterend", memberItem(itemNumberNext))

    memberArray.push(itemNumberNext);
}

// remove member item
const removeMemberItem = (i) => {
    document.getElementById("member-area").removeChild(document.getElementById(`member-${i}`))

    memberArray.splice(memberArray.indexOf(i), 1)
}


const showAddCouncilModal = (endpoint) => {
    loadDataOptions(() => {
        document.getElementById("myModalAddAndEditCouncil").innerText = "Thành lập hội đồng"
        $('#modal-add-edit-council').modal()
        document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint)
    })
}
//
// const showEditMajorModal = (endpoint, majorId) => {
//     loadMajorById(endpoint, (data) => {
//         document.getElementById("myModalAddAndEditMajor").innerText = "Cập nhật ngành"
//         document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint, majorId)
//         $('#modal-add-edit-major').modal()
//
//         for (let d in data) {
//             if (d === "department") {
//                 data[d] === null ? document.forms['form-add-edit-major'][d].value = ''
//                     : document.forms['form-add-edit-major'][d].value = data[d].id
//             } else {
//                 document.forms['form-add-edit-major'][d].value = data[d];
//             }
//         }
//
//     })
// }
//
// const showViewMajorModal = (endpoint) => {
//     loadMajorById(endpoint, (data) => {
//
//         for (let d in data) {
//             if (d === "department") {
//                 data[d] === null ? document.getElementById(`data-department-name`).innerText = 'Chưa cập nhật' : document.getElementById(`data-department-name`).innerText = data[d].name
//             } else {
//                 data[d].toString() === "" ? document.getElementById(`data-${d}`).innerText = 'Chưa cập nhật' : document.getElementById(`data-${d}`).innerText = data[d]
//             }
//         }
//         $('#modal-view-major').modal()
//     })
// }
//
// const loadMajorById = (endpoint, callback) => {
//     fetch(endpoint, {
//         method: 'GET', headers: {
//             "Content-Type": "application/json"
//         }
//     }).then(res => res.json()).then(data => {
//         callback(data);
//     }).catch(err => {
//         errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok")
//     })
// }

const saveChange = (endpoint, councilId = null) => {
    $('input').next('span').remove();
    $('select + span').next('span').remove()
    $('textarea').next('span').remove()

    let formCouncilElement = document.getElementById("form-add-edit-council");
    let formData = {}
    formData["name"] = formCouncilElement["name"].value;
    formData["description"] = formCouncilElement["description"].value;
    formData["schoolYear"] = formCouncilElement["schoolYear"].value;
    formData["theses"] = [...formCouncilElement["theses"].options]
        .filter(option => option.selected)
        .map(option => parseInt(option.value));
    formData["councilDetails"] = memberArray.map(member => {
        return {
            "position": formCouncilElement[`position-${member}`].value,
            "lecturer": formCouncilElement[`lecturer-${member}`].value
        }
    })

    if (councilId === null) {
        // ADD
        fetch(endpoint, {
            method: "POST", body: JSON.stringify(formData), headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-council').hide();
                successfulAlert("Thành lập hội đồng thành công", "Ok", () => location.reload());
            } else {
                // error
                if (data["otherErrors"] !== undefined) {
                    errorAlert("Đã có lỗi khác rồi", data["errorTotalWeight"], "Ok")
                } else {
                    console.log(data)
                    $.each(data, function (key, value) {
                        if (key === "name" || key === "description" || key === "schoolYear") {
                            document.getElementsByName(`${key}`)[0].insertAdjacentHTML('afterend', '<span class="text-danger font-weight-normal">' + value + '</span>');
                        } else if (key === "theses") {
                            $('select[name=' + key + '] + span').after('<span class="text-danger">' + value + '</span>');
                        } else {
                            $('input[name=' + `position-${memberArray[parseInt(key.match(/\d/g).join(""))]}` + ']').after('<span class="text-danger font-weight-normal">' + value + '</span>');
                        }
                    });
                }
            }
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình thêm dữ liệu!", "Ok")
        })
    } else {
        // UPDATE
        // fetch(endpoint, {
        //     method: "PATCH", body: JSON.stringify(formData), headers: {
        //         "Content-Type": "application/json"
        //     }
        // }).then(res => res.json()).then(data => {
        //     if (Object.keys(data).length === 0) {
        //         // successful
        //         $('#modal-add-edit-major').hide();
        //         successfulAlert("Cập nhật thông tin ngành thành công", "Ok", () => location.reload());
        //     } else {
        //         // errror
        //         $.each(data, function (key, value) {
        //             $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
        //         });
        //     }
        // }).catch(err => {
        //     errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình cập nhật!", "Ok")
        // })
    }
}

// const deleteMajorItem = (endpoint) => {
//     // DELETE
//     confirmAlert("Bạn có chắc không?", "Bạn sẽ không thể khôi phục điều này!", "Có, xóa nó", "Không, hủy bỏ", () => {
//         const response = fetch(endpoint, {
//             method: "DELETE", headers: {
//                 'Content-Type': 'application/json'
//             }
//         }).then(function (res) {
//             if (res.status === 204) successfulAlert("Xóa ngành thành công", "Ok", () => location.reload());
//         }).catch(err => {
//             errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!", "Ok")
//         })
//     })
// }

// event before hidden modal
$('#modal-add-edit-council').on('hidden.bs.modal', function (e) {
    $('input').next('span').remove();
    $('textarea').next('span').remove()
    $('select + span').next('span').remove()
    document.forms['form-add-edit-council'].reset();

    memberArray = [0]
    loadMemberForm(memberArray)
})
