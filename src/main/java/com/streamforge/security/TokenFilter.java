package com.streamforge.security;

import com.streamforge.data.entity.Session;
import com.streamforge.service.common.AuthorizationService;
import com.streamforge.service.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        if (authorizationService == null){
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            authorizationService = webApplicationContext.getBean(AuthorizationService.class);
        }

        String token = request.getHeader("Authorization");
        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            if (token.startsWith(Constants.TOKEN_CUSTOM)) {
                String tokenValue = token.split(" ")[1];
                Session session = authorizationService.findSession(tokenValue);
                if (session == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                } else if (Session.State.EXPIRED == session.getState()) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                } else {
                    filterChain.doFilter(request, response);
                }
            }
        }

    }
}
