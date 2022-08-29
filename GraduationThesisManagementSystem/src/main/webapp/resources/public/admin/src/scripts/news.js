
const loadNewsById = (endpoint, callback) => {
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

const saveChange = (endpoint, newsId = null) => {
  let form = $("#form-add-edit-news");
  let formData = {};

  form.serializeArray().forEach((item) => (formData[item.name] = item.value));
  delete formData["_wysihtml5_mode"];
  $("input").next("span").remove();
  document.getElementById("error-content").innerText = "";

  showLoading();
  if (newsId === null) {
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
            $("#modal-add-edit-news").hide();
            successfulAlert("Thêm bản tin thành công", "Ok", () =>
                location.reload()
            );
          } else {
            console.log(data);
            // error
            $.each(data, function (key, value) {
              if (key !== "content")
                $("input[name=" + key + "]").after(
                    '<span class="text-danger">' + value + "</span>"
                );
              else document.getElementById("error-content").innerText = value;
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
            $("#modal-add-edit-news").hide();
            successfulAlert("Cập nhật thông tin bản tin thành công", "Ok", () =>
                location.reload()
            );
          } else {
            // error
            $.each(data, function (key, value) {
              if (key !== "content")
                $("input[name=" + key + "]").after(
                    '<span class="text-danger">' + value + "</span>"
                );
              else document.getElementById("error-content").innerText = value;
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

const showAddNewsModal = (endpoint) => {
  document.getElementById("myModalAddAndEditNews").innerText = "Thêm bản tin";
  $("#modal-add-edit-news").modal();
  document.getElementById("btn-submit-form").onclick = () =>
    saveChange(endpoint);
};

const showEditNewsModal = (endpoint, newsId) => {
  loadNewsById(endpoint, (data) => {
    document.getElementById("myModalAddAndEditNews").innerText =
      "Cập nhật bản tin";
    document.getElementById("btn-submit-form").onclick = () =>
      saveChange(endpoint.replace("/single", ""), newsId);
    $("#modal-add-edit-news").modal();

    document.forms["form-add-edit-news"]["title"].value = data[1];
    $("#content").data("wysihtml5").editor.setValue(data[2]);
  });
};

const showViewNewsModal = (endpoint) => {
  loadNewsById(endpoint, (data) => {
    console.log(data);
    for (let d in data) {
      if (d === "user") {
        if (data[d] === null) {
          document.getElementById(`data-user-username`).innerText =
            "Chưa cập nhật";
          document.getElementById(`data-avatar`).src = "#";
        } else {
          document.getElementById(`data-user-username`).innerText =
            data[d].username;
          document.getElementById(`data-avatar`).src = data[d].avatar;
        }
      } else {
        if (["title", "content"].indexOf(d.toString()) > -1)
          document.getElementById(`data-${d}`).innerHTML = data[d];
        else if (d === "createdDate") {
          document.getElementById(`data-${d}`).innerHTML = moment(
            data[d]
          ).fromNow();
        }
      }
    }
    $("#modal-view-news").modal();
  });
};

const deleteNewsItem = (endpoint) => {
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
            successfulAlert("Xóa bản tin thành công", "Ok", () =>
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
$("#modal-add-edit-news").on("hidden.bs.modal", function (e) {
  $("input").next("span").remove();
  document.getElementById("error-content").innerText = "";
  document.forms["form-add-edit-news"].reset();
});
