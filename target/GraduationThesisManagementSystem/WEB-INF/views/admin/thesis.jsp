<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url var="filterThesis" value=""/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>
                    <spring:message code="thesis.header.title.label"/>
                </h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">
                        <spring:message code="layout.home.label"/>
                    </a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="thesis.header.title.label"/>
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
                           placeholder="<spring:message code="thesis.table.search.keyword.label"/>"
                           name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="schoolYearId" id="schoolYearId"
                            style="width: 100%">
                        <option value="${""}">
                            <spring:message code="thesis.table.search.schoolYear"/>
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
                            <spring:message code="thesis.table.search.major"/>
                        </option>
                        <c:forEach var="majorOption" items="${majorOptions}">
                            <option value="${majorOption[0]}">${majorOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="result" id="result"
                            style="width: 100%">
                        <option value="">
                            <spring:message code="thesis.table.search.result"/>
                        </option>
                        <option value="1">
                            <spring:message code="thesis.table.search.result.option.1"/>
                        </option>
                        <option value="2">
                            <spring:message code="thesis.table.search.result.option.2"/>
                        </option>
                        <option value="3">
                            <spring:message code="thesis.table.search.result.option.3"/>
                        </option>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div>
                    <button class="btn-warning btn form-control" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i>
                        <spring:message code="thesis.table.search.button.label"/>
                    </button>
                </div>
            </div>
        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">
                <spring:message code="thesis.table.list.title.label"/>
            </h4>
        </div>
        <div class="pull-right">
            <sec:authorize access="hasAnyAuthority('ADMIN', 'MINISTRY')">
                <button onclick="showAddThesisModal('<c:url value="/admin/api/theses"/>')"
                        type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                    <spring:message code="thesis.table.list.button.addThesis.label"/>
                </button>
            </sec:authorize>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">
                <spring:message code="thesis.table.list.header.code"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="thesis.table.list.header.topic"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="thesis.table.list.header.major"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="thesis.table.list.header.schoolYear"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="thesis.table.list.header.startDate"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="thesis.table.list.header.complateDate"/>
            </th>
            <th scope="col">
                <spring:message code="thesis.table.list.header.reportFileStatus"/>
            </th>
            <th scope="col">
                <spring:message code="thesis.table.list.header.totalScores"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="thesis.table.list.header.result"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="thesis.table.list.header.action"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${theses.size() != 0}">
            <c:forEach var="thesis" items="${theses}">
                <tr>
                    <td class="text-center">
                            ${thesis.code}
                    </td>
                    <td>
                        <c:if test="${thesis.topic != null}">
                            ${thesis.topic.name}
                        </c:if>
                        <c:if test="${thesis.topic == null}">
                            <span class="text-black-50 text-center">
                                 <spring:message code="thesis.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${thesis.major != null}">
                            ${thesis.major.name}
                        </c:if>
                        <c:if test="${thesis.major == null}">
                            <span class="text-black-50 text-center">
                                  <spring:message code="thesis.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${thesis.schoolYear != null}">
                            ${thesis.schoolYear.name}
                        </c:if>
                        <c:if test="${thesis.schoolYear == null}">
                            <span class="text-black-50 text-center">
                                  <spring:message code="thesis.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td>
                        <fmt:formatDate type="date" value="${thesis.startDate}"/>
                    </td>
                    <td>
                        <fmt:formatDate type="date" value="${thesis.complateDate}"/>
                    </td>
                    <td>
                        <c:if test="${thesis.reportFile == null || thesis.reportFile.isEmpty()}">
                            <span class="text-danger font-weight-bold">
                                 <spring:message code="thesis.table.list.header.reportFileStatus.notYetPaid"/>
                            </span>
                        </c:if>
                        <c:if test="${thesis.reportFile != null && !thesis.reportFile.isEmpty()}">
                            <span class="text-success font-weight-bold">
                                 <spring:message code="thesis.table.list.header.reportFileStatus.submitted"/>
                            </span>
                        </c:if>
                    </td>
                    <td>
                        <fmt:formatNumber type="number"
                                          groupingUsed="false" value="${thesis.totalScore}"/>
                    </td>
                    <td class="text-center">
                        <c:if test="${thesis.result == 1}">
                            <span class="text-warning font-weight-bold">
                                <spring:message code="thesis.table.search.result.option.1"/>
                            </span>
                        </c:if>
                        <c:if test="${thesis.result == 2}">
                            <span class="text-danger font-weight-bold">
                                 <spring:message code="thesis.table.search.result.option.2"/>
                            </span>
                        </c:if>
                        <c:if test="${thesis.result == 3}">
                            <span class="text-success font-weight-bold">
                                 <spring:message code="thesis.table.search.result.option.3"/>
                            </span>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <div class="btn-list">
                            <button onclick="showViewThesisModal('<c:url value="/admin/api/theses/${thesis.id}"/>')"
                                    type="button" class="btn btn-sm bg-info text-white" data-toggle="tooltip"
                                    data-placement="bottom"
                                    title=" <spring:message code="thesis.table.list.button.viewDetail"/>">
                                <i class="icon-copy dw dw-eye"></i>
                            </button>
                            <sec:authorize access="hasAnyAuthority('ADMIN', 'MINISTRY')">
                                <button onclick="showEditThesisModal('<c:url
                                        value="/admin/api/theses/${thesis.id}"/>', ${thesis.id})"
                                        type="button" class="btn btn-sm bg-warning text-white" data-toggle="tooltip"
                                        data-placement="bottom"
                                        title=" <spring:message code="thesis.table.list.button.edit"/>">
                                    <i class="icon-copy dw dw-edit1"></i>
                                </button>
                                <button onclick="deleteThesisItem('<c:url
                                        value="/admin/api/theses/${thesis.id}"/>')"
                                        type="button" class="btn btn-sm bg-danger text-white" data-toggle="tooltip"
                                        data-placement="bottom"
                                        title=" <spring:message code="thesis.table.list.button.delete"/>">
                                    <i class="icon-copy dw dw-delete-3"></i>
                                </button>
                            </sec:authorize>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${theses.size() == 0}">
            <tr>
                <td colspan="11" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png" alt="empty"/>
                    <p class="text-center">
                        <spring:message code="thesis.table.list.data.empty"/>
                    </p>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <c:if test="${Math.ceil(totalPage / pageSize) > 1}">
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
<div class="modal fade bs-example-modal-lg" data-focus="false" id="modal-add-edit-thesis" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditThesis" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditThesis"></h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>

            </div>

            <div class="modal-body">
                <form id="form-add-edit-thesis">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.code.label"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="code" id="code" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.topic"/><span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select2 form-control w-100"
                                        name="topic" id="topic" style="width: 100%; ">
                                    <c:forEach var="topicOption" items="${topicOptions}">
                                        <option value="${topicOption[0]}">${topicOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.startDate"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="startDate" id="startDate" type="date" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.complateDate"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="complateDate" id="complateDate" type="date" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.thesisStartDate"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="thesisStartDate" id="thesisStartDate" type="date"
                                   class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.thesisEndDate"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="thesisEndDate" id="thesisEndDate" type="date" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.major"/><span
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
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.schoolYear"/><span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select2 form-control"
                                        name="schoolYear" id="schoolYear" style="width: 100%; ">
                                    <c:forEach var="schoolYearOption" items="${schoolYearOptions}">
                                        <option value="${schoolYearOption[0]}">${schoolYearOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.students"/><span
                                    class="text-danger">(*)</span></label>
                            <select name="students" id="students"
                                    class="custom-select2 form-control" multiple="multiple" style="width: 100%;">
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.lecturers"/><span
                                    class="text-danger">(*)</span></label>
                            <select name="lecturers" id="lecturers"
                                    class="custom-select2 form-control" multiple="multiple" style="width: 100%;">
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.reviewLecturer"/><span
                                    class="text-danger">(*)</span></label>
                            <select name="reviewLecturer" id="reviewLecturer"
                                    class="custom-select2 form-control" style="width: 100%;">
                            </select>
                            <span></span>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="thesis.modal.comment"/>
                            </label>
                            <textarea name="comment" id="comment" class="textarea_editor form-control border-radius-0"
                                      placeholder="Viết nội dung đánh giá tại đây..."></textarea>
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
<div class="modal fade bs-example-modal-lg " id="modal-view-thesis" tabindex="-1" role="dialog"
     aria-labelledby="myModalViewThesis" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalViewThesis">Chi tiết khóa luận</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body" id="body-content">
                <div class="pd-20">
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.table.list.header.code"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-code"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.table.list.header.topic"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-topic"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.table.list.students"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-students"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.table.list.lecturers"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-lecturers"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.table.list.reviewLecturer"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-review-lecturer"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.table.list.header.startDate"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-start-date"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.table.list.header.complateDate"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-completed-date"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.modal.thesisStartDate"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-thesis-start-date"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.modal.thesisEndDate"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-thesis-end-date"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.modal.major"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-major"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.modal.schoolYear"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-school-year"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.modal.comment"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-comment"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.table.list.header.totalScores"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-total-score"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.table.list.header.result"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-result"></div>
                    <h6 class="mb-10 text-danger">
                        <spring:message code="thesis.table.list.reportFile"/>
                    </h6>
                    <div class="ml-1 mb-3" id="data-report-file"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
            </div>
        </div>
    </div>
</div>
<%--VIEW modal--%>

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