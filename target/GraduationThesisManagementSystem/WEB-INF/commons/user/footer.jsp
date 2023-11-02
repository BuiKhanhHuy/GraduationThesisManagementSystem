<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<footer class="bg-primary text-white text-center text-lg-start">
    <!-- Grid container -->
    <div class="container p-4">
        <!--Grid row-->
        <div class="row">
            <!--Grid column-->
            <div class="col-lg-5 col-md-12 mb-4 mb-md-0">
               <img src="<c:url value="/public/common/images/logo/logo-text-white.png"/>" width="400"/>
            </div>
            <!--Grid column-->

            <!--Grid column-->
            <div class="col-lg-2 col-md-6 mb-4 mb-md-0">
                <h6 class="text-uppercase fw-bold mb-4">
                    <spring:message code="footer.category.label"/>
                </h6>
                <p>
                    <spring:message code="footer.category.home"/>
                </p>
                <p>
                    <spring:message code="footer.category.news"/>
                </p>
            </div>
            <!--Grid column-->

            <!--Grid column-->
            <div class="col-lg-5 col-md-6 mb-4 mb-md-0">
                <!-- Links -->
                <h6 class="text-uppercase fw-bold mb-4">
                    <spring:message code="footer.info.label"/>
                </h6>
                <p><i class="fas fa-home me-3"></i> Phòng 604 (Lầu 6), số 35 - 37 Hồ Hảo Hớn, Phường Cô Giang, Quận 1, Thành phố Hồ Chí Minh</p>
                <p>
                    <i class="fas fa-envelope me-3"></i>
                    hethongquanlykhoaluan@gmail.com
                </p>
                <p><i class="fas fa-phone me-3"></i> 0888-425-094</p>
            </div>
            <!--Grid column-->
        </div>
        <!--Grid row-->
    </div>
    <!-- Grid container -->

    <!-- Copyright -->
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        © 2020 Copyright:
        <a class="text-white" href="#">Huy Bui Khanh</a>
    </div>
    <!-- Copyright -->
</footer>