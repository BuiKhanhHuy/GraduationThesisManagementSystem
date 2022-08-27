var isLoadDataOptions = true;

const loadDataOptions = (callback) => {
    if (isLoadDataOptions) {
        fetch("/GraduationThesisManagementSystem/admin/api/student-options", {
            method: "GET", headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            let performStudentsHtml = ''
            data.forEach(d => performStudentsHtml += `<option value=${d[0]}>${d[1]}</option>`)
            document.getElementById("performStudentsId").innerHTML = performStudentsHtml;
        }).then(() => fetch("/GraduationThesisManagementSystem/admin/api/lecturer-options", {
            method: "GET", headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            let lecturersHtml = ''
            data.forEach(d => lecturersHtml += `<option value=${d[0]}>${d[1]}</option>`)
            document.getElementById("instructorsId").innerHTML = lecturersHtml;
            document.getElementById("reviewLecturer").innerHTML = lecturersHtml;

            // callback
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

const showAddThesisModal = (endpoint) => {
    loadDataOptions(() => {
        document.getElementById("myModalAddAndEditThesis").innerText = "Thêm khóa luận"
        $('#modal-add-edit-thesis').modal()
        document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint)
    })
}

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

const saveChange = (endpoint, thesisId = null) => {
    let form = $("#form-add-edit-thesis")
    let formData = {}

    form.serializeArray().forEach(item => {
        formData[item.name] = item.value
    })
    formData["instructorsId"] = [...document.getElementById("instructorsId").options]
        .filter(option => option.selected)
        .map(option => parseInt(option.value));
    formData["performStudentsId"] = [...document.getElementById("performStudentsId").options]
        .filter(option => option.selected)
        .map(option => parseInt(option.value));

    $('input').next('span').remove();
    $('select + span').next('span').remove()
    console.warn({
        "code": formData.code,
        "startDate": formData.startDate,
        "complateDate": formData.complateDate,
        "thesisStartDate": formData.thesisStartDate,
        "thesisEndDate": formData.thesisEndDate,
        "department": formData.department,
        "schoolYear": formData.schoolYear,
        "topic": formData.topic,
        "instructorsId": formData.instructorsId,
        "reviewLecturer": formData.reviewLecturer,
        "performStudentsId": formData.performStudentsId
    })
    if (thesisId === null) {
        // ADD
        fetch(endpoint, {
            method: "POST", body: JSON.stringify({
                "code": formData.code,
                "startDate": formData.startDate,
                "complateDate": formData.complateDate,
                "thesisStartDate": formData.thesisStartDate,
                "thesisEndDate": formData.thesisEndDate,
                "department": formData.department,
                "schoolYear": formData.schoolYear,
                "topic": formData.topic,
                "instructorsId": formData.instructorsId,
                "reviewLecturer": formData.reviewLecturer,
                "performStudentsId": formData.performStudentsId
            }), headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-thesis').hide();
                successfulAlert("Thêm khóa luận thành công", "Ok", () => location.reload());
            } else {
                // error
                console.warn(data)
                $.each(data, function (key, value) {
                    if (['code', 'startDate', 'complateDate', 'thesisStartDate', 'thesisEndDate'].indexOf(key) > -1) {
                        $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
                    } else {
                        $('select[name=' + key + '] + span').after('<span class="text-danger">' + value + '</span>');
                    }
                });
            }
        }).catch(err => {
            console.error(err)
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình thêm dữ liệu!", "Ok")
        })
    } else {
        // // UPDATE
        // fetch(endpoint, {
        //     method: "PATCH", body: JSON.stringify({
        //         "fullName": formData.fullName,
        //         "email": formData.email,
        //         "phone": formData.phone,
        //         "user": {
        //             "username": formData.username,
        //             "password": formData.password,
        //             "active": formData.active,
        //         }
        //     }), headers: {
        //         "Content-Type": "application/json"
        //     }
        // }).then(res => res.json()).then(data => {
        //     if (Object.keys(data).length === 0) {
        //         // successful
        //         $('#modal-add-edit-manage').hide();
        //         successfulAlert("Cập nhật quản trị viên thành công", "Ok", () => location.reload());
        //     } else {
        //         // error
        //         $.each(data, function (key, value) {
        //             if (key === "file")
        //                 console.log("tính sau")
        //             else
        //                 $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
        //         });
        //     }
        // }).catch(err => {
        //     errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình cập nhật!", "Ok")
        // })
    }
}

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
//
// event before hidden modal
$('#modal-add-edit-thesis').on('hidden.bs.modal', function (e) {
    $('input').next('span').remove();
    $('select + span').next('span').remove()
    document.forms['form-add-edit-thesis'].reset();
})
