// overlay loading
const showLoading = () => {
  $("#cover-spin").show(0);
};

const hideLoading = () => {
  $("#cover-spin").hide(0);
};

// sweet alert 2
const successfulAlert = (title, confirmButtonText, callback) => {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success",
    },
    buttonsStyling: false,
  });

  swalWithBootstrapButtons
    .fire({
      position: "center",
      icon: "success",
      title: title,
      showConfirmButton: true,
      confirmButtonText: confirmButtonText,
    })
    .then(callback);
};

const errorAlert = (title, text, confirmButtonText) => {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-danger",
    },
    buttonsStyling: false,
  });
  swalWithBootstrapButtons.fire({
    icon: "error",
    title: title,
    text: text,
    showConfirmButton: true,
    confirmButtonText: confirmButtonText,
  });
};

const confirmAlert = (
  title,
  text,
  confirmButtonText,
  cancelButtonText,
  callback
) => {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success ml-1",
      cancelButton: "btn btn-danger  mr-1",
    },
    buttonsStyling: false,
  });

  swalWithBootstrapButtons
    .fire({
      title: title,
      text: text,
      icon: "warning",
      showCancelButton: true,
      confirmButtonText: confirmButtonText,
      cancelButtonText: cancelButtonText,
      reverseButtons: true,
    })
    .then(function (result) {
      if (result.isConfirmed) callback();
    });
};

const viewAlert = (title, cancelButtonText, htmlBody) => {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      cancelButton: "btn btn-success",
    },
    buttonsStyling: false,
  });

  swalWithBootstrapButtons.fire({
    title: title,
    html: htmlBody,
    focusConfirm: false,
    showConfirmButton: false,
    showCloseButton: true,
    showCancelButton: true,
    cancelButtonText: cancelButtonText,
    reverseButtons: true,
    width: "800px",
  });
};

const turnOffNotification = (appContext, notificationUserId) => {
  fetch(`${appContext}admin/api/notifications-user/${notificationUserId}`, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => {
      if (res.ok) {
        let notificationUserArea = document.getElementById(
          "notification-user-area"
        );

        notificationUserArea.removeChild(
          document.getElementById(`notification-user-${notificationUserId}`)
        );

        if (document.getElementsByClassName("notification-user").length === 0) {
          notificationUserArea.innerHTML = `<li class="text-center text-secondary">Không có thông báo.</li>`;
          document.getElementById("badge-notifi-active").style.display = "none";
        }
      }
    })
    .catch((err) => {
      console.error(err);
    });
};

const changeLang = (appContext, lang = "vi") => {
  fetch(`${appContext}api/lang/?lang=${lang}`, {
    method: "GET",
  })
    .then((res) => {
      if (res.ok) {
        console.log(res.ok);
        location.reload();
      } else {
        Promise.reject("Error");
      }
    })
    .catch((err) => {
      console.error(err);
    });
};

document.addEventListener("DOMContentLoaded", () => {
  let notificationCreatedDate = document.getElementsByClassName(
    "notification-created-date"
  );
  for (let i = 0; i < notificationCreatedDate.length; i++) {
    notificationCreatedDate[i].innerText = moment(
      `${notificationCreatedDate[i].innerText}`,
      "YYYYMMDD HHmmSS"
    ).fromNow();
  }
});
