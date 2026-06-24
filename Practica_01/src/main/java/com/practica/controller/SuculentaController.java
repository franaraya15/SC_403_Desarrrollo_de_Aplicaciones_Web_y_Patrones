package com.practica.controller;

import com.practica.domain.Suculenta;
import com.practica.service.SuculentaService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/suculenta")
public class SuculentaController {

    private final SuculentaService suculentaService;

    public SuculentaController(SuculentaService suculentaService) {
        this.suculentaService = suculentaService;
    }

    // Listado dinamico de todas las suculentas
    @GetMapping("/listado")
    public String listado(Model model) {
        var suculentas = suculentaService.getSuculentas(false);
        model.addAttribute("suculentas", suculentas);
        model.addAttribute("totalSuculentas", suculentas.size());
        return "suculenta/listado";
    }

    // Formulario para registrar una nueva suculenta
    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("suculenta", new Suculenta());
        return "suculenta/modifica";
    }

    // Formulario para editar una suculenta existente
    @GetMapping("/modificar/{idSuculenta}")
    public String modificar(@PathVariable("idSuculenta") Integer idSuculenta,
            Model model,
            RedirectAttributes redirectAttributes) {
        Optional<Suculenta> suculentaOpt = suculentaService.getSuculenta(idSuculenta);
        if (suculentaOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "La suculenta solicitada no existe.");
            return "redirect:/suculenta/listado";
        }
        model.addAttribute("suculenta", suculentaOpt.get());
        return "suculenta/modifica";
    }

    // Guarda (crea o actualiza) una suculenta
    @PostMapping("/guardar")
    public String guardar(@Valid Suculenta suculenta, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Si hay errores de validacion se vuelve al formulario
            return "suculenta/modifica";
        }
        suculentaService.save(suculenta);
        redirectAttributes.addFlashAttribute("todoOk", "La suculenta se guardo correctamente.");
        return "redirect:/suculenta/listado";
    }

    // Elimina una suculenta
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idSuculenta,
            RedirectAttributes redirectAttributes) {
        try {
            suculentaService.delete(idSuculenta);
            redirectAttributes.addFlashAttribute("todoOk", "La suculenta se elimino correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar la suculenta.");
        }
        return "redirect:/suculenta/listado";
    }

}
