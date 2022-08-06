const showAddManageModal = (endpoint) => {
    document.getElementById("myModalAddAndEditManage").innerText = "Thêm quản trị viên"
    $('#modal-add-edit-manage').modal()
    document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint)

}

const showEditManageModal = (endpoint, manageId) => {
    loadManageById(endpoint, (data) => {
        document.getElementById("myModalAddAndEditManage").innerText = "Cập nhật quản trị viên"
        document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint, manageId)
        $('#modal-add-edit-manage').modal()

        let form = document.forms['form-add-edit-manage']
        form["fullName"].value = data.fullName;
        form["email"].value = data.email
        form["phone"].value = data.phone
        if (data.user !== null) {
            form["username"] = data.user.username
            form["password"] = data.user.password
            form["active"].checked = data.user.active
        }
    })
}
const loadManageById = (endpoint, callback) => {
    fetch(endpoint, {
        method: 'GET', headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        callback(data);
    })
        .catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok")
        })
}

const saveChange = (endpoint, manageId = null) => {
    let form = $("#form-add-edit-manage")
    let formData = {}

    form.serializeArray().forEach(item => {
        formData[item.name] = item.value
    })
    formData["active"] = document.getElementById("active").checked;

    $('input').next('span').remove();

    if (manageId === null) {
        // ADD
        fetch(endpoint, {
            method: "POST", body: JSON.stringify({
                "fullName": formData.fullName,
                "email": formData.email,
                "phone": formData.phone,
                "user": {
                    "username": formData.username,
                    "password": formData.password,
                    "active": formData.active,
                }
            }), headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-manage').hide();
                successfulAlert("Thêm quản trị viên thành công", "Ok", () => location.reload());
            } else {
                // error
                $.each(data, function (key, value) {
                    if (key === "file")
                        console.log("tính sau")
                    else
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
                "fullName": formData.fullName,
                "email": formData.email,
                "phone": formData.phone,
                "user": {
                    "username": formData.username,
                    "password": formData.password,
                    "active": formData.active,
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
                    if (key === "file")
                        console.log("tính sau")
                    else
                        $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
                });
            }
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình cập nhật!", "Ok")
        })
    }
}

const deleteManageItem = (endpoint) => {
    // DELETE
    confirmAlert("Bạn có chắc không?", "Bạn sẽ không thể khôi phục điều này!", "Có, xóa nó", "Không, hủy bỏ", () => {
        fetch(endpoint, {
            method: "DELETE", headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (res) {
            if (res.status === 204) successfulAlert("Xóa quản trị viên thành công", "Ok", () => location.reload());
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!", "Ok")
        })
    })
}

// event before hidden modal
$('#modal-add-edit-manage').on('hidden.bs.modal', function (e) {
    $('input').next('span').remove();
    document.forms['form-add-edit-manage'].reset();
})
