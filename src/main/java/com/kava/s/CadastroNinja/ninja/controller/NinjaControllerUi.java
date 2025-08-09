package com.kava.s.CadastroNinja.ninja.controller;

import com.kava.s.CadastroNinja.ninja.dto.NinjaDTO;
import com.kava.s.CadastroNinja.ninja.service.NinjaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {
    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "ninja/listarNinjas";
    }

    @GetMapping("/deletar/{id}")
    public String deletaNinja(@PathVariable Long id){
        ninjaService.deletarNinja(id);

        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja =  ninjaService.listarNinjaId(id);
        if (ninja !=null) {
            model.addAttribute("ninja", ninja);
            return "ninja/detalhesNinja.html";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "ninja/listarNinjas";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "ninja/adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }
}
