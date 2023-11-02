const loadPositionById = (endpoint, callback) => {
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

const saveChange = (endpoint, positionId = null) => {
  let form = $("#form-add-edit-position");
  let formData = {};

  form.serializeArray().forEach((item) => (formData[item.name] = item.value));
  $("input").next("span").remove();

  showLoading();
  if (positionId === null) {
    // ADD
    fetch(endpoint, {
      method: "POST",
      body: JSON.stringify(formData),
      headers: {
        "Content-Type": "application/json",
      },
    })
        .then((res) => res.json())
        .then((data) => {
          if (Object.keys(data).length === 0) {
            // successful
            $("#modal-add-edit-position").hide();
            successfulAlert("Thêm chức vụ thành công", "Ok", () =>
                location.reload()
            );
          } else {
            // errror
            $.each(data, function (key, value) {
              $("input[name=" + key + "]").after(
                  '<span class="text-danger">' + value + "</span>"
              );
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
    formData["id"] = positionId;
    fetch(endpoint, {
      method: "PATCH",
      body: JSON.stringify(formData),
      headers: {
        "Content-Type": "application/json",
      },
    })
        .then((res) => res.json())
        .then((data) => {
          if (Object.keys(data).length === 0) {
            // successful
            $("#modal-add-edit-position").hide();
            successfulAlert("Cập nhật thông tin chức vụ thành công", "Ok", () =>
                location.reload()
            );
          } else {
            // errror
            $.each(data, function (key, value) {
              $("input[name=" + key + "]").after(
                  '<span class="text-danger">' + value + "</span>"
              );
            });
          }
        })
        .catch((err) => {
          errorAlert(
              "Đã có lỗi",
              "Đã có lỗi xảy ra trong quá trình cập nhật!",
              "Ok"
          );
        })
        .finally(hideLoading);
  }
};

const showAddPosition = (endpoint) => {
  document.getElementById("myModalAddAndEditPosition").innerText =
    "Thêm chức vụ";
  $("#modal-add-edit-position").modal();
  document.getElementById("btn-submit-form").onclick = () =>
    saveChange(endpoint);
};

const showEditPosition = (endpoint, positionId) => {
  loadPositionById(endpoint, (data) => {
    document.getElementById("myModalAddAndEditPosition").innerText =
      "Cập nhật chức vụ";
    document.getElementById("btn-submit-form").onclick = () =>
      saveChange(endpoint, positionId);
    $("#modal-add-edit-position").modal();

    for (let d in data) {
      document.forms["form-add-edit-position"][d].value = data[d];
    }
  });
};

const deletePositionItem = (endpoint) => {
  // DELETE
  confirmAlert(
    "Bạn có chắc không?",
    "Bạn sẽ không thể khôi phục điều này!",
    "Có, xóa nó",
    "Không, hủy bỏ",
    () => {
      showLoading();

      fetch(endpoint, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then(function (res) {
          if (res.status === 204)
            successfulAlert("Xóa chức vụ thành công", "Ok", () =>
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
$("#modal-add-edit-position").on("hidden.bs.modal", function (e) {
  $("input").next("span").remove();
  document.forms["form-add-edit-position"].reset();
});
