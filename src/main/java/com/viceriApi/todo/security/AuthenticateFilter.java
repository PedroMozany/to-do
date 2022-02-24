package com.viceriApi.todo.security;


import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viceriApi.todo.entities.User;
import com.viceriApi.todo.settings.SettingsToken;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class AuthenticateFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_FINAL = 600_000;
    public static final String TOKEN_PASSWORD = "1a60134d-7569-4bbc-beb7-e72bdd967b31";

    private final AuthenticationManager authenticationManager;

    public AuthenticateFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), new ArrayList<>()));
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to authenticate user", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException,ServletException {



        SettingsToken settingsToken = (SettingsToken) authResult.getPrincipal();

        String token = com.auth0.jwt.JWT.create()
                .withSubject(settingsToken.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_FINAL))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
