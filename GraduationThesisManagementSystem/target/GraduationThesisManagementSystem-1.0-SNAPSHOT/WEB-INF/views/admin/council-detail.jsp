<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="filterCouncilDetail" value=""/>
<c:url var="home" value="/admin/"/>
<c:url var="appContext" value="/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>
                    <spring:message code="council.header.title.label"/>
                </h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">
                        <spring:message code="layout.home.label"/>
                    </a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="council.header.title.label"/>
                    </li>
                </ol>
            </nav>
        </div>
    </div>
</div>

<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterCouncilDetail}">
        <input name="page" id="page" hidden/>
        <div class="row justify-content-end mt-2">
            <div class="col-md-6 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text"
                           placeholder="<spring:message code="council.table.search.keyword.label"/>"
                           name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="schoolYearId" id="schoolYearId"
                            style="width: 100%">
                        <option value="${""}"><spring:message code="council.table.search.schoolYear"/></option>
                        <c:forEach var="schoolYearOption" items="${schoolYearOptions}">
                            <option value="${schoolYearOption[0]}">${schoolYearOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <label for="block"></label><select class="custom-select2 form-control" name="block" id="block"
                                                       style="width: 100%">
                        <option value="${""}">
                            <spring:message code="council.table.search.status"/>
                        </option>
                        <option value="true">
                            <spring:message code="council.table.search.status.close"/>
                        </option>
                        <option value="false">
                            <spring:message code="council.table.search.status.open"/>
                        </option>
                    </select>
                </div>
            </div>
            <div class="col-md-2 col-sm-12">
                <div>
                    <button class="btn-warning btn form-control" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i>
                        <spring:message code="council.table.search.button.label"/>
                    </button>
                </div>
            </div>
        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">
                <spring:message code="council.table.list.title.label"/>
            </h4>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center"><spring:message code="council.table.list.header.name"/></th>
            <th scope="col" class="text-center"><spring:message code="council.table.list.header.schoolYear"/></th>
            <th scope="col" class="text-center"><spring:message code="council.table.list.header.position"/></th>
            <th scope="col" class="text-center"><spring:message code="council.table.list.header.status"/></th>
            <th scope="col" class="text-center"><spring:message code="council.table.list.header.action"/></th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${councilsDetail.size() != 0}">
            <c:forEach var="council" items="${councilsDetail}">
                <tr>
                    <td>
                            ${council[2]}
                    </td>
                    <td>
                        <c:if test="${council[4] != null}">
                            ${council[4]}
                        </c:if>
                        <c:if test="${council[4] == null}">
                            <span class="text-black-50 text-center">
                                <spring:message code="council.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${council[1] != null}">
                            ${council[1] }
                        </c:if>
                        <c:if test="${council[1]  == null}">
                            <span class="text-black-50 text-center">
                                <spring:message code="council.table.list.cell.notUpdate"/>
                            </span>
                        </c:if>
                    </td>
                    <td colspan="text-center">
                        <c:if test="${council[3] != true}">
                            <div class="text-center text-success">
                                <i class="icon-copy fa fa-unlock font-20" aria-hidden="true"
                                   data-toggle="tooltip"
                                   data-placement="bottom"
                                   title="<spring:message code="council.table.list.header.status.unLock"/>"></i>
                            </div>
                        </c:if>
                        <c:if test="${council[3] == true}">
                            <div class="text-center text-danger">
                                <i class="icon-copy fa fa-lock font-20" aria-hidden="true"
                                   data-toggle="tooltip"
                                   data-placement="bottom"
                                   title="<spring:message code="council.table.list.header.status.lock"/>"></i>
                            </div>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <div class="btn-list">
                            <a href="<c:url value="/admin/councils-detail/${council[0]}"/>"
                               type="button" class="btn btn-sm bg-success text-white">
                                <spring:message code="council.button.score"/>
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${councilsDetail.size() == 0}">
            <tr>
                <td colspan="11" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png" alt="empty"/>
                    <p class="text-center"> <spring:message code="council.table.list.data.empty"/></p>
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
            if (currentPage !== page) {
                $("#page").val(page)
                $("#form-filter").submit();
            }
        }
    });
</script>