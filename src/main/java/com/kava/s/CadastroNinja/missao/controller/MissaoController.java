package com.kava.s.CadastroNinja.missao.controller;

import com.kava.s.CadastroNinja.missao.dto.MissaoCreateDTO;
import com.kava.s.CadastroNinja.missao.dto.MissaoResponseDTO;
import com.kava.s.CadastroNinja.missao.dto.MissaoUpdateNinjaDTO;
import com.kava.s.CadastroNinja.missao.dto.MissaoUpdateRequestDTO;
import com.kava.s.CadastroNinja.missao.service.MissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria uma nova missão", description = "Rota cira uma nova missão e insere no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Erro na criação.")
    })
    public ResponseEntity<String> criarMissao(
            @Parameter(description = "Usuario envia os dados da missão por JSON.")
            @RequestBody MissaoCreateDTO missaoCreateDTO){
        MissaoResponseDTO missao = missaoService.criarMissao(missaoCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(missao + " criada com sucesso!");
    }

    //GET -- Manda uma requisição para listar todos as missões
    @GetMapping("/listar")
    @Operation(summary = "Lista todas as missões", description = "Rota lista todas as missões no banco.")
    public ResponseEntity<List<MissaoResponseDTO>> listarMissoes(){
        List<MissaoResponseDTO> missoes = missaoService.listarMissoes();

        return ResponseEntity.ok(missoes);
    }

    //GET -- Manda uma requisição para listar missao por ID
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista missão por id", description = "Rota lista uma missão por id passado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada."),
            @ApiResponse(responseCode = "404", description = "Id da missão não encontrado.")
    })
    public ResponseEntity<MissaoResponseDTO> listarMissaoPorId(
            @Parameter(description = "Usuario insere o id da missão.")
            @PathVariable Long id){
        return ResponseEntity.ok(missaoService.listarMissaoId(id));
    }

    //PATCH -- Manda uma requisição para Alterar uma Missao
    @PatchMapping("/alterar/{id}")
    @Operation(summary = "Atualiza a missão", description = "Rota atualiza os dados escolhidos da missão.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Id da missão não encontrado.")
    })
    public ResponseEntity<MissaoResponseDTO> alterarMissao(
            @Parameter(description = "Usuario envia o id da missão.")
            @PathVariable Long id,
            @Parameter(description = "Usuario envia o JSON contendo os itens que deseja atualizar.")
            @RequestBody MissaoUpdateRequestDTO missaoUpdateRequestDTO){
        return ResponseEntity.ok(missaoService.alterarMissao(id, missaoUpdateRequestDTO));
    }

    @PutMapping("/{id}/ninjas")
    @Operation(summary = "Adiciona ninja na missão", description = "Rota adiciona uma lista de ninjas na missão.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista atualizada."),
            @ApiResponse(responseCode = "404", description = "Ninja ou missão inexistente.")
    })
    public ResponseEntity<MissaoResponseDTO> definirEquipe(
            @Parameter(description = "Usuario envia o id da missão.")
            @PathVariable Long id,
            @Parameter(description = "Usuario envia um DTO de uma lista de Ids de ninjas.")
            @RequestBody MissaoUpdateNinjaDTO missaoUpdateNinjaDTO){

        return ResponseEntity.ok(missaoService.definirEquipe(id, missaoUpdateNinjaDTO.getNinjaIds()));
    }

    //DELETE -- Manda uma requisição para deletar uma Missão
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta uma missão", description = "Rota deleta uma missão com base no id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada.")
    })
    public ResponseEntity<Void> deletarMissao(
            @Parameter(description = "Usuario envia o id da missão.")
            @PathVariable Long id){
        missaoService.deletarMissao(id);

        return ResponseEntity.noContent().build();
    }
}
