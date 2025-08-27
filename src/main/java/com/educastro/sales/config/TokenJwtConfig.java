package com.educastro.sales.config;

import javax.crypto.SecretKey;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenJwtConfig {

    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

    public static SecretKey getSecretKey() {
        String secretKey = System.getenv("SECRET_KEY");
        System.out.println("Secret Key: " + secretKey);
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }
}
