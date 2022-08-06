<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="filterTopic" value=""/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>Đề tài</h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Đề tài</li>
                </ol>
            </nav>
        </div>
    </div>
</div>


<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterTopic}">
        <input name="page" id="page" hidden/>
        <div class="row justify-content-end mt-2">
            <div class="col-md-4 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text" placeholder="Nhập tên đề tài tìm kiếm ..." name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="departmentId" id="departmentId"
                            style="width: 100%; height: 38px;">
                        <option value="${""}">Tất cả khoa</option>
                        <c:forEach var="departmentOption" items="${departmentOptions}">
                            <option value="${departmentOption[0]}">${departmentOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="col-md-2 col-sm-12">
                <div>
                    <button class="form-control ml-1 btn-warning btn" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i> Tìm kiếm
                    </button>
                </div>
            </div>

        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">Danh sách đề tài</h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddTopicModal('<c:url value="/admin/api/topics"/>')"
                    type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm đề tài
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">Tên đề tài</th>
            <th scope="col">Mô tả</th>
            <th scope="col">Thuộc khoa</th>
            <th scope="col" class="col-2 text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${topics.size() != 0}">
            <c:forEach var="topic" items="${topics}">
                <tr>
                    <td>${topic.name}</td>
                    <td>${topic.description}</td>
                    <td>
                        <c:if test="${topic.department != null}">
                            ${topic.department.name}
                        </c:if>
                        <c:if test="${topic.department == null}">
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td class="col-2 text-center">
                        <div class="btn-list">
                            <button onclick="showEditTopicModal('<c:url
                                    value="/admin/api/topics/${topic.id}"/>', ${topic.id})"
                                    type="button" class="btn btn-sm bg-warning text-white"
                                    data-toggle="tooltip"
                                    data-placement="bottom" title="Xem chi tiết">
                                <i class="icon-copy dw dw-edit1"></i>
                            </button>
                            <button onclick="deleteTopicItem('<c:url value="/admin/api/topics/${topic.id}"/>')"
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
        <c:if test="${topics.size() == 0}">
            <tr>
                <td colspan="5" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">Danh sách đề tài trống</p>
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
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-topic" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditTopic" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditTopic"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

            </div>

            <div class="modal-body">
                <form id="form-add-edit-topic">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Tên đề tài <span
                                    class="text-danger">(*)</span></label>
                            <input name="name" id="name" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Thuộc khoa<span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select form-control"
                                        name="department" id="department" style="width: 100%;">
                                    <c:forEach var="departmentOption" items="${departmentOptions}">
                                        <option value="${departmentOption[0]}">${departmentOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Mô tả</label>
                            <textarea name="description" id="description" class="form-control"></textarea>
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