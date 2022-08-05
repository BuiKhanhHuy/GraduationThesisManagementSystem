<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/admin/src/images/avatar/avatar-default.jpg" var="avatarDefault"/>
<c:url var="filterLecturer" value=""/>
<c:url var="home" value="/admin/"/>

<div class="page-header">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="title">
                <h4>Giảng viên</h4>
            </div>
            <nav aria-label="breadcrumb" role="navigation">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${home}">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Giảng viên</li>
                </ol>
            </nav>
        </div>
    </div>
</div>


<!-- table start -->
<div class="pd-20 card-box mb-30">
    <form id="form-filter" action="${filterManage}">
        <input name="page" id="page" hidden/>
        <div class="row justify-content-end mt-2">
            <div class="col-md-6 col-sm-12">
                <div class="form-group">
                    <input class="form-control" type="text" placeholder="Mã giảng viên, họ và tên, email, sđt..."
                           name="kw"
                           aria-label="Search">
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="positionId" id="positionId"
                            style="width: 100%">
                        <option value="${""}">Tất cả chức vụ</option>
                        <c:forEach var="positionOption" items="${positionOptions}">
                            <option value="${positionOption[0]}">${positionOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="departmentId" id="departmentId"
                            style="width: 100%">
                        <option value="${""}">Tất cả khoa</option>
                        <c:forEach var="departmentOption" items="${departmentOptions}">
                            <option value="${departmentOption[0]}">${departmentOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="roleId" id="roleId"
                            style="width: 100%">
                        <option value="${""}">Tất cả quyền</option>
                        <c:forEach var="roleOption" items="${roleOptions}">
                            <option value="${roleOption[0]}">${roleOption[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <div class="form-group">
                    <select class="custom-select2 form-control" name="active" id="active-filter"
                            style="width: 100%">
                        <option value="${""}">Tất cả trạng thái</option>
                        <option value="${true}">Hoạt động</option>
                        <option value="${false}">
                            Không hoạt động
                        </option>
                    </select>
                </div>
            </div>
            <div class="col-md-2 col-sm-12">
                <div>
                    <button class="btn-warning btn form-control" type="submit">
                        <i class=" fa fa-search" aria-hidden="true"></i> Tìm kiếm
                    </button>
                </div>
            </div>
        </div>
    </form>
    <hr style="height:5px;" class="text-black-50">
    <div class="clearfix mb-20">
        <div class="pull-left">
            <h4 class="text-blue h4">Danh sách giảng viên</h4>
        </div>
        <div class="pull-right">
            <button onclick="showAddLecturerModal('<c:url value="/admin/api/lecturers"/>')"
                    type="button" class="btn btn-success btn-md"><i class="micon icon-copy dw dw-add"></i>
                Thêm giảng viên
            </button>
        </div>
    </div>
    <table class="table table-bordered table-responsive">
        <thead>
        <tr>
            <th scope="col" class="text-center col-1">Hình ảnh</th>
            <th scope="col" class="text-center">Mã giảng viên</th>
            <th scope="col" class="text-center">Thông tin</th>
            <th scope="col">Chức vụ</th>
            <th scope="col">Thuộc khoa</th>
            <th scope="col">Quyền</th>
            <th scope="col" class="text-center">Trạng thái</th>
            <th scope="col" class="text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>

        <c:if test="${lecturers.size() != 0}">
            <c:forEach var="lecturer" items="${lecturers}">
                <tr>
                    <td class="text-center col-1">
                        <c:if test="${lecturer.user.avatar == null}">
                            <img src="${avatarDefault}" style="border-radius: 50%;" class="img-fluid" height="70"
                                 width="70" alt="">
                        </c:if>
                        <c:if test="${lecturer.user.avatar != null}">
                            <img src="${lecturer.user.avatar}" style="border-radius: 50%;" class="img-fluid" height="70"
                                 width="70" alt="">
                        </c:if>
                    </td>
                    <td class="text-center">${lecturer.code}</td>
                    <td>
                        <ul style="list-style-type: circle; list-style-position: inside; ">
                            <li>${lecturer.fullName}</li>
                            <li>${lecturer.email}</li>
                            <li>${lecturer.phone}</li>
                            <li>
                                <fmt:formatDate pattern="yyyy/MM/dd" value="${lecturer.birthday}"/>
                            </li>
                            <li>
                                <c:if test="${lecturer.gender == 1}">
                                    Nam
                                </c:if>
                                <c:if test="${lecturer.gender == 2}">
                                    Nữ
                                </c:if>
                                <c:if test="${lecturer.gender == 3}">
                                    Khác
                                </c:if>
                            </li>
                            <li>${lecturer.address}</li>
                        </ul>
                    </td>
                    <td>
                        <c:if test="${lecturer.position != null}">
                            ${lecturer.position.name}
                        </c:if>
                        <c:if test="${lecturer.position == null}">
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${lecturer.department != null}">
                            ${lecturer.department.name}
                        </c:if>
                        <c:if test="${lecturer.department == null}">
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${lecturer.user.role != null}">
                            ${lecturer.user.role.description}
                        </c:if>
                        <c:if test="${lecturer.user.role == null}">
                            <span class="text-black-50 text-center">Chưa cập nhật</span>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <c:if test="${lecturer.user.active == true}">
                            <i class="icon-copy fa fa-check-circle-o text-success" aria-hidden="true"
                               data-toggle="tooltip" data-placement="bottom"
                               title="Hoạt động"></i>
                        </c:if>
                        <c:if test="${lecturer.user.active != true}">
                            <i class="icon-copy fa fa-times-circle-o text-danger" aria-hidden="true"
                               data-toggle="tooltip"
                               data-placement="bottom"
                               title="Không hoạt động"></i>
                        </c:if>
                    </td>
                    <td class="text-center">
                        <div class="btn-list">
                            <button onclick="showEditLectureModal('<c:url
                                    value="/admin/api/lecturers/${lecturer.id}"/>', ${lecturer.id})"
                                    type="button" class="btn btn-sm bg-warning text-white" data-toggle="tooltip"
                                    data-placement="bottom" title="Chỉnh sửa">
                                <i class="icon-copy dw dw-edit1"></i>
                            </button>
                            <button onclick="deleteLecturerItem('<c:url
                                    value="/admin/api/lecturers/${lecturer.id}"/>', ${lecturer.id})"
                                    type="button" class="btn btn-sm bg-danger text-white" data-toggle="tooltip"
                                    data-placement="bottom" title="Xóa">
                                <i class="icon-copy dw dw-delete-3"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${lecturers.size() == 0}">
            <tr>
                <td colspan="7" class="text-black-50 text-center">
                    <img width="75" src="https://cdn-icons-png.flaticon.com/512/7465/7465679.png"/>
                    <p class="text-center">Danh sách giảng viên trống</p>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <div class="blog-pagination pagination-md mt-5 mb-2">
        <div class="btn-toolbar justify-content-center">
            <div class="btn-group">
                <nav aria-label="Page navigation">
                    <ul class="pagination" id="pagination"></ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<!-- table End -->

<!-- ADD and EDIT modal -->
<div class="modal fade bs-example-modal-lg " id="modal-add-edit-lecturer" tabindex="-1" role="dialog"
     aria-labelledby="myModalAddAndEditLecturer" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalAddAndEditLecturer"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

            </div>

            <div class="modal-body">
                <form id="form-add-edit-lecturer">
                    <div class="pd-10">
                        <div class="form-group">
                            <label class="font-weight-bold">Ảnh đại diện<span
                                    class="text-danger">(*)</span></label>
                            <div class="custom-file">
                                <input type="file" name="avatar" id="avatar" class="custom-file-input">
                                <label class="custom-file-label">Chọn ảnh</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Mã giảng viên<span
                                    class="text-danger">(*)</span></label>
                            <input name="code" id="code" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Họ và tên<span
                                    class="text-danger">(*)</span></label>
                            <input name="fullName" id="fullName" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Email<span
                                    class="text-danger">(*)</span></label>
                            <input name="email" id="email" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Số điện thoại<span
                                    class="text-danger">(*)</span></label>
                            <input name="phone" id="phone" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Ngày sinh<span
                                    class="text-danger">(*)</span></label>
                            <input name="birthday" id="birthday" type="date" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Giới tính<span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select form-control"
                                        name="gender" id="gender" style="width: 100%;">
                                    <option value="1">Nam</option>
                                    <option value="2">Nữ</option>
                                    <option value="3">Khác</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Địa chỉ<span
                                    class="text-danger">(*)</span></label>
                            <input name="address" id="address" type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="font-weight-bold">Chức vụ<span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select form-control"
                                        name="position" id="position" style="width: 100%;">
                                    <c:forEach var="positionOption" items="${positionOptions}">
                                        <option value="${positionOption[0]}">${positionOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
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
                            <label class="font-weight-bold">Quyền<span
                                    class="text-danger">(*)</span></label>
                            <div>
                                <select class="custom-select form-control"
                                        name="role" id="role" style="width: 100%;">
                                    <c:forEach var="roleOption" items="${roleOptions}">
                                        <option value="${roleOption[0]}">${roleOption[1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div id="new-password-area"></div>
                        <div class="form-group">
                            <div class="custom-control custom-checkbox mb-5">
                                <input type="checkbox" class="custom-control-input" id="active" name="active">
                                <label class="custom-control-label" for="active">Hoạt động</label>
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
    let totalPage = ${totalPage};
    let pageSize = ${pageSize};

    $('#pagination').twbsPagination({
        totalPages: Math.ceil(totalPage / pageSize),
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