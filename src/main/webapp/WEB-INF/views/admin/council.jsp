<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="filterCouncil" value=""/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>Hội đồng</h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Hội đồng
                    </li>
                </ol>
            </nav>
        </div>
    </div>
</div>


<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterCouncil}">
        <input name="page" id="page" hidden/>
        <div class="row justify-content-end mt-2">
            <div class="col-md-6 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text" placeholder="Mã khóa luận, chủ đề..."
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
                    <select class="custom-select2 form-control" name="departmentId" id="departmentId"
                            style="width: 100%">
                        <option value="${""}">Tất cả khoa</option>
                        <c:forEach var="departmentOption" items="${departmentOptions}">
                            <option value="${departmentOption[0]}">${departmentOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="result" id="result"
                            style="width: 100%">
                        <option value="">Tất cả kết quả</option>
                        <option value="1">Chưa có kết quả</option>
                        <option value="2">Chưa đạt</option>
                        <option value="3">Đạt</option>
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
        <div class="pull-right">
            <button onclick="showAddCouncilModal('<c:url value="/admin/api/councils"/>')"
                    type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thành lập hội đồng
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">Tên hội đồng</th>
            <th scope="col" class="text-center">Mô tả</th>
            <th scope="col" class="text-center">Niên khóa</th>
            <th scope="col" class="text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${councils.size() != 0}">
            <c:forEach var="council" items="${councils}">
                <tr>
                    <td>
                            ${council.name}
                    </td>
                    <td>
                        <c:if test="${council.description != null && !council.description.isEmpty()}">
                            ${council.description}
                        </c:if>
                        <c:if test="${council.description == null || council.description.isEmpty()}">
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${council.schoolYear != null}">
                            ${council.schoolYear.name}
                        </c:if>
                        <c:if test="${council.schoolYear == null}">
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <div class="btn-list">
                            <button type="button" class="btn btn-sm bg-success text-white" data-toggle="tooltip"
                                    data-placement="bottom" title="Khóa hội đồng">
                                <i class="icon-copy fa fa-unlock" aria-hidden="true"></i>
                            </button>
                            <span class="text-black-50">|</span>
                            <button type="button" class="btn btn-sm bg-info text-white" data-toggle="tooltip"
                                    data-placement="bottom" title="Xem chi tiết">
                                <i class="icon-copy dw dw-eye"></i>
                            </button>
                            <button onclick="showEditThesisModal('<c:url
                                    value="/admin/api/councils/${council.id}"/>', ${council.id})"
                                    type="button" class="btn btn-sm bg-warning text-white" data-toggle="tooltip"
                                    data-placement="bottom" title="Cập nhật">
                                <i class="icon-copy dw dw-edit1"></i>
                            </button>
                            <button onclick="deleteCouncilItem('<c:url
                                    value="/admin/api/councils/${council.id}"/>')"
                                    type="button" class="btn btn-sm bg-danger text-white" data-toggle="tooltip"
                                    data-placement="bottom" title="Xóa">
                                <i class="icon-copy dw dw-delete-3"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${councils.size() == 0}">
            <tr>
                <td colspan="11" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png" alt="empty"/>
                    <p class="text-center">Danh sách hội đồng trống</p>
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
<div class="modal fade bs-example-modal-lg" data-focus="false" id="modal-add-edit-council" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditCouncil" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditCouncil"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

            </div>

            <div class="modal-body">
                <form id="form-add-edit-council">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Tên hội đồng<span
                                    class="text-danger">(*)</span></label>
                            <input name="name" id="name" type="text" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Mô tả</label>
                            <textarea name="description" id="description" class="form-control"></textarea>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Niên khóa<span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select form-control"
                                        name="schoolYear" id="schoolYear" style="width: 100%; ">
                                    <c:forEach var="schoolYearOption" items="${schoolYearOptions}">
                                        <option value="${schoolYearOption[0]}">${schoolYearOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Khóa luận bảo vệ<span
                                    class="text-danger">(*)</span></label>
                            <select name="theses" id="theses"
                                    class="custom-select2 form-control" multiple="multiple" style="width: 100%;">
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Thành viên hội đồng bảo vệ<span
                                    class="text-danger">(*)</span></label>
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th scope="col" class="col-6">Chức vụ</th>
                                    <th scope="col">Giảng viên</th>
                                    <th scope="col" class="col-1"></th>
                                </tr>
                                </thead>
                                <tbody id="member-area">
                                <tr>
                                    <td colspan="3">
                                        <div class="add-more-task" id="add-more-task">
                                            <a href="javascript:;" onclick="addMemberItem()"><i
                                                    class="ion-plus-circled"></i>
                                                Thêm thành viên</a>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <p id="councilDetails" class="text-danger mt-0" ></p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
                <button type="button" class="btn btn-success" id="btn-submit-form">
                    <i class="micon fa fa-save"> </i> Lưu dữ liệu
                </button>
            </div>
        </div>
    </div>
</div>
<!-- ADD and EDIT modal -->

<%--<script>--%>
<%--  let currentPage = ${page};--%>
<%--  let totalPage = ${totalPage};--%>
<%--  let pageSize = ${pageSize};--%>

<%--  $('#pagination').twbsPagination({--%>
<%--    totalPages: Math.ceil(totalPage / pageSize),--%>
<%--    visiblePages: 8,--%>
<%--    first: '',--%>
<%--    last: '',--%>
<%--    prev: '&laquo;',--%>
<%--    next: '&raquo;',--%>
<%--    startPage: currentPage,--%>
<%--    onPageClick: function (event, page) {--%>
<%--      if (currentPage != page) {--%>
<%--        $("#page").val(page)--%>
<%--        $("#form-filter").submit();--%>
<%--      }--%>
<%--    }--%>
<%--  });--%>
<%--</script>--%>