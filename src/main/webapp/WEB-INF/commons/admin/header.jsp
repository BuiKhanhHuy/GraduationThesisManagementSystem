<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url value="/public/common/images/avatars/avatar-default.jpg" var="avatarDefault"/>
<c:url var="logout" value="/logout"/>
<c:url var="appContext" value="/"/>

<div class="header">
    <div class="header-left">
        <div class="menu-icon dw dw-menu"></div>
        <div class="search-toggle-icon dw dw-search2" data-toggle="header_search"></div>
        <div class="header-search">
            <form>
                <div class="form-group mb-0">
                    <i class="dw dw-search2 search-icon"></i>
                    <input type="text" class="form-control search-input" placeholder="Search Here">
                    <div class="dropdown">
                        <a class="dropdown-toggle no-arrow" href="#" role="button" data-toggle="dropdown">
                            <i class="ion-arrow-down-c"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <div class="form-group row">
                                <label class="col-sm-12 col-md-2 col-form-label">From</label>
                                <div class="col-sm-12 col-md-10">
                                    <input class="form-control form-control-sm form-control-line" type="text">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-12 col-md-2 col-form-label">To</label>
                                <div class="col-sm-12 col-md-10">
                                    <input class="form-control form-control-sm form-control-line" type="text">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-12 col-md-2 col-form-label">Subject</label>
                                <div class="col-sm-12 col-md-10">
                                    <input class="form-control form-control-sm form-control-line" type="text">
                                </div>
                            </div>
                            <div class="text-right">
                                <button class="btn btn-primary">Search</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="header-right">
        <div class="dashboard-setting user-notification">
            <div class="dropdown">
                <a class="dropdown-toggle no-arrow" href="javascript:;" data-toggle="right-sidebar">
                    <i class="dw dw-settings2"></i>
                </a>
            </div>
        </div>
        <sec:authorize access="isAuthenticated()">
            <div class="user-notification">
                <div class="dropdown">
                    <a class="dropdown-toggle no-arrow" href="#" role="button" data-toggle="dropdown">
                        <i class="icon-copy dw dw-notification"></i>
                        <c:if test="${notificationUsers.size() > 0}">
                            <span class="badge notification-active" id="badge-notifi-active"></span>
                        </c:if>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <div class="notification-list mx-h-350 customscroll">
                            <ul id="notification-user-area">
                                <c:if test="${notificationUsers.size() > 0}">
                                    <c:forEach var="notificationUser" items="${notificationUsers}">
                                        <li id="notification-user-${notificationUser.id}" class="notification-user">
                                            <a href="javascript:;"
                                               style="padding-left: 15px; padding-right: 30px; cursor: context-menu;">
                                                <div class="row mr-0">
                                                    <div class="col">
                                                        <h3>${notificationUser.notification.title}</h3>
                                                        <p>${notificationUser.notification.content}</p>
                                                    </div>
                                                    <div onclick="turnOffNotification('${appContext}', ${notificationUser.id})"
                                                         class="col-md-1 text-danger" style="cursor: pointer;">
                                                        Xóa
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${notificationUsers.size() == 0}">
                                    <li class="text-center text-secondary">Không có thông báo.</li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="user-info-dropdown">
                <div class="dropdown">
                    <a class="dropdown-toggle" href="#" role="button" data-toggle="dropdown">
						<span class="user-icon">

                            <c:if test="${currentUser.avatar != null && !currentUser.avatar.isEmpty()}">
                                <img src="${currentUser.avatar}" alt="${currentUser.username}"/>
                            </c:if>
                             <c:if test="${currentUser.avatar == null || currentUser.avatar.isEmpty()}">
                                 <img src="${avatarDefault}" alt="avatar"/>
                             </c:if>
						</span>
                        <span class="user-name">${currentUser.username}</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
                        <a class="dropdown-item" href="profile.html"><i class="dw dw-user1"></i> Profile</a>
                        <a class="dropdown-item" href="profile.html"><i class="dw dw-settings2"></i> Setting</a>
                        <a onclick="changePasswordByUser('${appContext}', ${currentUser.id})"
                           class="dropdown-item" href="javascript:;">
                            <i class="dw dw-key2"></i> Change password
                        </a>
                        <a class="dropdown-item text-danger" href="${logout}"><i class="dw dw-logout"></i> Log Out</a>
                    </div>
                </div>
            </div>
        </sec:authorize>
    </div>
</div>


<%--change password modal--%>
<div class="modal fade" id="change-password-modal" tabindex="-1" role="dialog"
     aria-labelledby="myChangePasswordModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myChangePasswordModalLabel">Thay đổi mật khẩu</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form id="form-change-password">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Mật khẩu cũ <span class="text-danger">(*)</span></label>
                            <input name="oldPassword" id="oldPassword" type="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Mật khẩu mới <span
                                    class="text-danger">(*)</span></label>
                            <input name="newPassword" id="newPassword" type="password" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                <button id="btn-submit-form-change-password"
                        type="button" class="btn btn-success">Đổi mật khẩu
                </button>
            </div>
        </div>
    </div>
</div>

<form id="logoutForm" action="<c:url value="/logout"/> ">
</form>
<%--change password modal--%>
