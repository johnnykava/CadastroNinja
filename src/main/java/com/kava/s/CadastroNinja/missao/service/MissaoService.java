package com.kava.s.CadastroNinja.missao.service;

import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import com.kava.s.CadastroNinja.missao.repository.MissaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissaoService {

    private MissaoRepository missaoRepository;

    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    //POST -- Manda uma requisição para criar as missões
    public MissaoModel criarMissao(MissaoModel missao){
        return missaoRepository.save(missao);
    }

    //GET -- Manda uma requisição para listar todos as missões
    public List<MissaoModel> listarMissoes(){
        return missaoRepository.findAll();
    }

    //GET -- Manda uma requisição para listar missao por ID
    public MissaoModel listarMissaoId(Long id){
        Optional<MissaoModel> caixaMissao = missaoRepository.findById(id);

        return caixaMissao.orElse(null);
    }

    //DELETE -- Manda uma requisição para deletar uma Missão
    public void deletarMissao(Long id){
        missaoRepository.deleteById(id);
    }
}
