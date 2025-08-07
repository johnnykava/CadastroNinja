package com.kava.s.CadastroNinja.missao.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kava.s.CadastroNinja.ninja.models.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tb_missao")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MissaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String dificuldade;

    @OneToMany(mappedBy = "missoes")
    @JsonIgnore //Ignora a Serialização
    private List<NinjaModel> ninjas;
}
