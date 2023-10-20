package br.com.priscyladepaula.desafioonline.alunos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping("/alunos")
@OpenAPIDefinition(info = @Info(title = "Alunos API",
                                description = "API para cadastrar alunos. Linguagem usada: Java Spring Boot. Banco de dados: Postegresql. Desafio online do Brasil Generation.",
                                version = "1.0"
                                ))
public class AlunoController {

    @Autowired
    private IAlunoRepository alunoRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody AlunoModel alunoModel){

        var task = this.alunoRepository.save(alunoModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
    
    @GetMapping("/")
    public ResponseEntity<?> list() {

        var alunos = this.alunoRepository.findAll();

        if(alunos.isEmpty()){
            return new ResponseEntity<String>("Nenhum aluno cadastrado!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<AlunoModel>>(alunos, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable Integer id) {

        var aluno = this.alunoRepository.findById(id).orElse(null);

        if(aluno == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }

        return ResponseEntity.ok().body(aluno);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody AlunoModel alunoModel, @PathVariable Integer id) {

        var aluno = this.alunoRepository.findById(id).orElse(null);

        if(aluno == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }

        aluno.setIdadeAluno(alunoModel.getIdadeAluno());
        aluno.setNomeAluno(alunoModel.getNomeAluno());
        aluno.setNomeProfessor(alunoModel.getNomeProfessor());
        aluno.setNota1semestre(alunoModel.getNota1semestre());
        aluno.setNota2semestre(alunoModel.getNota2semestre());
        aluno.setNumeroSala(alunoModel.getNumeroSala());
        AlunoModel alunoUpdated = this.alunoRepository.save(aluno);

        return ResponseEntity.ok().body(alunoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){

        var aluno = this.alunoRepository.findById(id).orElse(null);

        if(aluno == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }
        
        this.alunoRepository.deleteById(id);

        return ResponseEntity.ok().body("Cadastro deletado com sucesso!");

    }
}
