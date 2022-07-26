<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="searchRole" value="/admin/roles/"/>

<div class="page-header">
    <div>
        <nav class="navbar navbar-expand-sm navbar-dark indigo">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <form class="form-inline ml-auto" action="${searchRole}">
                    <div class="md-form my-0">
                        <input class="form-control" type="text" placeholder="Tìm kiếm ..." name="kw"
                               aria-label="Search">
                    </div>
                    <input class="form-control ml-1 btn-warning btn" type="submit" value="Tìm kiếm"/>
                </form>

            </div>
        </nav>
    </div>
</div>
<!-- table start -->
<div class="pd-20 card-box mb-30">
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