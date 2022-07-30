<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="searchRole" value="/admin/roles/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>pricing Table</h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">pricing Table</li>
                </ol>
            </nav>
        </div>
    </div>
</div>

<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form>
        <div class="row" action="${searchRole}">
            <div class="col-md-4 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text" placeholder="Nhập tên quyền cần tìm..." name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="id" style="width: 100%; height: 38px;">
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.id}">${role.roleName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-2 col-sm-12">
                <div>
                    <button class="form-control ml-1 btn-warning btn" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i> Tìm kiếm
                    </button>
                </div>
            </div>
        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">Danh sách quyền</h4>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID</th>
            <th scope="col">Tên quyền</th>
            <th scope="col">Mô tả</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${roles.size() != 0}">
            <c:forEach var="role" items="${roles}">
                <tr>
                    <th scope="row" class="text-center">${role.id}</th>
                    <td class="text-danger font-weight-bold">${role.roleName}</td>
                    <td>${role.description}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${roles.size() == 0}">
            <tr>
                <td colspan="3" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">Danh sách quyền trống</p>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<!-- table End -->