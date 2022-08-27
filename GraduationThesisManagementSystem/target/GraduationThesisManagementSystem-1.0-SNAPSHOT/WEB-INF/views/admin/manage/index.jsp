<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-header">
    <div>

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
        <c:if test="${manages.size() != 0}">
            <c:forEach var="manage" items="${manages}">
                <tr>
                    <th scope="row" class="text-center">
                        <img src="${manage.user.avatar}" style="border-radius: 50%;" height="70" width="70" alt="">
                    </th>
                    <td class="text-center">${manage.user.username}</td>
                    <td>
                        <ul style="list-style-type: circle; list-style-position: inside; ">
                            <li>${manage.fullName}</li>
                            <li>${manage.email}</li>
                            <li>${manage.phone}</li>
                        </ul>
                    </td>
                    <td class="text-center">
                        <c:if test="${manage.user.active == true}">
                            <i class="fa fa-circle text-success" data-toggle="tooltip" data-placement="bottom"
                               title="Hoạt động"></i>
                        </c:if>
                        <c:if test="${manage.user.active != true}">
                            <i class="fa fa-circle text-danger" data-toggle="tooltip" data-placement="bottom"
                               title="Hoạt động"></i>
                        </c:if>
                    </td>
                    <td class="text-light-purple font-weight-bold">${manage.user.role.roleName}</td>
                    <td class="col-2 text-center">
                        <button type="button" class="btn btn-sm bg-warning text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Chỉnh sửa">
                            <i class="icon-copy dw dw-edit1"></i>
                        </button>
                        <button type="button" class="btn btn-sm bg-danger text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Xóa">
                            <i class="icon-copy dw dw-delete-3"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${manages.size() == 0}">
            <tr>
                <td colspan="6" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">Danh sách quản lý trống</p>
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

<script>

</script>