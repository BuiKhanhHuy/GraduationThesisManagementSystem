<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
            <h4 class="text-blue h4">Danh sách bản tin</h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddNewsModal('<c:url value="/admin/api/news"/>')"
                    type="button" class="btn btn-primary btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm bản tin
            </button>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" class="text-center col-1">ID</th>
            <th scope="col" class="col-2">Tiêu đề</th>
            <th scope="col" class="text-center col-2">Người tạo</th>
            <th scope="col" class="col-2 text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${news.size() != 0}">
            <c:forEach var="n" items="${news}">
                <tr>
                    <th scope="row" class="text-center">${n.id}</th>
                    <td>${n.title}</td>
                    <td class="text-center font-weight-bold text-danger">
                        <c:if test="${n.user != null}">
                            ${n.user.username}
                        </c:if>
                        <c:if test="${n.user == null}">
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td class="col-2 text-center">
                        <button onclick="showViewNewsModal('<c:url value="/admin/api/news/${n.id}"/>')"
                                type="button" class="btn btn-sm bg-info text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Xem chi tiết">
                            <i class="icon-copy dw dw-eye"></i>
                        </button>
                        <button onclick="showEditNewsModal('<c:url value="/admin/api/news/${n.id}/single"/>', ${n.id})"
                                type="button" class="btn btn-sm bg-warning text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Cập nhật">
                            <i class="icon-copy dw dw-edit1"></i>
                        </button>
                        <button onclick="deleteNewsItem('<c:url value="/admin/api/news/${n.id}"/>')"
                                type="button" class="btn btn-sm bg-danger text-white"
                                data-toggle="tooltip"
                                data-placement="bottom" title="Xóa">
                            <i class="icon-copy dw dw-delete-3"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${news.size() == 0}">
            <tr>
                <td colspan="6" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">Danh sách bản tin trống</p>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<!-- table End -->

<%--VIEW modal--%>
<div class="modal fade bs-example-modal-lg " id="modal-view-news" tabindex="-1" role="dialog"
     aria-labelledby="myModalViewNews" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalViewNews">#<span id="data-id"></span>  <span id="data-title"></span></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <div class="pd-10">
                    <div class="media">
                        <img class="mr-3 rounded" width="60" height="60" id="data-avatar"
                             src="#"
                             alt="avatar">
                    </div>
                    <h6 class="mb-3 text-black-50 mt-2">Người tạo: <span class="text-danger" id="data-user-username"></span></h6>
                    <hr>
                    <div>
                        <h6>Nội dung:</h6>
                        <div class="mt-3" id="data-content">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
            </div>
        </div>
    </div>
</div>
<%--VIEW modal--%>


<!-- ADD and EDIT modal -->
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-news" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditNews" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditNews"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

            </div>

            <div class="modal-body">
                <form id="form-add-edit-news">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Tiêu đề<span
                                    class="text-danger">(*)</span></label>
                            <input name="title" id="title" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Nội dung<span
                                    class="text-danger">(*)</span></label>
                            <textarea name="content" id="content" class="textarea_editor form-control border-radius-0"
                                      placeholder="Viết nội dung tại đây..."></textarea>
                            <span id="error-content" class="text-danger"></span>
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