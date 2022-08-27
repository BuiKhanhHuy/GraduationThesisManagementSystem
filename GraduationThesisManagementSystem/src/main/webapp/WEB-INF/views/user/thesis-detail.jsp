<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="now" class="java.util.Date"/>

<c:url var="appContext" value="/"/>


<section style="padding-top: 120px; padding-bottom: 120px;" class="bg-primary">
</section>
<section class="container-lg mb-5" style="margin-top: -140px;">
    <div class="box-shadow shadow-lg bg-white rounded">
        <div class="p-5">
            <h5>
                <spring:message code="thesisDetail.title"/>
            </h5>
            <div class="mt-4">
                <div class="row">
                    <div class="col-md-6">
                        <h6 class="mb-3 text-danger"><spring:message code="thesisDetail.title"/></h6>
                        <table class="table table-responsive-lg ">
                            <tbody>
                            <tr>
                                <th scope="row"><spring:message code="thesisDetail.code"/>:</th>
                                <td>${thesis.code}</td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="thesisDetail.topic"/>:</th>
                                <td>
                                    <c:if test="${thesis.topic != null}">
                                        ${thesis.topic.name}
                                    </c:if>
                                    <c:if test="${thesis.topic == null}">
                                        <span class="text-black-50">
                                            <spring:message code="index.thesis.list.table.data.notUpdate"/>
                                        </span>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="thesisDetail.lecturers"/>:</th>
                                <td>
                                    <c:forEach var="lecturer" items="${thesis.lecturers}">
                                        ${lecturer.fullName},
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="thesisDetail.reviewLecturer"/>:</th>
                                <td>
                                    <c:if test="${thesis.reviewLecturer != null}">
                                        ${thesis.reviewLecturer.fullName}
                                    </c:if>
                                    <c:if test="${thesis.reviewLecturer == null}">
                                        <span class="text-black-50">
                                            <spring:message code="index.thesis.list.table.data.notUpdate"/>
                                        </span>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="thesisDetail.students"/>:</th>
                                <td>
                                    <c:forEach var="student" items="${thesis.students}">
                                        <span>${student.fullName}</span>,
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="thesisDetail.date"/>:</th>
                                <td>
                                    <fmt:formatDate type="date" value="${thesis.startDate}"/> - <fmt:formatDate
                                        type="date" value="${thesis.complateDate}"/>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="thesisDetail.deadline"/>:</th>
                                <td>
                                    <fmt:formatDate type="date" value="${thesis.thesisStartDate}"/> -
                                    <fmt:formatDate type="date" value="${thesis.thesisEndDate}"/>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="thesisDetail.major"/>:</th>
                                <td>
                                    <c:if test="${thesis.major != null}">
                                        ${thesis.major.name}
                                    </c:if>
                                    <c:if test="${thesis.major == null}">
                                        <span class="text-black-50">
                                            <spring:message code="index.thesis.list.table.data.notUpdate"/>
                                        </span>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="thesisDetail.schoolYear"/>:</th>
                                <td>
                                    <c:if test="${thesis.schoolYear != null}">
                                        ${thesis.schoolYear.name}
                                    </c:if>
                                    <c:if test="${thesis.schoolYear == null}">
                                        <span class="text-black-50">
                                            <spring:message code="index.thesis.list.table.data.notUpdate"/>
                                        </span>
                                    </c:if>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-6">
                        <div>
                            <h6 class="mb-3 text-danger"><spring:message code="thesisDetail.file.title"/></h6>
                            <table class="table table-responsive-lg ">
                                <tbody>
                                <tr>
                                    <th scope="row"><spring:message code="thesisDetail.file.link"/>:</th>
                                    <td>
                                        <c:if test="${thesis.reportFile != null && !thesis.reportFile.isEmpty()}">
                                            <a href="${thesis.reportFile}" class="text-blue"><i
                                                    class="icon-copy fa fa-download"
                                                    aria-hidden="true"></i>
                                                <spring:message code="thesisDetail.file.download"/>
                                            </a>
                                        </c:if>
                                        <c:if test="${thesis.reportFile == null || thesis.reportFile.isEmpty()}">
                                                    <span class="text-black-50"><i class="icon-copy fa fa-download"
                                                                                   aria-hidden="true"></i>
                                                       <spring:message code="thesisDetail.file.empty"/>
                                                    </span>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><spring:message code="thesisDetail.file.status"/>:</th>
                                    <td>
                                        <c:if test="${thesis.reportFile == null || thesis.reportFile.isEmpty()}">
                                            <span class="text-danger font-weight-bold">
                                                <spring:message code="thesisDetail.file.status.unfinished"/>
                                            </span>
                                        </c:if>
                                        <c:if test="${thesis.reportFile != null && !thesis.reportFile.isEmpty()}">
                                            <span class="text-success font-weight-bold">
                                                 <spring:message code="thesisDetail.file.status.done"/>
                                            </span>
                                        </c:if>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="mt-4">
                            <h6 class="mb-3 text-danger"><spring:message code="thesisDetail.result.title"/></h6>
                            <table class="table table-responsive-lg ">
                                <tbody>
                                <tr>
                                    <th scope="row"><spring:message code="thesisDetail.result.score"/>:</th>
                                    <td style="font-weight: bold; color: black  ; font-size: 18px; vertical-align: middle;">
                                        <fmt:formatNumber type="number"
                                                          groupingUsed="false" value="${thesis.totalScore}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><spring:message code="thesisDetail.result.result"/>:</th>
                                    <td class="font-weight-bold"
                                        style="font-weight: bold; font-size: 14px; vertical-align: middle;">
                                        <c:if test="${thesis.result == 1}">
                                            <span class="text-warning font-weight-bold">
                                                <spring:message code="thesisDetail.result.result.1"/>
                                            </span>
                                        </c:if>
                                        <c:if test="${thesis.result == 2}">
                                            <span class="text-danger font-weight-bold">
                                                 <spring:message code="thesisDetail.result.result.2"/>
                                            </span>
                                        </c:if>
                                        <c:if test="${thesis.result == 3}">
                                            <span class="text-success font-weight-bold">
                                                <spring:message code="thesisDetail.result.result.3"/>
                                            </span>
                                        </c:if>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="mt-4 d-flex align-items-end flex-column">
                            <c:if test="${thesis.thesisStartDate le now and now le thesis.thesisEndDate}">
                                <button onclick="uploadReportFile('${appContext}', ${thesis.id})"
                                        class="mt-auto btn btn-warning">
                                    <i class="fa-solid fa-upload"
                                       style="font-size: 16px; margin-right: 5px;"></i><spring:message
                                        code="thesisDetail.reportFile.button.label"/>
                                </button>
                            </c:if>
                            <c:if test="${thesis.thesisStartDate lt now and thesis.thesisEndDate lt now}">
                                <button disabled
                                        class="mt-auto btn btn-warning">
                                    <i class="fa-solid fa-upload" style="font-size: 16px; margin-right: 5px;"></i>
                                    <spring:message code="thesisDetail.reportFile.button.label"/>
                                </button>
                                <p class="mt-2 text-danger"><spring:message
                                        code="thesisDetail.reportFile.button.overtime"/></p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
