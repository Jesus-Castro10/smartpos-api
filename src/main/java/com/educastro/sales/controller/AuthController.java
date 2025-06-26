package com.educastro.sales.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educastro.sales.config.security.JWTUtil;
import com.educastro.sales.model.dto.LoginRequest;
import com.educastro.sales.model.dto.LoginResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(HttpServletRequest request, HttpServletResponse response,@RequestBody LoginRequest loginRequest) throws IOException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String token = jwtUtil.generateToken(request, response, authentication);
        return ResponseEntity.ok(new LoginResponse(token, "Login successful"));
    }
}
