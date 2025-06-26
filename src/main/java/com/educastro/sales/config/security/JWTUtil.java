package com.educastro.sales.config.security;

import static com.educastro.sales.config.TokenJwtConfig.SECRET_KEY;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.educastro.sales.config.SimpleGrantedAuthorityJsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTUtil {

    public String generateToken(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
            throws IOException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult
                .getPrincipal();
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        Claims claims = Jwts.claims()
                .add("authorities", new ObjectMapper().writeValueAsString(roles))
                .build();

        String token = Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SECRET_KEY)
                .compact();
        return token;
    }

    public String getSubject(String token) {
        Claims claims = this.getClaims(token);
        return claims.getSubject();
    }

    public boolean validateToken(String token, String username) {
        // Implement token validation logic here
        return true; // Placeholder for actual validation logic
    }

    public Claims getClaims(String token) {
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token) throws IOException {
        Claims claims = this.getClaims(token);
        Object authoritiesClaims = claims.get("authorities");

        Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                new ObjectMapper()
                        .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                        .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));
        return authorities;
    }
}
