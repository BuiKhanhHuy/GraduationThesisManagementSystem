const myModalEl = document.getElementById("change-password-modal");
const modal = new mdb.Modal(myModalEl);

const changePasswordByUser = (appContext, userId) => {
  document.getElementById("btn-submit-form-change-password").onclick = () =>
    saveChangePassword(appContext, userId);

  modal.show();
};

const saveChangePassword = (appContext, userId) => {
  let form = $("#form-change-password");
  let formData = {};
  form.serializeArray().forEach((item) => (formData[item.name] = item.value));

  $("input").next("span").remove();

  showLoading();
  fetch(`${appContext}api/users/password`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      userId: userId,
      oldPassword: formData.oldPassword,
      newPassword: formData.newPassword,
    }),
  })
    .then((res) => res.json())
    .then((data) => {
      console.log(data);
      if (Object.keys(data).length === 0) {
        // successful
        modal.hide();
        successfulAlert(
          "Thay đổi mật khẩu thành công. \nBạn cần đăng nhập lại!",
          "Ok",
          () => document.getElementById("logoutForm").submit()
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
        "Đã có lỗi xảy ra trong quá trình cập nhật",
        "Ok"
      );
    })
    .finally(hideLoading);
};

// event before hidden modal
$("#change-password-modal").on("hidden.bs.modal", function (e) {
  $("input").next("span").remove();
  document.forms["form-change-password"].reset();
});

// upload report file
const uploadReportFile = async (appContext, thesisId) => {
  Swal.fire({
    title: "Nộp khóa luận",
    html: `<form name="form-upload-file">
                    <div class="text-left pb-2 pt-4" style="text-align: left;">
                        <label for="fileName" class="form-label mb-2">Nhập tên file <span class="text-danger">(*)</span></label>
                        <input  class="form-control form-control-lg" name="fileName" id="fileName" type="text" />
                    </div>
                    <div class="text-left pb-2 pt-2" style="text-align: left;">
                        <label for="reportFile" class="form-label mb-2">Chọn tập tin khóa luận (tập tin có đuôi <strong>.zip</strong>) <span class="text-danger">(*)</span></label>
                        <input accept=".zip, .rar" class="form-control form-control-lg" name="reportFile" id="reportFile" type="file" />
                    </div>
               </form>`,
    showCancelButton: true,
    confirmButtonText: '<i class="fa-solid fa-cloud-arrow-up"></i> Tải tệp lên',
    confirmButtonColor: "#218838",
    reverseButtons: true,
    showLoaderOnConfirm: true,
    cancelButtonText: "Hủy",
    preConfirm: () => {
      var reportFile =
        document.forms["form-upload-file"]["reportFile"].files[0];
      var fileName = document.forms["form-upload-file"]["fileName"].value;

      if (fileName === "") {
        Swal.showValidationMessage("Tên tập tin không được để trống!");
      } else {
        if (reportFile) {
          var formData = new FormData();
          formData.append("reportFile", reportFile);
          formData.append(
            "fileName",
            new Blob([JSON.stringify(fileName.toString())], {
              type: "application/json",
            })
          );

          return fetch(`${appContext}api/theses/${thesisId}/report-file`, {
            method: "POST",
            body: formData,
          }).then((response) => {
            if (!response.ok) {
              errorAlert(
                "Đã có lỗi",
                "Upload tập tin khóa luận không thành công!",
                "Ok"
              );
            } else {
              successfulAlert(
                "Upload tập tin khóa luận thành công.",
                "Ok",
                () => location.reload()
              );
            }
            return response.json();
          });
        } else {
          Swal.showValidationMessage("Tập tin không được để trống!");
        }
      }
    },
    allowOutsideClick: () => !Swal.isLoading(),
  });
};
