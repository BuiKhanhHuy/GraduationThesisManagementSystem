<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="filterThesis" value=""/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>Khóa luận</h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Khóa luận
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
            <h4 class="text-blue h4">Danh sách khóa luận</h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddThesisModal('<c:url value="/admin/api/theses"/>')"
                    type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm khóa luận
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">Mã khóa luận</th>
            <th scope="col" class="text-center">Chủ đề</th>
            <th scope="col" class="text-center">Khoa</th>
            <th scope="col" class="text-center">Niên khóa</th>
            <th scope="col" class="text-center">Ngày bắt đầu</th>
            <th scope="col" class="text-center">Ngày kết thúc</th>
            <th scope="col">Trạng thái tập tin</th>
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
                        <fmt:formatDate type="date" value="${thesis.startDate}"/>
                    </td>
                    <td>
                        <fmt:formatDate type="date" value="${thesis.complateDate}"/>
                    </td>
                    <td>
                        <c:if test="${thesis.reportFile == null || thesis.reportFile.isEmpty()}">
                            <span class="text-danger font-weight-bold">Chưa nộp</span>
                        </c:if>
                        <c:if test="${thesis.reportFile != null && !thesis.reportFile.isEmpty()}">
                            <span class="text-success font-weight-bold">Đã nộp</span>
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
                            <span class="text-danger font-weight-bold">Chưa đạt</span>
                        </c:if>
                        <c:if test="${thesis.result == 3}">
                            <span class="text-success font-weight-bold">Đạt</span>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <div class="btn-list">
                            <button type="button" class="btn btn-sm bg-info text-white" data-toggle="tooltip"
                                    data-placement="bottom" title="Xem chi tiết">
                                <i class="icon-copy dw dw-eye"></i>
                            </button>
                            <button onclick="showEditThesisModal('<c:url
                                    value="/admin/api/theses/${thesis.id}"/>', ${thesis.id})"
                                    type="button" class="btn btn-sm bg-warning text-white" data-toggle="tooltip"
                                    data-placement="bottom" title="Cập nhật">
                                <i class="icon-copy dw dw-edit1"></i>
                            </button>
                            <button onclick="deleteThesisItem('<c:url
                                    value="/admin/api/theses/${thesis.id}"/>')"
                                    type="button" class="btn btn-sm bg-danger text-white" data-toggle="tooltip"
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
                            <input name="startDate" id="startDate" type="date" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Ngày hoàn thành<span
                                    class="text-danger">(*)</span></label>
                            <input name="complateDate" id="complateDate" type="date" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Ngày bắt đầu nộp khóa luận<span
                                    class="text-danger">(*)</span></label>
                            <input name="thesisStartDate" id="thesisStartDate" type="date"
                                   class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Ngày hết hạn nộp khóa luận<span
                                    class="text-danger">(*)</span></label>
                            <input name="thesisEndDate" id="thesisEndDate" type="date" class="form-control">
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
                            <select name="students" id="students"
                                    class="custom-select2 form-control" multiple="multiple" style="width: 100%;">
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Giảng viên hướng dẫn<span
                                    class="text-danger">(*)</span></label>
                            <select name="lecturers" id="lecturers"
                                    class="custom-select2 form-control" multiple="multiple" style="width: 100%;">
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Giảng viên phản biện<span
                                    class="text-danger">(*)</span></label>
                            <select name="reviewLecturer" id="reviewLecturer"
                                    class="custom-select form-control" style="width: 100%;">
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Nội dung đánh giá</label>
                            <textarea name="comment" id="comment" class="textarea_editor form-control border-radius-0"
                                      placeholder="Viết nội dung đánh giá tại đây..."></textarea>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Tập tin khóa luận</label>
                            <div class="custom-file">
                                <input type="file" name="file" id="file" class="custom-file-input">
                                <label class="custom-file-label">Chọn tệp</label>
                            </div>

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