package com.kava.s.CadastroNinja.missao.mapper;

import com.kava.s.CadastroNinja.missao.dto.MissaoCreateDTO;
import com.kava.s.CadastroNinja.missao.dto.MissaoResponseDTO;
import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import com.kava.s.CadastroNinja.ninja.dto.NinjaResponseDTO;
import com.kava.s.CadastroNinja.ninja.mapper.NinjaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissaoMapper {

    private final NinjaMapper ninjaMapper;

    public MissaoMapper(NinjaMapper ninjaMapper) {
        this.ninjaMapper = ninjaMapper;
    }

    public MissaoModel map(MissaoCreateDTO missaoCreateDTO){
        if(missaoCreateDTO == null) throw new IllegalArgumentException("Teste");

        MissaoModel missaoModel = new MissaoModel();
        missaoModel.setNome(missaoCreateDTO.getNome());
        missaoModel.setDificuldade(missaoCreateDTO.getDificuldade());

        return missaoModel;
    }

    public MissaoResponseDTO map(MissaoModel missaoModel){
        if(missaoModel == null) throw new IllegalArgumentException("Teste");

        MissaoResponseDTO missaoResponseDTO = new MissaoResponseDTO();
        missaoResponseDTO.setId(missaoModel.getId());
        missaoResponseDTO.setNome(missaoModel.getNome());
        missaoResponseDTO.setDificuldade(missaoModel.getDificuldade());

        if(missaoModel.getNinjas() != null){
            missaoResponseDTO.setNinjas(ninjaMapper.map(missaoModel.getNinjas()));
        }

        return missaoResponseDTO;
    }

    public List<MissaoResponseDTO> map(List<MissaoModel> missaoModels){
        return missaoModels.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
