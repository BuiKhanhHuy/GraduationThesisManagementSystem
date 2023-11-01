const saveChangePassword = (appContext, userId) => {
  let form = $("#form-change-password");
  let formData = {};
  form.serializeArray().forEach((item) => (formData[item.name] = item.value));

  $("input").next("span").remove();

  showLoading();
  fetch(`${appContext}admin/api/users/password`, {
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
          $("#change-password-modal").hide();
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

const changePasswordByUser = (appContext, userId) => {
  document.getElementById("btn-submit-form-change-password").onclick = () =>
    saveChangePassword(appContext, userId);

  $("#change-password-modal").modal();
};

// event before hidden modal
$("#change-password-modal").on("hidden.bs.modal", function (e) {
  $("input").next("span").remove();
  document.forms["form-change-password"].reset();
});
