package com.kava.s.CadastroNinja.ninja.mapper;

import com.kava.s.CadastroNinja.ninja.dto.NinjaCreateDTO;
import com.kava.s.CadastroNinja.ninja.dto.NinjaResponseDTO;
import com.kava.s.CadastroNinja.ninja.models.NinjaModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NinjaMapper {

    public NinjaModel map(NinjaCreateDTO ninjaCreateDTO){
        if(ninjaCreateDTO == null) throw new IllegalArgumentException("Teste");

        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setNome(ninjaCreateDTO.getNome());
        ninjaModel.setEmail(ninjaCreateDTO.getEmail());
        ninjaModel.setIdade(ninjaCreateDTO.getIdade());
        ninjaModel.setRank(ninjaCreateDTO.getRank());
        ninjaModel.setImagemUrl(ninjaCreateDTO.getImagemUrl());

        return ninjaModel;
    }

    public NinjaResponseDTO map(NinjaModel ninjaModel){
        if(ninjaModel == null) throw new IllegalArgumentException("Teste");

        NinjaResponseDTO ninjaResponseDTO = new NinjaResponseDTO();
        ninjaResponseDTO.setId(ninjaModel.getId());
        ninjaResponseDTO.setNome(ninjaModel.getNome());
        ninjaResponseDTO.setEmail(ninjaModel.getEmail());
        ninjaResponseDTO.setIdade(ninjaModel.getIdade());
        ninjaResponseDTO.setRank(ninjaModel.getRank());
        ninjaResponseDTO.setImagemUrl(ninjaModel.getImagemUrl());

        if(ninjaModel.getMissoes() != null){
            ninjaResponseDTO.setMissaoId(ninjaModel.getMissoes().getId());
        }

        return ninjaResponseDTO;
    }

    public List<NinjaResponseDTO> map(List<NinjaModel> ninjaModels){
        return ninjaModels.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
