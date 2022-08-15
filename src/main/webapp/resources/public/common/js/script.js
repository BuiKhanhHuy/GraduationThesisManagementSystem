// overlay loading
const showLoading = () => {
   $('#cover-spin').show(0)

}

const hideLoading = () => {
    $('#cover-spin').hide(0)
}

// sweet alert 2
const successfulAlert = (title, confirmButtonText, callback) => {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
        }, buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        position: 'center', icon: 'success', title: title, showConfirmButton: true, confirmButtonText: confirmButtonText
    }).then(callback)
}

const errorAlert = (title, text, confirmButtonText) => {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-danger',
        }, buttonsStyling: false
    })
    swalWithBootstrapButtons.fire({
        icon: 'error', title: title, text: text, showConfirmButton: true, confirmButtonText: confirmButtonText
    })
}

const confirmAlert = (title, text, confirmButtonText, cancelButtonText, callback) => {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success ml-1', cancelButton: 'btn btn-danger  mr-1'
        }, buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: title,
        text: text,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: confirmButtonText,
        cancelButtonText: cancelButtonText,
        reverseButtons: true,
    }).then(function (result) {
        if (result.isConfirmed) callback();
    })
}

const turnOffNotification = (appContext, notificationUserId) => {
    fetch(`${appContext}admin/api/notifications-user/${notificationUserId}`, {
        method: "PATCH", headers: {
            "Content-Type": "application/json"
        }
    }).then(res => {
        if (res.ok) {
            let notificationUserArea = document.getElementById("notification-user-area");

            notificationUserArea.removeChild(document.getElementById(`notification-user-${notificationUserId}`))

            if (document.getElementsByClassName("notification-user").length === 0) {
                notificationUserArea.innerHTML = `<li class="text-center text-secondary">Không có thông báo.</li>`
            }
        }
    }).catch(err => {
        console.error(err)
    })
}


