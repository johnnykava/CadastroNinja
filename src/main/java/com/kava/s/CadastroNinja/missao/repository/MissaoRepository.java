package com.kava.s.CadastroNinja.missao.repository;

import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissaoRepository extends JpaRepository<MissaoModel, Long> {
}
