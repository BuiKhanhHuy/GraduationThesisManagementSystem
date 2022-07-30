<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="endpointLoadUsers" value="/admin/api/users"/>

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
            <h4 class="text-blue h4">Danh sách thông báo</h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddNotificationModal('<c:url value="/admin/api/notifications"/>')"
                    type="button" class="btn btn-primary btn-md"><i class="micon icon-copy dw dw-add"></i>
                Gửi thông báo
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID</th>
            <th scope="col" class="col-2">Tiêu đề</th>
            <th scope="col">Nội dung</th>
            <th scope="col" class="col-2 text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${notifications.size() != 0}">
            <c:forEach var="notification" items="${notifications}">
                <tr>
                    <th scope="row" class="text-center">${notification.id}</th>
                    <td>${notification.title}</td>
                    <td>
                            ${notification.content}
                    </td>
                    <td class="col-2 text-center">
                        <button
                                type="button" class="btn btn-sm bg-warning text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Cập nhật">
                            <i class="icon-copy dw dw-edit1"></i>
                        </button>
                        <button
                                type="button" class="btn btn-sm bg-danger text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Xóa">
                            <i class="icon-copy dw dw-delete-3"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${notifications.size() == 0}">
            <tr>
                <td colspan="5" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">Danh sách thông báo trống</p>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<!-- table End -->


<!-- ADD and EDIT modal -->
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-notification" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditNotification" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditNotification"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

            </div>

            <div class="modal-body">
                <form id="form-add-edit-notification">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Tiêu đề<span
                                    class="text-danger">(*)</span></label>
                            <input name="title" id="title" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Nội dung<span
                                    class="text-danger">(*)</span></label>
                            <textarea name="content" id="content" class="form-control"></textarea>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Đối tượng cần gửi</label>
                            <div>
                                <select onchange="objectChange('${endpointLoadUsers}')"
                                        name="role" id="role"
                                        class="custom-select form-control" style="width: 100%; height:38px;">
                                    <option value="">Tất cả</option>
                                    <c:forEach var="role" items="${roles}">
                                        <option value="${role.id}">${role.description}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Sinh viên thuộc khóa</label>
                            <div>
                                <select onchange="schoolYearChange('${endpointLoadUsers}')"
                                        disabled id="schoolYear" name="schoolYear"
                                        class="custom-select form-control" style="width: 100%; height:38px;">
                                    <option value=""> Tất cả niên khóa</option>
                                    <c:forEach var="schoolYear" items="${schoolYears}">
                                        <option value="${schoolYear.id}">${schoolYear.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Gửi trực tiếp</label>
                            <select name="users" id="users" disabled
                                    class="custom-select2 form-control" multiple="multiple" style="width: 100%;">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
                <button onclick="addNotification()"
                        type="button" class="btn btn-success" id="btn-submit-form">
                    <i class="icon-copy fa fa-send" aria-hidden="true"></i> Gửi đi
                </button>
            </div>
        </div>
    </div>
</div>
<!-- ADD and EDIT modal -->