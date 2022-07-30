<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            <h4 class="text-blue h4">Danh sách niên khóa</h4>
        </div>
        <div class="pull-right">
            <button type="button" class="btn btn-primary btn-md"
                    onclick="showAddSchoolYear('<c:url value="/admin/api/school-years"/>')"
            ><i class="micon icon-copy dw dw-add"></i>
                Thêm niên khóa
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID</th>
            <th scope="col" class="text-center">Niên khóa</th>
            <th scope="col" class="col-2 text-center">Ngày bắt đầu</th>
            <th scope="col" class="col-2 text-center">Ngày kết thúc</th>
            <th scope="col" class="col-2 text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${schoolYears.size() != 0}">
            <c:forEach var="schoolYear" items="${schoolYears}">
                <tr>
                    <th scope="row" class="text-center">${schoolYear.id}</th>
                    <td class="text-center">${schoolYear.name}</td>
                    <td class="col-2 text-center">
                        <fmt:formatDate pattern="yyyy/MM/dd" value="${schoolYear.startDate}"/>
                </td>
                    <td class="col-2 text-center">
                        <fmt:formatDate pattern="yyyy/MM/dd" value="${schoolYear.endDate}"/>
                    </td>
                    <td class="col-2 text-center">
                        <button type="button"
                                onclick="showEditSchoolYear('<c:url value="/admin/api/school-years/${schoolYear.id}"/>',
                                    ${schoolYear.id})"
                                class="btn btn-sm bg-warning text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Cập nhật">
                            <i class="icon-copy dw dw-edit1"></i>
                        </button>
                        <button type="button"
                                onclick="deleteSchoolYearItem('<c:url value="/admin/api/school-years/${schoolYear.id}"/>')"
                                class="btn btn-sm bg-danger text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Xóa">
                            <i class="icon-copy dw dw-delete-3"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${schoolYears.size() == 0}">
            <tr>
                <td colspan="5" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">Danh sách niên khóa trống</p>
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
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-school-year" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditSchoolYear" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditSchoolYear"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form id="form-add-edit-school-year">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Niên khóa<span
                                    class="text-danger">(*)</span></label>
                            <input name="name" id="name" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Ngày bắt đầu<span
                                    class="text-danger">(*)</span></label>
                            <input name="startDate" id="startDate" type="date" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Ngày kết thúc <span
                                    class="text-danger">(*)</span></label>
                            <input name="endDate" id="endDate" type="date" class="form-control">
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