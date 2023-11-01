<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="filterDeparment" value="/admin/departments/"/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4><spring:message code="department.header.title.label"/></h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">
                        <spring:message code="layout.home.label"/>
                    </a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="department.header.title.label"/>
                    </li>
                </ol>
            </nav>
        </div>
    </div>
</div>

<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterDeparment}">
        <input name="page" id="page" hidden/>
        <div class="row justify-content-end mt-2">
            <div class="col-md-4 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text"
                           placeholder="<spring:message code="department.table.search.keyword.label"/>" name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div>
                    <button class="form-control ml-1 btn-warning btn" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i>
                        <spring:message code="department.table.search.button.label"/>
                    </button>
                </div>
            </div>
        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">
                <spring:message code="department.table.list.title.label"/>
            </h4>
        </div>
        <sec:authorize access="hasAuthority('ADMIN')">
            <div class="pull-right">
                <button onclick="showAddDepartmentModal('<c:url value="/admin/api/departments"/>')"
                        class="btn btn-success btn-md"><i
                        class="micon icon-copy dw dw-add"></i>
                    <spring:message code="department.table.list.button.addDepartment.label"/>
                </button>
            </div>
        </sec:authorize>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">
                <spring:message code="department.table.list.header.departmentCode"/>
            </th>
            <th scope="col">
                <spring:message code="department.table.list.header.departmentName"/>
            </th>
            <th scope="col" class="text-center col-2">
                <spring:message code="department.table.list.header.founding"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="department.table.list.header.action"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${departments.size() > 0}">
            <c:forEach var="department" items="${departments}">
                <tr>
                    <td>${department.code}</td>
                    <td>${department.name}</td>
                    <td class="text-center">
                        <fmt:formatDate type="date" value="${department.founding}"/>
                    </td>
                    <td class=" text-center">
                        <div class="btn-list">
                            <button onclick="showViewDepartmentModal('<c:url
                                    value="/admin/api/departments/${department.id}"/>')"
                                    type="button" class="btn btn-sm bg-info text-white"
                                    data-toggle="tooltip"
                                    data-placement="bottom" title="<spring:message code="department.table.list.button.viewDetail"/>">
                                <i class="icon-copy dw dw-eye"></i>
                            </button>
                            <sec:authorize access="hasAuthority('ADMIN')">
                                <button onclick="showEditDepartmentModal('<c:url
                                        value="/admin/api/departments/${department.id}"/>', ${department.id})"
                                        type="button" class="btn btn-sm bg-warning text-white"
                                        data-toggle="tooltip"
                                        data-placement="bottom" title="<spring:message code="department.table.list.button.edit"/>">
                                    <i class="icon-copy dw dw-edit1"></i>
                                </button>
                                <button onclick="deleteDepartmentItem('<c:url
                                        value="/admin/api/departments/${department.id}"/>')"
                                        type="button" class="btn btn-sm bg-danger text-white"
                                        data-toggle="tooltip"
                                        data-placement="bottom" title="<spring:message code="department.table.list.button.delete"/>">
                                    <i class="icon-copy dw dw-delete-3"></i>
                                </button>
                            </sec:authorize>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${departments.size() == 0}">
            <tr>
                <td colspan="6" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">
                        <spring:message code="department.table.list.data.empty"/>
                    </p>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <c:if test="${Math.ceil(totalResult / pageSize) > 1}">
        <div class="blog-pagination pagination-md mt-5 mb-2">
            <div class="btn-toolbar justify-content-center">
                <div class="btn-group">
                    <nav aria-label="Page navigation">
                        <ul class="pagination" id="pagination"></ul>
                    </nav>
                </div>
            </div>
        </div>
    </c:if>
</div>
<!-- table End -->

<!-- ADD and EDIT modal -->
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-department" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditDepartment" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditDepartment"></h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form id="form-add-edit-department">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="department.modal.departmentCode.label"/><span class="text-danger">(*)</span></label>
                            <input name="code" id="code" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold"> <spring:message code="department.modal.departmentName.label"/> <span
                                    class="text-danger">(*)</span></label>
                            <input name="name" id="name" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold"><spring:message code="department.modal.founding.label"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="founding" id="founding" type="date" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold"><spring:message code="department.modal.description.label"/></label>
                            <textarea name="description" id="description" class="form-control"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary close-custom" data-dismiss="modal">
                    <spring:message code="layout.button.cancel.label"/>
                </button>
                <button type="button" class="btn btn-success" id="btn-submit-form">
                    <i class="micon fa fa-save"> </i> <spring:message code="layout.button.saveData.label"/>
                </button>
            </div>
        </div>
    </div>
</div>
<!-- ADD and EDIT modal -->

<%--VIEW modal--%>
<div class="modal fade bs-example-modal-lg " id="modal-view-department" tabindex="-1" role="dialog"
     aria-labelledby="myModalViewDepartment" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalViewDepartment">
                    <spring:message code="department.modal.view.title.label"/>
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <div class="pd-10">
                    <h6 class="mb-10 text-danger">ID</h6>
                    <p id="data-id" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="department.modal.view.departmentCode.label"/>
                    </h6>
                    <p id="data-code" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="department.modal.view.departmentName.label"/>
                    </h6>
                    <p id="data-name" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="department.modal.view.founding.label"/>
                    </h6>
                    <p id="data-founding" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="department.modal.view.description.label"/>
                    </h6>
                    <p id="data-description" class="ml-1"></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <spring:message code="layout.button.cancel.label"/>
                </button>
            </div>
        </div>
    </div>
</div>
<%--VIEW modal--%>

<script>
    let currentPage = ${page};
    let totalResult = ${totalResult};
    let pageSize = ${pageSize};


    $('#pagination').twbsPagination({
        totalPages: Math.ceil(totalResult / pageSize),
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