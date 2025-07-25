package com.kava.s.CadastroNinja.missao.controller;

import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import com.kava.s.CadastroNinja.missao.service.MissaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    //POST -- Manda uma requisição para criar as missões
    @PostMapping("/criar")
    public String adicionarMissao(){
        return "Cria missao";
    }

    //GET -- Manda uma requisição para listar todos as missões
    @GetMapping("/listar")
    public List<MissaoModel> mostrarTodasMissoes(){
        return missaoService.listarMissoes();
    }

    //GET -- Manda uma requisição para listar missao por ID
    @GetMapping("/listarID")
    public String mostrarMissaoID(){
        return "Mostra missao por ID";
    }

    //PUT -- Manda uma requisição para Alterar uma Missao
    @PutMapping("/alterarID")
    public String alterarMissao(){
        return "Altera missao";
    }

    //DELETE -- Manda uma requisição para deletar uma Missão
    @DeleteMapping("/deletarID")
    public String deletarMissao(){
        return "Deleta missao";
    }
}
