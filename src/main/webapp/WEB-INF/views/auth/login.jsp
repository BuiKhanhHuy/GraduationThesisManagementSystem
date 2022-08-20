<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="vh-100" style="background-color: hsl(217, 87%, 50%);">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100 ">
            <div class="col-12 col-md-8 col-lg-6 col-xl-4">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <form action="<c:url value="/login"/>" method="post">
                            <h3 class="mb-5">
                                <spring:message code="layout.login.title.label"/>
                            </h3>
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger" role="alert">
                                  <spring:message code="layout.login.error.label" />
                                </div>
                            </c:if>
                            <c:if test="${param.accessDenied != null}">
                                <div class="alert alert-danger" role="alert">
                                    <spring:message code="layout.login.accessDenied" />
                                </div>
                            </c:if>
                            <div class="form-outline mb-5">
                                <input type="text" id="username" name="username" class="form-control form-control-lg"/>
                                <label class="form-label" for="username">
                                    <spring:message code="layout.login.username.label"/>
                                </label>
                            </div>

                            <div class="form-outline mb-5">
                                <input type="password" id="password" name="password"
                                       class="form-control form-control-lg"/>
                                <label class="form-label" for="password">
                                    <spring:message code="layout.login.password.label"/>
                                </label>
                            </div>

                            <button class="btn btn-primary btn-lg btn-block" type="submit">
                                <spring:message code="layout.login.loginButton.label"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


