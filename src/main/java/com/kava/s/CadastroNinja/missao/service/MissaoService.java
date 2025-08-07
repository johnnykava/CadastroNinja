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

    private final MissaoRepository missaoRepository;
    private final MissaoMapper missaoMapper;

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
        MissaoModel missao = buscaPorId(id);

        return missaoMapper.map(missao);
    }

    //PUT -- Manda uma requisição para Alterar uma Missao
    public MissaoDTO alterarMissao(Long id, MissaoDTO missaoDTO){
        MissaoModel missao = buscaPorId(id);

        missao = missaoMapper.map(missaoDTO);
        missao.setId(id);
        missaoRepository.save(missao);

        return missaoMapper.map(missao);
    }

    //DELETE -- Manda uma requisição para deletar uma Missão
    public void deletarMissao(Long id){
        MissaoModel missao = buscaPorId(id);

        missaoRepository.deleteById(missao.getId());
    }

    private MissaoModel buscaPorId(Long id){
        Optional<MissaoModel> caixaMissao = missaoRepository.findById(id);

        if(caixaMissao.isPresent()){
            return caixaMissao.get();
        }

        throw new IllegalArgumentException("Recurso não encontrado.");
    }
}
