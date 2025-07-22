package com.kava.s.CadastroNinja.ninja.repository;

import com.kava.s.CadastroNinja.ninja.models.NinjaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
}
