<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url value="/public/common/images/avatars/avatar-default.jpg" var="avatarDefault"/>
<c:url var="filterManage" value=""/>
<c:url var="home" value="/admin/"/>
<c:url var="appContext" value="/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>
                    <spring:message code="manage.header.title.label"/>
                </h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">
                        <spring:message code="layout.home.label"/>
                    </a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="manage.header.title.label"/>
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
            <div class="col-md-4 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text"
                           placeholder="<spring:message code="manage.table.search.keyword.label"/>"
                           name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="active" id="active"
                            style="width: 100%; height: 38px;">
                        <option value="${""}"><spring:message code="manage.table.search.status"/></option>
                        <option value="${true}">
                            <spring:message code="manage.table.search.status.option.active"/>
                        </option>
                        <option value="${false}">
                            <spring:message code="manage.table.search.status.option.unActive"/>
                        </option>
                    </select>
                </div>
            </div>

            <div class="col-md-3 col-sm-12">
                <div>
                    <button class="form-control ml-1 btn-warning btn" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i>
                        <spring:message code="manage.table.search.button.label"/>
                    </button>
                </div>
            </div>

        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">
                <spring:message code="manage.table.list.title.label"/>
            </h4>
        </div>
        <sec:authorize access="hasAuthority('ADMIN')">
            <div class="pull-right">
                <button onclick="showAddManageModal('${appContext}')"
                        type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                    <spring:message code="manage.table.list.button.addManage.label"/>
                </button>
            </div>
        </sec:authorize>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center col-1">
                <spring:message code="manage.table.list.header.image"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="manage.table.list.header.username"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="manage.table.list.header.info"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="manage.table.list.header.active"/>
            </th>
            <th scope="col">
                <spring:message code="manage.table.list.header.role"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="manage.table.list.header.action"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${manages.size() != 0}">
            <c:forEach var="manage" items="${manages}">
                <tr>
                    <th scope="row" class="text-center">
                        <c:if test="${manage.user.avatar == null}">
                            <img src="${avatarDefault}" style="border-radius: 50%;" height="70" width="70" alt="">
                        </c:if>
                        <c:if test="${manage.user.avatar != null}">
                            <img src="${manage.user.avatar}" style="border-radius: 50%;" height="70" width="70" alt="">
                        </c:if>
                    </th>
                    <td class="text-center">${manage.user.username}</td>
                    <td>
                        <ul style="list-style-type: circle; list-style-position: inside; ">
                            <li>${manage.fullName}</li>
                            <li>${manage.email}</li>
                            <li>${manage.phone}</li>
                            <a onclick="changePassword('${appContext}', ${manage.user.id})"
                               href="javascript:;"
                               class="ml-2 text-blue">
                                <i class="icon-copy fa fa-key" aria-hidden="true"></i>
                                <spring:message code="manage.table.list.data.option.changePassword.label"/></a>
                        </ul>
                    </td>
                    <td class="text-center">
                        <c:if test="${manage.user.active == true}">
                            <i class="icon-copy fa fa-check-circle-o text-success" aria-hidden="true"
                               data-toggle="tooltip" data-placement="bottom"
                               title="<spring:message code="manage.table.search.status.option.active"/>"></i>
                        </c:if>
                        <c:if test="${manage.user.active != true}">
                            <i class="icon-copy fa fa-times-circle-o text-danger" aria-hidden="true"
                               data-toggle="tooltip"
                               data-placement="bottom"
                               title="<spring:message code="manage.table.search.status.option.unActive"/>"></i>
                        </c:if>
                    </td>
                    <td class="text-light-purple font-weight-bold">${manage.user.role.roleName}</td>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <td class="text-center">
                            <div class="btn-list">
                                <button onclick="showEditManageModal('${appContext}',
                                    ${manage.id})"
                                        type="button" class="btn btn-sm bg-warning text-white"
                                        data-toggle="tooltip"
                                        data-placement="bottom"
                                        title="<spring:message code="manage.table.list.button.edit"/>">
                                    <i class="icon-copy dw dw-edit1"></i>
                                </button>
                                <button onclick="deleteManageItem('${appContext}', ${manage.id})"
                                        type="button" class="btn btn-sm bg-danger text-white"
                                        data-toggle="tooltip"
                                        data-placement="bottom"
                                        title="<spring:message code="manage.table.list.button.delete"/>">
                                    <i class="icon-copy dw dw-delete-3"></i>
                                </button>
                            </div>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${manages.size() == 0}">
            <tr>
                <td colspan="6" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center"><spring:message code="manage.table.list.data.empty"/></p>
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
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-manage" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditManage" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditManage"></h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>
            </div>

            <div class="modal-body">
                <form id="form-add-edit-manage">
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
                                <spring:message code="manage.modal.chooseImageButton.label"/>
                                <input type="file" id="file" name="file" accept="image/*" hidden>
                            </label>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="manage.modal.username.label"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="username" id="username" type="text" class="form-control" autocomplete="off">
                        </div>
                        <div class="form-group" id="password-area">

                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="manage.modal.fullName"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="fullName" id="fullName" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="manage.modal.email"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="email" id="email" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="manage.modal.phone"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="phone" id="phone" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <div class="custom-control custom-checkbox mb-5">
                                <input type="checkbox" class="custom-control-input" name="is-active" id="is-active">
                                <label class="custom-control-label" for="is-active">
                                    <spring:message code="manage.modal.active"/>
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