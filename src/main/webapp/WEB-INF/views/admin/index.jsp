<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="card-box pd-20 height-100-p mb-30">
    <div class="row align-items-center">
        <div class="col-md-4">
            <img src="vendors/images/banner-img.png" alt="">
        </div>
        <div class="col-md-8">
            <h4 class="font-20 weight-500 mb-10 text-capitalize">
                Welcome back
                <div class="weight-600 font-30 text-blue">Bùi Khánh Huy</div>
            </h4>
            <p class="font-18 max-width-600">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Unde hic non
                repellendus debitis iure, doloremque assumenda. Autem modi, corrupti, nobis ea iure fugiat, veniam
                non quaerat mollitia animi error corporis.</p>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xl-3 mb-30">
        <div class="card-box height-100-p widget-style1">
            <div class="d-flex flex-wrap align-items-center">
                <div class="progress-data">
                    <div id="chart"></div>
                </div>
                <div class="widget-data">
                    <div class="h4 mb-0">2020</div>
                    <div class="weight-600 font-14">Contact</div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 mb-30">
        <div class="card-box height-100-p widget-style1">
            <div class="d-flex flex-wrap align-items-center">
                <div class="progress-data">
                    <div id="chart2"></div>
                </div>
                <div class="widget-data">
                    <div class="h4 mb-0">400</div>
                    <div class="weight-600 font-14">Deals</div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 mb-30">
        <div class="card-box height-100-p widget-style1">
            <div class="d-flex flex-wrap align-items-center">
                <div class="progress-data">
                    <div id="chart3"></div>
                </div>
                <div class="widget-data">
                    <div class="h4 mb-0">350</div>
                    <div class="weight-600 font-14">Campaign</div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 mb-30">
        <div class="card-box height-100-p widget-style1">
            <div class="d-flex flex-wrap align-items-center">
                <div class="progress-data">
                    <div id="chart4"></div>
                </div>
                <div class="widget-data">
                    <div class="h4 mb-0">$6060</div>
                    <div class="weight-600 font-14">Worth</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xl-12 mb-30">
        <div class="card-box height-100-p pd-20">
            <h4 class="h4 text-blue">Điểm khoá luận qua các năm</h4>
            <div id="chart5"></div>
        </div>
    </div>
    <div class="col-xl-12 mb-30">
        <div class="card-box height-100-p pd-20">
            <h4 class="h4 text-blue">Tần suất tham gia làm khoá luận từng ngành</h4>
            <div id="chart6"></div>
        </div>
    </div>
</div>

<div>
 <form id="myform">
     <input type="file" name="file" id="file" value="Chọn file"/>
     <img id="output" alt="image" width="100" height="100"/>
     <button onclick="upload()">Upload</button>
 </form>
</div>

<script>
    const fileUploader = document.getElementById('file');
    var file = null;

    fileUploader.addEventListener('change', (event) => {
        file = event.target.files[0];
        console.log(file)

        var output = document.getElementById('output');
        output.src = URL.createObjectURL(file);
        output.onload = function() {
            URL.revokeObjectURL(output.src) // free memory
        }
    });

    const upload = () => {
        var formData = new FormData();
        formData.append("file", file);
        formData.append('user', new Blob([JSON.stringify({
            "username": "root",
            "password": "root"
        })], {
            type: "application/json"
        }));

        fetch("/GraduationThesisManagementSystem/api/upload-file", {
            method: 'POST',
            body: formData,

        }).then(res => {
            if (res.ok) {
                alert("thanh cong")
            } else {
                Promise.reject("Lỗi")
            }
        }).catch(err => {
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok")
        })
    }
</script>