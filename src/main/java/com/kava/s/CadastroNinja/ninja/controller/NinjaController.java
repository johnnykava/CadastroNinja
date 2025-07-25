package com.kava.s.CadastroNinja.ninja.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

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
    public String mostrarTodosNinjas(){
        return "Mostra todos os Ninjas";
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
