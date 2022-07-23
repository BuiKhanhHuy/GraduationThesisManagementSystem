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
            <h4 class="text-blue h4">Danh sách bản tin</h4>
        </div>
        <div class="pull-right">
            <button type="button" class="btn btn-primary btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm bản tin
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID</th>
            <th scope="col" class="col-2">Tiêu đề</th>
            <th scope="col">Nội dung</th>
            <th scope="col" class="text-center col-2">Người tạo</th>
            <th scope="col" class="col-2 text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row" class="text-center">1</th>
            <td>Đến hạn nộp khóa luận</td>
            <td>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Unde hic non repellendus debitis iure, doloremque assumenda. Autem modi, corrupti, nobis ea iure fugiat, veniam non quaerat mollitia animi error corporis.
            </td>
            <td>
               Admin
            </td>
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
</div>
<!-- table End -->