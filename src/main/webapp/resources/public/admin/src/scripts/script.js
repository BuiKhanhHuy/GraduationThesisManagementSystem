const onSaveDataLoading = () => {
    $('#btn-submit-form').html('<span class="spinner-border spinner-border-sm mr-2" role="status" aria-hidden="true"></span>Đang lưu dữ liệu...').attr('disabled', true);
    let closeButtons = document.getElementsByClassName("close-custom")
    for (let i = 0; i < closeButtons.length; i++) {
        closeButtons[i].disabled = true
    }
}

const offSaveDataLoading = () => {
    $('#btn-submit-form').html('<i class="micon fa fa-save"> </i> Lưu dữ liệu').attr('disabled', false);
    let closeButtons = document.getElementsByClassName("close-custom")
    for (let i = 0; i < closeButtons.length; i++) {
        closeButtons[i].disabled = false
    }
}