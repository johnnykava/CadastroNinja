package com.kava.s.CadastroNinja.ninja.models;

import com.kava.s.CadastroNinja.missao.models.MissaoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_ninja")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private int idade;

    private String rank;

    @ManyToOne
    @JoinColumn(name = "missoes_id")
    private MissaoModel missoes;
}
