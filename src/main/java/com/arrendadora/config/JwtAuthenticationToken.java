package com.arrendadora.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String username;

    public JwtAuthenticationToken(String username) {
        super(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))); // Puedes asignar el rol que desees
        this.username = username;
        setAuthenticated(true);  // Ya está autenticado con el JWT
    }

    @Override
    public Object getCredentials() {
        return null;  // El JWT ya no se necesita en este punto
    }

    @Override
    public Object getPrincipal() {
        return username;  // El principal es el nombre de usuario extraído del JWT
    }
}
