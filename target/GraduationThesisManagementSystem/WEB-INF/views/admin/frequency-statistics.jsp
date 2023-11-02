<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="filterFrequencyStatistics" value="/admin/stats/frequency-statistics"/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>
                    <spring:message code="frequencyStatistics.header.title.label"/>
                </h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">
                        <spring:message code="layout.home.label"/>
                    </a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="frequencyStatistics.header.title.label"/>
                    </li>
                </ol>
            </nav>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xl-12 mb-30">
        <div class="card-box height-100-p pd-20">
            <h4 class="h4 text-blue">
                <spring:message code="frequencyStatistics.chart.title"/>
            </h4>
            <div id="chart6"></div>
        </div>
    </div>
</div>

<div class="pd-20 col-xl-12 card-box mb-30">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">
                <spring:message code="frequencyStatistics.table.list.title.label"/>
            </h4>
        </div>
        <form id="form-filter" action="${filterFrequencyStatistics}">
            <div class="row justify-content-end mt-2">
                <div class="col-md-3 col-sm-12">
                    <div class="form-group">
                        <select class="custom-select2 form-control" name="schoolYearId" id="schoolYearId"
                                style="width: 100%">
                            <option value="${""}">
                                <spring:message code="frequencyStatistics.table.search.schoolYear"/>
                            </option>
                            <c:forEach var="schoolYearOption" items="${schoolYearOptions}">
                                <option value="${schoolYearOption[0]}">${schoolYearOption[1]}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-md-3 col-sm-12">
                    <div>
                        <button class="form-control ml-1 btn-warning btn" type="submit">
                            <spring:message code="frequencyStatistics.table.search.button.label"/>
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <table class="table table-sm table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">
                <spring:message code="frequencyStatistics.table.list.header.no"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="frequencyStatistics.table.list.header.major"/>
            </th>
            <th scope="col" class="text-center">
                <spring:message code="frequencyStatistics.table.list.header.quantity"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${frequencyStatistics.size() > 0}">
            <c:forEach var="stats" items="${frequencyStatistics}" varStatus="loop">
                <tr>
                    <td class="text-center">${loop.index + 1}</td>
                    <td>${stats[1]}</td>
                    <td class="text-center">${stats[2]}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${frequencyStatistics.size() == 0}">
            <tr>
                <td colspan="6" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">
                        <spring:message code="frequencyStatistics.table.list.data.empty"/>
                    </p>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        let data2 = []
        let labels2 = []

        <c:forEach var="stats" items="${frequencyStatistics}">
        labels2.push('${stats[1]}')
        data2.push(${stats[2]})
        </c:forEach>

        thesisStatisticsByMajor('bar', labels2, data2)
    });
</script>
