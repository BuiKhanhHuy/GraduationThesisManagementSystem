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
            <button type="button" class="btn btn-primary btn-md"><i class="micon icon-copy dw dw-add"></i>
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
                        <button type="button" class="btn btn-sm bg-info text-white"
                                data-toggle="modal" data-target="#bd-example-modal-lg">
                            <i class="icon-copy dw dw-eye"></i>
                        </button>
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

<!-- Modal View -->
<div class="pd-20 card-box height-100-p">
    <div class="modal fade bs-example-modal-lg" id="bd-example-modal-lg" tabindex="-1" role="dialog"
         aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="h4 modal-title" id="myLargeModalLabel"># Đến hạn nộp khóa luận</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <h6 class="mb-3 text-black-50">Người tạo: <span class="text-danger">admin</span></h6>
                    <div>
                        Thời hạn nộp Khóa luận (hệ đại trà và Chất lượng cao): thứ Hai, ngày 23/5/2022 Thời hạn nộp Báo cáo thực tập tốt nghiệp (hệ Chất lượng cao): thứ Hai, ngày 23/5/2022 Thời hạn nộp chuyên đề: thứ ba (24/5) và thứ tư (24/5/2022) Địa điểm nộp: Văn phòng Khoa Tài chính, Phòng 404 nhà A2, Trụ sở chính Học viện Ngân hàng, 12 Chùa Bộc, Hà Nội. Thời gian bảo vệ Khóa luận tốt nghiệp: 29/5/2022 Quy cách nộp cụ thể sẽ được liên tục cập nhật trên website và fanpage Khoa Tài chính. Chúc các bạn sinh viên đạt kết quả tốt!
                    </div>
                </div>
                <div class="modal-footer">

                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
</div>