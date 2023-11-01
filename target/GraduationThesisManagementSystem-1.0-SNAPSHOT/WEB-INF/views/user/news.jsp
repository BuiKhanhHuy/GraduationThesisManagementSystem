<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="filterNews" value=""/>

<section style="padding-top: 120px; padding-bottom: 120px;" class="bg-primary">
</section>
<form id="form-filter" action="${filterNews}">
    <input name="page" id="page" hidden/>
</form>

<section class="container-lg mb-5" style="margin-top: -140px;">
    <div class="box-shadow shadow-lg bg-white rounded">
        <div class="p-5">
            <h5>
                <spring:message code="news.header.title.label" />
            </h5>
            <div class="mt-4">
                <div class="container">
                    <section>
                        <div class="row">
                            <c:forEach var="n" items="${news}">
                                <div class="col-lg-6 col-md-6 mb-4 mb-lg-0">
                                    <div class="p-2">
                                        <a href="<c:url value="/news/${n.id}"/>" class="text-dark">
                                            <div class="row mb-4 pb-2">
                                                <div class="col-3">
                                                    <img src="<c:url value="/public/common/images/news/news.jpg"/>"
                                                         class="img-fluid shadow-1-strong rounded"
                                                         alt="Five Lands National Park"/>
                                                </div>

                                                <div class="col-9">
                                                    <p class="mb-2"><strong>${n.title}</strong></p>
                                                    <p>
                                                        <u class="notification-created-date text-black-50 text-decoration-none">${n.createdDate}</u>
                                                    </p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </section>
                    <c:if test="${Math.ceil(totalResult / pageSize) > 1}">
                        <nav class="my-4" aria-label="...">
                            <div class="btn-toolbar justify-content-center">
                                <ul class="pagination" id="pagination"></ul>
                            </div>
                        </nav>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    let currentPage = ${page};
    let totalResult = ${totalResult};
    let pageSize = ${pageSize};


    $('#pagination').twbsPagination({
        totalPages: Math.ceil(totalResult / pageSize),
        visiblePages: 8,
        first: '',
        last: '',
        prev: '&laquo;',
        next: '&raquo;',
        startPage: currentPage,
        onPageClick: function (event, page) {
            if (currentPage != page) {
                $("#page").val(page)
                $("#form-filter").submit();
            }
        }
    });
</script>