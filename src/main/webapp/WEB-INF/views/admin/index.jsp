<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="col-xl-3 col-lg-6  mb-30">
        <div class="card-box height-100-p widget-style1">
            <div class="card-body">
                <div class="row">
                    <div class="col">
                        <h5 class="card-title text-uppercase text-muted mb-2">
                            <spring:message code="index.statsNumber.thesis.label"/>
                        </h5>
                        <span class="h2 font-weight-bold mb-0">${countAllThesis}</span>
                    </div>
                    <div class="col-auto">
                        <div class="bg-warning text-white rounded-circle shadow text-center" style="width: 60px; height: 60px;">
                            <i class="icon-copy fa fa-book font-30 mt-2" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-lg-6  mb-30">
        <div class="card-box height-100-p widget-style1">
            <div class="card-body">
                <div class="row">
                    <div class="col">
                        <h5 class="card-title text-uppercase text-muted mb-2">
                            <spring:message code="index.statsNumber.council.label"/>
                        </h5>
                        <span class="h2 font-weight-bold mb-0">${countAllCouncil}</span>
                    </div>
                    <div class="col-auto">
                        <div class="bg-success text-white rounded-circle shadow text-center" style="width: 60px; height: 60px;">
                            <i class="icon-copy fa fa-users font-30 mt-2" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-lg-6  mb-30">
        <div class="card-box height-100-p widget-style1">
            <div class="card-body">
                <div class="row">
                    <div class="col">
                        <h5 class="card-title text-uppercase text-muted mb-2">
                            <spring:message code="index.statsNumber.student.label"/>
                        </h5>
                        <span class="h2 font-weight-bold mb-0">${countAllStudent}</span>
                    </div>
                    <div class="col-auto">
                        <div class="bg-light-purple text-white rounded-circle shadow text-center" style="width: 60px; height: 60px;">
                            <i class="icon-copy fa fa-user-o font-30 mt-2" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-lg-6  mb-30">
        <div class="card-box height-100-p widget-style1">
            <div class="card-body">
                <div class="row">
                    <div class="col">
                        <h5 class="card-title text-uppercase text-muted mb-2">
                            <spring:message code="index.statsNumber.lecturer.label"/>
                        </h5>
                        <span class="h2 font-weight-bold mb-0">${countAllLecturer}</span>
                    </div>
                    <div class="col-auto">
                        <div class="bg-info text-white rounded-circle shadow text-center align-middle" style="width: 60px; height: 60px;">
                            <i class="icon-copy fa fa-user font-30 mt-2" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xl-12 mb-30">
        <div class="card-box height-100-p pd-20">
            <h4 class="h4 text-blue">
                <spring:message code="index.chart.scoreStatistics.title.label"/>
            </h4>
            <div id="chart5"></div>
        </div>
    </div>
    <div class="col-xl-12 mb-30">
        <div class="card-box height-100-p pd-20">
            <h4 class="h4 text-blue">
                <spring:message code="index.chart.frequencyStatistics.title.label"/>
            </h4>
            <div id="chart6"></div>
        </div>
    </div>
</div>



<script>
    document.addEventListener("DOMContentLoaded", () => {
        let data1 = []
        let labels1 = []
        let data2 = []
        let labels2 = []

        <c:forEach var="stats" items="${thesisStatisticsByMajor}">
        labels2.push('${stats[1]}')
        data2.push(${stats[2]})
        </c:forEach>
        thesisStatisticsByMajor('bar', labels2, data2)

        let a = {categories: [], series: {}}
        let keyArr = []
        let flag = false
        let idx = 0

        <c:forEach var="stats" items="${thesisScoreStatistics}">
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
    });
</script>
