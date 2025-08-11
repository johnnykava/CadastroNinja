package com.kava.s.CadastroNinja.missao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissaoUpdateNinjaDTO {

    private List<Long> ninjaIds;
}
