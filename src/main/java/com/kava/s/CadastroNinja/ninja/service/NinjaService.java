package com.kava.s.CadastroNinja.ninja.service;

import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import com.kava.s.CadastroNinja.missao.repository.MissaoRepository;
import com.kava.s.CadastroNinja.ninja.dto.NinjaCreateDTO;
import com.kava.s.CadastroNinja.ninja.dto.NinjaResponseDTO;
import com.kava.s.CadastroNinja.ninja.dto.NinjaUpdateDTO;
import com.kava.s.CadastroNinja.ninja.mapper.NinjaMapper;
import com.kava.s.CadastroNinja.ninja.models.NinjaModel;
import com.kava.s.CadastroNinja.ninja.repository.NinjaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final MissaoRepository missaoRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, MissaoRepository missaoRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.missaoRepository = missaoRepository;
        this.ninjaMapper = ninjaMapper;
    }

    //POST -- Manda uma requisição para criar ninja
    public NinjaResponseDTO criarNinja(NinjaCreateDTO ninjaCreateDTO){
        NinjaModel ninja = ninjaMapper.map(ninjaCreateDTO);

        List<NinjaModel> ninjasEmail = ninjaRepository.findAll();

        Optional<NinjaModel> resultado = ninjasEmail.stream()
                .filter(n -> n.getEmail().equals(ninja.getEmail()))
                .findFirst();

        if(resultado.isPresent()){
            throw new IllegalArgumentException("Não foi possivel realizar o cadastro. Verifique os dados e tente novamente.");
        }

        if(ninjaCreateDTO.getMissaoId() != null){
            Optional<MissaoModel> caixaMissao = missaoRepository.findById(ninjaCreateDTO.getMissaoId());

            if(caixaMissao.isPresent()){
                MissaoModel missao = caixaMissao.get();

                missao.adicionarNinja(ninja);
            }
            else{
                throw new IllegalArgumentException("Missão não encontrada.");
            }
        }

        return ninjaMapper.map(ninjaRepository.save(ninja));
    }

    //GET -- Manda uma requisição para listar todos os ninjas
    public List<NinjaResponseDTO> listarNinjas(){
        List<NinjaModel> ninjaModel = ninjaRepository.findAll();

        return ninjaMapper.map(ninjaModel);
    }

    //GET -- Manda uma requisição para listar ninja por ID
    public NinjaResponseDTO listarNinjaId(Long id){
        NinjaModel ninja = buscaPorId(id);

        return ninjaMapper.map(ninja);
    }

    //PATCH -- Manda uma requisição para alterar um ninja
    public NinjaResponseDTO alterarNinja(Long id, NinjaUpdateDTO ninjaUpdateDTO){
        NinjaModel ninja = buscaPorId(id);

        if(ninjaUpdateDTO.getNome() != null){
            ninja.setNome(ninjaUpdateDTO.getNome());
        }

        if(ninjaUpdateDTO.getEmail() != null){
            ninja.setEmail(ninjaUpdateDTO.getEmail());
        }

        if(ninjaUpdateDTO.getIdade() != null){
            ninja.setIdade(ninjaUpdateDTO.getIdade());
        }

        if(ninjaUpdateDTO.getRank() != null){
            ninja.setRank(ninjaUpdateDTO.getRank());
        }

        if(ninjaUpdateDTO.getImagemUrl() != null){
            ninja.setImagemUrl(ninjaUpdateDTO.getImagemUrl());
        }

        return ninjaMapper.map(ninjaRepository.save(ninja));
    }

    //PUT -- Manda uma requisição para altarerar uma missao do ninja
    public NinjaResponseDTO atribuirMissao(Long ninjaId, Long missaoId){
        NinjaModel ninja = buscaPorId(ninjaId);
        Optional<MissaoModel> caixaMissao = missaoRepository.findById(missaoId);

        if(caixaMissao.isPresent()){
            MissaoModel missao = caixaMissao.get();

            missao.adicionarNinja(ninja);
        }
        else{
            throw new IllegalArgumentException("Missão não encontrada.");
        }

        ninjaRepository.save(ninja);

        return ninjaMapper.map(ninja);
    }

    //DELETE -- Manda uma requisição para deletar um ninja
    public void deletarNinja(Long id){
        NinjaModel ninja = buscaPorId(id);

        ninjaRepository.deleteById(ninja.getId());
    }

    //DELETE -- Manda uma requisição para deletar o ninja de missão
    public void removerMissao(Long ninjaId){
        NinjaModel ninja = buscaPorId(ninjaId);

        if(ninja.getMissoes() != null){
            ninja.getMissoes().removerNinja(ninja);
        }

        ninjaRepository.save(ninja);
    }

    private NinjaModel buscaPorId(Long id){
        Optional<NinjaModel> caixaNinja = ninjaRepository.findById(id);

        if(caixaNinja.isPresent()){
            return caixaNinja.get();
        }

        throw new IllegalArgumentException("Recurso não encontrado.");
    }
}
