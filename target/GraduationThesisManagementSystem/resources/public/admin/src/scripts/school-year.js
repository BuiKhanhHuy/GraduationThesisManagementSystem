
const loadSchoolYearById = (endpoint, callback) => {
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

const saveChange = (endpoint, schoolYearId = null) => {
  let form = $("#form-add-edit-school-year");
  let formData = {};

  form.serializeArray().forEach((item) => (formData[item.name] = item.value));
  $("input").next("span").remove();

  showLoading();
  if (schoolYearId === null) {
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
            $("#modal-add-edit-school-year").hide();
            successfulAlert("Thêm niên khóa thành công", "Ok", () =>
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
    formData["id"] = schoolYearId;
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
            $("#modal-add-edit-school-year").hide();
            successfulAlert("Cập nhật thông tin niên khóa thành công", "Ok", () =>
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


const showAddSchoolYear = (endpoint) => {
  document.getElementById("myModalAddAndEditSchoolYear").innerText =
    "Thêm niên khóa";
  $("#modal-add-edit-school-year").modal();
  document.getElementById("btn-submit-form").onclick = () =>
    saveChange(endpoint);
};

const showEditSchoolYear = (endpoint, schoolYearId) => {
  loadSchoolYearById(endpoint, (data) => {
    console.log(data);
    document.getElementById("myModalAddAndEditSchoolYear").innerText =
      "Cập nhật niên khóa";
    document.getElementById("btn-submit-form").onclick = () =>
      saveChange(endpoint, schoolYearId);
    $("#modal-add-edit-school-year").modal();

    for (let d in data) {
      document.forms["form-add-edit-school-year"][d].value = data[d];
      if (d === "startDate" || d === "endDate") {
        document.forms["form-add-edit-school-year"][d].value = new Date(data[d])
          .toISOString()
          .slice(0, 10);
      } else {
        document.forms["form-add-edit-school-year"][d].value = data[d];
      }
    }
  });
};

const deleteSchoolYearItem = (endpoint) => {
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
            successfulAlert("Xóa niên khóa thành công", "Ok", () =>
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
$("#modal-add-edit-school-year").on("hidden.bs.modal", function (e) {
  $("input").next("span").remove();
  document.forms["form-add-edit-school-year"].reset();
});
