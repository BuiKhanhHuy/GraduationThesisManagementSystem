<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="vh-100" style="background-color: hsl(0, 0%, 96%)">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-4 col-lg-4 col-xl-4">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5">
                        <form action="<c:url value="/login"/>" method="post">
                            <h4 class="mb-5 text-center">Đăng nhập</h4>
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger" role="alert">
                                    Thông tin đăng nhập không hợp lệ!
                                </div>
                            </c:if>
                            <c:if test="${param.accessDenied != null}">
                                <div class="alert alert-danger" role="alert">
                                    Bạn không có quyên truy cập!
                                </div>
                            </c:if>
                            <div class="form-outline mb-4">
                                <label class="form-label" for="username">Tên người dùng</label>
                                <input type="text" id="username" name="username" class="form-control form-control-lg"/>
                            </div>

                            <div class="form-outline mb-4">
                                <label class="form-label" for="password">Mật khẩu</label>
                                <input type="password" id="password" name="password"
                                       class="form-control form-control-lg"/>
                            </div>
                            <button class="btn btn-primary btn-lg btn-block" type="submit">Đăng nhập</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
