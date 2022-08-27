<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section style="padding-top: 120px; padding-bottom: 120px;" class="bg-primary">
</section>

<section class="container-lg mb-5" style="margin-top: -140px;">
    <div class="box-shadow shadow-lg bg-white rounded">
        <div class="p-5">
            <div>
                <div class="row mb-3">
                    <div class="col-6">
                        <a href="" class="text-info">
                            <i class="fas fa-user"></i>
                           <c:if test="${newsDetail.user != null}">
                               ${newsDetail.user.username}
                           </c:if>
                            <c:if test="${newsDetail.user == null}">
                                Chưa cập nhật
                            </c:if>
                        </a>
                    </div>
                    <div class="col-6 text-end">
                        <u class="notification-created-date text-decoration-none" style="font-style: italic;">${newsDetail.createdDate}</u>
                    </div>
                </div>

                <div>
                    <h4 class="mb-4">${newsDetail.title}</h4>

                    <p>
                       ${newsDetail.content}
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>