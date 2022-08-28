<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url value="/public/common/images/avatars/avatar-default.jpg" var="avatarDefault"/>
<c:url var="filterStudent" value=""/>
<c:url var="home" value="/admin/"/>
<c:url var="appContext" value="/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>
                    <spring:message code="student.header.title.label"/>
                </h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">
                        <spring:message code="layout.home.label"/>
                    </a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="student.header.title.label"/>
                    </li>
                </ol>
            </nav>
        </div>
    </div>
</div>

<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterStudent}">
        <input name="page" id="page" hidden/>
        <div class="row justify-content-end mt-2">
            <div class="col-md-6 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text"
                           placeholder="<spring:message code="student.table.search.keyword.label"/>"
                           name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="schoolYearId" id="schoolYearId"
                            style="width: 100%">
                        <option value="${""}">
                            <spring:message code="student.table.search.schoolYear"/>
                        </option>
                        <c:forEach var="schoolYearOption" items="${schoolYearOptions}">
                            <option value="${schoolYearOption[0]}">${schoolYearOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="majorId" id="majorId"
                            style="width: 100%">
                        <option value="${""}">
                            <spring:message code="student.table.search.major"/>
                        </option>
                        <c:forEach var="majorOption" items="${majorOptions}">
                            <option value="${majorOption[0]}">${majorOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="active" id="active-filter"
                            style="width: 100%">
                        <option value="${""}">
                            <spring:message code="student.table.search.status"/>
                        </option>
                        <option value="${true}">
                            <spring:message code="student.table.search.status.option.active"/>
                        </option>
                        <option value="${false}">
                            <spring:message code="student.table.search.status.option.unActive"/>
                        </option>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div>
                    <button class="btn-warning btn form-control" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i>
                        <spring:message code="student.table.search.button.label"/>
                    </button>
                </div>
            </div>
        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">
                <spring:message code="student.table.list.title.label"/>
            </h4>
        </div>
        <div class="pull-right">
            <button onclick="importStudentsFile('${appContext}')"
                    type="button" class="btn btn-success btn-md mr-1"><i class="micon icon-copy dw dw-upload1"></i>
                Import File
            </button>
            <button onclick="showAddStudentModal('${appContext}')"
                    type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                <spring:message code="student.table.list.button.addStudent.label"/>
            </button>
        </div>
    </div>
    <table class="table table-bordered table-responsive">
        <thead>
        <tr>
            <th scope="col" class="text-center col-1">
                <spring:message code="student.table.list.header.image"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="student.table.list.header.code"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="student.table.list.header.info"/>
            </th>
            <th scope="col">
                <spring:message code="student.table.list.header.schoolYear"/>
            </th>
            <th scope="col">
                <spring:message code="student.table.list.header.major"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="student.table.list.header.status"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="student.table.list.header.action"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${students.size() != 0}">
            <c:forEach var="student" items="${students}">
                <tr>
                    <td class="text-center col-1">
                        <c:if test="${student.user.avatar == null || student.user.avatar.isEmpty()}">
                            <img src="${avatarDefault}" style="border-radius: 50%;" class="img-fluid" height="70"
                                 width="70" alt="">
                        </c:if>
                        <c:if test="${student.user.avatar != null && !student.user.avatar.isEmpty()}">
                            <img src="${student.user.avatar}" style="border-radius: 50%;" class="img-fluid" height="70"
                                 width="70" alt="">
                        </c:if>
                    </td>
                    <td class="text-center">${student.code}</td>
                    <td>
                        <ul style="list-style-type: circle; list-style-position: inside; ">
                            <li>${student.fullName}</li>
                            <li>${student.email}</li>
                            <li>${student.phone}</li>
                            <li><fmt:formatDate type="date" value="${student.birthday}"/></li>
                            <li>
                                <c:if test="${student.gender == 1}">
                                    <spring:message code="student.table.list.data.option.gender.male"/>
                                </c:if>
                                <c:if test="${student.gender == 2}">
                                    <spring:message code="student.table.list.data.option.gender.female"/>
                                </c:if>
                                <c:if test="${student.gender == 3}">
                                    <spring:message code="student.table.list.data.option.gender.other"/>
                                </c:if>
                            </li>
                            <li>${student.address}</li>
                        </ul>
                        <a onclick="changePassword('${appContext}', ${student.user.id})"
                           href="javascript:;"
                           class="ml-2 text-blue">
                            <i class="icon-copy fa fa-key" aria-hidden="true"></i>
                            <spring:message code="student.table.list.data.option.changePassword.label"/>
                        </a>
                    </td>
                    <td>
                        <c:if test="${student.schoolYear != null}">
                            ${student.schoolYear.name}
                        </c:if>
                        <c:if test="${student.schoolYear == null}">
                            <span class="text-black-50 text-center">
                                <spring:message code="student.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${student.major != null}">
                            ${student.major.name}
                        </c:if>
                        <c:if test="${student.major == null}">
                            <span class="text-black-50 text-center">
                                 <spring:message code="student.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <c:if test="${student.user.active == true}">
                            <i class="icon-copy fa fa-check-circle-o text-success" aria-hidden="true"
                               data-toggle="tooltip" data-placement="bottom"
                               title="<spring:message code="student.table.search.status.option.active"/> "></i>
                        </c:if>
                        <c:if test="${student.user.active != true}">
                            <i class="icon-copy fa fa-times-circle-o text-danger" aria-hidden="true"
                               data-toggle="tooltip"
                               data-placement="bottom"
                               title="<spring:message code="student.table.search.status.option.unActive"/> "></i>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <div class="btn-list">
                            <button onclick="showEditStudentModal('${appContext}', ${student.id} )"
                                    type="button" class="btn btn-sm bg-warning text-white" data-toggle="tooltip"
                                    data-placement="bottom"
                                    title="<spring:message code="student.table.list.button.edit"/>">
                                <i class="icon-copy dw dw-edit1"></i>
                            </button>
                            <button onclick="deleteStudentItem('${appContext}', ${student.id} )"
                                    type="button" class="btn btn-sm bg-danger text-white" data-toggle="tooltip"
                                    data-placement="bottom"
                                    title="<spring:message code="student.table.list.button.delete"/>">
                                <i class="icon-copy dw dw-delete-3"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${students.size() == 0}">
            <tr>
                <td colspan="7" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">
                        <spring:message code="student.table.list.data.empty"/>
                    </p>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <div class="blog-pagination pagination-md mt-5 mb-2">
        <div class="btn-toolbar justify-content-center">
            <div class="btn-group">
                <nav aria-label="Page navigation">
                    <ul class="pagination" id="pagination"></ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<!-- table End -->

<!-- ADD and EDIT modal -->
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-student" tabindex="-1" data-focus="false" role="dialog"
     aria-labelledby="myModalAddAndEditStudent" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditStudent"></h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>

            </div>
            <div class="modal-body">
                <form id="form-add-edit-student">
                    <div class="pd-10">
                        <div class="profile-photo text-center" style="width: 120px; height: 120px">
                            <img style="width: 120px; height: 120px"
                                 id="file-output"
                                 src="${avatarDefault}"
                                 alt=""
                                 class="avatar-photo img-fluid">
                        </div>
                        <div class="text-center">
                            <label class="btn btn-outline-info btn-sm">
                                <i class="fa fa-upload"></i>
                                <spring:message code="student.modal.chooseImageButton.label"/>
                                <input type="file" id="file" name="file" accept="image/*" hidden>
                            </label>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="student.modal.code.label"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="code" id="code" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="student.modal.fullName.label"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="fullName" id="fullName" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="student.modal.email"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="email" id="email" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="student.modal.phone"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="phone" id="phone" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="student.modal.birthday"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="birthday" id="birthday" type="date" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="student.modal.gender"/><span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select2 form-control"
                                        name="gender" id="gender" style="width: 100%;">
                                    <option value="1">
                                        <spring:message code="student.table.list.data.option.gender.male"/>
                                    </option>
                                    <option value="2">
                                        <spring:message code="student.table.list.data.option.gender.female"/>
                                    </option>
                                    <option value="3">
                                        <spring:message code="student.table.list.data.option.gender.other"/>
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="student.modal.address"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="address" id="address" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="student.modal.gpa"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="gpa" id="gpa" type="number" min="0" max="10" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="student.modal.schoolYear"/><span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select2 form-control"
                                        name="schoolYear" id="schoolYear" style="width: 100%;">
                                    <c:forEach var="schoolYearOption" items="${schoolYearOptions}">
                                        <option value="${schoolYearOption[0]}">${schoolYearOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="student.modal.major"/><span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select2 form-control"
                                        name="major" id="major" style="width: 100%;">
                                    <c:forEach var="majorOption" items="${majorOptions}">
                                        <option value="${majorOption[0]}">${majorOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div id="new-password-area"></div>
                        <div class="form-group">
                            <div class="custom-control custom-checkbox mb-5">
                                <input type="checkbox" class="custom-control-input" id="is-active" name="is-active">
                                <label class="custom-control-label" for="is-active">
                                    <spring:message code="student.modal.active"/>
                                </label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary close-custom" data-dismiss="modal">
                    <spring:message code="layout.button.cancel.label"/>
                </button>
                <button type="button" class="btn btn-success" id="btn-submit-form">
                    <i class="micon fa fa-save"> </i>
                    <spring:message code="layout.button.saveData.label"/>
                </button>
            </div>
        </div>
    </div>
</div>
<!-- ADD and EDIT modal -->

<!-- import file modal -->
<div class="modal fade bs-example-modal-lg " id="modal-add-import-student" tabindex="-1" role="dialog"
     aria-labelledby="myModalImportStudent" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalImportStudent">
                    <spring:message code="student.modal.import.title"/>
                </h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form name="form-upload-file">
                    <div class="pd-10">
                        <div class="form-group">
                            <div class="alert alert-warning" role="alert">
                                <h5 class="alert-heading h4">
                                    <spring:message code="student.modal.import.subTitle"/>
                                </h5>
                                <ul type="circle">
                                    <li>- <spring:message code="student.modal.import.labelOne"/></li>
                                    <li>- <spring:message code="student.modal.import.labelTwo"/></li>
                                    <li>- <spring:message code="student.modal.import.labelThree"/></li>
                                </ul>
                                <hr>
                                <a href="<c:url value="/public/excel-file/students-example.xlsx"/> ">
                                    <i class="icon-copy fa fa-download" aria-hidden="true"></i>
                                    <spring:message code="student.modal.import.fileExample"/>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="student.modal.import.file.label"/> <span class="text-danger">(*)</span>
                            </label>
                            <input accept=".xlsx, .xls" name="file" id="excelFile" type="file" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button
                        type="button" class="btn btn-secondary close-custom" data-dismiss="modal">
                    <spring:message code="layout.button.cancel.label"/>
                </button>
                <button
                        type="button" class="btn btn-success" id="btn-import">
                    <i class="micon fa fa-upload"> </i>
                    Tải lên
                </button>
            </div>
        </div>
    </div>
</div>
<!-- import file modal -->

<script>
    let currentPage = ${page};
    let totalPage = ${totalPage};
    let pageSize = ${pageSize};

    $('#pagination').twbsPagination({
        totalPages: Math.ceil(totalPage / pageSize),
        visiblePages: 8,
        first: '',
        last: '',
        prev: '&laquo;',
        next: '&raquo;',
        startPage: currentPage,
        onPageClick: function (event, page) {
            if (currentPage != page) {
                $("#page").val(page)
                $("#form-filter").submit();
            }
        }
    });
</script>