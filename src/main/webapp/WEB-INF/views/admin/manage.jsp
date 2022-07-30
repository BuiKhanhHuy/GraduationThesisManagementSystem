<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/admin/src/images/avatar/avatar-default.jpg" var="avatarDefault"/>

<div class="page-header">
    <div>

    </div>
</div>
<!-- table start -->
<div class="pd-20 card-box mb-30">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">Danh sách quản trị viên</h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddManageModal('<c:url value="/admin/api/manages"/>')"
                    type="button" class="btn btn-primary btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm quản trị viên
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
                        <c:if test="${manage.user.avatar == null}">
                            <img src="${avatarDefault}" style="border-radius: 50%;" height="70" width="70" alt="">
                        </c:if>
                        <c:if test="${manage.user.avatar != null}">
                            <img src="${manage.user.avatar}" style="border-radius: 50%;" height="70" width="70" alt="">
                        </c:if>
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
                            <i class="icon-copy fa fa-check-circle-o text-success" aria-hidden="true"
                               data-toggle="tooltip" data-placement="bottom"
                               title="Hoạt động"></i>
                        </c:if>
                        <c:if test="${manage.user.active != true}">
                            <i class="icon-copy fa fa-times-circle-o text-danger" aria-hidden="true"
                               data-toggle="tooltip"
                               data-placement="bottom"
                               title="Không hoạt động"></i>
                        </c:if>
                    </td>
                    <td class="text-light-purple font-weight-bold">${manage.user.role.roleName}</td>
                    <td class="col-2 text-center">
                        <button onclick="showEditManageModal('<c:url value="/admin/api/manages/${manage.id}"/>',
                            ${manage.id})"
                                type="button" class="btn btn-sm bg-warning text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Cập nhật">
                            <i class="icon-copy dw dw-edit1"></i>
                        </button>
                        <button onclick="deleteManageItem('<c:url value="/admin/api/manages/${manage.id}"/>')"
                                type="button" class="btn btn-sm bg-danger text-white"
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
                    <p class="text-center">Danh sách quản trị viên trống</p>
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
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-manage" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditManage" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditManage"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

            </div>

            <div class="modal-body">
                <form id="form-add-edit-manage">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Ảnh đại diện<span
                                    class="text-danger">(*)</span></label>
                            <div class="custom-file">
                                <input type="file" name="avatar" id="avatar" class="custom-file-input">
                                <label class="custom-file-label">Chọn ảnh</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Tên người dùng<span
                                    class="text-danger">(*)</span></label>
                            <input name="username" id="username" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Mật khẩu<span
                                    class="text-danger">(*)</span></label>
                            <input name="password" id="password" type="password" class="form-control" >
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Họ và tên<span
                                    class="text-danger">(*)</span></label>
                            <input name="fullName" id="fullName" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Email<span
                                    class="text-danger">(*)</span></label>
                            <input name="email" id="email" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Số điện thoại<span
                                    class="text-danger">(*)</span></label>
                            <input name="phone" id="phone" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <div class="custom-control custom-checkbox mb-5">
                                <input type="checkbox" class="custom-control-input" id="active" name="active">
                                <label class="custom-control-label" for="active">Hoạt động</label>
                            </div>
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