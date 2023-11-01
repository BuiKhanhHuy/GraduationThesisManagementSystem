var memberArray = [0];
var lecturersHtml = "";
var isLoadDataOptions = true;
var council;

const loadDataOptions = (callback) => {
    if (isLoadDataOptions) {
        fetch(
            "/GraduationThesisManagementSystem/admin/api/thesis-options/?isCouncil=False",
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            }
        )
            .then((res) => res.json())
            .then((data) => {
                let thesesHtml = "";
                data.forEach(
                    (d) =>
                        (thesesHtml += `<option value=${d[0]}>${d[1]} - ${d[2]}</option>`)
                );
                document.getElementById("theses").innerHTML = thesesHtml;
            })
            .then(() =>
                fetch(
                    "/GraduationThesisManagementSystem/admin/api/lecturer-options/?isMinistry=False",
                    {
                        method: "GET",
                        headers: {
                            "Content-Type": "application/json",
                        },
                    }
                )
                    .then((res) => res.json())
                    .then((data) => {
                        data.forEach(
                            (d) => (lecturersHtml += `<option value=${d[0]}>${d[1]}</option>`)
                        );
                        document.getElementById("lecturer-0").innerHTML = lecturersHtml;

                        callback();
                    })
            )
            .catch((err) => {
                console.log(err);
                errorAlert(
                    "Đã có lỗi",
                    "Đã có lỗi xảy ra trong quá trình tải dữ liệu!",
                    "Ok"
                );
            });
        isLoadDataOptions = false;
    } else {
        callback();
    }
};


