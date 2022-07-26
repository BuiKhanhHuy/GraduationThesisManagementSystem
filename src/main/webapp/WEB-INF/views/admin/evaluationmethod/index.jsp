<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-header">
    <div class="row">
        <div class="col-md-6 col-sm-12">
            <div class="title">
                <h4>Lọc</h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Form Basic</li>
                </ol>
            </nav>
        </div>
        <div class="col-md-6 col-sm-12 text-right">
            <div class="dropdown">
                <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-toggle="dropdown">
                    January 2018
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <a class="dropdown-item" href="#">Export List</a>
                    <a class="dropdown-item" href="#">Policies</a>
                    <a class="dropdown-item" href="#">View Assets</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- table start -->
<div class="pd-20 card-box mb-30">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">Danh sách phương pháp đánh giá</h4>
        </div>
        <div class="pull-right">
            <button type="button" class="btn btn-primary btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm phương pháp đánh giá
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID</th>
            <th scope="col">Tên phương pháp đánh giá</th>
            <th scope="col" class="col-6">Nội dung đánh giá</th>
            <th scope="col" class="text-center">Hoạt động</th>
            <th scope="col" class="col-2 text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${evaluationMethods.size() != 0}">
            <c:forEach var="evaluationMethod" items="${evaluationMethods}">
                <tr>
                    <th scope="row" class="text-center">${evaluationMethod.id}</th>
                    <td>${evaluationMethod.name}</td>
                    <td>
                        <c:if test="${evaluationMethod.scoreComponents != null}">
                            <c:forEach var="scoreComponent" items="${evaluationMethod.scoreComponents}">
                                <div class="m-0 p-0">
                                    <p class="m-0 p-0 font-weight-bold">${scoreComponent.name}
                                        <span class="ml-2 text-danger">(${scoreComponent.weight * 100} %)</span>
                                    </p>
                                    <div class="ml-5">
                                        <ol style="list-style: -moz-ethiopic-numeric;">
                                            <c:if test="${scoreComponent.scoreColumns != null}">
                                                <c:forEach var="scoreColumn" items="${scoreComponent.scoreColumns}">
                                                    <li>${scoreColumn.name}
                                                        <span class="ml-2 font-weight-bold text-info">(${scoreColumn.weight * 100} %)</span>
                                                    </li>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${scoreComponent.scoreColumns == null}">
                                                <li><span class="text-black-50 text-center">Chưa cập nhật</span></li>
                                            </c:if>
                                        </ol>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${evaluationMethod.scoreComponents == null}">
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <c:if test="${evaluationMethod.active == true}">
                            <i class="fa fa-circle text-success" data-toggle="tooltip" data-placement="bottom"
                               title="Hoạt động"></i>
                        </c:if>
                        <c:if test="${evaluationMethod.active != true}">
                            <i class="fa fa-circle text-danger" data-toggle="tooltip" data-placement="bottom"
                               title="Hoạt động"></i>
                        </c:if>
                    </td>
                    <td class="col-2 text-center">
                        <button type="button" class="btn btn-sm bg-warning text-white">
                            <i class="icon-copy dw dw-edit1"></i>
                        </button>
                        <button type="button" class="btn btn-sm bg-danger text-white">
                            <i class="icon-copy dw dw-delete-3"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${evaluationMethods.size() == 0}">
            <tr>
                <td colspan="4" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">Danh sách phương pháp đánh giá trống</p>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <div class="blog-pagination pagination-sm mt-5 mb-2">
        <div class="btn-toolbar justify-content-center">
            <div class="btn-group">
                <a href="#" class="btn btn-outline-primary prev"><i class="fa fa-angle-double-left"></i></a>
                <a href="#" class="btn btn-outline-primary">1</a>
                <a href="#" class="btn btn-outline-primary">2</a>
                <span class="btn btn-primary current">3</span>
                <a href="#" class="btn btn-outline-primary">4</a>
                <a href="#" class="btn btn-outline-primary">5</a>
                <a href="#" class="btn btn-outline-primary next"><i class="fa fa-angle-double-right"></i></a>
            </div>
        </div>
    </div>
</div>
<!-- table End -->