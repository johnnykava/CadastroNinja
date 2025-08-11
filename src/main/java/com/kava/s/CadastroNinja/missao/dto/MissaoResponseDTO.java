package com.kava.s.CadastroNinja.missao.dto;

import com.kava.s.CadastroNinja.ninja.dto.NinjaResponseDTO;
import com.kava.s.CadastroNinja.ninja.models.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissaoResponseDTO {

    private Long id;
    private String nome;
    private String dificuldade;
    private List<NinjaResponseDTO> ninjas;
}
