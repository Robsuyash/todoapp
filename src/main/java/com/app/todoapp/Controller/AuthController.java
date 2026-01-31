package com.app.todoapp.controller;

import com.app.todoapp.dto.AuthResponse;
import com.app.todoapp.dto.LoginRequest;
import com.app.todoapp.dto.RegisterRequest;
import com.app.todoapp.service.AuthService;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin // optional but safe
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // @PostMapping("/register")
    // public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    //     authService.register(request);
    //     return ResponseEntity.ok("User registered successfully");
    // }

    // @PostMapping("/login")
    // public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    //     String token = authService.login(request);
    //     return ResponseEntity.ok(new AuthResponse(token));
    // }
    @PostMapping("/register")
public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
    authService.register(request);
    return ResponseEntity.ok("registered");
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    String token = authService.login(request);
    return ResponseEntity.ok(Map.of("token", token));
}


}
