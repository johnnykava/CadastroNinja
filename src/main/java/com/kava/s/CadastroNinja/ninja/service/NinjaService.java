package com.kava.s.CadastroNinja.ninja.service;

import com.kava.s.CadastroNinja.ninja.dto.NinjaDTO;
import com.kava.s.CadastroNinja.ninja.mapper.NinjaMapper;
import com.kava.s.CadastroNinja.ninja.models.NinjaModel;
import com.kava.s.CadastroNinja.ninja.repository.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    //POST -- Manda uma requisição para criar ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    //GET -- Manda uma requisição para listar todos os ninjas
    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel> ninjaModel = ninjaRepository.findAll();

        return ninjaModel.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    //GET -- Manda uma requisição para listar ninja por ID
    public NinjaDTO listarNinjaId(Long id){
        Optional<NinjaModel> caixaNinja = ninjaRepository.findById(id);

        return caixaNinja.map(ninjaMapper::map).orElse(null);
    }

    //PUT -- Manda uma requisição para alterar um ninja
    public NinjaDTO alterarNinja(Long id, NinjaDTO ninjaDTO){
        Optional<NinjaModel> caixaNinja = ninjaRepository.findById(id);

        if(caixaNinja.isPresent()){
            NinjaModel ninja = ninjaMapper.map(ninjaDTO);
            ninja.setId(id);

            ninjaRepository.save(ninja);

            return ninjaMapper.map(ninja);
        }
        return null;
    }

    //DELETE -- Manda uma requisição para deletar um ninja
    public void deletarNinja(Long id){
        Optional<NinjaModel> caixaNinja = ninjaRepository.findById(id);

        if(caixaNinja.isPresent()){
            ninjaRepository.deleteById(id);
        }
    }
}
