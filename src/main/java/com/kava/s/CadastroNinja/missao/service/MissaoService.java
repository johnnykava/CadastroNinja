package com.kava.s.CadastroNinja.missao.service;

import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import com.kava.s.CadastroNinja.missao.repository.MissaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissaoService {

    private MissaoRepository missaoRepository;

    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    public List<MissaoModel> listarMissoes(){
        return missaoRepository.findAll();
    }
}
