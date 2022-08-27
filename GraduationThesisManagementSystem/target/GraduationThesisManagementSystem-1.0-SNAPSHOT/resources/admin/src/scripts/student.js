const showAddStudentModal = (endpoint) => {
    document.getElementById("myModalAddAndEditStudent").innerText = "Thêm sinh viên"

    // remove new password area
    let newPasswordArea = document.getElementById("new-password-area")
    if (newPasswordArea.innerHTML !== '') {
        newPasswordArea.innerHTML = ""
    }

    document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint)
    $('#modal-add-edit-student').modal()

}

const showEditStudentModal = (endpoint, studentId) => {
    loadStudentById(endpoint, (data) => {
        document.getElementById("myModalAddAndEditStudent").innerText = "Cập nhật sinh viên"

        // add input new password
        let newPasswordArea = document.getElementById("new-password-area")
        if (newPasswordArea.innerHTML === '') {
            newPasswordArea.innerHTML = `
                            <div class="form-group">
                                <label class="font-weight-bold">Mật khẩu mới</label>
                                <input name="newPassword" id="newPassword" type="password" class="form-control">
                                <small class="form-text text-muted">Nhập vô ô nhập liệu nếu muốn thay đổi mật khẩu</small>
                            </div>
                            <input name="password" id="password" type="password" hidden>`
        }

        document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint, studentId)
        $('#modal-add-edit-student').modal()

        let form = document.forms['form-add-edit-student']
        form["code"].value = data.code;
        form["fullName"].value = data.fullName;
        form["email"].value = data.email
        form["phone"].value = data.phone
        form["birthday"].value = new Date(data.birthday).toISOString().slice(0, 10)
        form["gender"].value = data.gender
        form["address"].value = data.address
        form["gpa"].value = data.gpa
        if (data.major != null) {
            form["major"].value = data.major.id
        }
        if (data.schoolYear != null) {
            form["schoolYear"].value = data.schoolYear.id
        }
        if (data.user !== null) {
            form["password"].value = data.user.password
            form["active"].checked = data.user.active
        }
    })
}

const loadStudentById = (endpoint, callback) => {
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

const saveChange = (endpoint, studentId = null) => {
    let form = $("#form-add-edit-student")
    let formData = {}

    form.serializeArray().forEach(item => {
        formData[item.name] = item.value
    })
    formData["active"] = document.getElementById("active").checked;
    $('input').next('span').remove();

    if (studentId === null) {
        // ADD
        fetch(endpoint, {
            method: "POST", body: JSON.stringify({
                "code": formData.code,
                "fullName": formData.fullName,
                "email": formData.email,
                "phone": formData.phone,
                "birthday": formData.birthday,
                "gender": formData.gender,
                "address": formData.address,
                "gpa": formData.gpa,
                "schoolYear": formData.schoolYear,
                "major": formData.major,
                "user": {
                    "username": formData.code,
                    "password": formData.code,
                    "active": formData.active,
                }
            }), headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-student').hide();
                successfulAlert("Thêm sinh viên thành công", "Ok", () => location.reload());
            } else {
                // error
                $.each(data, function (key, value) {
                    if (key === "file") {
                        console.log("tính sau")
                    } else if (key === "code" || key === "fullName" || key === "phone" || key === "email" || key === "birthday" || key === "address") {
                        $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
                    }
                });
            }
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình thêm dữ liệu!", "Ok")
        })
    } else {
        // UPDATE
        fetch(endpoint, {
            method: "PATCH", body: JSON.stringify({
                "code": formData.code,
                "fullName": formData.fullName,
                "email": formData.email,
                "phone": formData.phone,
                "birthday": formData.birthday,
                "gender": formData.gender,
                "address": formData.address,
                "gpa": formData.gpa,
                "schoolYear": formData.schoolYear,
                "major": formData.major,
                "user": {
                    "username": formData.code,
                    "password": formData.password,
                    "newPassword": formData.newPassword,
                    "active": formData.active,
                }
            }), headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-student').hide();
                successfulAlert("Cập nhật sinh viên thành công", "Ok", () => location.reload());
            } else {
                // error
                $.each(data, function (key, value) {
                    if (key === "file") {
                        console.log("tính sau")
                    } else if (key === "code" || key === "fullName" || key === "phone" || key === "email" || key === "birthday" || key === "address") {
                        $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
                    }
                });
            }
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình cập nhật!", "Ok")
        })
    }
}

const deleteStudentItem = (endpoint) => {
    // DELETE
    confirmAlert("Bạn có chắc không?", "Bạn sẽ không thể khôi phục điều này!", "Có, xóa nó", "Không, hủy bỏ", () => {
        fetch(endpoint, {
            method: "DELETE", headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (res) {
            if (res.status === 204) successfulAlert("Xóa sinh viên thành công", "Ok", () => location.reload());
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!", "Ok")
        })
    })
}

// event before hidden modal
$('#modal-add-edit-student').on('hidden.bs.modal', function (e) {
    $('input').next('span').remove();
    document.forms['form-add-edit-student'].reset();
})
