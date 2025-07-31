package com.kava.s.CadastroNinja.missao.service;

import com.kava.s.CadastroNinja.missao.dto.MissaoDTO;
import com.kava.s.CadastroNinja.missao.mapper.MissaoMapper;
import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import com.kava.s.CadastroNinja.missao.repository.MissaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<MissaoDTO> listarMissoes(){
        List<MissaoModel> missaoModel = missaoRepository.findAll();

        return missaoModel.stream()
                .map(missaoMapper::map)
                .collect(Collectors.toList());
    }

    //GET -- Manda uma requisição para listar missao por ID
    public MissaoDTO listarMissaoId(Long id){
        Optional<MissaoModel> caixaMissao = missaoRepository.findById(id);

        return caixaMissao.map(missaoMapper::map).orElse(null);
    }

    //PUT -- Manda uma requisição para Alterar uma Missao
    public MissaoDTO alterarMissao(Long id, MissaoDTO missaoDTO){
        Optional<MissaoModel> caixaMissao = missaoRepository.findById(id);

        if(caixaMissao.isPresent()){
            MissaoModel missao = missaoMapper.map(missaoDTO);
            missao.setId(id);

            return missaoMapper.map(missao);
        }

        return null;
    }

    //DELETE -- Manda uma requisição para deletar uma Missão
    public void deletarMissao(Long id){
        Optional<MissaoModel> caixaMissao = missaoRepository.findById(id);

        if(caixaMissao.isPresent()){
            missaoRepository.deleteById(id);
        }
    }
}
