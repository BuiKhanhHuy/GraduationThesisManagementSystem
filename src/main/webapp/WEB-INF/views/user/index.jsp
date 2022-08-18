<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section style="padding-top: 120px; padding-bottom: 120px;" class="bg-primary">
</section>
<section class="container-lg mb-5" style="margin-top: -140px;">
    <div class="box-shadow shadow-lg bg-white rounded">
        <div class="p-5">
            <h5>Danh sách khóa luận</h5>
            <div class="mt-4">
                <table class="table table-responsive-lg ">
                    <thead>
                    <tr>
                        <th scope="col">Mã khóa luận</th>
                        <th scope="col">Chủ đề</th>
                        <th scope="col" class="text-center">Ngày bắt đầu</th>
                        <th scope="col" class="text-center">Ngày kết thúc</th>
                        <th scope="col">Khoa</th>
                        <th scope="col">Niên khóa</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col" class="text-center">Hành động</th>
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
                                        <span class="text-black-50 text-center">Chưa cập nhật</span>
                                    </c:if>
                                </td>
                                <td class="text-center">
                                    <fmt:formatDate type="date" value="${thesis.startDate}"/>
                                </td>
                                <td class="text-center">
                                    <fmt:formatDate type="date" value="${thesis.complateDate}"/>
                                </td>
                                <td>
                                    <c:if test="${thesis.department != null}">
                                        ${thesis.department.name}
                                    </c:if>
                                    <c:if test="${thesis.department == null}">
                                        <span class="text-black-50 text-center">Chưa cập nhật</span>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${thesis.schoolYear != null}">
                                        ${thesis.schoolYear.name}
                                    </c:if>
                                    <c:if test="${thesis.schoolYear == null}">
                                        <span class="text-black-50 text-center">Chưa cập nhật</span>
                                    </c:if>
                                </td>
                                <td>
                                    <span class="text-danger">Đã kết thúc</span>
                                </td>
                                <td class="text-center col-2">
                                    <a href="<c:url value="thesis/${thesis.id}"/> " class="btn btn-info btn-sm">Xem chi tiết</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${student != null &&  student.theses.size() == 0}">
                        <tr>
                            <td colspan="11" class="text-black-50 text-center">
                                <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"
                                     alt="empty"/>
                                <p class="text-center">Danh sách khóa luận trống</p>
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
