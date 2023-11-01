<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="filterTopic" value=""/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>
                    <spring:message code="topic.header.title.label"/>
                </h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">
                        <spring:message code="layout.home.label"/>
                    </a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="topic.header.title.label"/>
                    </li>
                </ol>
            </nav>
        </div>
    </div>
</div>


<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterTopic}">
        <input name="page" id="page" hidden/>
        <div class="row justify-content-end mt-2">
            <div class="col-md-4 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text"
                           placeholder="<spring:message code="topic.table.search.keyword.label"/>"
                           name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="departmentId" id="departmentId"
                            style="width: 100%; height: 38px;">
                        <option value="${""}">
                            <spring:message code="topic.table.search.department.label"/>
                        </option>
                        <c:forEach var="departmentOption" items="${departmentOptions}">
                            <option value="${departmentOption[0]}">${departmentOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="col-md-3 col-sm-12">
                <div>
                    <button class="form-control ml-1 btn-warning btn" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i>
                        <spring:message code="topic.table.search.button.label"/>
                    </button>
                </div>
            </div>

        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">
                <spring:message code="topic.table.list.title.label"/>
            </h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddTopicModal('<c:url value="/admin/api/topics"/>')"
                    type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                <spring:message code="topic.table.list.button.addTopic.label"/>
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">
                <spring:message code="topic.table.list.header.topicName"/>
            </th>
            <th scope="col">
                <spring:message code="topic.table.list.header.description"/>
            </th>
            <th scope="col">
                <spring:message code="topic.table.list.header.department"/>
            </th>
            <th scope="col" class="col-2 text-center">
                <spring:message code="topic.table.list.header.action"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${topics.size() != 0}">
            <c:forEach var="topic" items="${topics}">
                <tr>
                    <td>${topic.name}</td>
                    <td>${topic.description}</td>
                    <td>
                        <c:if test="${topic.department != null}">
                            ${topic.department.name}
                        </c:if>
                        <c:if test="${topic.department == null}">
                            <span class="text-black-50 text-center">
                                <spring:message code="topic.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td class="col-2 text-center">
                        <div class="btn-list">
                            <button onclick="showEditTopicModal('<c:url
                                    value="/admin/api/topics/${topic.id}"/>', ${topic.id})"
                                    type="button" class="btn btn-sm bg-warning text-white"
                                    data-toggle="tooltip"
                                    data-placement="bottom"
                                    title="<spring:message code="topic.table.list.button.edit"/>">
                                <i class="icon-copy dw dw-edit1"></i>
                            </button>
                            <button onclick="deleteTopicItem('<c:url value="/admin/api/topics/${topic.id}"/>')"
                                    type="button" class="btn btn-sm bg-danger text-white"
                                    data-toggle="tooltip"
                                    data-placement="bottom"
                                    title="<spring:message code="topic.table.list.button.delete"/>">
                                <i class="icon-copy dw dw-delete-3"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${topics.size() == 0}">
            <tr>
                <td colspan="5" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">
                        <spring:message code="topic.table.list.data.empty"/>
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
<div class="modal fade bs-example-modal-lg" id="modal-add-edit-topic" tabindex="-1" data-focus="false" role="dialog"
     aria-labelledby="myModalAddAndEditTopic" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditTopic"></h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form id="form-add-edit-topic">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="topic.modal.topicName.label"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="name" id="name" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="topic.modal.department.label"/><span
                                    class="text-danger">(*)</span></label>
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
                                <spring:message code="topic.modal.description.label"/>
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