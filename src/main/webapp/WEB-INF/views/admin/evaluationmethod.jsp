<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="filterEvaluationMethod" value="/admin/evaluations-method"/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>Phương pháp đánh giá</h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Phương pháp đánh giá</li>
                </ol>
            </nav>
        </div>
    </div>
</div>

<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterEvaluationMethod}">
        <input name="page" id="page" hidden/>
    </form>
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">Danh sách phương pháp đánh giá</h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddEvaluationMethodModal('<c:url value="/admin/api/evaluations-method"/>')"
                    type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm phương pháp đánh giá
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">Tên phương pháp đánh giá</th>
            <th scope="col">Nội dung đánh giá</th>
            <th scope="col" class="text-center">Hoạt động</th>
            <th scope="col" class="text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${evaluationMethods.size() != 0}">
            <c:forEach var="evaluationMethod" items="${evaluationMethods}">
                <tr>
                    <td>${evaluationMethod.name}</td>
                    <td>
                        <c:if test="${evaluationMethod.scoreComponents != null}">
                            <c:forEach var="scoreComponent" items="${evaluationMethod.scoreComponents}">
                                <div class="m-0 p-0">
                                    <p class="m-0 p-0 font-weight-bold">${scoreComponent.name}</p>
                                    <div class="ml-5">
                                        <ol style="list-style: -moz-ethiopic-numeric;">
                                            <c:if test="${scoreComponent.scoreColumns != null}">
                                                <c:forEach var="scoreColumn" items="${scoreComponent.scoreColumns}">
                                                    <li>${scoreColumn.name}
                                                        <span class="ml-2 font-weight-bold text-danger">(${scoreColumn.weight * 100} %)</span>
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
                            <i class="icon-copy fa fa-check-circle-o text-success" aria-hidden="true"
                               data-toggle="tooltip" data-placement="bottom"
                               title="Hoạt động"></i>
                        </c:if>
                        <c:if test="${evaluationMethod.active != true}">
                            <i class="icon-copy fa fa-times-circle-o text-danger" aria-hidden="true"
                               data-toggle="tooltip"
                               data-placement="bottom"
                               title="Không hoạt động"></i>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <div class="btn-list">
                            <button onclick="showEditEvaluationMethodModal('<c:url
                                    value="/admin/api/evaluations-method/${evaluationMethod.id}"/>',
                                ${evaluationMethod.id})"
                                    type="button" class="btn btn-sm bg-warning text-white"
                                    data-toggle="tooltip"
                                    data-placement="bottom" title="Cập nhật">
                                <i class="icon-copy dw dw-edit1"></i>
                            </button>
                            <button onclick="deleteEvaluationMethodItem('<c:url
                                    value="/admin/api/evaluations-method/${evaluationMethod.id}"/>',
                                ${evaluationMethod.id})"
                                    type="button" class="btn btn-sm bg-danger text-white"
                                    data-toggle="tooltip"
                                    data-placement="bottom" title="Xóa">
                                <i class="icon-copy dw dw-delete-3"></i>
                            </button>
                        </div>
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
    <c:if test="${Math.ceil(totalResult / pageSize) > 1}">
        <div class="blog-pagination pagination-md mt-5 mb-2">
            <div class="btn-toolbar justify-content-center">
                <div class="btn-group">
                    <nav aria-label="Page navigation">
                        <ul class="pagination" id="pagination"></ul>
                    </nav>
                </div>
            </div>
        </div>
    </c:if>
</div>
<!-- table End -->


<!-- ADD and EDIT modal -->
<div class="modal fade bs-example-modal-lg" id="modal-add-edit-evaluation-method" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditEvaluationMethod" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditEvaluationMethod"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

            </div>

            <div class="modal-body">
                <form id="form-add-edit-evaluation-method">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Tên phương pháp đánh giá<span
                                    class="text-danger">(*)</span></label>
                            <input name="name" id="name" type="text" class="form-control">
                            <hr/>
                        </div>
                        <div>
                            <div class="task-list-form">
                                <ul id="score-components">
                                </ul>
                            </div>
                            <div class="add-more-task" id="add-more-task">
                                <a href="javascript:;" onclick="addScoreComponent()"><i class="ion-plus-circled"></i>
                                    Thêm điểm thành phần</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
                <button type="button" class="btn btn-success" id="btn-submit-form">
                    <i class="micon fa fa-save"> </i> Lưu dữ liệu
                </button>
            </div>
        </div>
    </div>
</div>
<!-- ADD and EDIT modal -->

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