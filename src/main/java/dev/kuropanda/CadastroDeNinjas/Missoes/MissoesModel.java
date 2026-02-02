package dev.kuropanda.CadastroDeNinjas.Missoes;

import dev.kuropanda.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    private String nome;
    private String dificuldade;

    // @OneToMany - Uma missão pode ser atribuida para vários ninjas.
    @OneToMany(mappedBy = "missoes")
    private List<NinjaModel> ninjas;




}
