<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url value="/public/common/images/avatars/avatar-default.jpg" var="avatarDefault"/>
<c:url var="filterLecturer" value=""/>
<c:url var="home" value="/admin/"/>
<c:url var="appContext" value="/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>
                    <spring:message code="lecturer.header.title.label"/>
                </h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">
                        <spring:message code="layout.home.label"/>
                    </a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="lecturer.header.title.label"/>
                    </li>
                </ol>
            </nav>
        </div>
    </div>
</div>

<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterManage}">
        <input name="page" id="page" hidden/>
        <div class="row justify-content-end mt-2">
            <div class="col-md-6 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text"
                           placeholder="<spring:message code="lecturer.table.search.keyword.label"/>"
                           name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="positionId" id="positionId"
                            style="width: 100%">
                        <option value="${""}">
                            <spring:message code="lecturer.table.search.position"/>
                        </option>
                        <c:forEach var="positionOption" items="${positionOptions}">
                            <option value="${positionOption[0]}">${positionOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="departmentId" id="departmentId"
                            style="width: 100%">
                        <option value="${""}">
                            <spring:message code="lecturer.table.search.department"/>
                        </option>
                        <c:forEach var="departmentOption" items="${departmentOptions}">
                            <option value="${departmentOption[0]}">${departmentOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="roleId" id="roleId"
                            style="width: 100%">
                        <option value="${""}">
                            <spring:message code="lecturer.table.search.role"/>
                        </option>
                        <c:forEach var="roleOption" items="${roleOptions}">
                            <option value="${roleOption[0]}">${roleOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="active" id="active-filter"
                            style="width: 100%">
                        <option value="${""}">
                            <spring:message code="lecturer.table.search.status"/>
                        </option>
                        <option value="${true}">
                            <spring:message code="lecturer.table.search.status.option.active"/>
                        </option>
                        <option value="${false}">
                            <spring:message code="lecturer.table.search.status.option.unActive"/>
                        </option>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div>
                    <button class="btn-warning btn form-control" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i>
                        <spring:message code="lecturer.table.search.button.label"/>
                    </button>
                </div>
            </div>
        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">
                <spring:message code="lecturer.table.list.title.label"/>
            </h4>
        </div>
        <div class="pull-right">
            <button onclick="importLecturersFile('${appContext}')"
                    type="button" class="btn btn-success btn-md mr-1"><i class="micon icon-copy dw dw-upload1"></i>
                Import File
            </button>
            <button onclick="showAddLecturerModal('${appContext}')"
                    type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                <spring:message code="lecturer.table.list.button.addLecturer.label"/>
            </button>
        </div>
    </div>
    <table class="table table-bordered table-responsive">
        <thead>
        <tr>
            <th scope="col" class="text-center col-1">
                <spring:message code="lecturer.table.list.header.image"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="lecturer.table.list.header.code"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="lecturer.table.list.header.info"/>
            </th>
            <th scope="col">
                <spring:message code="lecturer.table.list.header.position"/>
            </th>
            <th scope="col">
                <spring:message code="lecturer.table.list.header.department"/>
            </th>
            <th scope="col">
                <spring:message code="lecturer.table.list.header.role"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="lecturer.table.list.header.status"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="lecturer.table.list.header.action"/>
            </th>
        </tr>
        </thead>
        <tbody>

        <c:if test="${lecturers.size() != 0}">
            <c:forEach var="lecturer" items="${lecturers}">
                <tr>
                    <td class="text-center col-1">
                        <c:if test="${lecturer.user.avatar == null}">
                            <img src="${avatarDefault}" style="border-radius: 50%;" class="img-fluid" height="70"
                                 width="70" alt="">
                        </c:if>
                        <c:if test="${lecturer.user.avatar != null}">
                            <img src="${lecturer.user.avatar}" style="border-radius: 50%;" class="img-fluid" height="70"
                                 width="70" alt="">
                        </c:if>
                    </td>
                    <td class="text-center">${lecturer.code}</td>
                    <td>
                        <ul style="list-style-type: circle; list-style-position: inside; ">
                            <li>${lecturer.fullName}</li>
                            <li>${lecturer.email}</li>
                            <li>${lecturer.phone}</li>
                            <li>
                                <fmt:formatDate type="date" value="${lecturer.birthday}"/>
                            </li>
                            <li>
                                <c:if test="${lecturer.gender == 1}">
                                    <spring:message code="lecturer.table.list.data.option.gender.male"/>
                                </c:if>
                                <c:if test="${lecturer.gender == 2}">
                                    <spring:message code="lecturer.table.list.data.option.gender.female"/>
                                </c:if>
                                <c:if test="${lecturer.gender == 3}">
                                    <spring:message code="lecturer.table.list.data.option.gender.other"/>
                                </c:if>
                            </li>
                            <li>${lecturer.address}</li>
                            <a onclick="changePassword('${appContext}', ${lecturer.user.id})"
                               href="javascript:;"
                               class="ml-2 text-blue">
                                <i class="icon-copy fa fa-key" aria-hidden="true"></i>
                                <spring:message code="lecturer.table.list.data.option.changePassword.label"/>
                            </a>
                        </ul>
                    </td>
                    <td>
                        <c:if test="${lecturer.position != null}">
                            ${lecturer.position.name}
                        </c:if>
                        <c:if test="${lecturer.position == null}">
                            <span class="text-black-50 text-center">
                                <spring:message code="lecturer.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${lecturer.department != null}">
                            ${lecturer.department.name}
                        </c:if>
                        <c:if test="${lecturer.department == null}">
                            <span class="text-black-50 text-center">
                                <spring:message code="lecturer.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${lecturer.user.role != null}">
                            ${lecturer.user.role.description}
                        </c:if>
                        <c:if test="${lecturer.user.role == null}">
                            <span class="text-black-50 text-center">
                                <spring:message code="lecturer.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <c:if test="${lecturer.user.active == true}">
                            <i class="icon-copy fa fa-check-circle-o text-success" aria-hidden="true"
                               data-toggle="tooltip" data-placement="bottom"
                               title="<spring:message code="lecturer.table.search.status.option.active"/>"></i>
                        </c:if>
                        <c:if test="${lecturer.user.active != true}">
                            <i class="icon-copy fa fa-times-circle-o text-danger" aria-hidden="true"
                               data-toggle="tooltip"
                               data-placement="bottom"
                               title="<spring:message code="lecturer.table.search.status.option.unActive"/>"></i>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <div class="btn-list">
                            <button onclick="showEditLectureModal('${appContext}', ${lecturer.id})"
                                    type="button" class="btn btn-sm bg-warning text-white" data-toggle="tooltip"
                                    data-placement="bottom"
                                    title="<spring:message code="lecturer.table.list.button.edit"/>">
                                <i class="icon-copy dw dw-edit1"></i>
                            </button>
                            <button onclick="deleteLecturerItem('${appContext}', ${lecturer.id})"
                                    type="button" class="btn btn-sm bg-danger text-white" data-toggle="tooltip"
                                    data-placement="bottom" title="<spring:message code="lecturer.table.list.button.delete"/>">
                                <i class="icon-copy dw dw-delete-3"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${lecturers.size() == 0}">
            <tr>
                <td colspan="8" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">
                        <spring:message code="lecturer.table.list.data.empty"/>
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
<div class="modal fade bs-example-modal-lg" id="modal-add-edit-lecturer" tabindex="-1" data-focus="false" role="dialog"
     aria-labelledby="myModalAddAndEditLecturer" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditLecturer"></h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>

            </div>
            <div class="modal-body">
                <form id="form-add-edit-lecturer">
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
                                <spring:message code="lecturer.modal.chooseImageButton.label"/>
                                <input type="file" id="file" name="file" accept="image/*" hidden>
                            </label>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="lecturer.modal.code.label"/>
                                <span  class="text-danger">(*)</span></label>
                            <input name="code" id="code" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="lecturer.modal.fullName.label"/>
                                <span class="text-danger">(*)</span></label>
                            <input name="fullName" id="fullName" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="lecturer.modal.email"/>
                                <span class="text-danger">(*)</span></label>
                            <input name="email" id="email" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="lecturer.modal.phone"/>
                                <span class="text-danger">(*)</span></label>
                            <input name="phone" id="phone" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="lecturer.modal.birthday"/>
                                <span  class="text-danger">(*)</span></label>
                            <input name="birthday" id="birthday" type="date" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="lecturer.modal.gender"/>
                                <span class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select form-control"
                                        name="gender" id="gender" style="width: 100%;">
                                    <option value="1">
                                        <spring:message code="lecturer.table.list.data.option.gender.male"/>
                                    </option>
                                    <option value="2">
                                        <spring:message code="lecturer.table.list.data.option.gender.female"/>
                                    </option>
                                    <option value="3">
                                        <spring:message code="lecturer.table.list.data.option.gender.other"/>
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="lecturer.modal.address"/>
                                <span  class="text-danger">(*)</span></label>
                            <input name="address" id="address" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="lecturer.modal.position"/>
                                <span class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select2 form-control"
                                        name="position" id="position" style="width: 100%;">
                                    <c:forEach var="positionOption" items="${positionOptions}">
                                        <option value="${positionOption[0]}">${positionOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="lecturer.modal.department"/>
                                <span class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select2 form-control"
                                        name="department" id="department" style="width: 100%;">
                                    <c:forEach var="departmentOption" items="${departmentOptions}">
                                        <option value="${departmentOption[0]}">${departmentOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="lecturer.modal.role"/>
                                <span class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select2 form-control"
                                        name="role" id="role" style="width: 100%;">
                                    <c:forEach var="roleOption" items="${roleOptions}">
                                        <option value="${roleOption[0]}">${roleOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div id="new-password-area"></div>
                        <div class="form-group">
                            <div class="custom-control custom-checkbox mb-5">
                                <input type="checkbox" class="custom-control-input" id="is-active" name="is-active">
                                <label class="custom-control-label" for="is-active">
                                    <spring:message code="lecturer.modal.active"/>
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
<div class="modal fade bs-example-modal-lg " id="modal-add-import-lecturer" tabindex="-1" role="dialog"
     aria-labelledby="myModalImportLecturer" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalImportLecturer">
                    <spring:message code="lecturer.modal.import.title"/>
                </h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form name="form-upload-file">
                    <div class="pd-10">
                        <div class="form-group">
                            <div class="alert alert-warning" role="alert">
                                <h5 class="alert-heading h4">
                                    <spring:message code="lecturer.modal.import.subTitle"/>
                                </h5>
                                <ul type="circle">
                                    <li>- <spring:message code="lecturer.modal.import.labelOne"/></li>
                                    <li>- <spring:message code="lecturer.modal.import.labelTwo"/></li>
                                    <li>- <spring:message code="lecturer.modal.import.labelThree"/></li>
                                </ul>
                                <hr>
                                <a href="<c:url value="/public/excel-file/lecturers-example.xlsx"/> ">
                                    <i class="icon-copy fa fa-download" aria-hidden="true"></i>
                                    <spring:message code="lecturer.modal.import.fileExample"/>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="lecturer.modal.import.file.label"/> <span class="text-danger">(*)</span>
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