<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="addDepartment" value="/admin/departments/add"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-6 col-sm-12">
            <div class="title">
                <h4>Khoa</h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Danh sách khoa</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Chỉnh sửa</li>
                </ol>
            </nav>
        </div>
        <div class="col-md-6 col-sm-12 text-right">
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xl-8 col-lg-8 col-md-8 col-sm-12 mb-30">
        <div class="card-box pd-20 height-100-p overflow-hidden">
            <h5 class="h5 text-blue">Sửa thông tin khoa</h5>
            <div class="profile-info">
                <form:form acceptCharset="UTF-8" id="form-add-department" method="POST" action="${addDepartment}" modelAttribute="department">
                    <div class="form-group">
                        <label class="font-weight-bold">Mã khoa <span class="text-danger">(*)</span></label>
                        <form:input cssClass="form-control form-control-lg" type="text" path="code" id="code"/>
                        <form:errors path="code" element="div" cssClass="text-danger"/>
                    </div>
                    <div class="form-group">
                        <label class="font-weight-bold">Tên khoa <span class="text-danger">(*)</span></label>
                        <form:input cssClass="form-control form-control-lg" type="text" path="name" id="name"/>
                        <form:errors path="name" element="div" cssClass="text-danger"/>
                    </div>
                    <div class="form-group">
                        <label class="font-weight-bold">Ngày thành lập <span class="text-danger">(*)</span></label>
                        <form:input cssClass="form-control form-control-lg" type="date" path="founding"
                                    id="founding"/>
                        <form:errors path="founding" element="div" cssClass="text-danger"/>
                    </div>
                    <div class="form-group">
                        <label class="font-weight-bold">Mô tả</label>
                        <form:textarea cssClass="form-control" path="description" id="description"/>
                        <form:errors path="description" element="div" cssClass="text-danger"/>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 mb-30">
        <div class="pd-20 card-box">
            <h5 class="h5 text-blue">Hành động</h5>
            <div class="profile-info">
                <button type="submit" form="form-add-department" class="btn btn-success"><i class="fa fa-save"></i> Lưu
                    dữ liệu
                </button>
                <button type="reset" form="form-add-department" class="btn btn-danger"><i
                        class="icon-copy fa fa-refresh" aria-hidden="true"></i> Đặt lại
                </button>
            </div>
        </div>
    </div>
</div>