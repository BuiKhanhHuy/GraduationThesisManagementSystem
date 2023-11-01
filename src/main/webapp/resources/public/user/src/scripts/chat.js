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
  ) => `<li class="p-1 border-bottom" id="${chatId}">
                            <a href="#!" class="d-flex justify-content-between">
                            <div class="d-flex flex-row">
                                <div>
                                <img
                                    src="${avatar}"
                                    alt="avatar"
                                    class="d-flex align-self-center me-3"
                                    width="60"
                                    style="border-radius: 50%;"/>
                                <span class="badge bg-success badge-dot"></span>
                                </div>
                                <div class="pt-1">
                                <p class="fw-bold mb-0">${name}</p>
                                <p class="small text-muted">
                                    ${lastMessage}
                                </p>
                                </div>
                            </div>
                            <div class="pt-1">
                                <p class="small text-muted" style="font-size: 10px;">
                                    ${
                                      timestamp !== ""
                                        ? new Date(timestamp).toLocaleString()
                                        : ""
                                    }
                                </p>
<!--                                <span class="badge bg-danger rounded-pill float-end" >${numberNewMessage}</span >-->
                            </div>
                            </a>
                        </li>`;

  let messageSentHTML = (
    message,
    timestamp,
    avatar
  ) => ` <div class="d-flex flex-row justify-content-end">
                                <div>
                                <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">
                                    ${message}
                                </p>
                                <p class="small me-3 mb-3 rounded-3 text-muted">${new Date(
                                  timestamp
                                ).toLocaleString()}</p>
                                </div>
                                <img src="${avatar}"
                                alt="avatar 1" style="width: 45px; height: 100%; border-radius: 50%;">
                            </div> `;
  let messageReceivedHTML = (
    message,
    timestamp,
    avatar
  ) => `<div class="d-flex flex-row justify-content-start">
                                <img src="${avatar}"
                                alt="avatar 1" style="width: 45px; height: 100%;border-radius: 50%;">
                                <div>
                                <p class="small p-2 ms-3 mb-1 rounded-3" style="background-color: #f5f6f7;">
                                    ${message}
                                </p>
                                <p class="small ms-3 mb-3 rounded-3 text-muted float-end">${new Date(
                                  timestamp
                                ).toLocaleString()}</p>
                                </div>
                            </div>`;

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
