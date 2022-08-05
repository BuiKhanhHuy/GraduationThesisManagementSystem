<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="endpointLoadUsers" value="/admin/api/users"/>

<c:url var="filterNews" value=""/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>Bản tin</h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Bản tin</li>
                </ol>
            </nav>
        </div>
    </div>
</div>


<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterNews}">
        <input name="page" id="page" hidden/>
        <div class="row justify-content-end mt-2">
            <div class="col-md-4 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text" placeholder="Nhập tiêu đề tìm kiếm..." name="kw"
                           aria-label="Search">
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
            <h4 class="text-blue h4">Danh sách thông báo</h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddNotificationModal('<c:url value="/admin/api/notifications"/>')"
                    type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                Gửi thông báo
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="col-2">Tiêu đề</th>
            <th scope="col">Nội dung</th>
            <th scope="col" class="text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${notifications.size() != 0}">
            <c:forEach var="notification" items="${notifications}">
                <tr>
                    <td>${notification.title}</td>
                    <td>
                            ${notification.content}
                    </td>
                    <td class="text-center">
                        <div class="btn-list">
                            <button
                                    type="button" class="btn btn-sm bg-warning text-white"
                                    data-toggle="tooltip"
                                    data-placement="bottom" title="Cập nhật">
                                <i class="icon-copy dw dw-edit1"></i>
                            </button>
                            <button
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
        <c:if test="${notifications.size() == 0}">
            <tr>
                <td colspan="5" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">Danh sách thông báo trống</p>
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
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-notification" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditNotification" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditNotification"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

            </div>

            <div class="modal-body">
                <form id="form-add-edit-notification">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Tiêu đề<span
                                    class="text-danger">(*)</span></label>
                            <input name="title" id="title" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Nội dung<span
                                    class="text-danger">(*)</span></label>
                            <textarea name="content" id="content" class="form-control"></textarea>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Đối tượng cần gửi</label>
                            <div>
                                <select onchange="objectChange('${endpointLoadUsers}')"
                                        name="role" id="role"
                                        class="custom-select form-control" style="width: 100%;">
                                    <option value="">Tất cả</option>
                                    <c:forEach var="role" items="${roles}">
                                        <option value="${role.id}">${role.description}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Sinh viên thuộc khóa</label>
                            <div>
                                <select onchange="schoolYearChange('${endpointLoadUsers}')"
                                        disabled id="schoolYear" name="schoolYear"
                                        class="custom-select form-control" style="width: 100%;">
                                    <option value=""> Tất cả niên khóa</option>
                                    <c:forEach var="schoolYearOption" items="${schoolYearOptions}">
                                        <option value="${schoolYearOption[0]}">${schoolYearOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Gửi trực tiếp</label>
                            <select name="users" id="users" disabled
                                    class="custom-select2 form-control" multiple="multiple" style="width: 100%;">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
                <button onclick="addNotification()"
                        type="button" class="btn btn-success" id="btn-submit-form">
                    <i class="icon-copy fa fa-send" aria-hidden="true"></i> Gửi đi
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