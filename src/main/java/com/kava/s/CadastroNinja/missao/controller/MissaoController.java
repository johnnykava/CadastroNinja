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
        missaoService.criarMissao(missaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(missaoDTO + " criada com sucesso!");
    }

    //GET -- Manda uma requisição para listar todos as missões
    @GetMapping("/listar")
    public ResponseEntity<List<MissaoDTO>> listarMissoes(){
        List<MissaoDTO> missoes = missaoService.listarMissoes();

        return ResponseEntity.ok(missoes);
    }

    //GET -- Manda uma requisição para listar missao por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id){
        MissaoDTO missao =  missaoService.listarMissaoId(id);

        if(missao != null){
            return ResponseEntity.ok(missao);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("missao com id: " + id + " não encontrada");
    }

    //PUT -- Manda uma requisição para Alterar uma Missao
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarMissao(@PathVariable Long id, @RequestBody MissaoDTO missaoDTO){
        if( missaoService.alterarMissao(id, missaoDTO) != null){
            return ResponseEntity.ok("Missão com id: " + id + " atualizado com sucesso!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Missao com id: " + id + " não encontrada!");
    }

    //DELETE -- Manda uma requisição para deletar uma Missão
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){
        if(missaoService.listarMissaoId(id) != null){
            missaoService.deletarMissao(id);

            return ResponseEntity.ok("Missao com id: " + id + " deletada com sucesso");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Missao com id: " + id + " não encontrada");
    }
}
