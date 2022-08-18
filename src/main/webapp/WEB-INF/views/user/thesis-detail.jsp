<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="appContext" value="/"/>

<section style="padding-top: 120px; padding-bottom: 120px;" class="bg-primary">
</section>
<section class="container-lg mb-5" style="margin-top: -140px;">
    <div class="box-shadow shadow-lg bg-white rounded">
        <div class="p-5">
            <h5>Danh sách khóa luận</h5>
            <div class="mt-4">
                <div class="row">
                    <div class="col-md-6">
                        <h6 class="mb-3 text-danger">Chi tiết khóa luận</h6>
                        <table class="table table-responsive-lg ">
                            <tbody>
                            <tr>
                                <th scope="row">Mã khóa luận:</th>
                                <td>${thesis.code}</td>
                            </tr>
                            <tr>
                                <th scope="row">Tên đề tài:</th>
                                <td>
                                    <c:if test="${thesis.topic != null}">
                                        ${thesis.topic.name}
                                    </c:if>
                                    <c:if test="${thesis.topic == null}">
                                        <span class="text-black-50">Chưa cập nhật </span>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Giảng viên hướng dẫn:</th>
                                <td>Trần Thị A, Trần Văn B</td>
                            </tr>
                            <tr>
                                <th scope="row">Giảng viên phản biện:</th>
                                <td>
                                    <c:if test="${thesis.reviewLecturer != null}">
                                        ${thesis.reviewLecturer.fullName}
                                    </c:if>
                                    <c:if test="${thesis.reviewLecturer == null}">
                                        <span class="text-black-50">Chưa cập nhật </span>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Sinh viên thực hiện:</th>
                                <td>
                                    <c:forEach var="student" items="${thesis.students}">
                                        <span>${student.fullName}</span>,
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Thời gian thực hiện khóa luận:</th>
                                <td>
                                    <fmt:formatDate type="date" value="${thesis.startDate}"/> - <fmt:formatDate
                                        type="date" value="${thesis.complateDate}"/>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Thời hạn nộp khóa luận:</th>
                                <td>
                                    <fmt:formatDate type="date" value="${thesis.thesisStartDate}"/> -
                                    <fmt:formatDate type="date" value="${thesis.thesisEndDate}"/>
                            </tr>
                            <tr>
                                <th scope="row">Khoa:</th>
                                <td>
                                    <c:if test="${thesis.department != null}">
                                        ${thesis.department.name}
                                    </c:if>
                                    <c:if test="${thesis.department == null}">
                                        <span class="text-black-50">Chưa cập nhật </span>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Niên khóa:</th>
                                <td>
                                    <c:if test="${thesis.schoolYear != null}">
                                        ${thesis.schoolYear.name}
                                    </c:if>
                                    <c:if test="${thesis.schoolYear == null}">
                                        <span class="text-black-50">Chưa cập nhật </span>
                                    </c:if>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-6">
                        <div>
                            <h6 class="mb-3 text-danger">Tập tin khóa luận</h6>
                            <table class="table table-responsive-lg ">
                                <tbody>
                                <tr>
                                    <th scope="row">Link tập tin:</th>
                                    <td>
                                        <c:if test="${thesis.reportFile != null && !thesis.reportFile.isEmpty()}">
                                            <a href="${thesis.reportFile}" class="text-blue"><i
                                                    class="icon-copy fa fa-download"
                                                    aria-hidden="true"></i>
                                                Tải xuống tập tin khóa luận
                                            </a>
                                        </c:if>
                                        <c:if test="${thesis.reportFile == null || thesis.reportFile.isEmpty()}">
                                                    <span class="text-black-50"><i class="icon-copy fa fa-download"
                                                                                    aria-hidden="true"></i>
                                                        Tập tin khóa luận không tồn tại
                                                    </span>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">Trạng thái:</th>
                                    <td>
                                        <c:if test="${thesis.reportFile == null || thesis.reportFile.isEmpty()}">
                                            <span class="text-danger font-weight-bold">Chưa nộp</span>
                                        </c:if>
                                        <c:if test="${thesis.reportFile != null && !thesis.reportFile.isEmpty()}">
                                            <span class="text-success font-weight-bold">Đã nộp</span>
                                        </c:if>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="mt-4">
                            <h6 class="mb-3 text-danger">Điểm số và kết quả</h6>
                            <table class="table table-responsive-lg ">
                                <tbody>
                                <tr>
                                    <th scope="row">Điểm số:</th>
                                    <td style="font-weight: bold; color: black  ; font-size: 18px; vertical-align: middle;">
                                        <fmt:formatNumber type = "number"
                                                          groupingUsed = "false" value = "${thesis.totalScore}" />
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">Kết quả:</th>
                                    <td class="font-weight-bold" style="font-weight: bold; font-size: 14px; vertical-align: middle;">
                                        <c:if test="${thesis.result == 1}">
                                            <span class="text-warning font-weight-bold">Chưa có kết quả</span>
                                        </c:if>
                                        <c:if test="${thesis.result == 2}">
                                            <span class="text-danger font-weight-bold">Chưa đạt</span>
                                        </c:if>
                                        <c:if test="${thesis.result == 3}">
                                            <span class="text-success font-weight-bold">Đạt</span>
                                        </c:if>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="mt-4 d-flex align-items-end flex-column">
                            <button onclick="uploadReportFile('${appContext}', ${thesis.id})"
                                    class="mt-auto btn btn-warning">
                                <i class="fa-solid fa-upload" style="font-size: 16px; margin-right: 5px;"></i> Nộp tập
                                tin khóa luận
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
