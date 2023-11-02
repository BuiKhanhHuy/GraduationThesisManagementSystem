<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="filterNews" value=""/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>
                    <spring:message code="news.header.title.label"/>
                </h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">
                        <spring:message code="layout.home.label"/>
                    </a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="news.header.title.label"/>
                    </li>
                </ol>
            </nav>
        </div>
    </div>
</div>


<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterNews}">
        <input name="page" id="page" hidden/>
        <div class="row justify-content-end mt-2">
            <div class="col-md-4 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text"
                           placeholder="<spring:message code="news.table.search.keyword.label"/>"
                           name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div>
                    <button class="form-control ml-1 btn-warning btn" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i>
                        <spring:message code="news.table.search.button.label"/>
                    </button>
                </div>
            </div>
        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">
                <spring:message code="news.table.list.title.label"/>
            </h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddNewsModal('<c:url value="/admin/api/news"/>')"
                    type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                <spring:message code="news.table.list.button.addNews.label"/>
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">
                <spring:message code="news.table.list.header.newsTitle"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="news.table.list.header.newsCreator"/>
            </th>
            <th scope="col" class="col-2 text-center">
                <spring:message code="news.table.list.header.action"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${news.size() != 0}">
            <c:forEach var="n" items="${news}">
                <tr>
                    <td>${n.title}</td>
                    <td class="text-center font-weight-bold text-danger">
                        <c:if test="${n.user != null}">
                            ${n.user.username}
                        </c:if>
                        <c:if test="${n.user == null}">
                            <span class="text-black-50 text-center">
                                <spring:message code="news.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <div class="btn-list">
                            <button onclick="showViewNewsModal('<c:url value="/admin/api/news/${n.id}"/>')"
                                    type="button" class="btn btn-sm bg-info text-white"
                                    data-toggle="tooltip"
                                    data-placement="bottom"
                                    title="<spring:message code="news.table.list.button.viewDetail"/>">
                                <i class="icon-copy dw dw-eye"></i>
                            </button>
                            <button onclick="showEditNewsModal('<c:url
                                    value="/admin/api/news/${n.id}/single"/>', ${n.id})"
                                    type="button" class="btn btn-sm bg-warning text-white"
                                    data-toggle="tooltip"
                                    data-placement="bottom"
                                    title="<spring:message code="news.table.list.button.update"/>">
                                <i class="icon-copy dw dw-edit1"></i>
                            </button>
                            <button onclick="deleteNewsItem('<c:url value="/admin/api/news/${n.id}"/>')"
                                    type="button" class="btn btn-sm bg-danger text-white"
                                    data-toggle="tooltip"
                                    data-placement="bottom"
                                    title="<spring:message code="news.table.list.button.delete"/>">
                                <i class="icon-copy dw dw-delete-3"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${news.size() == 0}">
            <tr>
                <td colspan="6" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">
                        <spring:message code="news.table.list.data.empty"/>
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


<%--VIEW modal--%>
<div class="modal fade bs-example-modal-lg " id="modal-view-news" tabindex="-1" role="dialog"
     aria-labelledby="myModalViewNews" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalViewNews">#<span id="data-id"></span> <span id="data-title"></span>
                </h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <div class="pd-10">
                    <div class="media">
                        <img class="mr-3 rounded" width="60" height="60" id="data-avatar"
                             src="#"
                             alt="avatar">
                    </div>
                    <h6 class="mb-3 text-black-50 mt-2"> <spring:message code="news.modal.view.creator.label"/>: <span class="text-danger"
                                                                         id="data-user-username"></span></h6>
                    <div class="text-black-50 font-italic" id="data-createdDate"></div>
                    <hr>
                    <div>
                        <h6> <spring:message code="news.modal.view.content.label"/>:</h6>
                        <div class="mt-3" id="data-content">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary close-custom" data-dismiss="modal">Thoát</button>
            </div>
        </div>
    </div>
</div>
<%--VIEW modal--%>


<!-- ADD and EDIT modal -->
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-news" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditNews" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditNews"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

            </div>

            <div class="modal-body">
                <form id="form-add-edit-news">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="news.modal.newsTitle.label"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="title" id="title" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="news.modal.newsContent.label"/><span
                                    class="text-danger">(*)</span></label>
                            <textarea name="content" id="content" class="textarea_editor form-control border-radius-0"
                                      placeholder="Viết nội dung tại đây..."></textarea>
                            <span id="error-content" class="text-danger"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
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