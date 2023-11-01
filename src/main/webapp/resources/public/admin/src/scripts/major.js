const loadMajorById = (endpoint, callback) => {
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

const saveChange = (endpoint, majorId = null) => {
  let form = $("#form-add-edit-major");
  let formData = {};

  form.serializeArray().forEach((item) => (formData[item.name] = item.value));
  $("input").next("span").remove();

  showLoading();
  if (majorId === null) {
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
            $("#modal-add-edit-major").hide();
            successfulAlert("Thêm ngành thành công", "Ok", () =>
                location.reload()
            );
          } else {
            // error
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
    formData["id"] = majorId;
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
            $("#modal-add-edit-major").hide();
            successfulAlert("Cập nhật thông tin ngành thành công", "Ok", () =>
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


const showAddMajorModal = (endpoint) => {
  document.getElementById("myModalAddAndEditMajor").innerText = "Thêm ngành";
  $("#modal-add-edit-major").modal();
  document.getElementById("btn-submit-form").onclick = () =>
    saveChange(endpoint);
};

const showEditMajorModal = (endpoint, majorId) => {
  loadMajorById(endpoint, (data) => {
    document.getElementById("myModalAddAndEditMajor").innerText =
      "Cập nhật ngành";
    document.getElementById("btn-submit-form").onclick = () =>
      saveChange(endpoint, majorId);
    $("#modal-add-edit-major").modal();

    for (let d in data) {
      if (d === "department") {
        data[d] === null
          ? (document.forms["form-add-edit-major"][d].value = "")
          : (document.forms["form-add-edit-major"][d].value = data[d].id);
      } else {
        document.forms["form-add-edit-major"][d].value = data[d];
      }
    }
  });
};

const showViewMajorModal = (endpoint) => {
  loadMajorById(endpoint, (data) => {
    for (let d in data) {
      if (d === "department") {
        data[d] === null ? (document.getElementById(`data-department-name`).innerText =
              "Chưa cập nhật") : (document.getElementById(`data-department-name`).innerText =
              data[d].name);
      } else {
        data[d].toString() === "" ? (document.getElementById(`data-${d}`).innerText = "Chưa cập nhật")
          : (document.getElementById(`data-${d}`).innerText = data[d]);
      }
    }
    $("#modal-view-major").modal();
  });
};

const deleteMajorItem = (endpoint) => {
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
            successfulAlert("Xóa ngành thành công", "Ok", () =>
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
$("#modal-add-edit-major").on("hidden.bs.modal", function (e) {
  $("input").next("span").remove();
  document.forms["form-add-edit-major"].reset();
});
