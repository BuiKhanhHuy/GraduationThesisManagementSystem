<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="now" class="java.util.Date"/>

<section style="padding-top: 120px; padding-bottom: 120px;" class="bg-primary">
</section>
<section class="container-lg mb-5" style="margin-top: -140px;">
    <div class="box-shadow shadow-lg bg-white rounded">
        <div class="p-5">
            <h5>
                <spring:message code="index.thesis.list.title"/>
            </h5>
            <div class="mt-4">
                <table class="table table-responsive-lg ">
                    <thead>
                    <tr>
                        <th scope="col"><spring:message code="index.thesis.list.table.header.code"/></th>
                        <th scope="col"><spring:message code="index.thesis.list.table.header.topic"/></th>
                        <th scope="col" class="text-center"><spring:message
                                code="index.thesis.list.table.header.startDate"/></th>
                        <th scope="col" class="text-center"><spring:message
                                code="index.thesis.list.table.header.endDate"/></th>
                        <th scope="col"><spring:message code="index.thesis.list.table.header.major"/></th>
                        <th scope="col"><spring:message code="index.thesis.list.table.header.schoolYear"/></th>
                        <th scope="col"><spring:message code="index.thesis.list.table.header.status"/></th>
                        <th scope="col" class="text-center"><spring:message
                                code="index.thesis.list.table.header.action"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${student != null && student.theses.size() != 0}">
                        <c:forEach var="thesis" items="${student.theses}">
                            <tr>
                                <th scope="row"> ${thesis.code}</th>
                                <td>
                                    <c:if test="${thesis.topic != null}">
                                        ${thesis.topic.name}
                                    </c:if>
                                    <c:if test="${thesis.topic == null}">
                                        <span class="text-black-50 text-center">
                                            <spring:message code="index.thesis.list.table.data.notUpdate"/>
                                        </span>
                                    </c:if>
                                </td>
                                <td class="text-center">
                                    <fmt:formatDate type="date" value="${thesis.startDate}"/>
                                </td>
                                <td class="text-center">
                                    <fmt:formatDate type="date" value="${thesis.complateDate}"/>
                                </td>
                                <td>
                                    <c:if test="${thesis.major != null}">
                                        ${thesis.major.name}
                                    </c:if>
                                    <c:if test="${thesis.major == null}">
                                        <span class="text-black-50 text-center">
                                             <spring:message code="index.thesis.list.table.data.notUpdate"/>
                                        </span>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${thesis.schoolYear != null}">
                                        ${thesis.schoolYear.name}
                                    </c:if>
                                    <c:if test="${thesis.schoolYear == null}">
                                        <span class="text-black-50 text-center">
                                             <spring:message code="index.thesis.list.table.data.notUpdate"/>
                                        </span>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${thesis.thesisStartDate le now and now le thesis.thesisEndDate}">
                                        <span class="text-success">
                                             <spring:message code="index.thesis.list.table.header.status.active"/>
                                        </span>
                                    </c:if>
                                    <c:if test="${thesis.thesisStartDate lt now and thesis.thesisEndDate lt now}">
                                        <span class="text-danger">
                                             <spring:message code="index.thesis.list.table.header.status.unActive"/>
                                        </span>
                                    </c:if>
                                </td>
                                <td class="text-center col-2">
                                    <a href="<c:url value="thesis/${thesis.id}"/>" class="btn btn-info btn-sm">
                                        <spring:message code="index.thesis.list.table.header.action.button"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${student != null &&  student.theses.size() == 0}">
                        <tr>
                            <td colspan="11" class="text-black-50 text-center">
                                <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"
                                     alt="empty"/>
                                <p class="text-center">
                                    <spring:message code="index.thesis.list.table.data.empty"/>
                                </p>
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
