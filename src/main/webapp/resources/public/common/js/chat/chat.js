import {initializeApp} from "https://www.gstatic.com/firebasejs/9.9.3/firebase-app.js";
import {
    getDatabase,
    ref,
    set,
    get,
    child,
    push,
    onChildAdded,
} from "https://www.gstatic.com/firebasejs/9.9.3/firebase-database.js";

const firebaseConfig = {
    apiKey: "AIzaSyBIQCG1q5K3BzVNmnB-ui4IgVUR3bGc-wM",
    authDomain: "graduationthesismanagementsys.firebaseapp.com",
    databaseURL:
        "https://graduationthesismanagementsys-default-rtdb.firebaseio.com",
    projectId: "graduationthesismanagementsys",
    storageBucket: "graduationthesismanagementsys.appspot.com",
    messagingSenderId: "545449100632",
    appId: "1:545449100632:web:aad056d409d790ec18912d",
    measurementId: "G-Z0V74T681Z",
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const database = getDatabase(app);
const dbRef = ref(database);

// create new user
const addNewUser = (userId, name, avatar) => {
    set(child(dbRef, `users/${userId}`), {
        id: userId,
        name: name,
        avatar: avatar,
    });
};

// get or create user
const getOrCreateUser = (userId, name, avatar) => {
    try {
        get(child(dbRef, `users/${userId}`)).then((user) => {
            if (!user.exists()) {
                addNewUser(userId, name, avatar);
            }
        });

        return userId;
    } catch (err) {
        return null;
    }
};

window.loadCurrentUser = (endpoint, callback) => {
    setTimeout(fetch(endpoint, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => {
            if (res.ok) {
                return res.json();
            } else {
                Promise.reject("Error");
            }
        })
        .then((data) => {
            callback(data);
        })
        .catch((err) => {
            console.log(err);
        }), 300);
};

window.chat = (
    appContext,
    currentUser,
    allChatHTML,
    messageSentHTML,
    messageReceivedHTML
) => {
    const dbRef = ref(database);
    let chats = document.getElementById("chats");
    let messages = document.getElementById("messages");
    let formSendMessage = document.getElementById("formSendMessage");
    formSendMessage.style.display = "none";
    let areaMessageText = document.getElementById("areaMessageText");
    let areaSearchUser = document.getElementById("area-search-user");
    let avatarOtherUser = document.getElementById("avatar-other-user");

    let chatId = null;
    let myUserId = currentUser.id;
    let unsubscribeOnChildAdded = null;

    // get or create user
    myUserId = getOrCreateUser(
        myUserId,
        currentUser.username,
        currentUser.avatar
    );

    // FUNCTION
    // load message
    const onLoadMessage = (chatId, myUserId) => {
        let messagesHTML = "";
        messages.innerHTML = "";

        unsubscribeOnChildAdded = onChildAdded(
            child(dbRef, `messages/${chatId}`),
            (data) => {
                get(child(dbRef, `/users/${data.val().senderId}`)).then((u) => {
                    let user = u.val();

                    if (data.val().senderId === myUserId) {
                        messagesHTML = messageSentHTML(
                            data.val().message,
                            data.val().timestamp,
                            user.avatar
                        );
                    } else {
                        messagesHTML = messageReceivedHTML(
                            data.val().message,
                            data.val().timestamp,
                            user.avatar
                        );
                    }

                    messages.insertAdjacentHTML("beforeend", messagesHTML);
                });
            }
        );
    };
    onLoadMessage(chatId, myUserId);

    let chatsHTML = "";
    onChildAdded(child(dbRef, `userRooms/${myUserId}`), (chat) => {
        if (chat.exists()) {
            get(child(dbRef, `members/${chat.key}`))
                .then((users) => {
                    let otherUserId = Object.values(users.val()).filter(
                        (value) => value !== myUserId
                    )[0];

                    return otherUserId;
                })
                .then((otherUserId) => {
                    get(child(dbRef, `users/${otherUserId}`)).then((otherUser) => {
                        let user = otherUser.val();

                        get(child(dbRef, `chats/${chat.key}`)).then((chatObject) => {
                            let chatObj = chatObject.val();

                            chatsHTML = allChatHTML(
                                chat.key,
                                user.name,
                                user.avatar,
                                true,
                                chatObj.lastMessage,
                                chatObj.timestamp,
                                1
                            );
                            chats.insertAdjacentHTML("afterbegin", chatsHTML);

                            document
                                .getElementById(`${chat.key}`)
                                .addEventListener("click", (e) => {
                                    chatId = chat.key;

                                    console.log("Mở đoạn chat:" + `${chatId}`);
                                    unsubscribeOnChildAdded();
                                    formSendMessage.style.display = "block";
                                    avatarOtherUser.src = user.avatar;
                                    onLoadMessage(chatId, myUserId);
                                });
                        });
                    });
                });
        }
    });

    // add chat
    var addChat = (myUserId, user) => {
        // get or create user
        let userId = getOrCreateUser(user.id, user.username, user.avatar);

        // load all members
        chatId = null;
        get(child(dbRef, "members"))
            .then((data) => {
                return data.val();
            })
            .then((allMembers) => {
                // check 2 users have chat room
                if (allMembers !== null) {
                    for (let member in allMembers) {
                        if (
                            Object.values(allMembers[member]).indexOf(myUserId) > -1 &&
                            Object.values(allMembers[member]).indexOf(user.id) > -1
                        ) {
                            console.log("chatId: " + member);
                            chatId = member;
                            break;
                        }
                    }
                }

                if (chatId === null) {
                    chatId = push(child(dbRef, "chats")).key;
                    set(child(dbRef, `chats/${chatId}`), {
                        lastMessage: "",
                        timestamp: "",
                    });

                    set(child(dbRef, `members/${chatId}`), {
                        userId1: myUserId,
                        userId2: user.id,
                    });

                    set(child(dbRef, `userRooms/${myUserId}/${chatId}`), chatId);
                    set(child(dbRef, `userRooms/${user.id}/${chatId}`), chatId);

                    console.log("Add chat successful!");
                } else {
                    console.log("Chat already!");
                }

                console.log("Mở đoạn chat:" + chatId);
                unsubscribeOnChildAdded();
                formSendMessage.style.display = "block";
                avatarOtherUser.src = user.avatar;
                onLoadMessage(chatId, myUserId);
            });
    };

    // add message
    const addMessage = (chatId, myUserId, msgText) => {
        const newMessageKey = push(child(dbRef, `messages/${chatId}`)).key;

        set(child(dbRef, `messages/${chatId}/${newMessageKey}`), {
            senderId: myUserId,
            message: msgText,
            timestamp: new Date().getTime(),
        }).then(() => {
            set(child(dbRef, `chats/${chatId}`), {
                lastMessage: msgText,
                timestamp: new Date().getTime(),
            }).then(() => {
                areaMessageText.value = "";
                messages.scrollIntoView(false);
            });
        });
    };
    //END FUNCTION

    // EVENT
    // event choose user
    $("#kw-user").keyup(function () {
        if (document.getElementById("kw-user").value === "") {
            areaSearchUser.innerHTML = "";
        } else {
            fetch(
                `${appContext}api/users-chat/?kw-username=${
                    document.getElementById("kw-user").value
                }`,
                {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                }
            )
                .then((res) => res.json())
                .then((data) => {
                    let userHtml = "";
                    areaSearchUser.innerHTML = "";
                    data.forEach((user) => {
                        userHtml = `<li class="p-2 border-bottom" id="user${user.id}">
                                <a href="javascript:;" class="d-flex justify-content-between">
                                    <div class="d-flex flex-row">
                                        <div>
                                            <img src="${user.avatar}"
                                                    alt="avatar" class="d-flex align-self-center me-3" width="40" style="border-radius: 50%;">
                                        </div>
                                        <div class="pt-0">
                                            <p class="mb-0 mt-2 ml-1 text-white">${user.username}</p>
                                        </div>
                                    </div>
                                </a>
                            </li>`;
                        areaSearchUser.insertAdjacentHTML("beforeend", userHtml);
                        document
                            .getElementById(`user${user.id}`)
                            .addEventListener("click", function (e) {
                                addChat(myUserId, user);
                                areaSearchUser.innerHTML = "";
                            });
                    });
                })
                .catch((err) => console.log(err));
        }
    });

    // event send message
    formSendMessage.addEventListener("submit", (e) => {
        e.preventDefault();
        addMessage(chatId, myUserId, areaMessageText.value);
    });
    // END EVENT
};
