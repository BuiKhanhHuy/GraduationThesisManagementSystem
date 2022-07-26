<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="addDepartment" value="/admin/departments/add"/>

<div class="row">
    <div class="col-xl-8 col-lg-8 col-md-8 col-sm-12 mb-30">
        <div class="card-box pd-30 height-100-p overflow-hidden">
            <h5 class="h5 text-blue">Thêm khoa</h5>
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
        <div class="pd-30 card-box">
            <h5 class="h5 text-blue">Hành động</h5>
            <div class="profile-info">
                <button type="submit" form="form-add-department" class="btn btn-success"><i class="fa fa-save"></i> Lưu
                    dữ liệu
                </button>
                <button type="reset" form="form-add-department" class="btn btn-danger"><i
                        class="icon-copy fa fa-refresh" aria-hidden="true"></i> Hủy bỏ
                </button>
            </div>
        </div>
    </div>
</div>