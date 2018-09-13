package com.hxkj.security;

import com.hxkj.model.HttpResult;
import com.hxkj.utils.JsonUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author dsd
 * @version 2018/6/21 13:42
 */
@SuppressWarnings("unused")
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LogManager.getLogger(JwtLoginFilter.class);

    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.setFilterProcessesUrl("/security/login");
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info("username: {} password: {}", username, password);
        if (StringUtils.isBlank(username) || StringUtils.isBlank("password")) {
            throw new AuthenticationServiceException("输入有误");
        }
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, JwtSign.SIGNATURE)
                .compact();

        JwtContainer.loggedInUsers.put(token, token);

        response.getWriter().write(JsonUtils.toJson(new HttpResult().setData(token).setStatusCode(HttpResult.SUCCESS)));
        response.getWriter().flush();
        response.getWriter().close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.getWriter().write(JsonUtils.toJson(new HttpResult().setStatusCode(HttpResult.NOT_LOGGED_IN)));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
