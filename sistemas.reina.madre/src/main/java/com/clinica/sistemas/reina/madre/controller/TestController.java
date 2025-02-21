package com.clinica.sistemas.reina.madre.controller;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/publico")
    public String allAccess() {
        return "Prueba de acceso publico.";
    }
    @GetMapping("/usuario")
    public String userAccess() {
        return "Prueba de acceso para usuarios.";
    }
}