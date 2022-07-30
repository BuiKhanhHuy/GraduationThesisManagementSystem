const showAddNotificationModal = (endpoint) => {
    document.getElementById("myModalAddAndEditNotification").innerText = "Thêm thông báo"
    $('#modal-add-edit-notification').modal()
    // document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint)

}
//
// const showEditManageModal = (endpoint, manageId) => {
//     loadManageById(endpoint, (data) => {
//         document.getElementById("myModalAddAndEditManage").innerText = "Cập nhật quản trị viên"
//         document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint, manageId)
//         $('#modal-add-edit-manage').modal()
//
//         let form = document.forms['form-add-edit-manage']
//         form["fullName"].value = data.fullName;
//         form["email"].value = data.email
//         form["phone"].value = data.phone
//         if (data.user !== null) {
//             form["username"] = data.user.username
//             form["password"] = data.user.password
//             form["active"].checked = data.user.active
//         }
//     })
// }

// const showViewNewsModal = (endpoint) => {
//     loadNewsById(endpoint, (data) => {
//         console.log(data)
//         for (let d in data) {
//             if (d === "user") {
//                 if(data[d] === null)
//                 {
//                     document.getElementById(`data-user-username`).innerText = 'Chưa cập nhật'
//                     document.getElementById(`data-avatar`).src = "#"
//                 }else {
//                     document.getElementById(`data-user-username`).innerText = data[d].username
//                     document.getElementById(`data-avatar`).src = data[d].avatar
//                 }
//             }
//             else {
//                 document.getElementById(`data-${d}`).innerHTML = data[d]
//             }
//         }
//         $('#modal-view-news').modal()
//     })
// }
//
// const loadManageById = (endpoint, callback) => {
//     fetch(endpoint, {
//         method: 'GET', headers: {
//             "Content-Type": "application/json"
//         }
//     }).then(res => res.json()).then(data => {
//         callback(data);
//     })
//         .catch(err => {
//             errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok")
//         })
// }

const saveChange = (endpoint, notificationId = null) => {
    let form = $("#form-add-edit-notification")
    let formData = {}

    form.serializeArray().forEach(item => {
        formData[item.name] = item.value
    })
    formData["active"] = document.getElementById("active").checked;

    $('input').next('span').remove();

    if (notificationId === null) {
        // ADD
        fetch(endpoint, {
            method: "POST", body: JSON.stringify(), headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-notification').hide();
                successfulAlert("Gửi thông báo thành công", "Ok", () => location.reload());
            } else {
                // error
                $.each(data, function (key, value) {
                    $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
                });
            }
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình thêm dữ liệu!", "Ok")
        })
    } else {
        // // UPDATE
        fetch(endpoint, {
            method: "PATCH", body: JSON.stringify({
                "fullName": formData.fullName, "email": formData.email, "phone": formData.phone, "user": {
                    "username": formData.username, "password": formData.password, "active": formData.active,
                }
            }), headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-manage').hide();
                successfulAlert("Cập nhật quản trị viên thành công", "Ok", () => location.reload());
            } else {
                // error
                $.each(data, function (key, value) {
                    $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
                });
            }
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình cập nhật!", "Ok")
        })
    }
}
//
// const deleteManageItem = (endpoint) => {
//     // DELETE
//     confirmAlert("Bạn có chắc không?", "Bạn sẽ không thể khôi phục điều này!", "Có, xóa nó", "Không, hủy bỏ", () => {
//         fetch(endpoint, {
//             method: "DELETE", headers: {
//                 'Content-Type': 'application/json'
//             }
//         }).then(function (res) {
//             if (res.status === 204) successfulAlert("Xóa quản trị viên thành công", "Ok", () => location.reload());
//         }).catch(err => {
//             errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!", "Ok")
//         })
//     })
// }

// event before hidden modal
$('#modal-add-edit-notification').on('hidden.bs.modal', function (e) {
    $('input').next('span').remove();
    document.forms['form-add-edit-notification'].reset();
})


let roleElement = document.getElementById("role")
let schoolYearElement = document.getElementById("schoolYear")
let usersElement = document.getElementById("users")

// object change
const objectChange = (endpoint) => {
    if (roleElement.value === "") {
        schoolYearElement.disabled = true;
        usersElement.disabled = true;
        schoolYearElement.value = ""
    } else {
        if (roleElement.value === "4") {
            schoolYearElement.disabled = false;
        } else {
            schoolYearElement.disabled = true;
            schoolYearElement.value = ""
        }
        usersElement.disabled = false;
    }
    loadUsers(endpoint);
}

const schoolYearChange = (endpoint) => {
    loadUsers(endpoint);
}

const loadUsers = (endpoint) => {
    fetch(`${endpoint}?roleId=${roleElement.value}&schoolYearId=${schoolYearElement.value}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        let optionText = ""
        data.forEach(d => {
            console.log(d[0] + d[1])
            optionText += `
                <option value="${d[0]}">${d[1]}</option>
            `
        })
        usersElement.innerHTML = optionText;
    }).catch(err => {
        console.error(err)
    })
}

const addNotification = () => {
    // let selected = [];
    // for (let option of document.getElementById('users').options) {
    //     if (option.selected) {
    //         selected.push(option.value);
    //     }
    // }
    // let data = {
    //     "title": "",
    //     "content": "",
    //     "role": roleElement.value,
    //     "schoolYear": schoolYearElement.value,
    //     "users": selected
    // }
    // console.log(data)
}