package com.kava.s.CadastroNinja.ninja.service;

import com.kava.s.CadastroNinja.ninja.models.NinjaModel;
import com.kava.s.CadastroNinja.ninja.repository.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    //POST -- Manda uma requisição para criar ninja
    public NinjaModel criarNinja(NinjaModel ninja){
        return ninjaRepository.save(ninja);
    }

    //GET -- Manda uma requisição para listar todos os ninjas
    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll();
    }

    //GET -- Manda uma requisição para listar ninja por ID
    public NinjaModel listarNinjaId(Long id){
        Optional<NinjaModel> caixaNinja = ninjaRepository.findById(id);

        return caixaNinja.orElse(null);
    }

    //PUT -- Manda uma requisição para alterar um ninja
    public NinjaModel alterarNinja(Long id, NinjaModel ninjaAtualizado){
        if(ninjaRepository.existsById(id)){
            ninjaAtualizado.setId(id);
            return ninjaRepository.save(ninjaAtualizado);
        }

        return null;
    }

    //DELETE -- Manda uma requisição para deletar um ninja
    public void deletarNinja(Long id){
        ninjaRepository.deleteById(id);
    }
}
