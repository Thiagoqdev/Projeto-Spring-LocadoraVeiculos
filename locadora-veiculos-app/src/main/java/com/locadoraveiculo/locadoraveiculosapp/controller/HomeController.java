package com.locadoraveiculo.locadoraveiculosapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Tag(name = "Home", description = "Página inicial da aplicação")
public class HomeController {

    @GetMapping("/")
    @Operation(summary = "Página inicial", description = "Retorna a página inicial da aplicação")
    public String home() {
        return "index";
    }
}