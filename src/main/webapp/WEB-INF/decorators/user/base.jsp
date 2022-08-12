<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="javascripts"/>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title><tiles:insertAttribute name="title"/></title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.svg"/>
    <!-- Place favicon.ico in the root directory -->

    <!-- Web Font -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- ========================= CSS here ========================= -->
    <link rel="stylesheet" href="<c:url value="/public/user/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/public/user/css/LineIcons.2.0.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/public/user/css/animate.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/public/user/css/tiny-slider.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/public/user/css/glightbox.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/public/user/css/main.css"/>"/>

    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="${css}">
    </c:forEach>
</head>

<body>
<div id="loading-area"></div>
<tiles:insertAttribute name="header"/>

<tiles:insertAttribute name="content"/>

<tiles:insertAttribute name="footer"/>

<!-- ========================= JS here ========================= -->
<script src="<c:url value="/public/user/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/public/user/js/wow.min.js"/>"></script>
<script src="<c:url value="/public/user/js/tiny-slider.js"/>"></script>
<script src="<c:url value="/public/user/js/glightbox.min.js"/>"></script>
<script src="<c:url value="/public/user/js/main.js"/>"></script>
<script type="text/javascript">
    //====== Clients Logo Slider
    tns({
        container: '.client-logo-carousel',
        slideBy: 'page',
        autoplay: true,
        autoplayButtonOutput: false,
        mouseDrag: true,
        gutter: 15,
        nav: false,
        controls: false,
        responsive: {
            0: {
                items: 1,
            },
            540: {
                items: 2,
            },
            768: {
                items: 3,
            },
            992: {
                items: 4,
            },
            1170: {
                items: 6,
            }
        }
    });
    //========= glightbox
    GLightbox({
        'href': 'https://www.youtube.com/watch?v=cz4z8CyvDas',
        'type': 'video',
        'source': 'youtube', //vimeo, youtube or local
        'width': 900,
        'autoplayVideos': true,
    });
</script>

<c:forEach var="js" items="${javascripts}">
    <script src="<c:url value="${js}"/>"></script>
</c:forEach>
</body>

</html>