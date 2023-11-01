package com.buikhanhhuy.configs.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogoutSuccessHandlerCustom implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (httpServletRequest.getSession().getAttribute("currentUser") != null)
            httpServletRequest.getSession().removeAttribute("currentUser");

        httpServletResponse.sendRedirect("/GraduationThesisManagementSystem/login");
    }
}
