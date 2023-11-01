const fileUploader = document.getElementById("file");
var file = null;

fileUploader.addEventListener("change", (event) => {
    file = event.target.files[0];

    let fileOutput = document.getElementById("file-output");

    fileOutput.src = URL.createObjectURL(file);
    fileOutput.onload = function () {
        URL.revokeObjectURL(fileOutput.src);
    };
});

const loadManageById = (endpoint, callback) => {
    fetch(endpoint, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => res.json())
        .then((data) => {
            callback(data);
        })
        .catch((err) => {
            errorAlert(
                "Đã có lỗi",
                "Đã có lỗi xảy ra trong quá trình tải dữ liệu!",
                "Ok"
            );
        });
};

const saveChange = (appContext, manageId = null) => {
    let form = $("#form-add-edit-manage");
    let formData = {};
    var formDataSubmit = new FormData();

    form.serializeArray().forEach((item) => {
        formData[item.name] = item.value;
    });
    formData["active"] = document.getElementById("is-active").checked;
    $("input").next("span").remove();

    formDataSubmit.append("avatarFile", file);

    showLoading();
    if (manageId === null) {
        // ADD
        formDataSubmit.append(
            "manage",
            new Blob(
                [
                    JSON.stringify({
                        fullName: formData.fullName,
                        email: formData.email,
                        phone: formData.phone,
                        user: {
                            username: formData.username,
                            password: formData.password,
                            active: formData.active,
                        },
                    }),
                ],
                {
                    type: "application/json",
                }
            )
        );
        fetch(`${appContext}admin/api/manages`, {
            method: "POST",
            body: formDataSubmit,
        })
            .then((res) => res.json())
            .then((data) => {
                if (Object.keys(data).length === 0) {
                    // successful
                    $("#modal-add-edit-manage").hide();
                    successfulAlert("Thêm quản trị viên thành công", "Ok", () =>
                        location.reload()
                    );
                } else {
                    // error
                    $.each(data, function (key, value) {
                        if (key === "file") {
                            console.log("tính sau");
                        } else {
                            $("input[name=" + key + "]").after(
                                '<span class="text-danger">' + value + "</span>"
                            );
                        }
                    });
                }
            })
            .catch((err) => {
                errorAlert(
                    "Đã có lỗi",
                    "Đã có lỗi xảy ra trong quá trình thêm dữ liệu!",
                    "Ok"
                );
            })
            .finally(hideLoading);
    } else {
        // UPDATE
        formDataSubmit.append(
            "manage",
            new Blob(
                [
                    JSON.stringify({
                        id: manageId,
                        fullName: formData.fullName,
                        email: formData.email,
                        phone: formData.phone,
                        user: {
                            username: formData.username,
                            password: " ",
                            active: formData.active,
                        },
                    }),
                ],
                {
                    type: "application/json",
                }
            )
        );
        fetch(`${appContext}admin/api/manages/${manageId}`, {
            method: "POST",
            body: formDataSubmit,
        })
            .then((res) => res.json())
            .then((data) => {
                if (Object.keys(data).length === 0) {
                    // successful
                    $("#modal-add-edit-manage").hide();
                    successfulAlert("Cập nhật quản trị viên thành công", "Ok", () =>
                        location.reload()
                    );
                } else {
                    // error
                    $.each(data, function (key, value) {
                        if (key === "file") {
                            console.log("tính sau");
                        } else {
                            $("input[name=" + key + "]").after(
                                '<span class="text-danger">' + value + "</span>"
                            );
                        }
                    });
                }
            })
            .catch((err) => {
                console.error(err);
                errorAlert(
                    "Đã có lỗi",
                    "Đã có lỗi xảy ra trong quá trình cập nhật!",
                    "Ok"
                );
            })
            .finally(hideLoading);
    }
};

const showAddManageModal = (appContext) => {
    document.getElementById("myModalAddAndEditManage").innerText =
        "Thêm quản trị viên";
    document.getElementById(
        "password-area"
    ).innerHTML = `<label class="font-weight-bold">Mật khẩu<span
                                    class="text-danger">(*)</span></label>
                            <input name="password" id="password" type="password" class="form-control"  autocomplete="off">`;

    $("#modal-add-edit-manage").modal();
    document.getElementById("btn-submit-form").onclick = () =>
        saveChange(appContext);
};

const showEditManageModal = (appContext, manageId) => {
    let endpoint = `${appContext}admin/api/manages/${manageId}`;

    loadManageById(endpoint, (data) => {
        document.getElementById("myModalAddAndEditManage").innerText =
            "Cập nhật quản trị viên";
        document.getElementById("password-area").innerHTML = "";

        document.getElementById("btn-submit-form").onclick = () =>
            saveChange(appContext, manageId);
        $("#modal-add-edit-manage").modal();

        let form = document.forms["form-add-edit-manage"];
        if (data.user.avatar && data.user.avatar !== "") {
            form["file-output"].src = data.user.avatar;
        }
        form["fullName"].value = data.fullName;
        form["email"].value = data.email;
        form["phone"].value = data.phone;
        if (data.user !== null) {
            document.getElementById("username").value = data.user.username;
            form["is-active"].checked = data.user.active;
        }
    });
};

const deleteManageItem = (appContext, manageId) => {
    // DELETE
    confirmAlert(
        "Bạn có chắc không?",
        "Bạn sẽ không thể khôi phục điều này!",
        "Có, xóa nó",
        "Không, hủy bỏ",
        () => {
            showLoading();

            fetch(`${appContext}admin/api/manages/${manageId}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                },
            })
                .then(function (res) {
                    if (res.status === 204)
                        successfulAlert("Xóa quản trị viên thành công", "Ok", () =>
                            location.reload()
                        );
                })
                .catch((err) => {
                    errorAlert(
                        "Đã có lỗi",
                        "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!",
                        "Ok"
                    );
                })
                .finally(hideLoading);
        }
    );
};

// event before hidden modal
$("#modal-add-edit-manage").on("hidden.bs.modal", function (e) {
    $("input").next("span").remove();
    document.forms["form-add-edit-manage"].reset();
});

// change password
const changePassword = (appContext, userId) => {
    Swal.fire({
        title: "Đổi mật khẩu",
        input: "password",
        inputAttributes: {
            autocapitalize: "off",
        },
        showCancelButton: true,
        confirmButtonText: "Đổi mật khẩu",
        confirmButtonColor: "#218838",
        reverseButtons: true,
        showLoaderOnConfirm: true,
        cancelButtonText: "Hủy",
        preConfirm: (password) => {
            if (!password) {
                Swal.showValidationMessage("Mật khẩu không được để trống");
            } else {
                if (password.toString().length > 50) {
                    Swal.showValidationMessage("Mật khẩu không vượt quá 50 ký tự");
                } else {
                    return fetch(`${appContext}admin/api/users/${userId}`, {
                        method: "POST",
                        body: password,
                        headers: {
                            "Content-Type": "application/json",
                        },
                    })
                        .then((response) => {
                            if (!response.ok) {
                                throw new Error(response.statusText);
                            }
                            return response.json();
                        })
                        .catch((error) => {
                            errorAlert("Đã có lỗi", "Đổi mật khẩu không thành công!", "Ok");
                        });
                }
            }
        },
        allowOutsideClick: () => !Swal.isLoading(),
    }).then((result) => {
        if (result.isConfirmed) {
            successfulAlert("Đổi mật khẩu thành công", "Ok", null);
        }
    });
};
