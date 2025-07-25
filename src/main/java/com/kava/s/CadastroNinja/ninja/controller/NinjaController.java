package com.kava.s.CadastroNinja.ninja.controller;

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

    //Adicionar Ninja
    @PostMapping("/criar")
    public String adicionarNinja(){
        return "Adiciona ninja";
    }

    //Mostrar todos os ninjas
    @GetMapping("/listar")
    public List<NinjaModel> mostrarTodosNinjas(){
        return ninjaService.listarNinjas();
    }

    //Mostrar ninja por id
    @GetMapping("/listarID")
    public String mostrarNinjaPorId(){
        return "Mostra ninja por ID";
    }

    //Alterar ninja
    @PutMapping("/alteraID")
    public String alterarNinja(){
        return "Altera ninja";
    }

    //Deletar Ninja
    @DeleteMapping("/deletarID")
    public String deletaNinja(){
        return "Deleta ninja";
    }
}
