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

    public NinjaModel criarNinja(NinjaModel ninja){
        return ninjaRepository.save(ninja);
    }

    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll();
    }

    public NinjaModel listarNinjaId(Long id){
        Optional<NinjaModel> caixaNinja = ninjaRepository.findById(id);

        return caixaNinja.orElse(null);
    }

    public void deletarNinja(Long id){
        ninjaRepository.deleteById(id);
    }
}
