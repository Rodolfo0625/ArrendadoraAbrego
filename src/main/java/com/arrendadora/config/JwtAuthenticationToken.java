package com.arrendadora.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String username;

    public JwtAuthenticationToken(String username) {
        super(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))); 
        this.username = username;
        setAuthenticated(true);  
    }

    @Override
    public Object getCredentials() {
        return null;  
    }

    @Override
    public Object getPrincipal() {
        return username;  
    }
}
