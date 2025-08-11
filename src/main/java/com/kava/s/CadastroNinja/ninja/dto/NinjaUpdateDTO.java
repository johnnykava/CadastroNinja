package com.kava.s.CadastroNinja.ninja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaUpdateDTO {

    private String nome;
    private String email;
    private Integer idade;
    private String rank;
    private String imagemUrl;
}
