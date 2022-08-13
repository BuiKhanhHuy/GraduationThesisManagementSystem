<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="filterCouncilDetail" value=""/>
<c:url var="home" value="/admin/"/>
<c:url var="appContext" value="/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>Hội đồng bảo vệ khóa luận</h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Hội đồng bảo vệ khóa luận
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
                    <input class="form-control" type="text" placeholder="Tên hội đồng"
                           name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="schoolYearId" id="schoolYearId"
                            style="width: 100%">
                        <option value="${""}">Tất cả niên khóa</option>
                        <c:forEach var="schoolYearOption" items="${schoolYearOptions}">
                            <option value="${schoolYearOption[0]}">${schoolYearOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="block" id="block"
                            style="width: 100%">
                        <option value="${""}">Tất cả trạng thái</option>
                        <option value="true">Đang khóa</option>
                        <option value="false">Đang mở</option>
                    </select>
                </div>
            </div>
            <div class="col-md-2 col-sm-12">
                <div>
                    <button class="btn-warning btn form-control" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i> Tìm kiếm
                    </button>
                </div>
            </div>
        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">Danh sách hội đồng</h4>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">Tên hội đồng</th>
            <th scope="col" class="text-center">Niên khóa</th>
            <th scope="col" class="text-center">Chức vụ trong hội đồng</th>
            <th scope="col" class="text-center">Trạng thái</th>
            <th scope="col" class="text-center">Hành động</th>
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
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${council[1] != null}">
                            ${council[1] }
                        </c:if>
                        <c:if test="${council[1]  == null}">
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td colspan="text-center">
                        <c:if test="${council[3] != true}">
                            <div class="text-center text-success">
                                <i class="icon-copy fa fa-unlock font-20" aria-hidden="true"
                                   data-toggle="tooltip"
                                   data-placement="bottom" title="Hội đồng đang được mở"></i>
                            </div>
                        </c:if>
                        <c:if test="${council[3] == true}">
                            <div class="text-center text-danger">
                                <i class="icon-copy fa fa-lock font-20" aria-hidden="true"
                                   data-toggle="tooltip"
                                   data-placement="bottom" title="Hội đồng đang bị khóa"></i>
                            </div>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <div class="btn-list">
                            <a href="<c:url value="/admin/councils-detail/${council[0]}"/>"
                               type="button" class="btn btn-sm bg-success text-white">
                                Chấm điểm khóa luận
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
                    <p class="text-center">Danh sách hội đồng trống</p>
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