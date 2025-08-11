package com.kava.s.CadastroNinja.ninja.controller;

import com.kava.s.CadastroNinja.missao.dto.MissaoResponseDTO;
import com.kava.s.CadastroNinja.missao.service.MissaoService;
import com.kava.s.CadastroNinja.ninja.dto.NinjaCreateDTO;
import com.kava.s.CadastroNinja.ninja.dto.NinjaResponseDTO;
import com.kava.s.CadastroNinja.ninja.service.NinjaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {
    private final NinjaService ninjaService;
    private final MissaoService missaoService;


    public NinjaControllerUi(NinjaService ninjaService, MissaoService missaoService) {
        this.ninjaService = ninjaService;
        this.missaoService = missaoService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model){
        // Passo 1: Busca a lista completa de ninjas (DTOs). Nenhuma mudança aqui.
        List<NinjaResponseDTO> ninjas = ninjaService.listarNinjas();

        // Passo 2: Busca a lista COMPLETA de TODAS as missões.
        // Estamos usando o método que já existe no seu service.
        List<MissaoResponseDTO> todasAsMissoes = missaoService.listarMissoes();

        // Passo 3: Cria o "mapa de tradução" em memória, a partir da lista de todas as missões.
        // A lógica é a mesma, mas a fonte dos dados mudou.
        Map<Long, String> mapaDeNomesDasMissoes = todasAsMissoes.stream()
                .collect(Collectors.toMap(MissaoResponseDTO::getId, MissaoResponseDTO::getNome));

        // Passo 4: Adiciona a lista de ninjas e o mapa de nomes ao Model. Nenhuma mudança aqui.
        model.addAttribute("ninjas", ninjas);
        model.addAttribute("nomesDasMissoes", mapaDeNomesDasMissoes);

        return "ninja/listarNinjas";
    }

    @GetMapping("/deletar/{id}")
    public String deletaNinja(@PathVariable Long id){
        ninjaService.deletarNinja(id);

        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model) {
        try {
            // --- Tenta executar o caminho feliz ---

            // 1. Busca o DTO do ninja. Se não encontrar, o 'catch' será acionado.
            NinjaResponseDTO ninja = ninjaService.listarNinjaId(id);

            // 2. Adiciona o ninja ao model para o Thymeleaf usar
            model.addAttribute("ninja", ninja);

            // 3. VERIFICA se o ninja tem uma missão e busca os detalhes dela
            if (ninja.getMissaoId() != null) {
                // Se tiver, busca os detalhes daquela missão...
                MissaoResponseDTO missao = missaoService.listarMissaoId(ninja.getMissaoId());
                // ...e adiciona a missão ao model também, com um nome diferente
                model.addAttribute("missao", missao);
            }

            // 4. Se tudo deu certo, mostra a página de detalhes
            return "ninja/detalhesNinja"; // O .html é opcional

        } catch (IllegalArgumentException e) {
            // --- Executa se a exceção for lançada (ninja não encontrado) ---

            // 1. Adiciona uma mensagem de erro ao model
            model.addAttribute("mensagemDeErro", "Ninja com ID " + id + " não foi encontrado.");

            // 2. Redireciona o usuário de volta para a página de listagem
            return "redirect:/ninjas/ui/listar"; // Usar redirect é uma boa prática
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaResponseDTO());
        return "ninja/adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaCreateDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }
}
