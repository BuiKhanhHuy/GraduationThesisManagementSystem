<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="home" value="/admin/"/>
<c:url var="appContext" value="/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-6 col-sm-12">
            <div class="title">
                <h4>
                    <spring:message code="councilDetail.header.title"/>
                </h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}"><spring:message code="layout.home.label"/></a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <spring:message code="councilDetail.header.title"/>
                    </li>
                </ol>
            </nav>
        </div>
        <div class="col-md-6 col-sm-12 text-right">
            <c:if test="${councilDetailDetail.council.block == false}">
                <span class="text-center text-success font-weight-bold">
                        <i class="icon-copy fa fa-unlock" aria-hidden="true"></i>
                    <spring:message code="councilDetail.header.unLock"/></span>
            </c:if>
            <c:if test="${councilDetailDetail.council.block == true}">
                <span class="text-center text-danger font-weight-bold">
                        <i class="icon-copy fa fa-lock" aria-hidden="true"></i>
                   <spring:message code="councilDetail.header.lock"/></span>
            </c:if>
        </div>
    </div>
</div>

<c:if test="${councilDetailDetail.council.block == true}">
    <div class="alert alert-warning alert-dismissible fade show" role="alert">

        <strong> <spring:message code="councilDetail.header.lock"/>!</strong> <spring:message code="councilDetail.warning"/>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>

<div class="row">
    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 mb-30">
        <div class="pd-20 card-box height-100-p">
            <div class="profile-info border-top-0">
                <h5 class="mb-20 h5 text-blue"><spring:message code="councilDetail.info.title"/></h5>
                <ul>
                    <li>
                        <span><spring:message code="councilDetail.info.councilName"/>:</span>
                        ${councilDetailDetail.council.name}
                    </li>
                    <li>
                        <span><spring:message code="councilDetail.info.description"/>:</span>
                        <c:if test="${councilDetailDetail.council.description != null}">
                            ${councilDetailDetail.council.description}
                        </c:if>
                        <c:if test="${councilDetailDetail.council.description == null}">
                            <span class="text-black-50 text-left"><spring:message code="councilDetail.notUpdate"/></span>
                        </c:if>
                    </li>
                </ul>
            </div>
            <div class="profile-skills">
                <h5 class="mb-20 h5 text-blue"><spring:message code="councilDetail.members.title"/></h5>
                <h6 class="mb-5 font-14">Bùi Khánh Huy
                    <span class="text-black font-weight-normal">(Chủ tịch)</span></h6>
                <h6 class="mb-5 font-14">Trần Văn A
                    <span class="text-black font-weight-normal">(Thư ký)</span></h6>
                <h6 class="mb-5 font-14">Bùi Khánh Huy
                    <span class="text-black font-weight-normal">(Phản biện)</span></h6>
            </div>
        </div>
    </div>
    <div class="col-xl-8 col-lg-8 col-md-8 col-sm-12 mb-30">
        <div class="pd-20 card-box height-100-p">
            <div class="profile-info border-top-0">
                <h5 class="mb-20 h5 text-blue"><spring:message code="councilDetail.theses.title"/></h5>
                <c:if test="${councilDetailDetail.council.theses.size() != 0}">
                    <div class="list-group">
                        <c:forEach var="thesis" items="${councilDetailDetail.council.theses}">
                            <div class="list-group-item">
                                <div>
                                    <div class="pricing-card-style2">
                                        <div class="pricing-card-header">
                                            <div>
                                                <h6><span class="text-danger">
                                                    # ${thesis.code}
                                                </span> -
                                                        ${thesis.topic.name}
                                                </h6>
                                            </div>
                                        </div>
                                        <div class="pricing-card-body pt-20">
                                            <p><spring:message code="councilDetail.theses.major"/>: <span class="font-weight-bold">${thesis.major.name}</span></p>
                                            <p><spring:message code="councilDetail.theses.students"/>:<span
                                                    class="font-weight-bold">
                                              <c:forEach var="student" items="${thesis.students}">
                                                  ${student.fullName},
                                              </c:forEach>
                                            </span></p>
                                            <div class="mt-4">
                                                <c:if test="${thesis.reportFile != null && !thesis.reportFile.isEmpty()}">
                                                    <a href="${thesis.reportFile}" class="text-blue"><i
                                                            class="icon-copy fa fa-download"
                                                            aria-hidden="true"></i>
                                                        <spring:message code="councilDetail.theses.reportFile.active"/>
                                                    </a>
                                                </c:if>
                                                <c:if test="${thesis.reportFile == null || thesis.reportFile.isEmpty()}">
                                                    <span class="text-secondary"><i class="icon-copy fa fa-download"
                                                                                    aria-hidden="true"></i>
                                                        <spring:message code="councilDetail.theses.reportFile.unActive"/>
                                                    </span>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="cta mb-10 text-center">
                                            <c:if test="${councilDetailDetail.council.block == true}">
                                                <button disabled
                                                        type="button" class="btn btn-warning btn-rounded btn-md mx-auto"><i
                                                        class="icon-copy fa fa-edit" aria-hidden="true"></i>
                                                    <spring:message code="councilDetail.theses.mark"/>
                                                </button>
                                                <c:if test="${thesis.scored == true}">
                                                    <div class="mt-2">
                                                        <a onclick="viewScoredDetail('${appContext}', ${thesis.id}, ${councilDetailDetail.id})"
                                                                href="javascript:;"
                                                           class="font-weight-bold text-light-purple"><i class="icon-copy fa fa-sort-numeric-asc" aria-hidden="true"></i>
                                                            <spring:message code="councilDetail.theses.viewScore"/>
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${councilDetailDetail.council.block != true}">
                                                <button onclick="mark('${appContext}', ${thesis.id}, ${councilDetailDetail.id})"
                                                        type="button" class="btn btn-warning btn-rounded btn-md mx-auto"><i
                                                        class="icon-copy fa fa-edit" aria-hidden="true"></i> <spring:message code="councilDetail.theses.mark"/>
                                                </button>
                                            </c:if>
                                        </div>
                                        <c:if test="${thesis.scored == true}">
                                            <div class="text-center text-success ">
                                                <i class="icon-copy fa fa-check-circle" aria-hidden="true"></i>
                                                <spring:message code="councilDetail.theses.scored"/>
                                            </div>
                                        </c:if>
                                        <c:if test="${thesis.scored != true}">
                                            <div class="text-center text-danger ">
                                                <i class="icon-copy fa fa-times-circle-o" aria-hidden="true"></i>
                                                <spring:message code="councilDetail.theses.notGradedYet"/>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${councilDetailDetail.council.theses.size() == 0}">
                    <div class="text-black-50 text-center" style="padding: 50px;">
                        <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png" alt="empty"/>
                        <p class="text-center">Danh sách khóa luận trống</p>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<!-- ADD and EDIT modal -->
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-score" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditScore" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditScore">Chấm điểm khóa luận</h4>
                <button type="button" class="close close-custom" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form id="form-add-edit-score">
                    <div class="pd-10">
                        <table class="table table-bordered table-sm">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col" class="text-center">Nội dung tiêu chí đánh giá</th>
                                <th scope="col" class="text-center col-md-1">Thang điểm</th>
                                <th scope="col" class="text-center col-md-3">Điểm số</th>
                            </tr>
                            </thead>
                            <tbody id="mark-form">
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary close-custom" data-dismiss="modal">Thoát</button>
                <button type="button" class="btn btn-success" id="btn-submit-form">
                    <i class="micon fa fa-save"> </i> Lưu dữ liệu
                </button>
            </div>
        </div>
    </div>
</div>
<!-- ADD and EDIT modal -->