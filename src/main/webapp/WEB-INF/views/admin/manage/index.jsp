<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
            <h4 class="text-blue h4">Danh sách quản lý</h4>
        </div>
        <div class="pull-right">
            <button type="button" class="btn btn-primary btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm quản lý
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center col-1">Hình ảnh</th>
            <th scope="col" class="text-center">Tên người dùng</th>
            <th scope="col" class="text-center">Thông tin</th>
            <th scope="col" class="text-center col-1">Hoạt động</th>
            <th scope="col" class="col-1">Quyền</th>
            <th scope="col" class="col-2 text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row" class="text-center">
                <img src="<c:url value="/admin/vendors/images/chat-img2.jpg"/> " width="70" alt="">
            </th>
            <td class="text-center">admin</td>
            <td>
                <ul style="list-style-type: circle; list-style-position: inside; ">
                    <li>Bùi Khánh Huy</li>
                    <li>khuy220@gmail.com</li>
                    <li>0888425094</li>
                    <li>27/02/2001</li>
                    <li>Nam</li>
                    <li>1242 QL1A, Tân Tạo A, Bình Tân, TP. HCM</li>
                </ul>
            </td>
            <td class="text-center">
                <i class="fa fa-circle text-success" data-toggle="tooltip" data-placement="bottom"
                   title="Hoạt động"></i>
            </td>
            <td class="text-light-purple font-weight-bold">ADMIN</td>
            <td class="col-2 text-center">
                <button type="button" class="btn btn-sm bg-info text-white">
                    <i class="icon-copy dw dw-eye"></i>
                </button>
                <button type="button" class="btn btn-sm bg-warning text-white">
                    <i class="icon-copy dw dw-edit1"></i>
                </button>
                <button type="button" class="btn btn-sm bg-danger text-white">
                    <i class="icon-copy dw dw-delete-3"></i>
                </button>
            </td>
        </tr>
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

<script>

</script>