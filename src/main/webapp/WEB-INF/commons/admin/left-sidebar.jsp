<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pagina" value="${requestScope['javax.servlet.forward.request_uri']}" />

<c:url var="adminIndex" value="/admin/"/>
<c:url var="news" value="/news/"/>
<c:url var="deparment" value="/admin/departments/"/>
<c:url var="major" value="/admin/majors/"/>
<c:url var="schoolYear" value="/admin/school-years/"/>
<c:url var="position" value="/admin/positions/"/>
<c:url var="lecturer" value="/admin/lecturers/"/>
<c:url var="student" value="/admin/students/"/>
<c:url var="topic" value="/admin/topics/"/>

<c:url var="evaluationMethod" value="/admin/evaluations-method/"/>
<c:url var="scoreComponent" value="/admin/score-components/"/>
<c:url var="scoreColumn" value="/admin/score-columns/"/>
<c:url var="thesis" value="/admin/theses/"/>
<c:url var="thesisGuide" value="/admin/theses-guide/"/>
<c:url var="thesisReview" value="/admin/theses-review/"/>
<c:url var="council" value="/admin/councils/"/>
<c:url var="councilDetail" value="/admin/councils-detail/"/>

<c:url var="role" value="/admin/roles/"/>

<c:url var="manage" value="/admin/manages/"/>
<c:url var="news" value="/admin/news/"/>
<c:url var="notification" value="/admin/notifications/"/>
<c:url var="chat" value="/admin/chats/"/>
<c:url var="scoreStatistics" value="/admin/stats/score-statistics/"/>
<c:url var="frequencyStatistics" value="/admin/stats/frequency-statistics/"/>

<c:url var="logout" value="/logout"/>

