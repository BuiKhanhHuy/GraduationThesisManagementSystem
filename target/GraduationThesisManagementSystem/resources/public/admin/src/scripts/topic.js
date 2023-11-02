const loadTopicById = (endpoint, callback) => {
    fetch(endpoint, {
        method: "GET", headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => res.json())
        .then((data) => {
            callback(data);
        })
        .catch((err) => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok");
        });
};

const saveChange = (endpoint, topicId = null) => {
    let form = $("#form-add-edit-topic");
    let formData = {};

    form.serializeArray().forEach((item) => (formData[item.name] = item.value));
    $("input").next("span").remove();

    showLoading();
    if (topicId === null) {
        // ADD
        fetch(endpoint, {
            method: "POST", body: JSON.stringify(formData), headers: {
                "Content-Type": "application/json",
            },
        })
            .then((res) => res.json())
            .then((data) => {
                if (Object.keys(data).length === 0) {
                    // successful
                    $("#modal-add-edit-topic").hide();
                    successfulAlert("Thêm đề tài thành công", "Ok", () => location.reload());
                } else {
                    // error
                    $.each(data, function (key, value) {
                        $("input[name=" + key + "]").after('<span class="text-danger">' + value + "</span>");
                    });
                }
            })
            .catch((err) => {
                errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình thêm dữ liệu!", "Ok");
            })
            .finally(hideLoading);
    } else {
        // UPDATE
        formData["id"] = topicId;
        fetch(endpoint, {
            method: "PATCH", body: JSON.stringify(formData), headers: {
                "Content-Type": "application/json",
            },
        })
            .then((res) => res.json())
            .then((data) => {
                if (Object.keys(data).length === 0) {
                    // successful
                    $("#modal-add-edit-topic").hide();
                    successfulAlert("Cập nhật thông tin đề tài thành công", "Ok", () => location.reload());
                } else {
                    // error
                    $.each(data, function (key, value) {
                        $("input[name=" + key + "]").after('<span class="text-danger">' + value + "</span>");
                    });
                }
            })
            .catch((err) => {
                errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình cập nhật!", "Ok");
            })
            .finally(hideLoading);
    }
};

const showAddTopicModal = (endpoint) => {
    document.getElementById("myModalAddAndEditTopic").innerText = "Thêm đề tài";
    $("#modal-add-edit-topic").modal();
    document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint);
};

const showEditTopicModal = (endpoint, topicId) => {
    loadTopicById(endpoint, (data) => {
        document.getElementById("myModalAddAndEditTopic").innerText = "Cập nhật đề tài";
        document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint, topicId);
        $("#modal-add-edit-topic").modal();

        for (let d in data) {
            if (d === "department") {
                data[d] === null ? (document.forms["form-add-edit-topic"][d].value = "") : (document.forms["form-add-edit-topic"][d].value = data[d].id);
            } else {
                document.forms["form-add-edit-topic"][d].value = data[d];
            }
        }
    });
};

const deleteTopicItem = (endpoint) => {
    // DELETE
    confirmAlert("Bạn có chắc không?", "Bạn sẽ không thể khôi phục điều này!", "Có, xóa nó", "Không, hủy bỏ", () => {
        showLoading();

        fetch(endpoint, {
            method: "DELETE", headers: {
                "Content-Type": "application/json",
            },
        })
            .then(function (res) {
                if (res.status === 204) successfulAlert("Xóa đề tài thành công", "Ok", () => location.reload());
            })
            .catch((err) => {
                errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!", "Ok");
            })
            .finally(hideLoading);
    });
};

// event before hidden modal
$("#modal-add-edit-topic").on("hidden.bs.modal", function (e) {
    $("input").next("span").remove();
    document.forms["form-add-edit-topic"].reset();
});
