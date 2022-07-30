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
            <h4 class="text-blue h4">Danh sách giảng viên</h4>
        </div>
        <div class="pull-right">
            <button type="button" class="btn btn-primary btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm giảng viên
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center col-1">Hình ảnh</th>
            <th scope="col" class="text-center">Mã giảng viên</th>
            <th scope="col" class="text-center">Thông tin</th>
            <th scope="col">Chức vụ</th>
            <th scope="col">Thuộc khoa</th>
            <th scope="col" class="col-1 text-center">Trạng thái</th>
            <th scope="col" class="col-2 text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>

        <c:if test="${lecturers.size() != 0}">
            <c:forEach var="lecturer" items="${lecturers}">
                <tr>
                    <td class="text-center col-1">
                        <img src="${lecturer.user.avatar}" style="border-radius: 50%;" class="img-fluid" height="70"
                             width="70" alt="">
                    </td>
                    <td class="text-center">${lecturer.code}</td>
                    <td>
                        <ul style="list-style-type: circle; list-style-position: inside; ">
                            <li>${lecturer.fullName}</li>
                            <li>${lecturer.email}</li>
                            <li>${lecturer.phone}</li>
                            <li>${lecturer.birthday}</li>
                            <li>
                                <c:if test="${lecturer.gender == 1}">
                                    Nam
                                </c:if>
                                <c:if test="${lecturer.gender == 2}">
                                    Nữ
                                </c:if>
                                <c:if test="${lecturer.gender == 3}">
                                    Khác
                                </c:if>
                            </li>
                            <li>${lecturer.address}</li>
                        </ul>
                    </td>
                    <td>
                        <c:if test="${lecturer.position != null}">
                            ${lecturer.position.name}
                        </c:if>
                        <c:if test="${lecturer.position == null}">
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${lecturer.department != null}">
                            ${lecturer.department.name}
                        </c:if>
                        <c:if test="${lecturer.department == null}">
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <i class="fa fa-circle text-success" data-toggle="tooltip" data-placement="bottom"
                           title="Hoạt động"></i>
                    </td>
                    <td class="col-2 text-center">
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
        <c:if test="${lecturers.size() == 0}">
            <tr>
                <td colspan="7" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">Danh sách giảng viên trống</p>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
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