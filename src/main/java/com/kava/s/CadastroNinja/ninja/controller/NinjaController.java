package com.kava.s.CadastroNinja.ninja.controller;

import com.kava.s.CadastroNinja.ninja.dto.NinjaDTO;
import com.kava.s.CadastroNinja.ninja.service.NinjaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira rota";
    }

    //POST -- Manda uma requisição para criar ninja
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO ninjaCriado = ninjaService.criarNinja(ninja);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaCriado + " criado com sucesso!");
    }

    //GET -- Manda uma requisição para listar todos os ninjas
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();

        return ResponseEntity.ok(ninjas);
    }

    //GET -- Manda uma requisição para listar ninja por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<NinjaDTO> listarNinjaPorId(@PathVariable Long id){
        return ResponseEntity.ok(ninjaService.listarNinjaId(id));
    }

    //PUT -- Manda uma requisição para alterar um ninja
    @PutMapping("/alterar/{id}")
    public ResponseEntity<NinjaDTO> alterarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
        return ResponseEntity.ok(ninjaService.alterarNinja(id, ninjaAtualizado));
    }

    //DELETE -- Manda uma requisição para deletar um ninja
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletaNinja(@PathVariable Long id){
        ninjaService.deletarNinja(id);

        return ResponseEntity.noContent().build();
    }
}
