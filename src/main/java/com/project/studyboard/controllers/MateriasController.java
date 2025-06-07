package com.project.studyboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.studyboard.models.Materias;
import com.project.studyboard.services.MateriasService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/materias")


      
/// rota para acessar as materias
public class MateriasController {

    @Autowired
    private MateriasService materiasService;

    @GetMapping
    public String materias(Model model) {
        try {
            model.addAttribute("materias", materiasService.listarMaterias());
            model.addAttribute("materia", new Materias());
            return "materias";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("materias", java.util.Collections.emptyList());
            model.addAttribute("materia", new Materias());
            return "materias";
        }
    }

    @PostMapping
    public String inserirMateria(@ModelAttribute Materias materia) {
        try {
            materiasService.inserirMateria(materia);
            return "redirect:/materias";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/autores?erro=true";
        }
    }

    @GetMapping("/edit/{id}")
    public String editarMateria(@PathVariable Long id, Model model) {
        try {
            Materias materia = materiasService.buscarpMaterias(id);
            if (materia == null) {
                return "redirect:/materias";
            }
            model.addAttribute("materia", materia);
            model.addAttribute("materias", materiasService.listarMaterias());
            return "materias";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/materias";
        }
    }

    @GetMapping("/delete/{id}")
    public String excluirMateria(@PathVariable Long id){
        try {
            materiasService.deletarMateria(id);
            return "redirect:/materias";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/materias?erro=exclusao";
        }
    }



}
