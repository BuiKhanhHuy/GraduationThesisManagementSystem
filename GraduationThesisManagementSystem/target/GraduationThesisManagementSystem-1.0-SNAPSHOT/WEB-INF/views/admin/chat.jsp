<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="min-height-200px">
    <div class="bg-white border-radius-4 box-shadow mb-30">
        <div class="row no-gutters">
            <div class="col-lg-3 col-md-4 col-sm-12">
                <div class="chat-list bg-light-gray">
                    <div class="row">
                        <div class="col-12">
                            <div class="form-group  chat-search" style="margin:20px 0px;">
                                <span class="ti-search"></span>
                                <input type="text" class="form-control" id="kw-user" placeholder="Enter user">
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="position-relative">
                                <ul id="area-search-user"
                                    class="w-100 bg-dark position-absolute list-unstyled mb-0 rounded box-shadow shadow-lg"
                                    style="z-index: 1; max-height: 400px; overflow: auto;">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="notification-list chat-notification-list customscroll" style="height: 400px;">
                        <ul id="chats"></ul>
                    </div>
                </div>
            </div>
            <div class="col-lg-9 col-md-8 col-sm-12">
                <div class="chat-detail">
                    <div class="chat-profile-header clearfix border-bottom-0    ">
                    </div>
                    <div class="chat-box" style="height: 490px;">
                        <div class="chat-desc customscroll" id="messages-area">
                                <ul id="messages"></ul>
                        </div>
                        <div class="chat-footer">
                            <form id="formSendMessage">
                                <div class="file-upload"
                                     style="margin-top: 1px;
                                            padding-top: 20px;
                                            padding-left: 5px;
                                            margin-right: 5px;">
                                    <img id="avatar-other-user" src=""
                                         alt="avatar 3"
                                         style="width: 45px; height: 100%; margin-right: 5px; border-radius: 50%;">
                                </div>
                                <%--                            <div class="file-upload"><a href="#"><i class="fa fa-paperclip"></i></a></div>--%>
                                <div class="chat_text_area">
                                    <textarea placeholder="Type your message…" id="areaMessageText"></textarea>
                                </div>
                                <div class="pt-4">
                                    <button class="btn btn-link" type="submit"><i
                                            class="icon-copy font-30 ion-paper-airplane"></i></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>