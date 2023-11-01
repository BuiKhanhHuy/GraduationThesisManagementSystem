<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="filterScoreStatistics" value="/admin/stats/score-statistics"/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>
                    <spring:message code="scoreStatistics.header.title.label"/>
                </h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">
                        <spring:message code="layout.home.label"/>
                    </a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="scoreStatistics.header.title.label"/>
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
                <spring:message code="scoreStatistics.chart.title"/>
            </h4>
            <div id="chart5"></div>
        </div>
    </div>
</div>

<div class="pd-20 col-xl-12 card-box mb-30">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">
                <spring:message code="scoreStatistics.table.list.title.label"/>
            </h4>
        </div>
        <form id="form-filter" action="${filterScoreStatistics}">
            <input name="page" id="page" hidden/>
            <div class="row justify-content-end mt-2">
                <div class="col-md-3 col-sm-12">
                    <div class="form-group">
                        <select class="custom-select2 form-control" name="schoolYearId" id="schoolYearId"
                                style="width: 100%">
                            <option value="${""}">
                                <spring:message code="scoreStatistics.table.search.schoolYear"/>
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
                            <spring:message code="scoreStatistics.table.search.button.label"/>
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr id="stats-school-year-area">
            <th scope="col" class="text-center">
                <spring:message code="scoreStatistics.table.list.header.no"/>
            </th>
            <th scope="col">
                <spring:message code="scoreStatistics.table.list.header.major"/>
            </th>
        </tr>
        </thead>
        <tbody id="stats-score-area">
        <c:if test="${scoreStatistics.size() == 0}">
            <tr>
                <td colspan="6" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">
                        <spring:message code="scoreStatistics.table.list.data.empty"/>
                    </p>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        <%--let a = {categories: [], series: {}}--%>
        <%--<c:forEach var="stats" items="${scoreStatistics}">--%>
        <%--if (a.categories.indexOf('${stats[0]}') === -1) {--%>
        <%--    a.categories.push('${stats[0]}')--%>
        <%--}--%>

        <%--if ('${stats[1]}' in a.series) {--%>
        <%--    a.series['${stats[1]}'].push(${stats[2]})--%>
        <%--} else {--%>
        <%--    a.series['${stats[1]}'] = [${stats[2]}]--%>
        <%--}--%>
        <%--</c:forEach>--%>


        let a = {categories: [], series: {}}
        let keyArr = []
        let flag = false
        let idx = 0

        <c:forEach var="stats" items="${scoreStatistics}">
        if (a.categories.indexOf('${stats[0]}') === -1) {
            a.categories.push('${stats[0]}')
        }

        if ('${stats[1]}' in a.series) {
            flag = true;
        } else {
            keyArr.push('${stats[1]}')
            a.series['${stats[1]}'] = [${stats[2]}]
        }

        if (flag) {
            a.series[keyArr[idx]].push('${stats[2]}' === '' ? null : parseInt('${stats[2]}'))

            idx++;
            if (idx > keyArr.length)
                idx = 0;
        }
        </c:forEach>

        thesisScoreStatistics(type = "spline", a);
        showStatsOnTable(a)
    });
</script>
