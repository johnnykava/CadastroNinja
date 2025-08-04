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
        ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninja + " criado com sucesso!");
    }

    //GET -- Manda uma requisição para listar todos os ninjas
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();

        return ResponseEntity.ok(ninjas);
    }

    //GET -- Manda uma requisição para listar ninja por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjaPorId(@PathVariable Long id){
        NinjaDTO ninja = ninjaService.listarNinjaId(id);

        if(ninja != null){
            return ResponseEntity.ok(ninja);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja com id: " + id + " não encontrado");
    }

    //PUT -- Manda uma requisição para alterar um ninja
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
        if(ninjaService.alterarNinja(id, ninjaAtualizado) != null){
            return ResponseEntity.ok("Ninja com id: " + id + " atualizado com sucesso!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja com id: " + id + " não encontrado!");
    }

    //DELETE -- Manda uma requisição para deletar um ninja
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletaNinja(@PathVariable Long id){
        if(ninjaService.listarNinjaId(id) != null){
            ninjaService.deletarNinja(id);

            return ResponseEntity.ok("Ninja com id: " + id + " deletado com sucesso!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja com id: " + id + " não encontrado!");
    }
}
