<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url value="/public/common/images/avatars/avatar-default.jpg" var="avatarDefault"/>
<c:url var="logout" value="/logout"/>
<c:url var="appContext" value="/"/>
<c:url var="langVi" value="/api/lang/?lang=vi"/>
<c:url var="langEn" value="/api/lang/?lang=en"/>
<c:url var="chat" value="/admin/chats/"/>
<c:set value="${pageContext.response.locale.language}" var="lang"/>


<div class="header">
    <div class="header-left">
        <div class="menu-icon dw dw-menu"></div>
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
                    <a class="no-arrow" href="${chat}" role="button" >
                        <i class="icon-copy dw dw-chat font-20"></i>
                    </a>
                    <a class="dropdown-toggle no-arrow" href="#" role="button" data-toggle="dropdown">
                        <i class="icon-copy dw dw-notification"></i>
                        <c:if test="${notificationUsers.size() > 0}">
                            <span class="badge notification-active" id="badge-notifi-active"></span>
                        </c:if>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" style="width: 550px;">
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
                                                        <span class="font-italic mt-2 text-black-50">
                                                            (<span class="notification-created-date">${notificationUser.notification.createdDate}</span>)
                                                        </span>
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
                                    <li class="text-center text-secondary">
                                        <spring:message code="layout.notification.emptyLabel"/>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="user-info-dropdown">
                <div class="dropdown">
                    <a class="dropdown-toggle" style="margin-top: 12px;" href="#" role="button" data-toggle="dropdown">
                        <c:if test="${lang == 'vi'}">
                            <span>
                                <img width="24" src="<c:url value="/public/common/images/flag/vietnam.png"/>"
                                     alt="Vietnam"/>
                             </span>
                        </c:if>
                        <c:if test="${lang == 'en'}">
                            <span>
                                <img width="24" src="<c:url value="/public/common/images/flag/united-kingdom.png"/>"
                                     alt="English"/>
                             </span>
                        </c:if>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list lang">
                        <a class="dropdown-item pl-4 align-middle" href="javascript:;"
                           onclick="changeLang('${appContext}', 'vi')">
                            <div>
                                <img width="24" src="<c:url value="/public/common/images/flag/vietnam.png"/>"
                                     alt="Vietnam"/>
                                Vietnamese
                                <c:if test="${lang == 'vi'}">
                                    <i class="fa fa-check text-success" style="margin-left: 135px;"></i>
                                </c:if>
                            </div>
                        </a>
                        <a class="dropdown-item pl-4 align-middle" href="javascript:;"
                           onclick="changeLang('${appContext}', 'en')">
                            <div>
                                <img width="24" src="<c:url value="/public/common/images/flag/united-kingdom.png"/>"
                                     alt="English"/>
                                English
                                <c:if test="${lang == 'en'}">
                                    <i class="fa fa-check text-success" style="margin-left: 135px;"></i>
                                </c:if>
                            </div>
                        </a>
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
                        <a class="dropdown-item" href="profile.html"><i class="dw dw-user1"></i>
                            <spring:message code="layout.account.profile.label"/>
                        </a>
                        <a onclick="changePasswordByUser('${appContext}', ${currentUser.id})"
                           class="dropdown-item" href="javascript:;">
                            <i class="dw dw-key2"></i>
                            <spring:message code="layout.account.changePassword.label"/>
                        </a>
                        <a class="dropdown-item text-danger" href="${logout}"><i class="dw dw-logout"></i>
                            <spring:message code="layout.account.logout.label"/>
                        </a>
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
                <h4 class="modal-title" id="myChangePasswordModalLabel">
                    <spring:message code="layout.modal.changePassword.title.label"/>
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form id="form-change-password">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">
                                <spring:message code="layout.modal.oldPassword.label"/><span class="text-danger">(*)</span></label>
                            <input name="oldPassword" id="oldPassword" type="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">  <spring:message code="layout.modal.newPassword.label"/><span
                                    class="text-danger">(*)</span></label>
                            <input name="newPassword" id="newPassword" type="password" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <spring:message code="layout.button.cancel.label"/>
                </button>
                <button id="btn-submit-form-change-password"
                        type="button" class="btn btn-success">
                    <spring:message code="layout.modal.button.password.label"/>
                </button>
            </div>
        </div>
    </div>
</div>

<form id="logoutForm" action="<c:url value="/logout"/> ">
</form>
<%--change password modal--%>
