<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-header">
    <div class="row">
        <div class="col-md-6 col-sm-12">
            <div class="title">
                <h4>Lọc</h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Form Basic</li>
                </ol>
            </nav>
        </div>
        <div class="col-md-6 col-sm-12 text-right">
            <div class="dropdown">
                <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-toggle="dropdown">
                    January 2018
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <a class="dropdown-item" href="#">Export List</a>
                    <a class="dropdown-item" href="#">Policies</a>
                    <a class="dropdown-item" href="#">View Assets</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- table start -->
<div class="pd-20 card-box mb-30">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">Danh sách khóa luận</h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddThesisModal('<c:url value="/admin/api/theses"/>')"
                    type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm khóa luận
            </button>
        </div>
    </div>
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col" class="text-center">Mã khóa luận</th>
                <th scope="col" class="text-center">Chủ đề</th>
                <th scope="col" class="text-center">Khoa</th>
                <th scope="col" class="text-center">Niên khóa</th>
                <th scope="col" class="text-center">Ngày bắt đầu</th>
                <th scope="col" class="text-center">Ngày kết thúc</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Tổng điểm</th>
                <th scope="col" class="text-center">Kết quả</th>
                <th scope="col" class="text-center">Hành động</th>
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
                                <span class="text-black-50 text-center">Chưa cập nhật</span>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${thesis.department != null}">
                                ${thesis.department.name}
                            </c:if>
                            <c:if test="${thesis.department == null}">
                                <span class="text-black-50 text-center">Chưa cập nhật</span>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${thesis.schoolYear != null}">
                                ${thesis.schoolYear.name}
                            </c:if>
                            <c:if test="${thesis.schoolYear == null}">
                                <span class="text-black-50 text-center">Chưa cập nhật</span>
                            </c:if>
                        </td>
                        <td>
                                ${thesis.startDate}
                        </td>
                        <td>
                                ${thesis.complateDate}
                        </td>
                        <td>
                            <c:if test="${thesis.status == 1}">
                                <span class="text-danger">Chưa nộp</span>
                            </c:if>
                            <c:if test="${thesis.status == 2}">
                                <span class="text-success">Đã nộp</span>
                            </c:if>
                            <c:if test="${thesis.status == 3}">
                                <span class="text-warning">Đã trả về chỉnh sửa</span>
                            </c:if>
                        </td>
                        <td>
                                ${thesis.totalScore}
                        </td>
                        <td class="text-center">
                            <c:if test="${thesis.result == 1}">
                                <span class="text-warning font-weight-bold">Chưa có kết quả</span>
                            </c:if>
                            <c:if test="${thesis.result == 2}">
                                <span class="text-danger font-weight-bold">Trượt</span>
                            </c:if>
                            <c:if test="${thesis.result == 3}">
                                <span class="text-success font-weight-bold">Đậu</span>
                            </c:if>
                        </td>
                        <td class="text-center">
                            <div class="btn-list">
                                <button type="button" class="btn btn-sm bg-info text-white" data-toggle="tooltip"
                                        data-placement="bottom" title="Xem chi tiết">
                                    <i class="icon-copy dw dw-eye"></i>
                                </button>
                                <button type="button" class="btn btn-sm bg-warning text-white" data-toggle="tooltip"
                                        data-placement="bottom" title="Chỉnh sửa">
                                    <i class="icon-copy dw dw-edit1"></i>
                                </button>
                                <button type="button" class="btn btn-sm bg-danger text-white" data-toggle="tooltip"
                                        data-placement="bottom" title="Xóa">
                                    <i class="icon-copy dw dw-delete-3"></i>
                                </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${theses.size() == 0}">
                <tr>
                    <td colspan="11" class="text-black-50 text-center">
                        <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png" alt="empty"/>
                        <p class="text-center">Danh sách khóa luận trống</p>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="blog-pagination pagination-sm mt-5 mb-2">
        <div class="btn-toolbar justify-content-center">
            <div class="btn-group">
                <a href="#" class="btn btn-outline-primary prev"><i class="fa fa-angle-double-left"></i></a>
                <a href="#" class="btn btn-outline-primary">1</a>
                <a href="#" class="btn btn-outline-primary">2</a>
                <span class="btn btn-primary current">3</span>
                <a href="#" class="btn btn-outline-primary">4</a>
                <a href="#" class="btn btn-outline-primary">5</a>
                <a href="#" class="btn btn-outline-primary next"><i class="fa fa-angle-double-right"></i></a>
            </div>
        </div>
    </div>
</div>
<!-- table End -->


<!-- ADD and EDIT modal -->
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-thesis" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditThesis" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditThesis"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

            </div>

            <div class="modal-body">
                <form id="form-add-edit-thesis">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Mã khóa luận<span
                                    class="text-danger">(*)</span></label>
                            <input name="code" id="code" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Chủ đề<span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select form-control w-100"
                                        name="topic" id="topic" style="width: 100%; ">
                                    <c:forEach var="topicOption" items="${topicOptions}">
                                        <option value="${topicOption[0]}">${topicOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Ngày bắt đầu<span
                                    class="text-danger">(*)</span></label>
                            <input name="startDate" id="startDate" type="datetime-local" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Ngày hoàn thành<span
                                    class="text-danger">(*)</span></label>
                            <input name="complateDate" id="complateDate" type="datetime-local" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Ngày bắt đầu nộp khóa luận<span
                                    class="text-danger">(*)</span></label>
                            <input name="thesisStartDate" id="thesisStartDate" type="datetime-local"
                                   class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Ngày hết hạn nộp khóa luận<span
                                    class="text-danger">(*)</span></label>
                            <input name="thesisEndDate" id="thesisEndDate" type="datetime-local" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Khoa<span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select form-control"
                                        name="department" id="department" style="width: 100%;">
                                    <c:forEach var="departmentOption" items="${departmentOptions}">
                                        <option value="${departmentOption[0]}">${departmentOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
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
                            <label class="font-weight-bold">Sinh viên thực hiện<span
                                    class="text-danger">(*)</span></label>
                            <select name="performStudentsId" id="performStudentsId"
                                    class="custom-select2 form-control" multiple="multiple" style="width: 100%;">
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Giảng viên hướng dẫn<span
                                    class="text-danger">(*)</span></label>
                            <select name="instructorsId" id="instructorsId"
                                    class="custom-select2 form-control" multiple="multiple" style="width: 100%;">
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Giảng viên phản biện<span
                                    class="text-danger">(*)</span></label>
                            <select name="reviewLecturer" id="reviewLecturer"
                                    class="custom-select2 form-control" style="width: 100%;">
                            </select>
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
<%--    let currentPage = ${page};--%>
<%--    let totalPage = ${totalPage};--%>
<%--    let pageSize = ${pageSize};--%>

<%--    $('#pagination').twbsPagination({--%>
<%--        totalPages: Math.ceil(totalPage / pageSize),--%>
<%--        visiblePages: 8,--%>
<%--        first: '',--%>
<%--        last: '',--%>
<%--        prev: '&laquo;',--%>
<%--        next: '&raquo;',--%>
<%--        startPage: currentPage,--%>
<%--        onPageClick: function (event, page) {--%>
<%--            if (currentPage != page) {--%>
<%--                $("#page").val(page)--%>
<%--                $("#form-filter").submit();--%>
<%--            }--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>