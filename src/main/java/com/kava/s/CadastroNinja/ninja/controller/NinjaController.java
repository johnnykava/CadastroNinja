package com.kava.s.CadastroNinja.ninja.controller;

import com.kava.s.CadastroNinja.ninja.dto.NinjaCreateDTO;
import com.kava.s.CadastroNinja.ninja.dto.NinjaResponseDTO;
import com.kava.s.CadastroNinja.ninja.dto.NinjaUpdateDTO;
import com.kava.s.CadastroNinja.ninja.dto.NinjaUpdateMissaoDTO;
import com.kava.s.CadastroNinja.ninja.service.NinjaService;
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
    public String boasVindas(){
        return "Essa é minha primeira rota";
    }

    //POST -- Manda uma requisição para criar ninja
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaCreateDTO ninja){
        NinjaResponseDTO ninjaCriado = ninjaService.criarNinja(ninja);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaCriado + " criado com sucesso!");
    }

    //GET -- Manda uma requisição para listar todos os ninjas
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaResponseDTO>> listarNinjas(){
        List<NinjaResponseDTO> ninjas = ninjaService.listarNinjas();

        return ResponseEntity.ok(ninjas);
    }

    //GET -- Manda uma requisição para listar ninja por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<NinjaResponseDTO> listarNinjaPorId(@PathVariable Long id){
        return ResponseEntity.ok(ninjaService.listarNinjaId(id));
    }

    //PATCH -- Manda uma requisição para alterar um ninja
    @PatchMapping("/alterar/{id}")
    public ResponseEntity<NinjaResponseDTO> alterarNinja(@PathVariable Long id, @RequestBody NinjaUpdateDTO ninjaUpdateDTO){
        return ResponseEntity.ok(ninjaService.alterarNinja(id, ninjaUpdateDTO));
    }

    //PUT -- Manda uma requisição para altarerar uma missao do ninja
    @PutMapping("/{id}/missao")
    public ResponseEntity<NinjaResponseDTO> atribuirMissao(@PathVariable Long id, @RequestBody NinjaUpdateMissaoDTO ninjaUpdateMissaoDTO){
        NinjaResponseDTO ninjaResponseDTO = ninjaService.atribuirMissao(id, ninjaUpdateMissaoDTO.getMissaoId());

        return ResponseEntity.ok(ninjaResponseDTO);
    }

    //DELETE -- Manda uma requisição para deletar um ninja
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletaNinja(@PathVariable Long id){
        ninjaService.deletarNinja(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/missao")
    public ResponseEntity<Void> removerMissao(@PathVariable Long id){
        ninjaService.removerMissao(id);

        return ResponseEntity.noContent().build();
    }
}
