package com.anhvv.base.security.filter;

import com.anhvv.base.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernameAndPasswordAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public UsernameAndPasswordAuthenticationProcessingFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper) {
        super(defaultFilterProcessesUrl);
        this.objectMapper = objectMapper;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        User loginRequest = objectMapper.readValue(httpServletRequest.getReader(), User.class);

        if (StringUtils.isEmpty(loginRequest.getUserName()) || StringUtils
                .isEmpty(loginRequest.getPassword())) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getUserName(), loginRequest.getPassword());

        return this.getAuthenticationManager().authenticate(token);
    }
}
