package com.seguridadInformatica.spring_boot.controller;

import com.seguridadInformatica.spring_boot.dto.RegisterRequest;
import com.seguridadInformatica.spring_boot.entity.User;
import com.seguridadInformatica.spring_boot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request) {
        System.out.println("¡Estoy recibiendo el request!");
        userService.register(request);
        return "User registered";
    }

    @GetMapping("/me")
    public Object me(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
}