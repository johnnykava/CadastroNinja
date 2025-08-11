package com.kava.s.CadastroNinja.missao.controller;

import com.kava.s.CadastroNinja.missao.dto.MissaoCreateDTO;
import com.kava.s.CadastroNinja.missao.dto.MissaoResponseDTO;
import com.kava.s.CadastroNinja.missao.dto.MissaoUpdateNinjaDTO;
import com.kava.s.CadastroNinja.missao.dto.MissaoUpdateRequestDTO;
import com.kava.s.CadastroNinja.missao.service.MissaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private final MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    //POST -- Manda uma requisição para criar as missões
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissaoCreateDTO missaoCreateDTO){
        MissaoResponseDTO missao = missaoService.criarMissao(missaoCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(missao + " criada com sucesso!");
    }

    //GET -- Manda uma requisição para listar todos as missões
    @GetMapping("/listar")
    public ResponseEntity<List<MissaoResponseDTO>> listarMissoes(){
        List<MissaoResponseDTO> missoes = missaoService.listarMissoes();

        return ResponseEntity.ok(missoes);
    }

    //GET -- Manda uma requisição para listar missao por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<MissaoResponseDTO> listarMissaoPorId(@PathVariable Long id){
        return ResponseEntity.ok(missaoService.listarMissaoId(id));
    }

    //PATCH -- Manda uma requisição para Alterar uma Missao
    @PatchMapping("/alterar/{id}")
    public ResponseEntity<MissaoResponseDTO> alterarMissao(@PathVariable Long id, @RequestBody MissaoUpdateRequestDTO missaoUpdateRequestDTO){
        return ResponseEntity.ok(missaoService.alterarMissao(id, missaoUpdateRequestDTO));
    }

    @PutMapping("/{id}/ninjas")
    public ResponseEntity<MissaoResponseDTO> definirEquipe(@PathVariable Long id, @RequestBody MissaoUpdateNinjaDTO missaoUpdateNinjaDTO){

        return ResponseEntity.ok(missaoService.definirEquipe(id, missaoUpdateNinjaDTO.getNinjaIds()));
    }

    //DELETE -- Manda uma requisição para deletar uma Missão
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMissao(@PathVariable Long id){
        missaoService.deletarMissao(id);

        return ResponseEntity.noContent().build();
    }
}
