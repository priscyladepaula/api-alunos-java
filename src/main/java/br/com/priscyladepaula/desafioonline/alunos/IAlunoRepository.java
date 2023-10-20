package br.com.priscyladepaula.desafioonline.alunos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlunoRepository extends JpaRepository<AlunoModel, Integer> {
    
}
