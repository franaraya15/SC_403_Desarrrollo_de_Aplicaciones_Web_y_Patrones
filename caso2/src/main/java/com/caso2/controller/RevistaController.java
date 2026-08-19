package com.caso2.controller;

import com.caso2.domain.Revista;
import com.caso2.service.RevistaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class RevistaController {

    @Autowired
    private RevistaService revistaService;

    @GetMapping("/revista/listado")
    public String listado(Model model) {
        var revistas = revistaService.listarRevistas();
        model.addAttribute("revistas", revistas);
        model.addAttribute("totalRevistas", revistas.size());
        return "/revista/listado";
    }

    @GetMapping("/revista/modificar/{idRevista}")
    public String modificarRevista(Revista revista, Model model) {
        revista = revistaService.buscarRevista(revista);
        model.addAttribute("revista", revista);
        return "/revista/modificar";
    }

    @PostMapping("/revista/guardar")
    public String guardarRevista(Revista revista, Model model) {
        revistaService.guardar(revista);
        return "redirect:/revista/listado";
    }
}
