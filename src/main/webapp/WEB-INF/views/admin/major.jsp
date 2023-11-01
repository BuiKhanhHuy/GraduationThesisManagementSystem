<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="filterMajor" value="/admin/majors/"/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>
                    <spring:message code="major.header.title.label"/>
                </h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">
                        <spring:message code="layout.home.label"/>
                    </a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="major.header.title.label"/>
                    </li>
                </ol>
            </nav>
        </div>
    </div>
</div>

<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterMajor}">
        <input name="page" id="page" hidden/>
        <div class="row justify-content-end mt-2">
            <div class="col-md-4 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text"
                           placeholder="<spring:message code="major.table.search.keyword.label"/>"
                           name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div>
                    <button class="form-control ml-1 btn-warning btn" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i>
                        <spring:message code="major.table.search.button.label"/>
                    </button>
                </div>
            </div>
        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">
                <spring:message code="major.table.list.title.label"/>
            </h4>
        </div>
        <sec:authorize access="hasAuthority('ADMIN')">
            <div class="pull-right">
                <button
                        onclick="showAddMajorModal('<c:url value="/admin/api/majors"/>')"
                        type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                    <spring:message code="major.table.list.button.addDepartment.label"/>
                </button>
            </div>
        </sec:authorize>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">
                <spring:message code="major.table.list.header.departmentCode"/>
            </th>
            <th scope="col">
                <spring:message code="major.table.list.header.departmentName"/>
            </th>
            <th scope="col">
                <spring:message code="major.table.list.header.department"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="major.table.list.header.action"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${majors.size() != 0}">
            <c:forEach var="major" items="${majors}">
                <tr>
                    <td>${major.code}</td>
                    <td>${major.name}</td>
                    <td>
                        <c:if test="${major.department != null}">
                            ${major.department.name}
                        </c:if>
                        <c:if test="${major.department == null}">
                            <span class="text-black-50 text-center">
                                <spring:message code="major.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <div class="btn-list ">
                            <button
                                    onclick="showViewMajorModal('<c:url value="/admin/api/majors/${major.id}"/> ')"
                                    type="button" class="btn btn-sm bg-info text-white"
                                    data-toggle="tooltip"
                                    data-placement="bottom" title="<spring:message code="major.table.list.button.viewDetail"/>">
                                <i class="icon-copy dw dw-eye"></i>
                            </button>
                            <sec:authorize access="hasAuthority('ADMIN')">
                                <button onclick="showEditMajorModal('<c:url
                                        value="/admin/api/majors/${major.id}"/>', ${major.id})"
                                        type="button" class="btn btn-sm bg-warning text-white"
                                        data-toggle="tooltip"
                                        data-placement="bottom" title="<spring:message code="major.table.list.button.edit"/>">
                                    <i class="icon-copy dw dw-edit1"></i>
                                </button>
                                <button onclick="deleteMajorItem('<c:url value="/admin/api/majors/${major.id}"/>')"
                                        type="button" class="btn btn-sm bg-danger text-white"
                                        data-toggle="tooltip"
                                        data-placement="bottom" title="<spring:message code="major.table.list.button.delete"/>">
                                    <i class="icon-copy dw dw-delete-3"></i>
                                </button>
                            </sec:authorize>
                        </div>

                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${majors.size() == 0}">
            <tr>
                <td colspan="6" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center"><spring:message code="major.table.list.data.empty"/></p>
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
<div class="modal fade bs-example-modal-lg " data-focus="false" id="modal-add-edit-major" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditMajor" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditMajor"></h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form id="form-add-edit-major">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="major.modal.departmentCode.label"/>
                                <span class="text-danger">(*)</span></label>
                            <input name="code" id="code" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="major.modal.departmentName.label"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="name" id="name" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="major.modal.department.label"/><span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select2 form-control"
                                        name="department" id="department" style="width: 100%; ">
                                    <c:forEach var="departmentOption" items="${departmentOptions}">
                                        <option value="${departmentOption[0]}">${departmentOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="major.modal.description.label"/>
                            </label>
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
                    <i class="micon fa fa-save"> </i>
                    <spring:message code="layout.button.saveData.label"/>
                </button>
            </div>
        </div>
    </div>
</div>
<!-- ADD and EDIT modal -->

<%--VIEW modal--%>
<div class="modal fade bs-example-modal-lg " id="modal-view-major" tabindex="-1" role="dialog"
     aria-labelledby="myModalViewMajor" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalViewMajor">
                    <spring:message code="major.modal.view.title.label"/>
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <div class="pd-10">
                    <h6 class="mb-10 text-danger">ID</h6>
                    <p id="data-id" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="major.modal.view.departmentCode.label"/>
                    </h6>
                    <p id="data-code" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="major.modal.view.departmentName.label"/>
                    </h6>
                    <p id="data-name" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="major.modal.view.department.label"/>
                    </h6>
                    <p id="data-department-name" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="major.modal.view.description.label"/>
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