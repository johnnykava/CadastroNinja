package com.kava.s.CadastroNinja.missao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissaoUpdateRequestDTO {

    private String nome;
    private String dificuldade;
}
