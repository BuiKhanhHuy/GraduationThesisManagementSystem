<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="javascripts"/>
<tiles:importAttribute name="javascriptsModule"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>Hệ thống quản lý khóa luận</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="apple-touch-icon" sizes="180x180" href="<c:url value="/public/admin/vendors/images/ou-icon.png"/> ">
    <link rel="icon" type="image/png" sizes="32x32" href="<c:url value="/public/admin/vendors/images/ou-icon.png"/> ">
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/public/admin/vendors/images/ou-icon.png"/> ">
    <!-- Place favicon.ico in the root directory -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="<c:url value="/public/common/plugins/pagination/jquery.twbsPagination.min.js"/> "></script>

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          rel="stylesheet"/>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
          rel="stylesheet"/>
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/4.4.0/mdb.min.css"
          rel="stylesheet"/>

    <%--    sweetalert2--%>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" id="theme-styles">

    <%--    overlay loading--%>
    <link href="<c:url value="/public/common/css/overlay/overlay-style.css"/> " rel="stylesheet">

    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/> ">
    </c:forEach>
</head>

<body>
<div id="cover-spin"></div>
<div id="loading-area"></div>

<tiles:insertAttribute name="header"/>
<section style="background-color: #f6f9fc;">
    <tiles:insertAttribute name="content"/>
    <tiles:insertAttribute name="footer"/>
</section>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
<!-- MDB -->
<script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/4.4.0/mdb.min.js"
></script>
<script src="<c:url value="/public/common/js/script.js"/> "></script>
<script src="<c:url value="/public/user/src/scripts/script.js"/> "></script>
<c:forEach var="js" items="${javascriptsModule}">
    <script type="module" src="<c:url value="${js}"/>"></script>
</c:forEach>
<c:forEach var="js" items="${javascripts}">
    <script src="<c:url value="${js}"/>"></script>
</c:forEach>
</body>
</html>