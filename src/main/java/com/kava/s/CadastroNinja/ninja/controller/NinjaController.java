package com.kava.s.CadastroNinja.ninja.controller;

import com.kava.s.CadastroNinja.ninja.dto.NinjaDTO;
import com.kava.s.CadastroNinja.ninja.models.NinjaModel;
import com.kava.s.CadastroNinja.ninja.service.NinjaService;
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
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja){
        return ninjaService.criarNinja(ninja);
    }

    //GET -- Manda uma requisição para listar todos os ninjas
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }

    //GET -- Manda uma requisição para listar ninja por ID
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjaPorId(@PathVariable Long id){
        return ninjaService.listarNinjaId(id);
    }

    //PUT -- Manda uma requisição para alterar um ninja
    @PutMapping("/alterar/{id}")
    public NinjaModel alterarNinja(@PathVariable Long id, @RequestBody NinjaModel ninjaAtualizado){
        return ninjaService.alterarNinja(id, ninjaAtualizado);
    }

    //DELETE -- Manda uma requisição para deletar um ninja
    @DeleteMapping("/deletar/{id}")
    public void deletaNinja(@PathVariable Long id){
        ninjaService.deletarNinja(id);
    }
}
