<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="javascripts"/>
<tiles:importAttribute name="javascriptsModule"/>

<!DOCTYPE html>
<html>
<head>
    <!-- Basic Page Info -->
    <meta charset="utf-8">
    <title><tiles:insertAttribute name="title"/></title>
    <!-- Site favicon -->
    <link rel="apple-touch-icon" sizes="180x180" href="<c:url value="/public/admin/vendors/images/ou-icon.png"/> ">
    <link rel="icon" type="image/png" sizes="32x32" href="<c:url value="/public/admin/vendors/images/ou-icon.png"/> ">
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/public/admin/vendors/images/ou-icon.png"/> ">

    <!-- Mobile Specific Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%--    pagination--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="<c:url value="/public/common/plugins/pagination/jquery.twbsPagination.min.js"/> "></script>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap"
          rel="stylesheet">
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/public/admin/vendors/styles/core.css"/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value="/public/admin/vendors/styles/icon-font.min.css"/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value="/public/admin/vendors/styles/style.css"/> ">

    <%--    sweetalert2--%>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" id="theme-styles">

    <%--    toast--%>
    <link rel="stylesheet" href="<c:url value="/public/admin/src/plugins/toast/jquery.toast.css"/>">

    <%--    overlay loading--%>
    <link href="<c:url value="/public/common/css/overlay/overlay-style.css"/> " rel="stylesheet">

    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="${css}">
    </c:forEach>

    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-119386393-1"></script>
    <script>
        window.dataLayer = window.dataLayer || [];

        function gtag() {
            dataLayer.push(arguments);
        }

        gtag('js', new Date());

        gtag('config', 'UA-119386393-1');
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"
            integrity="sha512-CryKbMe7sjSCDPl18jtJI5DR5jtkUWxPXWaLCst6QjH8wxDexfRJic2WRmRXmstr2Y8SxDDWuBO6CQC6IE4KTA=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<div id="cover-spin"></div>
<%--<div class="pre-loader">--%>
<%--    <div class="pre-loader-box">--%>
<%--        <div class="loader-logo"><img src="vendors/images/deskapp-logo.svg" alt=""></div>--%>
<%--        <div class='loader-progress' id="progress_div">--%>
<%--            <div class='bar' id='bar1'></div>--%>
<%--        </div>--%>
<%--        <div class='percent' id='percent1'>0%</div>--%>
<%--        <div class="loading-text">--%>
<%--            Loading...--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<tiles:insertAttribute name="header"/>

<tiles:insertAttribute name="rightSidebar"/>

<tiles:insertAttribute name="leftSidebar"/>

<div class="mobile-menu-overlay"></div>

<div class="main-container" id="main-content">
    <div class="pd-ltr-20">
        <tiles:insertAttribute name="content"/>
        <tiles:insertAttribute name="footer"/>
    </div>
</div>

<!-- js -->
<script src="<c:url value="/public/admin/src/scripts/script.js"/> "></script>
<script src="<c:url value="/public/admin/vendors/scripts/core.js"/>"></script>
<script src="<c:url value="/public/admin/vendors/scripts/script.min.js"/>"></script>
<script src="<c:url value="/public/admin/vendors/scripts/process.js"/>"></script>
<script src="<c:url value="/public/admin/vendors/scripts/layout-settings.js"/>"></script>
<script src="<c:url value="/public/common/js/script.js"/> "/>

<script src="<c:url value="/public/admin/src/plugins/toast/jquery.toast.js"/> "></script>

<c:forEach var="js" items="${javascriptsModule}">
    <script type="module" src="<c:url value="${js}"/>"></script>
</c:forEach>
<c:forEach var="js" items="${javascripts}">
    <script src="<c:url value="${js}"/>"></script>
</c:forEach>
</body>
</html>