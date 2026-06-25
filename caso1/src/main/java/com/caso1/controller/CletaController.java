package com.caso1.controller;

import com.caso1.domain.Cleta;
import com.caso1.service.CletaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/cleta")
public class CletaController {

    private final CletaService cletaService;

    public CletaController(CletaService cletaService) {
        this.cletaService = cletaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("cletas", cletaService.buscarTodos());
        model.addAttribute("cleta", new Cleta());
        return "/cleta/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Cleta cleta, @RequestParam MultipartFile imagenFile) {
        cletaService.guardar(cleta, imagenFile);
        return "redirect:/cleta/listado";
    }

    @GetMapping("/eliminar/{idCleta}")
    public String eliminar(@PathVariable Integer idCleta) {
        cletaService.eliminar(idCleta);
        return "redirect:/cleta/listado";
    }
}
