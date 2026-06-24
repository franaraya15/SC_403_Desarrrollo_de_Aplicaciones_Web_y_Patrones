package com.practica.controller;

import com.practica.service.SuculentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final SuculentaService suculentaService;

    public IndexController(SuculentaService suculentaService) {
        this.suculentaService = suculentaService;
    }

    // Pagina de inicio: muestra el catalogo de suculentas activas
    @GetMapping("/")
    public String inicio(Model model) {
        var suculentas = suculentaService.getSuculentas(true);
        model.addAttribute("suculentas", suculentas);
        model.addAttribute("totalSuculentas", suculentas.size());
        return "index";
    }

}
