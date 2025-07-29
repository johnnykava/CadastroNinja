package com.kava.s.CadastroNinja.missao.service;

import com.kava.s.CadastroNinja.missao.dto.MissaoDTO;
import com.kava.s.CadastroNinja.missao.mapper.MissaoMapper;
import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import com.kava.s.CadastroNinja.missao.repository.MissaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissaoService {

    private MissaoRepository missaoRepository;
    private MissaoMapper missaoMapper;

    public MissaoService(MissaoRepository missaoRepository, MissaoMapper missaoMapper) {
        this.missaoRepository = missaoRepository;
        this.missaoMapper = missaoMapper;
    }

    //POST -- Manda uma requisição para criar as missões
    public MissaoDTO criarMissao(MissaoDTO missaoDTO){
        MissaoModel missao = missaoMapper.map(missaoDTO);
        missaoRepository.save(missao);
        return missaoMapper.map(missao);
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

    //PUT -- Manda uma requisição para Alterar uma Missao
    public MissaoModel alterarMissao(Long id, MissaoModel missaoAtualizada){
        if(missaoRepository.existsById(id)){
            missaoAtualizada.setId(id);

            return missaoRepository.save(missaoAtualizada);
        }

        return null;
    }

    //DELETE -- Manda uma requisição para deletar uma Missão
    public void deletarMissao(Long id){
        missaoRepository.deleteById(id);
    }
}
