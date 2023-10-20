package br.com.priscyladepaula.desafioonline.alunos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_alunos")
public class AlunoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer idAluno;

    private String nomeAluno;
    private Integer idadeAluno;
    private String nomeProfessor;
    private Integer numeroSala;
    private double nota1semestre;
    private double nota2semestre;
}