var scoreDataArea = document.getElementById("score-data-area");
const printScoreData = (appContext, councilId) => {
    fetch(`${appContext}admin/api/councils/${councilId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => res.json())
        .then((councilData) => {
            let promises = [];
            let data = {};

            councilData.theses.map((thesis, idx) => {
                promises.push(
                    fetch(
                        `${appContext}admin/api/councils/${councilId}/scores/?thesisId=${thesis.id}`
                    )
                );
                data[idx] = {thesis: thesis};
            });

            Promise.all(promises)
                .then(async function (responses) {
                    for (let i = 0; i < responses.length; i++) {
                        data[i]["scores"] = await responses[i].json();
                    }
                })
                .then(() => {
                    let thesesHtml = ``;
                    let thesisNo = 0;
                    for (let key in data) {
                        let studentHtml = ``;
                        let scoresHtml = ``;
                        let totalScore = 0;
                        thesisNo += 1;

                        for (let iStudent = 0; iStudent < 2; iStudent++) {
                            if (data[key].thesis.students.length >= iStudent + 1) {
                                studentHtml += `<td class="text-center align-middle">${data[key].thesis.students[iStudent].code}</td>
                                        <td class="align-middle">${data[key].thesis.students[iStudent].fullName}</td>`;
                            } else {
                                studentHtml += `<td class="text-center align-middle"></td>
                                    <td class="align-middle"></td>`;
                            }
                        }

                        for (let iScore = 0; iScore < 5; iScore++) {
                            if (data[key].scores.length >= iScore + 1) {
                                scoresHtml += `<td class="text-center align-middle">${data[key].scores[iScore][3]}</td>`;
                                totalScore += data[key].scores[iScore][3];
                            } else {
                                scoresHtml += `<td class="text-center align-middle">-</td>`;
                            }
                        }

                        thesesHtml += `<tr>
                                    <td class="text-center align-middle">${thesisNo}</td>
                                    ${studentHtml}
                                    <td class="text-center align-middle">${
                            data[key].thesis.topic.name
                        }</td>
                                     ${scoresHtml}
                                     <td class="align-middle">${
                            totalScore / data[key].scores.length
                        }</td>
                                </tr>`;
                    }

                    scoreDataArea.innerHTML = `<div class="pd-30 card-box mb-30" style="padding-bottom: 120px;" id="score-data">
                                            <div class="pd-20 mb-5 row">
                                                <div class="col-4">
                                                    <div class="text-center">BỘ GIÁO DỤC VÀ ĐÀO TẠO</div>
                                                    <div class="font-weight-bold text-center">
                                                        TRƯỜNG ĐẠI HỌC MỞ THÀNH PHỐ HỒ CHÍ MINH
                                                    </div>
                                                    <h4 class="h5 mt-4 text-center">${councilData.name}</h4>
                                                </div>
                                                <div class="col-6 text-center">
                                                    <h5 class="text-danger mb-2">
                                                        PHIẾU CHẤM ĐỒ ÁN/KHÓA LUẬN TỐT NGHIỆP NIÊN KHÓA (${councilData.schoolYear.name})
                                                    </h5>
                                                    <h6 class="mb-2">
                                                        DÀNH CHO THÀNH VIÊN HỘI ĐỒNG BẢO VỆ KHÓA LUẬN TỐT NGHIỆP
                                                    </h6>
                                                </div>
                                                <div class="col-2">
                                                    <div class="p-2 mt-2 text-danger text-right font-weight-bold">
                                                        Mẫu 001
                                                    </div>
                                                </div>
                                            </div>
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th scope="col" rowspan="2" class="text-center align-middle">
                                                        Stt
                                                    </th>
                                                    <th scope="col" rowspan="2" class="text-center align-middle">
                                                        Mssv 1
                                                    </th>
                                                    <th scope="col" rowspan="2" class="text-center align-middle">
                                                        Họ và tên sinh viên 1
                                                    </th>
                                                    <th scope="col" rowspan="2" class="text-center align-middle">
                                                        Mssv 2
                                                    </th>
                                                    <th scope="col" rowspan="2" class="text-center align-middle">
                                                        Họ và tên sinh viên 2
                                                    </th>
                                                     <th scope="col" rowspan="2" class="text-center align-middle">
                                                        Đề tài
                                                    </th>
                                                    <th scope="col" colspan="5" class="text-center align-middle">
                                                        Điểm Hội đồng bảo vệ tốt nghiệp
                                                    </th>
                                                    <th scope="col" rowspan="2" class="text-center align-middle">
                                                        Điểm tổng kết
                                                    </th>
                                                </tr>
                                                <tr>
                                                    <th scope="col" class="text-center align-middle">UVHĐ 01</th>
                                                    <th scope="col" class="text-center align-middle">UVHĐ 02</th>
                                                    <th scope="col" class="text-center align-middle">UVHĐ 03</th>
                                                    <th scope="col" class="text-center align-middle">UVHĐ 04</th>
                                                    <th scope="col" class="text-center align-middle">UVHĐ 05</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                ${thesesHtml}
                                                </tbody>
                                            </table>
                                            <div class="row mt-4 mb-30">
                                                <div class="col-6">
                                                    <div class="text-center">
                                                        TP. Hồ Chí Minh, ngày ______ tháng ______ năm ______
                                                    </div>
                                                    <p class="text-center">Lãnh đạo ký và ghi rõ họ tên</p>
                                                </div>
                                                <div class="col-6">
                                                    <div class="text-center">
                                                        TP. Hồ Chí Minh, ngày ______ tháng ______ năm ______
                                                    </div>
                                                    <p class="text-center">Chủ tịch Hội đồng ký và ghi rõ họ tên</p>
                                                </div>
                                            </div>
                                        </div>`;

                    $("#score-data").printMe({
                        path: [
                            "https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css",
                        ],
                    });
                    location.reload();
                })
                .catch((err) => {
                    console.log(err);
                    errorAlert(
                        "Đã có lỗi!",
                        "Đã có lỗi trong quá trình tải dữ liệu!",
                        "Ok"
                    );
                });
        });
};


const loadCouncilById = (appContext, councilId, callback) => {
    let councilEndPoint = `${appContext}admin/api/councils/${councilId}`;

    fetch(councilEndPoint, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => res.json())
        .then((data) => {
            council = data;

            return fetch(
                `${appContext}admin/api/councilsDetail/?councilId=${data.id}`
            );
        })
        .then((res) => res.json())
        .then((data) => {
            callback(council, data);
        })
        .catch((err) => {
            console.error(err);
            errorAlert(
                "Đã có lỗi",
                "Đã có lỗi xảy ra trong quá trình tải dữ liệu!",
                "Ok"
            );
        });
};


const saveChange = (endpoint, councilId = null) => {
    $("input").next("span").remove();
    $("select + span").next("span").remove();
    $("textarea").next("span").remove();
    document.getElementById(`councilDetails`).innerText = "";

    let formCouncilElement = document.getElementById("form-add-edit-council");
    let formData = {};
    formData["name"] = formCouncilElement["name"].value;
    formData["description"] = formCouncilElement["description"].value;
    formData["schoolYear"] = formCouncilElement["schoolYear"].value;
    formData["theses"] = [...formCouncilElement["theses"].options]
        .filter((option) => option.selected)
        .map((option) => parseInt(option.value));
    formData["councilDetails"] = memberArray.map((member) => {
        return {
            position: formCouncilElement[`position-${member}`].value,
            lecturer: formCouncilElement[`lecturer-${member}`].value,
        };
    });
    console.warn(formData);

    showLoading();
    if (councilId === null) {
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
                    $("#modal-add-edit-council").hide();
                    successfulAlert("Thành lập hội đồng thành công", "Ok", () =>
                        location.reload()
                    );
                } else {
                    // error
                    if (data["otherErrors"] !== undefined) {
                        errorAlert("Đã có lỗi khác rồi", data["errorTotalWeight"], "Ok");
                    } else {
                        $.each(data, function (key, value) {
                            if (
                                key === "name" ||
                                key === "description" ||
                                key === "schoolYear"
                            ) {
                                document
                                    .getElementsByName(`${key}`)[0]
                                    .insertAdjacentHTML(
                                        "afterend",
                                        '<span class="text-danger font-weight-normal">' +
                                        value +
                                        "</span>"
                                    );
                            } else if (key === "theses") {
                                $("select[name=" + key + "] + span").after(
                                    '<span class="text-danger">' + value + "</span>"
                                );
                            } else if (key === "councilDetails") {
                                document.getElementById(`${key}`).innerText = value;
                            } else {
                                $(
                                    "input[name=" +
                                    `position-${
                                        memberArray[parseInt(key.match(/\d/g).join(""))]
                                    }` +
                                    "]"
                                ).after(
                                    '<span class="text-danger font-weight-normal">' +
                                    value +
                                    "</span>"
                                );
                            }
                        });
                    }
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
        // fetch(endpoint, {
        //     method: "PATCH", body: JSON.stringify(formData), headers: {
        //         "Content-Type": "application/json"
        //     }
        // }).then(res => res.json()).then(data => {
        //     if (Object.keys(data).length === 0) {
        //         // successful
        //         $('#modal-add-edit-major').hide();
        //         successfulAlert("Cập nhật thông tin ngành thành công", "Ok", () => location.reload());
        //     } else {
        //         // errror
        //         $.each(data, function (key, value) {
        //             $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
        //         });
        //     }
        // }).catch(err => {
        //     errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình cập nhật!", "Ok")
        // }).finally(hideLoading)
    }
};

const deleteCouncilItem = (appContext, councilId) => {
    let endpoint = `${appContext}admin/api/councils/${councilId}`;
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
                        successfulAlert("Xóa hội đồng thành công", "Ok", () =>
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


const memberItem = (i) => {
    let removeMemberButton = `
                             <a href="javascript:;" onclick="removeMemberItem(${i})"
                                class="remove-task text-danger"  data-toggle="tooltip" data-placement="bottom" title="" 
                                data-original-title="Remove member">
                                <i class="ion-minus-circled"></i>
                            </a>`;

    if (i === 0) removeMemberButton = "";

    return ` <tr id="member-${i}">
                <th scope="row">
                    <input name="position-${i}" id="position-${i}" type="text" class="form-control"/>
                </th>
                <td>
                     <div>
                        <select class="custom-select2 form-control"
                                name="lecturer-${i}" id="lecturer-${i}" style="width: 100%; ">
                            ${lecturersHtml}
                        </select>
                    </div>
                </td>
                 <th scope="row" class="text-center">
                   ${removeMemberButton}
                </th>
            </tr>`;
};

const loadMemberForm = (memberArray) => {
    let memberAreaElement = document.getElementById("member-area");
    let actionHtml = `<tr>
                        <td colspan="3">
                            <div class="add-more-task" id="add-more-task">
                                <a href="javascript:;" onclick="addMemberItem()"><i
                                        class="ion-plus-circled"></i>
                                    Thêm thành viên</a>
                            </div>
                        </td>
                    </tr>`;
    let memberHtml = "";
    memberArray.forEach((value) => (memberHtml += memberItem(value)));

    memberAreaElement.innerHTML = memberHtml + actionHtml;
};

// load member form
document.addEventListener("DOMContentLoaded", () => {
    loadMemberForm(memberArray);
});

// add member item
const addMemberItem = () => {
    let itemNumberCurrent = memberArray.at(-1);
    let itemNumberNext = itemNumberCurrent + 1;

    document
        .getElementById(`member-${itemNumberCurrent}`)
        .insertAdjacentHTML("afterend", memberItem(itemNumberNext));

    memberArray.push(itemNumberNext);
};

// remove member item
const removeMemberItem = (i) => {
    document
        .getElementById("member-area")
        .removeChild(document.getElementById(`member-${i}`));

    memberArray.splice(memberArray.indexOf(i), 1);
};

const showAddCouncilModal = (appContext) => {
    let endPoint = `${appContext}admin/api/councils`;

    loadDataOptions(() => {
        document.getElementById("myModalAddAndEditCouncil").innerText =
            "Thành lập hội đồng";
        $("#modal-add-edit-council").modal();
        document.getElementById("btn-submit-form").onclick = () =>
            saveChange(endPoint);
    });
};
//
// const showEditMajorModal = (endpoint, majorId) => {
//     loadMajorById(endpoint, (data) => {
//         document.getElementById("myModalAddAndEditMajor").innerText = "Cập nhật ngành"
//         document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint, majorId)
//         $('#modal-add-edit-major').modal()
//
//         for (let d in data) {
//             if (d === "department") {
//                 data[d] === null ? document.forms['form-add-edit-major'][d].value = ''
//                     : document.forms['form-add-edit-major'][d].value = data[d].id
//             } else {
//                 document.forms['form-add-edit-major'][d].value = data[d];
//             }
//         }
//
//     })
// }

const showViewCouncilModal = (appContext, councilId) => {
    loadCouncilById(appContext, councilId, (council, councilsDetail) => {
        let innerHtmlTheses = "";
        let innerHtmlLecturers = "";
        let innerHtmlScores = "";

        council.theses.map((thesis) => {
            innerHtmlTheses += `<tr>
                                    <td>${thesis.code}</td>
                                    <td>${
                thesis.topic !== null ? thesis.topic.name : "Chưa cập nhật"
            }</td>
                                </tr>`;

            innerHtmlScores += `<div class="card mb-3">
                                    <div class="card-header"
                                     onclick="loadThesisScoresWhenClickShow('${appContext}', ${councilId}, ${
                thesis.id
            })">
                                        <button class="btn btn-block text-left" data-toggle="collapse" data-target="#faq-score-${
                thesis.id
            }">
                                           ${
                thesis.topic !== null ? thesis.topic.name : "Chưa cập nhật"
            }
                                        </button>
                                    </div>
                                    <div id="faq-score-${
                thesis.id
            }" class="collapse">
                                        <div>
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th scope="col">Giảng viên</th>
                                                    <th scope="col" class="text-center">Tổng điểm</th>
                                                </tr>
                                                </thead>
                                                <tbody id="score-area-${
                thesis.id
            }"></tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>`;
        });

        councilsDetail.map(
            (councilDetail) =>
                (innerHtmlLecturers += `<tr>
                                    <td>${
                    councilDetail.lecturer !== null ? councilDetail.lecturer.code : "Chưa cập nhật"
                }</td>
                                    <td>${councilDetail.position}</td>
                                    <td>${councilDetail.lecturer !== null ? councilDetail.lecturer.fullName : "Chưa cập nhật"}
                                    </td>
                                </tr>`)
        );
        document.getElementById("data-name").innerText = council.name;
        document.getElementById("data-description").innerText = council.description;
        council.schoolYear === null ? (document.getElementById(`data-schoolYear`).innerText = "Chưa cập nhật") : document.getElementById(`data-schoolYear`).innerText = council.schoolYear.name;
        council.block === true ? (document.getElementById("data-block").innerHTML = ` <div class="text-warning"> <i class="icon-copy fa fa-unlock" aria-hidden="true"></i> Đang khóa </div>`) : document.getElementById("data-block").innerHTML = `<div class="text-success"> <i class="icon-copy fa fa-unlock" aria-hidden="true"></i> Đang mở </div>`;
        document.getElementById("data-theses").innerHTML = innerHtmlTheses;
        document.getElementById("data-lecturers").innerHTML = innerHtmlLecturers;
        document.getElementById("thesis-area").innerHTML = innerHtmlScores;
        document.getElementById("btn-print")
            .addEventListener("click", () => printScoreData(appContext, councilId));

        $("#modal-view-council").modal();
    });
};

