package br.com.rodolfols.projetoo.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.rodolfols.projetoo.controller.dto.AlunoDto;
import br.com.rodolfols.projetoo.controller.form.AlunoForm;
import br.com.rodolfols.projetoo.controller.form.AtualizacaoAlunoForm;
import br.com.rodolfols.projetoo.modelo.Aluno;
import br.com.rodolfols.projetoo.repository.AlunoRepository;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
	public List<AlunoDto> lista(){
		List<Aluno> alunos = alunoRepository.findAll();
		return AlunoDto.converter(alunos);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AlunoDto> cadastrar(@RequestBody @Valid AlunoForm form, UriComponentsBuilder uriBuilder) {
		Aluno aluno = form.converter();
		alunoRepository.save(aluno);
		
		URI uri = uriBuilder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(new AlunoDto(aluno));
	}
	
	@GetMapping("/{id}")
	public AlunoDto detalhar(@PathVariable Integer id) {
		Aluno aluno = alunoRepository.getById(id);
		return new AlunoDto(aluno);
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<AlunoDto> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizacaoAlunoForm form){
		Aluno aluno = form.atualizar(id, alunoRepository);
		
		return ResponseEntity.ok(new AlunoDto(aluno));
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<String> remover(@PathVariable Integer id) {
		alunoRepository.deleteById(id);
		
		return ResponseEntity.ok().body("Aluno foi removido com sucesso");
	}
	

}
