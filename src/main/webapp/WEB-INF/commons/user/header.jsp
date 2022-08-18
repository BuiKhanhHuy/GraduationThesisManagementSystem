<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/public/common/images/avatars/avatar-default.jpg" var="avatarDefault"/>
<c:url var="appContext" value="/"/>
<c:url var="logout" value="/logout"/>
<c:url var="home" value="/"/>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white sticky-top">
    <!-- Container wrapper -->
    <div class="container-fluid container-lg">
        <!-- Toggle button -->
        <button
                class="navbar-toggler"
                type="button"
                data-mdb-toggle="collapse"
                data-mdb-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>

        <!-- Collapsible wrapper -->
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <!-- Navbar brand -->
            <a class="navbar-brand mt-2 mt-lg-0" href="${home}">
                <img width="280"
                     src="<c:url value="/public/common/images/logo/logo-text-blue.png"/>"
                     alt="OU logo"
                     loading="lazy"/>
            </a>
            <!-- Left links -->
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="${home}">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Tin tức</a>
                </li>
            </ul>
            <!-- Left links -->
        </div>
        <!-- Collapsible wrapper -->

        <!-- Right elements -->
        <div class="d-flex align-items-center">
            <!-- Notifications -->
            <div class="dropdown">
                <a class="text-reset me-3 dropdown-toggle hidden-arrow"
                   href="#"
                   id="navbarDropdownMenuLink"
                   role="button"
                   data-mdb-toggle="dropdown"
                   aria-expanded="false">
                    <i class="fas fa-bell" style="font-size: 24px;"></i>
                    <c:if test="${notificationUsers.size() > 0}">
                        <span class="badge bg-danger badge-dot" id="badge-notifi-active"></span>
                    </c:if>
                </a>
                <ul id="notification-user-area"
                    class="dropdown-menu dropdown-menu-end overflow-auto shadow-lg mt-2" style="width: 450px; max-height: 350px;"
                    aria-labelledby="navbarDropdownMenuLink">
                    <c:if test="${notificationUsers.size() > 0}">
                        <c:forEach var="notificationUser" items="${notificationUsers}">
                            <li id="notification-user-${notificationUser.id}" class="notification-user">
                                <div class=" bg-white flex-row px-3 py-2 mb-1 d-flex justify-content-between align-items-center">
                                    <div class="d-block">
                                        <h6>${notificationUser.notification.title}</h6>
                                        <p class="text-muted mb-0">${notificationUser.notification.content}</p>
                                    </div>
                                    <a href="javascript:;"
                                       onclick="turnOffNotification('${appContext}', ${notificationUser.id})"
                                       class="close m-2 p-2 text-danger">&times;</a>
                                </div>
                            </li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${notificationUsers.size() == 0}">
                        <li class="text-center text-black-50 p-3">Không có thông báo.</li>
                    </c:if>
                </ul>
            </div>
            <!-- Avatar -->
            <div class="dropdown">
                <a class="dropdown-toggle d-flex align-items-center hidden-arrow"
                   href="#"
                   id="navbarDropdownMenuAvatar"
                   role="button"
                   data-mdb-toggle="dropdown"
                   aria-expanded="false">
                    <c:if test="${currentUser.avatar != null && !currentUser.avatar.isEmpty()}">
                        <img src="${currentUser.avatar}" alt="${currentUser.username}" width="40"
                             style="border-radius: 50%;"/>
                    </c:if>
                    <c:if test="${currentUser.avatar == null || currentUser.avatar.isEmpty()}">
                        <img src="${avatarDefault}" alt="avatar" width="40" style="border-radius: 50%;"/>
                    </c:if>
                    <span class="text-black" style="margin-left: 5px;">${currentUser.username}</span>
                </a>
                <ul class="dropdown-menu dropdown-menu-end shadow-lg p-2 mt-2"
                    aria-labelledby="navbarDropdownMenuAvatar">
                    <li>
                        <a class="dropdown-item" href="#"><i class="fa-solid fa-circle-info"
                                                             style="margin-right: 5px;"></i> Thông tin cá
                            nhân</a>
                    </li>
                    <li>
                        <a onclick="changePasswordByUser('${appContext}', ${currentUser.id})"
                           class="dropdown-item" href="javascript:;"><i class="fa-solid fa-key"
                                                                        style="margin-right: 5px;"></i> Đổi mật khẩu</a>
                    </li>
                    <li>
                        <a class="dropdown-item text-danger" href="${logout}"><i class="fa-solid fa-power-off"
                                                                                 style="margin-right: 5px;"></i> Đăng
                            xuất</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- Right elements -->
    </div>
    <!-- Container wrapper -->
</nav>
<!-- Navbar -->

<%--change password modal--%>
<div
        class="modal fade"
        data-mdb-backdrop="static"
        data-mdb-keyboard="false"
        tabindex="-1"
        aria-labelledby="myChangePasswordModalLabel"
        aria-hidden="true"
        id="change-password-modal">
    <div class="modal-dialog  modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="myChangePasswordModalLabel">Đổi mật khẩu</h5>
                <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body p-4">
                <form id="form-change-password">
                    <div class="pd-10">
                        <div class="form-group mb-3">
                            <label for="oldPassword" class="font-weight-bold mb-2">Mật khẩu cũ <span
                                    class="text-danger">(*)</span></label>
                            <input name="oldPassword" id="oldPassword" type="password" class="form-control form-control-lg">
                        </div>
                        <div class="form-group mb-3">
                            <label for="newPassword" class="font-weight-bold mb-2">Mật khẩu mới <span
                                    class="text-danger">(*)</span></label>
                            <input name="newPassword" id="newPassword" type="password" class="form-control form-control-lg">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">Hủy</button>
                <button type="button" class="btn btn-success" id="btn-submit-form-change-password">Đổi mật khẩu</button>
            </div>
        </div>
    </div>
</div>



<form id="logoutForm" action="<c:url value="/logout"/> ">
</form>
<%--change password modal--%>
