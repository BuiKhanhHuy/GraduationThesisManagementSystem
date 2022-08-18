package com.buikhanhhuy.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordUser {
    @NotNull
    private Integer userId;
    @NotEmpty(message = "{passwordUser.password.notNullMessage}")
    @NotNull(message = "{passwordUser.password.notNullMessage}")
    private String oldPassword;
    @NotEmpty(message = "{passwordUser.newPassword.notNullMessage}")
    @NotNull(message = "{passwordUser.newPassword.notNullMessage}")
    @Size(max = 50, message = "{passwordUser.newPassword.sizeMessage}")
    private String newPassword;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
