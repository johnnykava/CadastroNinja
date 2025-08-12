package com.kava.s.CadastroNinja.ninja.controller;

import com.kava.s.CadastroNinja.ninja.dto.NinjaCreateDTO;
import com.kava.s.CadastroNinja.ninja.dto.NinjaResponseDTO;
import com.kava.s.CadastroNinja.ninja.dto.NinjaUpdateDTO;
import com.kava.s.CadastroNinja.ninja.dto.NinjaUpdateMissaoDTO;
import com.kava.s.CadastroNinja.ninja.service.NinjaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa roda da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas(){
        return "Essa é minha primeira rota";
    }

    //POST -- Manda uma requisição para criar ninja
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Erro na criação.")
    })
    public ResponseEntity<String> criarNinja(
            @Parameter(description = "Usuario manda os dados em JSON para ser criado")
            @RequestBody NinjaCreateDTO ninja){
        NinjaResponseDTO ninjaCriado = ninjaService.criarNinja(ninja);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaCriado + " criado com sucesso!");
    }

    //GET -- Manda uma requisição para listar todos os ninjas
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os Ninjas", description = "Rota mostra todos os ninjas cadastrados no banco.")
    public ResponseEntity<List<NinjaResponseDTO>> listarNinjas(){
        List<NinjaResponseDTO> ninjas = ninjaService.listarNinjas();

        return ResponseEntity.ok(ninjas);
    }

    //GET -- Manda uma requisição para listar ninja por ID
    @Operation(summary = "Lista um ninja por id", description = "Rota mostra um ninja pelo id especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado."),
            @ApiResponse(responseCode = "404", description = "Não encontrado.")
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<NinjaResponseDTO> listarNinjaPorId(
            @Parameter(description = "Usuario manda um id para busca.")
            @PathVariable Long id){
        return ResponseEntity.ok(ninjaService.listarNinjaId(id));
    }

    //PATCH -- Manda uma requisição para alterar um ninja
    @PatchMapping("/alterar/{id}")
    @Operation(summary = "Altera os dados do ninja", description = "Rota para atualizar os dados selecionados do ninja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Id não encontrado.")
    })
    public ResponseEntity<NinjaResponseDTO> alterarNinja(
            @Parameter(description = "Usuario manda um id para busca.")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados atualizados em um JSON.")
            @RequestBody NinjaUpdateDTO ninjaUpdateDTO){
        return ResponseEntity.ok(ninjaService.alterarNinja(id, ninjaUpdateDTO));
    }

    //PUT -- Manda uma requisição para altarerar uma missao do ninja
    @PutMapping("/{id}/missao")
    @Operation(summary = "Altera a missão do ninja", description = "Rota para alterar a missão do ninja.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Id do ninja ou da missão não encontrado.")
    })
    public ResponseEntity<NinjaResponseDTO> atribuirMissao(
            @Parameter(description = "Usuario manda o id do ninja.")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda um DTO que contem o id da missão.")
            @RequestBody NinjaUpdateMissaoDTO ninjaUpdateMissaoDTO){
        NinjaResponseDTO ninjaResponseDTO = ninjaService.atribuirMissao(id, ninjaUpdateMissaoDTO.getMissaoId());

        return ResponseEntity.ok(ninjaResponseDTO);
    }

    //DELETE -- Manda uma requisição para deletar um ninja
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta um ninja", description = "Rota deleta um ninja por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado.")
    })
    public ResponseEntity<Void> deletaNinja(
            @Parameter(description = "Usuario manda o id do ninja.")
            @PathVariable Long id){
        ninjaService.deletarNinja(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/missao")
    @Operation(summary = "Deleta missão do ninja", description = "Rota tira (deleta) uma missão associada a um ninja.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Missão desassociada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<Void> removerMissao(
            @Parameter(description = "Usuario passa o id do ninja.")
            @PathVariable Long id){
        ninjaService.removerMissao(id);

        return ResponseEntity.noContent().build();
    }
}
