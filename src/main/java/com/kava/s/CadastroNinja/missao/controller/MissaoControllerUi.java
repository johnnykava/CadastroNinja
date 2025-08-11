package com.kava.s.CadastroNinja.missao.controller;

import com.kava.s.CadastroNinja.missao.dto.MissaoCreateDTO;
import com.kava.s.CadastroNinja.missao.dto.MissaoResponseDTO;
import com.kava.s.CadastroNinja.missao.service.MissaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissaoControllerUi {

    private final MissaoService missaoService;

    public MissaoControllerUi(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model){
        List<MissaoResponseDTO> missoes = missaoService.listarMissoes();

        model.addAttribute("missoes", missoes);

        return "missao/listarMissoes";
    }

    @GetMapping("/listar/{id}")
    public String detalhesDaMissao(@PathVariable Long id, Model model) {
        try {
            MissaoResponseDTO missao = missaoService.listarMissaoId(id);
            model.addAttribute("missao", missao);
            return "missao/detalhesMissao";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensagemDeErro", "Missão com ID " + id + " não foi encontrada.");
            return "redirect:/missoes/ui/listar";
        }
    }

    @GetMapping("deletar/{id}")
    public String deletarMissao(@PathVariable Long id){
        missaoService.deletarMissao(id);

        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/adicionar")
    public String mostrarFormulario(Model model){
        model.addAttribute("missoes", new MissaoResponseDTO());

        return "missao/adicionarMissao";
    }

    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute MissaoCreateDTO missaoCreateDTO, RedirectAttributes redirectAttributes){
        missaoService.criarMissao(missaoCreateDTO);
        redirectAttributes.addFlashAttribute("mensagem", "Missão criada com sucesso.");

        return "redirect:/missoes/ui/listar";
    }
}
