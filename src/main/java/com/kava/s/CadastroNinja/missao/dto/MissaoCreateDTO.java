package com.kava.s.CadastroNinja.missao.dto;

import com.kava.s.CadastroNinja.ninja.dto.NinjaResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissaoCreateDTO {

    private String nome;
    private String dificuldade;
    private List<Long> ninjaIds;
}
