package com.kava.s.CadastroNinja.ninja.dto;

import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {

    private Long id;
    private String nome;
    private String email;
    private int idade;
    private String rank;
    private String imagemUrl;
    private MissaoModel missoes;
}
