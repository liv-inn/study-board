package com.project.studyboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.studyboard.models.Estudos;
import com.project.studyboard.services.EstudosService;
import com.project.studyboard.services.MateriasService;

@Controller
@RequestMapping("/estudos")
public class EstudosController {

    @Autowired
    private EstudosService estudoService;

    @Autowired
    private MateriasService materiasService;

    @GetMapping
    public String estudo(Model model) {
        try {
            model.addAttribute("estudos", estudoService.listarTodos());
            model.addAttribute("estudo", new Estudos());
            model.addAttribute("materias", materiasService.listarMaterias());
            return "estudos";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("estudos", java.util.Collections.emptyList());
            model.addAttribute("estudo", new Estudos());
            model.addAttribute("materias", materiasService.listarMaterias());
            return "estudos";
        }
    }

    @PostMapping
    public String inserirEstudo(@ModelAttribute Estudos estudo) {
        try {
            estudoService.inserirEstudo(estudo);
            return "redirect:/estudos";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/estudos?erro=true";
        }
    }

    @GetMapping("/edit/{id}")
    public String editarEstudo(@PathVariable Long id, Model model) {
        try {
            Estudos estudo = estudoService.buscarpEstudos(id);
            if (estudo == null) {
                return "redirect:/estudos";
            }
            model.addAttribute("estudo", estudo);
            model.addAttribute("estudos", estudoService.listarTodos());
            model.addAttribute("materias", materiasService.listarMaterias());
            return "estudos";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/estudos";
        }
    }

    @GetMapping("/delete/{id}")
    public String deletarEstudo(@PathVariable Long id) {
        try {
            estudoService.deletarEstudo(id);
            return "redirect:/estudos";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/estudos?erro=excluisao";
        }
    }

}
