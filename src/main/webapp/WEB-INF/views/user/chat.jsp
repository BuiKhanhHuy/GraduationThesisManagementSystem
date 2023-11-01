<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="appContext" value="/"/>


<section style="padding-top: 80px; padding-bottom: 100px;" class="bg-primary">
</section>

<section class="container-lg mb-5" style="margin-top: -140px;">
    <div class="box-shadow shadow-lg bg-white rounded">
        <div class="py-2 px-4">
            <div class="row">
                <div class="col-md-6 col-lg-5 col-xl-4 mb-4 mb-md-0 border-end border-2">
                    <div>
                        <div class="row">
                            <div class="col-12">
                                <div class="form-group" style="margin:20px 0px;">
                                    <input type="text" class="form-control" id="kw-user" placeholder="Enter user">
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="position-relative">
                                    <ul id="area-search-user"
                                        class="w-100 bg-primary position-absolute list-unstyled mb-0 rounded box-shadow shadow-lg"
                                        style="z-index: 1; max-height: 400px; overflow: auto;">
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div data-mdb-perfect-scrollbar="true" style="
                          position: relative;
                          height: 400px;
                          overflow: auto;">
                            <div>
                                <ul class="list-unstyled mb-0" id="chats"></ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 col-lg-7 col-xl-8">
                    <div class="pt-3 pe-3" data-mdb-perfect-scrollbar="true"
                         style="position: relative;  height: 400px; overflow: auto;" id="messages-area">
                        <div id="messages">
                        </div>
                    </div>

                    <form id="formSendMessage">
                        <div class="text-muted d-flex justify-content-start align-items-center pe-3 pt-3 mt-2">
                            <img id="avatar-other-user" src=""
                                 alt="avatar 3"
                                 style="width: 45px; height: 100%; margin-right: 5px; border-radius: 50%;">
                            <input type="text" class="form-control form-control-lg" placeholder="Type your message…"
                                   id="areaMessageText"/>
                            <a class="ms-1 text-muted" href="#!"><i class="fas fa-paperclip"></i></a>
                            <a class="ms-3 text-muted" href="#!"><i class="fas fa-smile"></i></a>
                            <button type="submit" class="ms-3 border-0 bg-white text-primary" href="javascript:;"><i
                                    class="fas fa-paper-plane"></i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>