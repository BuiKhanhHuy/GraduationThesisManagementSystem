<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
            <h4 class="text-blue h4">Danh sách khoa</h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddDepartmentModal('<c:url value="/admin/api/departments"/>')"
                    class="btn btn-primary btn-md"><i
                    class="micon icon-copy dw dw-add"></i>
                Thêm khoa
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID</th>
            <th scope="col">Mã khoa</th>
            <th scope="col">Tên khoa</th>
            <th scope="col" class="text-center col-2">Ngày thành lập</th>
            <th scope="col" class="col-2 text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${departments.size() > 0}">
            <c:forEach var="department" items="${departments}">
                <tr>
                    <td scope="row" class="text-center">${department.id}</td>
                    <td>${department.code}</td>
                    <td>${department.name}</td>
                    <td class="text-center">
                        <fmt:formatDate pattern="yyyy/MM/dd" value="${department.founding}"/>
                    </td>
                    <td class="col-2 text-center">
                        <button onclick="showViewDepartmentModal('<c:url
                                value="/admin/api/departments/${department.id}"/>')"
                                type="button" class="btn btn-sm bg-info text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Xem chi tiết">
                            <i class="icon-copy dw dw-eye"></i>
                        </button>
                        <button onclick="showEditDepartmentModal('<c:url
                                value="/admin/api/departments/${department.id}"/>', ${department.id})"
                                type="button" class="btn btn-sm bg-warning text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Cập nhật">
                            <i class="icon-copy dw dw-edit1"></i>
                        </button>
                        <button onclick="deleteDepartmentItem('<c:url
                                value="/admin/api/departments/${department.id}"/>')"
                                type="button" class="btn btn-sm bg-danger text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Xóa">
                            <i class="icon-copy dw dw-delete-3"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${departments.size() == 0}">
            <tr>
                <td colspan="6" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">Danh sách khoa trống</p>
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

<!-- ADD and EDIT modal -->
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-department" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditDepartment" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditDepartment"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form id="form-add-edit-department">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Mã khoa <span class="text-danger">(*)</span></label>
                            <input name="code" id="code" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Tên khoa <span
                                    class="text-danger">(*)</span></label>
                            <input name="name" id="name" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Ngày thành lập <span
                                    class="text-danger">(*)</span></label>
                            <input name="founding" id="founding" type="date" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Mô tả</label>
                            <textarea name="description" id="description" class="form-control"></textarea>
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

<%--VIEW modal--%>
<div class="modal fade bs-example-modal-lg " id="modal-view-department" tabindex="-1" role="dialog"
     aria-labelledby="myModalViewDepartment" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalViewDepartment">Chi tiết khoa</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <div class="pd-10">
                    <h6 class="mb-10 text-danger">ID</h6>
                    <p id="data-id" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">Mã khoa</h6>
                    <p id="data-code" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">Tên khoa</h6>
                    <p id="data-name" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">Ngày thành lập</h6>
                    <p id="data-founding" class="ml-1"></p>
                    <h6 class="mb-10 text-danger">Mô tả</h6>
                    <p id="data-description" class="ml-1"></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
            </div>
        </div>
    </div>
</div>
<%--VIEW modal--%>