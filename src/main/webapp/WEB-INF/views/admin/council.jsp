<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="filterCouncil" value=""/>
<c:url var="home" value="/admin/"/>
<c:url var="appContext" value="/"/>

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
        <div class="pull-right">
            <button onclick="showAddCouncilModal('${appContext}')"
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
                            <c:if test="${council.block == true}">
                                <button onclick="lockOrUnLockCouncil('${appContext}', ${council.id}, ${!council.block})"
                                        type="button" class="btn btn-sm btn-danger" data-toggle="tooltip">
                                    <i class="icon-copy dw dw-lock font-weight-bold"></i> Mở khóa hội đồng
                                </button>
                            </c:if>
                            <c:if test="${council.block != true}">
                                <button onclick="lockOrUnLockCouncil('${appContext}', ${council.id}, ${!council.block})"
                                        type="button" class="btn btn-sm btn-success" data-toggle="tooltip">
                                    <i class="icon-copy dw dw-unlock font-weight-bold"></i> Khóa hội đồng
                                </button>
                            </c:if>
                            |
                            <button onclick="showViewCouncilModal('${appContext}', ${council.id})"
                                    type="button" class="btn btn-sm bg-info text-white" data-toggle="tooltip"
                                    data-placement="bottom" title="Xem chi tiết">
                                <i class="icon-copy dw dw-eye"></i>
                            </button>
                            <button onclick="deleteCouncilItem('${appContext}', ${council.id})"
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
<div class="modal fade bs-example-modal-lg" data-focus="false" id="modal-add-edit-council" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditCouncil" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditCouncil"></h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>
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
                                </tbody>
                            </table>
                            <p id="councilDetails" class="text-danger mt-0"></p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary close-custom" data-dismiss="modal">Thoát</button>
                <button type="button" class="btn btn-success" id="btn-submit-form">
                    <i class="micon fa fa-save"> </i> Lưu dữ liệu
                </button>
            </div>
        </div>
    </div>
</div>
<!-- ADD and EDIT modal -->

<%--VIEW modal--%>
<div class="modal fade bs-example-modal-lg " id="modal-view-council" tabindex="-1" role="dialog"
     aria-labelledby="myModalViewCouncil" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalViewCouncil">Chi tiết hội đồng</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <div class="tab height-100-p">
                    <ul class="nav nav-tabs customtab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#timeline" role="tab">Thông tin chi tiết</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#tasks" role="tab">Điểm số</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <!-- Timeline Tab start -->
                        <div class="tab-pane fade show active" id="timeline" role="tabpanel">
                            <div class="pd-20">
                                <h6 class="mb-10 text-danger">Tên hội đồng</h6>
                                <div id="data-name" class="ml-1 mb-3"></div>
                                <h6 class="mb-10 text-danger">Mô tả</h6>
                                <div id="data-description" class="ml-1 mb-3"></div>
                                <h6 class="mb-10 text-danger">Niên khóa</h6>
                                <div id="data-schoolYear" class="ml-1 mb-3"></div>
                                <h6 class="mb-10 text-danger">Khóa luận bảo vệ</h6>
                                <div class="ml-1 mb-3">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th scope="col">Mã khóa luận</th>
                                            <th scope="col">Chủ đề</th>
                                        </tr>
                                        </thead>
                                        <tbody id="data-theses">
                                        </tbody>
                                    </table>
                                </div>
                                <h6 class="mb-10 text-danger">Thành viên hội đồng</h6>
                                <div class="ml-1 mb-3">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th scope="col">Chức vụ</th>
                                            <th scope="col">Tên thành viên</th>
                                        </tr>
                                        </thead>
                                        <tbody id="data-lecturers">
                                        </tbody>
                                    </table>
                                </div>
                                <h6 class="mb-10 text-danger">Trạng thái hội đồng</h6>
                                <div class="ml-1" id="data-block">
                                </div>
                            </div>
                        </div>
                        <!-- Timeline Tab End -->
                        <!-- Tasks Tab start -->
                        <div class="tab-pane fade" id="tasks" role="tabpanel">
                            <div class="pd-10" id="thesis-area">
                            </div>
                            <div class="pd-10">
                                <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">
                                    <i class="icon-copy fi-page-export-pdf"></i> Xuất file PDF
                                </button>
                                <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">
                                    <i class="icon-copy fi-page-export-csv"></i> Xuất file EXCEL
                                </button>
                            </div>
                        </div>
                        <!-- Tasks Tab End -->
                    </div>
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
            if (currentPage !== page) {
                $("#page").val(page)
                $("#form-filter").submit();
            }
        }
    });
</script>