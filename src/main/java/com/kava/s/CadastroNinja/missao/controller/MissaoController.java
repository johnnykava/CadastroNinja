package com.kava.s.CadastroNinja.missao.controller;

import com.kava.s.CadastroNinja.missao.dto.MissaoDTO;
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
    public MissaoDTO criarMissao(@RequestBody MissaoDTO missao){
        return missaoService.criarMissao(missao);
    }

    //GET -- Manda uma requisição para listar todos as missões
    @GetMapping("/listar")
    public List<MissaoDTO> listarMissoes(){
        return missaoService.listarMissoes();
    }

    //GET -- Manda uma requisição para listar missao por ID
    @GetMapping("/listar/{id}")
    public MissaoDTO listarMissaoPorId(@PathVariable Long id){
        return missaoService.listarMissaoId(id);
    }

    //PUT -- Manda uma requisição para Alterar uma Missao
    @PutMapping("/alterar/{id}")
    public MissaoDTO alterarMissao(@PathVariable Long id, @RequestBody MissaoDTO missaoDTO){
        return missaoService.alterarMissao(id, missaoDTO);
    }

    //DELETE -- Manda uma requisição para deletar uma Missão
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id){
        missaoService.deletarMissao(id);
    }
}
