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


const loadLecturerById = (endpoint, callback) => {
    fetch(endpoint, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
            callback(data);
        })
        .catch((err) => {
            console.error(err);
            errorAlert(
                "Đã có lỗi",
                "Đã có lỗi xảy ra trong quá trình tải dữ liệu!",
                "Ok"
            );
        });
};

const saveChange = (appContext, lecturerId = null) => {
    let form = $("#form-add-edit-lecturer");
    let formData = {};
    let formDataSubmit = new FormData();
    formDataSubmit.append("avatarFile", file);

    form.serializeArray().forEach((item) => {
        formData[item.name] = item.value;
    });
    formData["active"] = document.getElementById("is-active").checked;
    $("input").next("span").remove();

    showLoading();
    if (lecturerId === null) {
        // ADD
        formDataSubmit.append(
            "lecturer",
            new Blob(
                [
                    JSON.stringify({
                        code: formData.code,
                        fullName: formData.fullName,
                        email: formData.email,
                        phone: formData.phone,
                        birthday: formData.birthday,
                        gender: formData.gender,
                        address: formData.address,
                        department: formData.department,
                        position: formData.position,
                        user: {
                            username: formData.code,
                            password: formData.code,
                            active: formData.active,
                            role: formData.role,
                        },
                    }),
                ],
                {
                    type: "application/json",
                }
            )
        );
        fetch(`${appContext}admin/api/lecturers`, {
            method: "POST",
            body: formDataSubmit,
        })
            .then((res) => res.json())
            .then((data) => {
                if (Object.keys(data).length === 0) {
                    // successful
                    $("#modal-add-edit-lecturer").hide();
                    successfulAlert("Thêm giảng viên thành công", "Ok", () =>
                        location.reload()
                    );
                } else {
                    // error
                    $.each(data, function (key, value) {
                        if (key === "file") {
                            console.log("tính sau");
                        } else if (
                            key === "code" ||
                            key === "fullName" ||
                            key === "phone" ||
                            key === "email" ||
                            key === "birthday" ||
                            key === "address"
                        ) {
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
                    "Đã có lỗi xảy ra trong quá trình thêm dữ liệu!",
                    "Ok"
                );
            })
            .finally(hideLoading);
    } else {
        // UPDATE
        formDataSubmit.append(
            "lecturer",
            new Blob(
                [
                    JSON.stringify({
                        id: lecturerId,
                        code: formData.code,
                        fullName: formData.fullName,
                        email: formData.email,
                        phone: formData.phone,
                        birthday: formData.birthday,
                        gender: formData.gender,
                        address: formData.address,
                        department: formData.department,
                        position: formData.position,
                        user: {
                            username: formData.code,
                            password: " ",
                            active: formData.active,
                            role: formData.role,
                        },
                    }),
                ],
                {
                    type: "application/json",
                }
            )
        );
        fetch(`${appContext}admin/api/lecturers/${lecturerId}`, {
            method: "POST",
            body: formDataSubmit,
        })
            .then((res) => res.json())
            .then((data) => {
                if (Object.keys(data).length === 0) {
                    // successful
                    $("#modal-add-edit-lecturer").hide();
                    successfulAlert("Cập nhật giảng viên thành công", "Ok", () =>
                        location.reload()
                    );
                } else {
                    // error
                    $.each(data, function (key, value) {
                        if (key === "file") {
                            console.log("tính sau");
                        } else if (
                            key === "code" ||
                            key === "fullName" ||
                            key === "phone" ||
                            key === "email" ||
                            key === "birthday" ||
                            key === "address"
                        ) {
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

const showAddLecturerModal = (appContext) => {
    document.getElementById("myModalAddAndEditLecturer").innerText =
        "Thêm giảng viên";

    document.getElementById("btn-submit-form").onclick = () =>
        saveChange(appContext);
    $("#modal-add-edit-lecturer").modal();
};

const showEditLectureModal = (appContext, lecturerId) => {
    let endpoint = `${appContext}admin/api/lecturers/${lecturerId}`;

    loadLecturerById(endpoint, (data) => {
        document.getElementById("myModalAddAndEditLecturer").innerText =
            "Cập nhật giảng viên";

        document.getElementById("btn-submit-form").onclick = () =>
            saveChange(appContext, lecturerId);
        $("#modal-add-edit-lecturer").modal();

        let form = document.forms["form-add-edit-lecturer"];
        if (data.user.avatar && data.user.avatar !== "") {
            form["file-output"].src = data.user.avatar;
        } else {
            form[
                "file-output"
                ].src = `${window.location.origin}${appContext}public/common/images/avatars/avatar-default.jpg`;
        }
        form["file-output"].onload = function () {
            URL.revokeObjectURL(form["file-output"].src);
        };

        form["code"].value = data.code;
        form["fullName"].value = data.fullName;
        form["email"].value = data.email;
        form["phone"].value = data.phone;
        form["birthday"].value = new Date(data.birthday).toISOString().slice(0, 10);
        form["gender"].value = data.gender;
        form["address"].value = data.address;
        if (data.position != null) {
            form["position"].value = data.position.id;
        }
        if (data.department != null) {
            form["department"].value = data.department.id;
        }
        if (data.user !== null) {
            form["is-active"].checked = data.user.active;
            if (data.user.role != null) form["role"].value = data.user.role.id;
        }
    });
};

const deleteLecturerItem = (appContext, lecturerId) => {
    // DELETE
    confirmAlert(
        "Bạn có chắc không?",
        "Bạn sẽ không thể khôi phục điều này!",
        "Có, xóa nó",
        "Không, hủy bỏ",
        () => {
            showLoading();

            fetch(`${appContext}admin/api/lecturers/${lecturerId}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                },
            })
                .then(function (res) {
                    if (res.status === 204)
                        successfulAlert("Xóa giảng viên thành công", "Ok", () =>
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
$("#modal-add-edit-lecturer").on("hidden.bs.modal", function (e) {
    $("input").next("span").remove();
    document.forms["form-add-edit-lecturer"].reset();
});

$("#modal-add-import-lecturer").on("hidden.bs.modal", function (e) {
    $("input").next("span").remove();
    document.forms["form-upload-file"].reset();
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

// add students from file
const importLecturersFile = async (appContext) => {
    document.getElementById("btn-import").onclick = () => {
        $("input").next("span").remove();
        var file = document.forms["form-upload-file"]["file"].files[0];

        showLoading();
        if (file) {
            var formData = new FormData();
            formData.append("file", file);

            return fetch(`${appContext}admin/api/lecturers/file`, {
                method: "POST",
                body: formData,
            })
                .then((response) => {
                    $("#modal-add-import-lecturer").hide();
                    if (!response.ok) {
                        errorAlert(
                            "Đã có lỗi",
                            "Import danh sách giảng viên không thành công. \nKiểm tra lại dữ liệu và thực hiện lại.",
                            "Ok"
                        );
                    } else {
                        successfulAlert(
                            "Import danh sách giảng viên thành công.",
                            "Ok",
                            () => location.reload()
                        );
                    }
                })
                .finally(hideLoading);
        } else {
            $("input[name=file]").after(
                '<span class="text-danger">Không được để trống</span>'
            );

            hideLoading();
        }
    };

    $("#modal-add-import-lecturer").modal();
};
