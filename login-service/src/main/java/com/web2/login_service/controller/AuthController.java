package com.web2.login_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web2.login_service.service.AuthService;
import com.web2.login_service.dto.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = authService.authenticate(loginRequest.getEmail(), loginRequest.getSenha());
        return isAuthenticated
                ? ResponseEntity.ok("Autenticado")
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais invalidas");
    }
}
