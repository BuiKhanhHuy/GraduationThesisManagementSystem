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
            <h4 class="text-blue h4">Danh sách cột điểm</h4>
        </div>
        <div class="pull-right">
            <button type="button" class="btn btn-primary btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm cột điểm
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID</th>
            <th scope="col">Tên cột điểm</th>
            <th scope="col">Trọng số</th>
            <th scope="col">Thuộc thành phần điểm</th>
            <th scope="col" class="col-2 text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row" class="text-center">1</th>
            <td>Otto</td>
            <td>
                <div class="progress">
                    <div class="progress-bar bg-success" role="progressbar" style="width: 75%" aria-valuenow="25"
                         aria-valuemin="0" aria-valuemax="100">75%
                    </div>
                </div>
            </td>
            <td>Điểm thuyết trình</td>
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