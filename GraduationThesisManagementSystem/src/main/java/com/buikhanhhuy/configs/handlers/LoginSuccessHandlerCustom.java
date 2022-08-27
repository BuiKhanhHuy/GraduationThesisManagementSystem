package com.buikhanhhuy.configs.handlers;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class LoginSuccessHandlerCustom implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User user = userDetailsService.getUserByUserName(authentication.getName());
        String roleName = user.getRole().getRoleName();

        httpServletRequest.getSession().setAttribute("currentUser", user);

        if (Objects.equals(roleName, SystemConstant.ROLE_ADMIN) || Objects.equals(roleName, SystemConstant.ROLE_MINISTRY) || Objects.equals(roleName, SystemConstant.ROLE_LECTURER)) {
            httpServletResponse.sendRedirect("/GraduationThesisManagementSystem/admin/");
        } else {
            httpServletResponse.sendRedirect("/GraduationThesisManagementSystem/");
        }
    }
}
