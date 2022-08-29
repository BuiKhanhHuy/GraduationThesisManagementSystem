const loadDepartmentById = (endpoint, callback) => {
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

const saveChange = (endpoint, departmentId = null) => {
  let form = $("#form-add-edit-department");
  let formData = {};

  form.serializeArray().forEach((item) => (formData[item.name] = item.value));
  $("input").next("span").remove();

  showLoading();
  if (departmentId === null) {
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
        console.log(data);
        if (Object.keys(data).length === 0) {
          // successful
          $("#modal-add-edit-department").hide();
          successfulAlert("Thêm khoa thành công", "Ok", () =>
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
    formData["id"] = departmentId;
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
          $("#modal-add-edit-department").hide();
          successfulAlert("Cập nhật thông tin khoa thành công", "Ok", () =>
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

const showAddDepartmentModal = (endpoint) => {
  document.getElementById("myModalAddAndEditDepartment").innerText =
    "Thêm khoa";
  $("#modal-add-edit-department").modal();
  document.getElementById("btn-submit-form").onclick = () =>
    saveChange(endpoint);
};

const showEditDepartmentModal = (endpoint, departmentId) => {
  loadDepartmentById(endpoint, (data) => {
    document.getElementById("myModalAddAndEditDepartment").innerText =
      "Cập nhật khoa";
    document.getElementById("btn-submit-form").onclick = () =>
      saveChange(endpoint, departmentId);
    $("#modal-add-edit-department").modal();

    for (let d in data) {
      document.forms["form-add-edit-department"][d].value = data[d];
      if (d === "founding") {
        document.forms["form-add-edit-department"][d].value = new Date(data[d])
          .toISOString()
          .slice(0, 10);
      } else {
        document.forms["form-add-edit-department"][d].value = data[d];
      }
    }
  });
};

const showViewDepartmentModal = (endpoint) => {
  loadDepartmentById(endpoint, (data) => {
    for (let d in data) {
      if (d === "founding") {
        document.getElementById(`data-${d}`).innerText = new Date(data[d])
          .toISOString()
          .slice(0, 10);
      } else {
        data[d].toString() === "" ? (document.getElementById(`data-${d}`).innerText = "Chưa cập nhật")
          : (document.getElementById(`data-${d}`).innerText = data[d]);
      }
    }
    $("#modal-view-department").modal();
  });
};

const deleteDepartmentItem = (endpoint) => {
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
            successfulAlert("Xóa khoa thành công", "Ok", () =>
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
$("#modal-add-edit-department").on("hidden.bs.modal", function (e) {
  $("input").next("span").remove();
  document.forms["form-add-edit-department"].reset();
});
