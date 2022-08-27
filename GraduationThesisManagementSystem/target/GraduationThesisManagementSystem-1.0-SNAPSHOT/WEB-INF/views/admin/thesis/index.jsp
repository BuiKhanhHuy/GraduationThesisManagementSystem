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
            <button type="button" class="btn btn-primary btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm khóa luận
            </button>
        </div>
    </div>
    <div class="table-responsive">
        <table class="table table-bordered ">
            <thead>
            <tr>
                <th scope="col" class="text-center col-1">ID</th>
                <th scope="col" class="text-center">Mã khóa luận</th>
                <th scope="col" class="text-center">Chủ đề</th>
                <th scope="col" class="text-center">Khoa</th>
                <th scope="col" class="text-center">Niên khóa</th>
                <th scope="col" class="text-center">Ngày bắt đầu</th>
                <th scope="col" class="text-center">Ngày kết thúc</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Tổng điểm</th>
                <th scope="col" class="text-center">Kết quả</th>
                <th scope="col" class="col-2 text-center">Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${theses.size() != 0}">
                <c:forEach var="thesis" items="${theses}">
                    <tr>
                        <td class="text-center col-1">
                                ${thesis.id}
                        </td>
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
                            <c:if test="${thesis.result == true}">
                                <span class="text-success font-weight-bold">Đạt</span>
                            </c:if>
                            <c:if test="${thesis.result != true}">
                                <span class="text-danger font-weight-bold">Trượt</span>
                            </c:if>
                        </td>
                        <td class="col-2 text-center">
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