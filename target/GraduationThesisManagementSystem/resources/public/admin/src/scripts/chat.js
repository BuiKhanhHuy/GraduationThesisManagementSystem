document.addEventListener("DOMContentLoaded", () => {
  let appContext = "/GraduationThesisManagementSystem/";
  let endpoint = `${appContext}api/current-user`;

  let allChatHTML = (
    chatId,
    name,
    avatar,
    active,
    lastMessage,
    timestamp,
    numberNewMessage
  ) => {
    return `<li id="${chatId}">
                                <a href="#">
                                    <img src="${avatar}" alt="">
                                    <h3 class="clearfix">${name}</h3>
                                    <p style="font-size: 10px;"> ${
                                      timestamp !== ""
                                        ? new Date(timestamp).toLocaleString()
                                        : ""
                                    }</p>
                                </a>
                            </li>`;
  };
  let messageSentHTML = (
    message,
    timestamp,
    avatar
  ) => `<li class="clearfix admin_chat">
                                                                <span class="chat-img">
                                                                    <img src="${avatar}" alt="">
                                                                </span>
                                                                <div class="chat-body clearfix">
                                                                <p class="bg-primary text-white">${message}</p>
                                                                <div class="chat_time">${new Date(
                                                                  timestamp
                                                                ).toLocaleString()}</div>
                                                                </div>
                                                            </li>`;
  let messageReceivedHTML = (
    message,
    timestamp,
    avatar
  ) => `<li class="clearfix">
                                                                <span class="chat-img">
                                                                    <img src="${avatar}" alt="">
                                                                </span>
                                                                <div class="chat-body clearfix">
                                                                <p style="text-align: left; display: inline-block;">${message}</p>
                                                                <div class="chat_time">${new Date(
                                                                  timestamp
                                                                ).toLocaleString()}</div>
                                                                </div>
                                                            </li>`;

  loadCurrentUser(endpoint, (currentUser) => {
    chat(
      appContext,
      currentUser,
      allChatHTML,
      messageSentHTML,
      messageReceivedHTML
    );
  });
});