const loadThesisScoresWhenClickShow = (appContext, councilId, thesisId) => {
    let scoreArea = document.getElementById(`score-area-${thesisId}`);
    let scoreItemHtml = "";

    if (scoreArea.innerHTML === "") {
        fetch(
            `${appContext}admin/api/councils/${councilId}/scores/?thesisId=${thesisId}`,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            }
        )
            .then((res) => {
                if (res.ok) {
                    return res.json();
                } else {
                    Promise.reject("Xảy ra lỗi trong quá trình tải dữ liệu!");
                }
            })
            .then((data) => {
                data.map((d) => {
                    scoreItemHtml += `<tr>
                                        <td>${d[2]}</td>
                                        <td class="text-center">${d[3] !== null ? d[3].toFixed(2) : `<span class="text-danger font-weight-bold">Chưa chấm điểm</span>`}
                                        </td>
                                    </tr>`;
                });

                scoreArea.innerHTML = scoreItemHtml;
            })
            .catch((err) => {
                errorAlert("Đã có lỗi", err, "Ok");
            });
    }
};

// lock or unlock council
const lockOrUnLockCouncil = (appContext, councilId, block = false) => {
    const lockOrUnlockHandle = (appContext, councilId, block) => {
        showLoading();
        fetch(`${appContext}admin/api/councils/${councilId}/?block=${block}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then((res) => {
                if (res.ok) {
                    if (block) {
                        successfulAlert("Khóa hội đồng thành công", "Ok", () =>
                            location.reload()
                        );
                    } else {
                        successfulAlert("Mở khóa hội đồng thành công", "Ok", () =>
                            location.reload()
                        );
                    }
                } else {
                    Promise.reject("Đã có lỗi trong quá trình cập nhật!");
                }
            })
            .catch((err) => {
                errorAlert("Đã có lỗi", err, "Ok");
            })
            .finally(hideLoading);
    };

    switch (block) {
        case true:
            confirmAlert(
                "Bạn có chắc chắn muốn khóa hội đồng này không?",
                "Khi hội đồng bị khóa, hệ thống sẽ tổng hợp điểm và gửi kết quả cho sinh viên!",
                "Có, khóa ngay",
                "Hủy",
                () => {
                    console.info("Khóa hội đồng.");
                    lockOrUnlockHandle(appContext, councilId, block);
                }
            );
            break;
        case false:
            confirmAlert(
                "Bạn có chắc chắn muốn mở khóa hội đồng này không?",
                "Khi hội đồng được mở khóa, giảng viên có chỉnh sửa điểm số cho khóa luận!",
                "Có, mở khóa",
                "Hủy",
                () => {
                    console.info("Mở khóa hội đồng.");
                    lockOrUnlockHandle(appContext, councilId, block);
                }
            );
            break;
    }
};

// event before hidden modal
$("#modal-add-edit-council").on("hidden.bs.modal", function (e) {
    $("input").next("span").remove();
    $("textarea").next("span").remove();
    $("select + span").next("span").remove();
    document.getElementById(`councilDetails`).innerText = "";
    document.forms["form-add-edit-council"].reset();

    memberArray = [0];
    loadMemberForm(memberArray);
});
