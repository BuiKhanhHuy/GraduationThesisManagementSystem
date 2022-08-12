<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<header class="header other-page">
    <div class="navbar-area">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-12">
                    <nav class="navbar navbar-expand-lg">
                        <a class="navbar-brand logo" href="index.html">
                            <img class="logo1" src="<c:url value="/public/common/images/logo/logo-text-blue.png"/> " alt="Logo" style="width: 320px;"/>
                        </a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="toggler-icon"></span>
                            <span class="toggler-icon"></span>
                            <span class="toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse sub-menu-bar" id="navbarSupportedContent">
                            <ul id="nav" class="navbar-nav ml-auto">
                                <li class="nav-item"><a href="index.html">Home</a></li>
                                <li class="nav-item"><a href="#" class="active">Pages</a>
                                    <ul class="sub-menu">
                                        <li><a href="about-us.html" class="active">About Us</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item"><a href="#">Candidates</a></li>
                                <li class="nav-item"><a href="#">Employers </a></li>
                                <li class="nav-item"><a href="#">Blog</a></li>
                                <li class="nav-item"><a href="contact.html">Contact </a> </li>
                            </ul>
                        </div>
                        <!-- navbar collapse -->
                        <div class="button">
                            <a href="javacript:" data-toggle="modal" data-target="#signup" class="btn">Sign Up</a>
                        </div>
                    </nav>
                    <!-- navbar -->
                </div>
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- navbar area -->
</header>
<section class="section my-0 py-5">
</section>

<section class="apply-process section">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-4 col-12">
                <div class="process-item">
                    <i class="lni lni-user"></i>
                    <h4>Register Your Account</h4>
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-12">
                <div class="process-item">
                    <i class="lni lni-book"></i>
                    <h4>Upload Your Resume</h4>
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-12">
                <div class="process-item">
                    <i class="lni lni-briefcase"></i>
                    <h4>Apply for Dream Job</h4>
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
                </div>
            </div>
        </div>
    </div>
</section>