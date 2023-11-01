let titleElement = document.getElementById("title");
let contentElement = document.getElementById("content");
let roleElement = document.getElementById("role");
let schoolYearElement = document.getElementById("schoolYear");
let usersElement = document.getElementById("users");

// show error in form validation
const showError = (data) => {
  $.each(data, function (key, value) {
    if (key === "title")
      $("input[name=" + key + "]").after(
          '<span class="text-danger">' + value + "</span>"
      );
    if (key === "content") {
      $("textarea[name=" + key + "]").after(
          '<span class="text-danger">' + value + "</span>"
      );
    }
  });
};


// load user filter
const loadUsers = (endpoint) => {
  fetch(
      `${endpoint}?roleId=${roleElement.value}&schoolYearId=${schoolYearElement.value}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
  )
      .then((res) => res.json())
      .then((data) => {
        let optionText = "";
        data.forEach((d) => {
          optionText += `
                <option value="${d[0]}">${d[1]}</option>
            `;
        });
        usersElement.innerHTML = optionText;
      })
      .catch((err) => {
        console.error(err);
      });
};

// const showViewNewsModal = (endpoint) => {
//     loadNewsById(endpoint, (data) => {
//         for (let d in data) {
//             if (d === "user") {
//                 if(data[d] === null)
//                 {
//                     document.getElementById(`data-user-username`).innerText = 'Chưa cập nhật'
//                     document.getElementById(`data-avatar`).src = "#"
//                 }else {
//                     document.getElementById(`data-user-username`).innerText = data[d].username
//                     document.getElementById(`data-avatar`).src = data[d].avatar
//                 }
//             }
//             else {
//                 document.getElementById(`data-${d}`).innerHTML = data[d]
//             }
//         }
//         $('#modal-view-news').modal()
//     })
// }
//
// const loadManageById = (endpoint, callback) => {
//     fetch(endpoint, {
//         method: 'GET', headers: {
//             "Content-Type": "application/json"
//         }
//     }).then(res => res.json()).then(data => {
//         callback(data);
//     })
//         .catch(err => {
//             errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok")
//         })
// }

const saveChange = (endpoint, notificationId = null) => {
  let selectedUsersId = [...usersElement.options]
      .filter((option) => option.selected)
      .map((option) => parseInt(option.value));
  let selectRoleTypeValue = roleElement.value;
  let selectSchoolYearValue = schoolYearElement.value;

  let formData = {
    title: titleElement.value,
    content: contentElement.value,
    usersId: selectedUsersId,
  };
  $("input").next("span").remove();
  $("textarea").next("span").remove();

  showLoading();
  if (notificationId === null) {
    // ADD
    fetch(
        `${endpoint}?roleId=${selectRoleTypeValue}&schoolYearId=${selectSchoolYearValue}`,
        {
          method: "POST",
          body: JSON.stringify(formData),
          headers: {
            "Content-Type": "application/json",
          },
        }
    )
        .then((res) => res.json())
        .then((data) => {
          if (Object.keys(data).length === 0) {
            // successful
            $("#modal-add-edit-notification").hide();
            successfulAlert("Gửi thông báo thành công", "Ok", () =>
                location.reload()
            );
          } else {
            console.log(data);
            // error
            showError(data);
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
  }
};

const showAddNotificationModal = (endpoint) => {
  document.getElementById("myModalAddAndEditNotification").innerText =
    "Thêm thông báo";
  $("#modal-add-edit-notification").modal();
  document.getElementById("btn-submit-form").onclick = () =>
    saveChange(endpoint);
};

// role type change
const objectChange = (endpoint) => {
  if (roleElement.value === "") {
    schoolYearElement.disabled = true;
    usersElement.disabled = true;
    schoolYearElement.value = "";
  } else {
    if (roleElement.value === "4") {
      schoolYearElement.disabled = false;
    } else {
      schoolYearElement.disabled = true;
      schoolYearElement.value = "";
    }
    usersElement.disabled = false;
  }
  loadUsers(endpoint);
};

// school year change
const schoolYearChange = (endpoint) => {
  loadUsers(endpoint);
};

const deleteNotificationItem = (endpoint) => {
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
            successfulAlert("Xóa thông báo thành công", "Ok", () =>
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
$("#modal-add-edit-notification").on("hidden.bs.modal", function (e) {
  $("input").next("span").remove();
  $("textarea").next("span").remove();
  document.forms["form-add-edit-notification"].reset();
});

