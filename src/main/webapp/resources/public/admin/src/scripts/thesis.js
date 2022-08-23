var isLoadDataOptions = true;

const loadDataOptions = (callback) => {
    if (isLoadDataOptions) {
        fetch("/GraduationThesisManagementSystem/admin/api/student-options", {
            method: "GET", headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            let studentsHtml = ''
            data.forEach(d => studentsHtml += `<option value=${d[0]}>${d[1]}</option>`)
            document.getElementById("students").innerHTML = studentsHtml;
        }).then(() => fetch("/GraduationThesisManagementSystem/admin/api/lecturer-options", {
            method: "GET", headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            let lecturersHtml = ''
            data.forEach(d => lecturersHtml += `<option value=${d[0]}>${d[1]}</option>`)
            document.getElementById("lecturers").innerHTML = lecturersHtml;
            document.getElementById("reviewLecturer").innerHTML = lecturersHtml;

            // callback
            callback();
        })).catch(err => {
            console.log(err)
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok")
        })
        isLoadDataOptions = false;
    } else {
        callback();
    }
}

const showAddThesisModal = (endpoint) => {
    loadDataOptions(() => {
        document.getElementById("myModalAddAndEditThesis").innerText = "Thêm khóa luận"
        $('#modal-add-edit-thesis').modal()
        document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint)
    })
}

const showEditThesisModal = (endpoint, thesisId) => {
    loadDataOptions(() => loadThesisById(endpoint, (data) => {
        // get data
        let form = document.forms['form-add-edit-thesis']
        form["code"].value = data.code;
        if (data.topic !== null) {
            form["topic"].value = data.topic.id
        }
        form["startDate"].value = new Date(data.startDate).toISOString().slice(0, 10)
        form["complateDate"].value = new Date(data.complateDate).toISOString().slice(0, 10)
        form["thesisStartDate"].value = new Date(data.thesisStartDate).toISOString().slice(0, 10)
        form["thesisEndDate"].value = new Date(data.thesisEndDate).toISOString().slice(0, 10)
        if (data.major !== null) {
            form["major"].value = data.major.id
        }
        if (data.schoolYear !== null) {
            form["schoolYear"].value = data.schoolYear.id
        }
        if (data.reviewLecturer !== null) {
            form["reviewLecturer"].value = data.reviewLecturer.id
        }
        $("#comment").data('wysihtml5').editor.setValue(data.comment);

        let studentsElement = document.getElementById("students")
        let values1 = data.students.map(value => value.id.toString())
        for (let i = 0; i < studentsElement.options.length; i++) {
            studentsElement.options[i].selected = values1.indexOf(studentsElement.options[i].value) >= 0;
        }

        let lecturersElement = document.getElementById("lecturers")
        let values2 = data.lecturers.map(value => value.id.toString())
        for (let i = 0; i < lecturersElement.options.length; i++) {
            lecturersElement.options[i].selected = values2.indexOf(lecturersElement.options[i].value) >= 0;
        }
        // end get data
        document.getElementById("myModalAddAndEditThesis").innerText = "Cập nhật khóa luận"
        document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint, thesisId)
        $('#modal-add-edit-thesis').modal()
    }))
}
const loadThesisById = (endpoint, callback) => {
    fetch(endpoint, {
        method: 'GET', headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        callback(data);
    })
        .catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok")
        })
}

const saveChange = (endpoint, thesisId = null) => {
    let form = $("#form-add-edit-thesis")
    let formData = {}

    form.serializeArray().forEach(item => {
        formData[item.name] = item.value
    })
    delete formData['_wysihtml5_mode'];
    formData["lecturers"] = [...document.getElementById("lecturers").options]
        .filter(option => option.selected)
        .map(option => parseInt(option.value));
    formData["students"] = [...document.getElementById("students").options]
        .filter(option => option.selected)
        .map(option => parseInt(option.value));

    $('input').next('span').remove();
    $('select + span').next('span').remove()

    showLoading()
    if (thesisId === null) {
        // ADD
        fetch(endpoint, {
            method: "POST", body: JSON.stringify({
                "code": formData.code,
                "startDate": formData.startDate,
                "complateDate": formData.complateDate,
                "thesisStartDate": formData.thesisStartDate,
                "thesisEndDate": formData.thesisEndDate,
                "major": formData.major,
                "schoolYear": formData.schoolYear,
                "topic": formData.topic,
                "comment": formData.comment,
                "reportFile": "",
                "lecturers": formData.lecturers,
                "reviewLecturer": formData.reviewLecturer,
                "students": formData.students
            }), headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-thesis').hide();
                successfulAlert("Thêm khóa luận thành công", "Ok", () => location.reload());
            } else {
                console.log(data)
                // error
                $.each(data, function (key, value) {
                    if (['code', 'startDate', 'complateDate', 'thesisStartDate', 'thesisEndDate'].indexOf(key) > -1) {
                        $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
                    } else {
                        $('select[name=' + key + '] + span').after('<span class="text-danger">' + value + '</span>');
                    }
                });
            }
        }).catch(err => {
            console.error(err)
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình thêm dữ liệu!", "Ok")
        }).finally(hideLoading)
    } else {
        console.log(formData)
        // UPDATE
        fetch(endpoint, {
            method: "PATCH", body: JSON.stringify({
                "code": formData.code,
                "startDate": formData.startDate,
                "complateDate": formData.complateDate,
                "thesisStartDate": formData.thesisStartDate,
                "thesisEndDate": formData.thesisEndDate,
                "major": formData.major,
                "schoolYear": formData.schoolYear,
                "topic": formData.topic,
                "comment": formData.comment,
                "reportFile": "",
                "lecturers": formData.lecturers,
                "reviewLecturer": formData.reviewLecturer,
                "students": formData.students
            }), headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json()).then(data => {
            if (Object.keys(data).length === 0) {
                // successful
                $('#modal-add-edit-thesis').hide();
                successfulAlert("Cập nhật khóa luận thành công", "Ok", () => location.reload());
            } else {
                // error
                $.each(data, function (key, value) {
                    if (['code', 'startDate', 'complateDate', 'thesisStartDate', 'thesisEndDate'].indexOf(key) > -1) {
                        $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
                    } else {
                        $('select[name=' + key + '] + span').after('<span class="text-danger">' + value + '</span>');
                    }

                });
            }
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình cập nhật!", "Ok")
        }).finally(hideLoading)
    }
}

const deleteThesisItem = (endpoint) => {
    // DELETE
    confirmAlert("Bạn có chắc không?", "Bạn sẽ không thể khôi phục điều này!", "Có, xóa nó", "Không, hủy bỏ", () => {
        showLoading()

        fetch(endpoint, {
            method: "DELETE", headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (res) {
            if (res.status === 204) successfulAlert("Xóa khóa luận thành công", "Ok", () => location.reload());
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!", "Ok")
        }).finally(hideLoading)
    })
}

// event before hidden modal
$('#modal-add-edit-thesis').on('hidden.bs.modal', function (e) {
    // delete all select in multiselect
    let a = document.querySelectorAll("li.select2-selection__choice")
    for (let i = 0; i < a.length; i++) {
        a[i].style.display = 'none'
    }
    $('input').next('span').remove();
    $('select + span').next('span').remove()
    document.forms['form-add-edit-thesis'].reset();
})
