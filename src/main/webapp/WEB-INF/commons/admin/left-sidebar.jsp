<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="adminIndex" value="/admin/"/>
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

<c:url var="role" value="/admin/roles/"/>

<c:url var="manage" value="/admin/manages/"/>
<c:url var="news" value="/admin/news/"/>
<c:url var="notification" value="/admin/notifications/"/>
<c:url var="chat" value="/admin/chats/"/>

<div class="left-side-bar">
    <div class="brand-logo">
        <a href="index.html">
            <img src="<c:url value="/admin/vendors/images/deskapp-logo.svg"/> " alt="" class="dark-logo">
            <img src="<c:url value="/admin/vendors/images/deskapp-logo-white.svg"/> " alt="" class="light-logo">
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
                    <a href="${adminIndex}" class="dropdown-toggle no-arrow">
                        <span class="micon fa fa-dashboard"></span><span class="mtext">Bảng điều khiển</span>
                    </a>
                </li>
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle">
                        <span class="micon fa fa-sitemap"></span><span class="mtext">Quản lý đào tạo</span>
                    </a>
                    <ul class="submenu">
                        <li><a href="${deparment}">Khoa</a></li>
                        <li><a href="${major}">Ngành</a></li>
                        <li><a href="${schoolYear}">Niên khóa</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle">
                        <span class="micon fa fa-user-o"></span><span class="mtext">Quản lý giảng viên</span>
                    </a>
                    <ul class="submenu">
                        <li><a href="${position}">Chức vụ</a></li>
                        <li><a href="${lecturer}">Giảng viên</a></li>
                    </ul>
                </li>
                <li>
                    <a href="${student}" class="dropdown-toggle no-arrow">
                        <span class="micon fa fa-user"></span><span class="mtext">Sinh viên</span>
                    </a>
                </li>
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle">
                        <span class="micon fa fa-file-word-o"></span><span class="mtext">Quản lý khóa luận</span>
                    </a>
                    <ul class="submenu">
                        <li><a href="${topic}">Đề tài</a></li>
                        <li><a href="ui-cards.html">Khóa luận</a></li>
                    </ul>
                </li>
                <li>
                    <a href="calendar.html" class="dropdown-toggle no-arrow">
                        <span class="micon fa fa-users"></span><span class="mtext">Hội đồng</span>
                    </a>
                </li>
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle">
                        <span class="micon fa fa-edit"></span><span class="mtext">Quản lý đánh giá</span>
                    </a>
                    <ul class="submenu">
                        <li><a href="${evaluationMethod}">Phương pháp đánh giá</a></li>
                        <li><a href="${scoreComponent}">Thành phần điểm</a></li>
                        <li><a href="${scoreColumn}">Cột điểm</a></li>
                    </ul>
                </li>
                <li>
                    <a href="${notification}" class="dropdown-toggle no-arrow">
                        <span class="micon fa fa-bell-o"></span><span class="mtext">Quản lý thông báo</span>
                    </a>
                </li>
                <li>
                    <a href="${news}" class="dropdown-toggle no-arrow">
                        <span class="micon fa fa-newspaper-o"></span><span class="mtext">Quản lý bảng tin</span>
                    </a>
                </li>
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle">
                        <span class="micon fa fa-pie-chart"></span><span class="mtext">Thống kê</span>
                    </a>
                    <ul class="submenu">
                        <li><a href="highchart.html">Thống kê điểm khóa luận</a></li>
                        <li><a href="knob-chart.html">Thống kê tần suất khóa luận</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle">
                        <span class="micon fa fa-gears"></span><span class="mtext">Quản lý hệ thống</span>
                    </a>
                    <ul class="submenu">
                        <li><a href="${role}">Quyền</a></li>
                        <li><a href="${manage}">Quản lý</a></li>
                    </ul>
                </li>
                <li>
                    <a href="${chat}" class="dropdown-toggle no-arrow">
                        <span class="micon dw dw-chat3"></span><span class="mtext">Trò chuyện</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
