package com.leveling.solo.handler;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class LoginAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException {
        response.addHeader("WWW-Authenticate", String.format("Basic realm=\"%s\"", getRealmName()));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String msg = "";

        if (authEx instanceof BadCredentialsException) {
            msg = "Bad credentials";
        } else if (authEx instanceof InsufficientAuthenticationException) {
            msg = "No credentials";
        } else {
            msg = authEx.getMessage();
        }

        System.out.println("\n" + authEx.toString() + "\n");

        PrintWriter writer = response.getWriter();
        writer.println(
                String.format("{ HTTP Status : 401, \nMessage : %s }", msg, authEx.toString()));
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("Login");
        super.afterPropertiesSet();
    }
}
