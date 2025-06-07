package com.project.studyboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.studyboard.models.Anotacoes;
import com.project.studyboard.services.EstudosService;

import org.springframework.ui.Model;

import com.project.studyboard.services.AnotacoesService;

@Controller
@RequestMapping("/anotacoes")
public class AnotacoesController {

    @Autowired
    private com.project.studyboard.services.AnotacoesService anotacaoService;

    @Autowired
    private EstudosService estudosService;

    @GetMapping
    public String anotacoes(Model model) {
        try {
            model.addAttribute("anotacoes", anotacaoService.listarTodos());
            model.addAttribute("anotacao", new Anotacoes());
            model.addAttribute("estudos", estudosService.listarTodos());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("anotacoes", java.util.Collections.emptyList());
            model.addAttribute("anotacao", new Anotacoes());
            model.addAttribute("estudos", java.util.Collections.emptyList());
            return "anotacoes";
        }
        return "anotacoes";
    }

    @PostMapping
    public String anotacoesPost(@ModelAttribute Anotacoes anotacao) {
        try {
            anotacaoService.inserirAnotacao(anotacao);
            return "redirect:/anotacoes";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/anotacoes?erro=true";
        }
    }

    @GetMapping("/edit/{id}")
    public String editarAnotacao(@PathVariable Long id, Model model) {
        try {
            Anotacoes anotacao = anotacaoService.listarporId(id);
            if (anotacao == null) {
                return "redirect:/anotacoes";
            }
            model.addAttribute("anotacao", anotacao);
            model.addAttribute("anotacoes", anotacaoService.listarTodos());
            model.addAttribute("estudos", estudosService.listarTodos());
            return "anotacoes";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/anotacoes";
        }
    }

      @GetMapping("/excluir/{id}")
    public String excluirAnotacao(@PathVariable Long id) {
        try {
            anotacaoService.deletarAnotacao(id);
            return "redirect:/anotacoes";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/anotacoes?erro=exclusao";
        }
    }

}