<div class="left-side-bar">
    <div class="brand-logo">
        <a href="${adminIndex}" style="padding:0px;">
            <img src="<c:url value="/public/common/images/logo/logo-text-blue.png"/>"
                 alt="" class="dark-logo" style="max-width: 275px;">
            <img src="<c:url value="/public/common/images/logo/logo-text-white.png"/>"
                 alt="" class="light-logo" style="max-width: 275px;">
        </a>
        <div class="dropdown-divider my-0 border-1 border-dark"></div>
        <div class="close-sidebar" data-toggle="left-sidebar-close">
            <i class="ion-close-round"></i>
        </div>
    </div>

    <div class="menu-block customscroll">
        <div class="sidebar-menu">
            <ul id="accordion-menu">
                <li>
                    <a href="${adminIndex}" class="dropdown-toggle no-arrow ${pagina.endsWith('/admin/') ? 'active' : ''}">
                        <span class="micon fa fa-dashboard"></span>
                        <span class="mtext">
                            <spring:message code="layout.leftSidebar.menu.label.index"/>
                        </span>
                    </a>
                </li>
                <sec:authorize access="hasAnyAuthority('LECTURER')">
                    <li>
                        <a href="${councilDetail}" class="dropdown-toggle no-arrow ${pagina.endsWith('/admin/councils-detail/') ? 'active' : ''}">
                            <span class="micon fa fa-users"></span>
                            <span class="mtext">
                                <spring:message code="layout.leftSidebar.menu.label.council"/>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="${thesisGuide}" class="dropdown-toggle no-arrow ${pagina.endsWith('/admin/theses-guide/') ? 'active' : ''}">
                            <i class="micon icon-copy dw dw-file4"></i>
                            <span class="mtext">
                                Khóa luận hướng dẫn
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="${thesisReview}" class="dropdown-toggle no-arrow ${pagina.endsWith('/admin/theses-review/') ? 'active' : ''}">
                            <i class="micon icon-copy fa fa-file-sound-o" aria-hidden="true"></i>
                            <span class="mtext">
                                Khóa luận phản biện
                            </span>
                        </a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasAnyAuthority('ADMIN', 'MINISTRY')">
                    <li class="dropdown">
                        <a href="javascript:;" class="dropdown-toggle">
                            <span class="micon fa fa-sitemap"></span>
                            <span class="mtext">
                                <spring:message code="layout.leftSidebar.menu.label.trainingManagement"/>
                            </span>
                        </a>
                        <ul class="submenu">
                            <li><a href="${deparment}" class="${pagina.endsWith('/admin/departments/') ? 'active' : ''}">
                                <spring:message code="layout.leftSidebar.menu.label.sub.department"/>
                            </a></li>
                            <li><a href="${major}" class="${pagina.endsWith('/admin/majors/') ? 'active' : ''}">
                                <spring:message code="layout.leftSidebar.menu.label.sub.major"/>
                            </a></li>
                            <li><a href="${schoolYear}" class="${pagina.endsWith('/admin/school-years/') ? 'active' : ''}">
                                <spring:message code="layout.leftSidebar.menu.label.sub.schoolYear"/></a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="javascript:;" class="dropdown-toggle">
                            <span class="micon fa fa-user-o"></span>
                            <span class="mtext">
                                 <spring:message code="layout.leftSidebar.menu.label.lecturerManagement"/>
                            </span>
                        </a>
                        <ul class="submenu">
                            <li><a href="${position}" class="${pagina.endsWith('/admin/positions/') ? 'active' : ''}">
                                <spring:message code="layout.leftSidebar.menu.label.sub.position"/>
                            </a></li>
                            <li><a href="${lecturer}" class="${pagina.endsWith('/admin/lecturers/') ? 'active' : ''}">
                                <spring:message code="layout.leftSidebar.menu.label.sub.lecturer"/>
                            </a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="${student}" class="dropdown-toggle no-arrow ${pagina.endsWith('/admin/students/') ? 'active' : ''}">
                            <span class="micon fa fa-user"></span>
                            <span class="mtext">
                                <spring:message code="layout.leftSidebar.menu.label.student"/>
                            </span>
                        </a>
                    </li>
                    <li class="dropdown">
                        <a href="javascript:;" class="dropdown-toggle">
                            <span class="micon fa fa-file-word-o"></span>
                            <span class="mtext">
                                 <spring:message code="layout.leftSidebar.menu.label.thesisManagement"/>
                            </span>
                        </a>
                        <ul class="submenu">
                            <li><a href="${topic}" class="${pagina.endsWith('/admin/topics/') ? 'active' : ''}">
                                <spring:message code="layout.leftSidebar.menu.label.sub.topic"/>
                            </a></li>
                            <li><a href="${thesis}" class="${pagina.endsWith('/admin/theses/') ? 'active' : ''}">
                                <spring:message code="layout.leftSidebar.menu.label.sub.thesis"/>
                            </a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="${council}" class="dropdown-toggle no-arrow  ${pagina.endsWith('/admin/councils/') ? 'active' : ''}">
                            <span class="micon fa fa-users"></span><span class="mtext">
                            <spring:message code="layout.leftSidebar.menu.label.council"/>
                        </span>
                        </a>
                    </li>
                    <li>
                        <a href="${evaluationMethod}" class="dropdown-toggle no-arrow ${pagina.endsWith('/admin/evaluations-method/') ? 'active' : ''}">
                            <span class="micon fa fa-edit"></span><span class="mtext">
                            <spring:message code="layout.leftSidebar.menu.label.evaluationMethod"/>
                        </span>
                        </a>
                    </li>
                    <li>
                        <a href="${notification}" class="dropdown-toggle no-arrow ${pagina.endsWith('/admin/notifications/') ? 'active' : ''}">
                            <span class="micon fa fa-bell-o"></span><span class="mtext">
                                <spring:message code="layout.leftSidebar.menu.label.notificationManagement"/>
                        </span>
                        </a>
                    </li>
                    <li>
                        <a href="${news}" class="dropdown-toggle no-arrow ${pagina.endsWith('/admin/news/') ? 'active' : ''}">
                            <span class="micon fa fa-newspaper-o"></span><span class="mtext">
                                  <spring:message code="layout.leftSidebar.menu.label.newsManagement"/>
                        </span>
                        </a>
                    </li>
                    <li class="dropdown">
                        <a href="javascript:;" class="dropdown-toggle">
                            <span class="micon fa fa-pie-chart"></span><span class="mtext">
                             <spring:message code="layout.leftSidebar.menu.label.stats"/>
                        </span>
                        </a>
                        <ul class="submenu">
                            <li><a href="${scoreStatistics}" class=" ${pagina.endsWith('/admin/stats/score-statistics/') ? 'active' : ''}">
                                <spring:message code="layout.leftSidebar.menu.label.sub.scoreStatistics"/>
                            </a></li>
                            <li><a href="${frequencyStatistics}" class=" ${pagina.endsWith('/admin/stats/frequency-statistics/') ? 'active' : ''}">
                                <spring:message code="layout.leftSidebar.menu.label.sub.frequencyStatistics"/>
                            </a></li>
                        </ul>
                    </li>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <li class="dropdown">
                            <a href="javascript:;" class="dropdown-toggle">
                                <span class="micon fa fa-gears"></span><span class="mtext">
                                <spring:message code="layout.leftSidebar.menu.label.systemManagement"/>
                            </span>
                            </a>
                            <ul class="submenu">
                                <li><a href="${role}" class=" ${pagina.endsWith('/admin/roles/') ? 'active' : ''}">
                                    <spring:message code="layout.leftSidebar.menu.label.sub.role"/>
                                </a></li>
                                <li><a href="${manage}" class=" ${pagina.endsWith('/admin/manages/') ? 'active' : ''}">
                                    <spring:message code="layout.leftSidebar.menu.label.sub.manage"/>
                                </a></li>
                            </ul>
                        </li>
                    </sec:authorize>
                </sec:authorize>
                <li>
                    <a href="${chat}" class="dropdown-toggle no-arrow ${pagina.endsWith('/admin/chats/') ? 'active' : ''}">
                        <span class="micon dw dw-chat3"></span><span class="mtext">
                         <spring:message code="layout.leftSidebar.menu.label.chat"/>
                    </span>
                    </a>
                </li>
                <li>
                    <div class="dropdown-divider"></div>
                </li>
                <li class="text-center" style="margin-bottom: 70px;">
                    <a href="${logout}" class=" btn btn-danger">
                        <span class="micon dw dw-logout mr-2"></span>Đăng xuất
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
