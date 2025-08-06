package com.kava.s.CadastroNinja.missao.controller;

import com.kava.s.CadastroNinja.missao.dto.MissaoDTO;
import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import com.kava.s.CadastroNinja.missao.service.MissaoService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> criarMissao(@RequestBody MissaoDTO missaoDTO){
        MissaoDTO missao = missaoService.criarMissao(missaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(missao + " criada com sucesso!");
    }

    //GET -- Manda uma requisição para listar todos as missões
    @GetMapping("/listar")
    public ResponseEntity<List<MissaoDTO>> listarMissoes(){
        List<MissaoDTO> missoes = missaoService.listarMissoes();

        return ResponseEntity.ok(missoes);
    }

    //GET -- Manda uma requisição para listar missao por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<MissaoDTO> listarMissaoPorId(@PathVariable Long id){
        return ResponseEntity.ok(missaoService.listarMissaoId(id));
    }

    //PUT -- Manda uma requisição para Alterar uma Missao
    @PutMapping("/alterar/{id}")
    public ResponseEntity<MissaoDTO> alterarMissao(@PathVariable Long id, @RequestBody MissaoDTO missaoDTO){
        return ResponseEntity.ok(missaoService.alterarMissao(id, missaoDTO));
    }

    //DELETE -- Manda uma requisição para deletar uma Missão
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMissao(@PathVariable Long id){
        missaoService.deletarMissao(id);

        return ResponseEntity.noContent().build();
    }
}
