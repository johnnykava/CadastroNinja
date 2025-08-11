package com.kava.s.CadastroNinja.missao.service;

import com.kava.s.CadastroNinja.missao.dto.MissaoCreateDTO;
import com.kava.s.CadastroNinja.missao.dto.MissaoResponseDTO;
import com.kava.s.CadastroNinja.missao.dto.MissaoUpdateNinjaDTO;
import com.kava.s.CadastroNinja.missao.dto.MissaoUpdateRequestDTO;
import com.kava.s.CadastroNinja.missao.mapper.MissaoMapper;
import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import com.kava.s.CadastroNinja.missao.repository.MissaoRepository;
import com.kava.s.CadastroNinja.ninja.models.NinjaModel;
import com.kava.s.CadastroNinja.ninja.repository.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;
    private final NinjaRepository ninjaRepository;
    private final MissaoMapper missaoMapper;

    public MissaoService(MissaoRepository missaoRepository, NinjaRepository ninjaRepository, MissaoMapper missaoMapper) {
        this.missaoRepository = missaoRepository;
        this.ninjaRepository = ninjaRepository;
        this.missaoMapper = missaoMapper;
    }

    //POST -- Manda uma requisição para criar as missões
    public MissaoResponseDTO criarMissao(MissaoCreateDTO missaoCreateDTO){
        MissaoModel missao = missaoMapper.map(missaoCreateDTO);

        if(missaoCreateDTO.getNinjaIds() != null && !missaoCreateDTO.getNinjaIds().isEmpty()){
            List<NinjaModel> equipe = ninjaRepository.findAllById(missaoCreateDTO.getNinjaIds());

            if(equipe.size() != missaoCreateDTO.getNinjaIds().size()){
                throw new IllegalArgumentException("Um ou mais ninjas com os IDs fornecidos não foram encontrados.");
            }

            for(NinjaModel ninja : equipe){
                missao.adicionarNinja(ninja);
            }
        }

        return missaoMapper.map(missaoRepository.save(missao));
    }

    //GET -- Manda uma requisição para listar todos as missões
    public List<MissaoResponseDTO> listarMissoes(){
        List<MissaoModel> missaoModel = missaoRepository.findAll();

        return missaoMapper.map(missaoModel);
    }

    //GET -- Manda uma requisição para listar missao por ID
    public MissaoResponseDTO listarMissaoId(Long id){
        MissaoModel missao = buscaPorId(id);

        return missaoMapper.map(missao);
    }

    //PATCH -- Manda uma requisição para Alterar uma Missao
    public MissaoResponseDTO alterarMissao(Long id, MissaoUpdateRequestDTO missaoUpdateRequestDTO){
        MissaoModel missao = buscaPorId(id);

        if(missaoUpdateRequestDTO.getNome() != null){
            missao.setNome(missaoUpdateRequestDTO.getNome());
        }

        if(missaoUpdateRequestDTO.getDificuldade() != null){
            missao.setDificuldade(missaoUpdateRequestDTO.getDificuldade());
        }

        return missaoMapper.map(missaoRepository.save(missao));
    }

    //PUT -- Manda uma requisição para Alterar a equipe de Ninjas na Missão
    public MissaoResponseDTO definirEquipe(Long id, List<Long> novosNinjaIds){
        MissaoModel missao = buscaPorId(id);

        List<NinjaModel> novaEquipe = ninjaRepository.findAllById(novosNinjaIds);

        if(novaEquipe.size() != novosNinjaIds.size()){
            throw new IllegalArgumentException("Um ou mais ninjas não foram encontrados.");
        }

        List<NinjaModel> equipeAntiga = new ArrayList<>(missao.getNinjas());

        for(NinjaModel ninja : equipeAntiga){
            missao.removerNinja(ninja);
        }

        for(NinjaModel ninja : novaEquipe){
            missao.adicionarNinja(ninja);
        }

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
