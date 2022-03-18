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

import br.com.rodolfols.projetoo.controller.dto.TurmaDto;
import br.com.rodolfols.projetoo.controller.form.AtualizacaoTurmaForm;
import br.com.rodolfols.projetoo.controller.form.TurmaForm;
import br.com.rodolfols.projetoo.modelo.Turma;
import br.com.rodolfols.projetoo.repository.TurmaRepository;

@RestController
@RequestMapping("/turma")
public class TurmaController {
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@GetMapping
	public List<TurmaDto> lista(){
		List<Turma> turmas = turmaRepository.findAll();
		return TurmaDto.converter(turmas);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TurmaDto> cadastrar(@RequestBody @Valid TurmaForm form, UriComponentsBuilder uriBuilder) {
		Turma turma = form.converter();
		turmaRepository.save(turma);
		
		URI uri = uriBuilder.path("/turma/{id}").buildAndExpand(turma.getId()).toUri();
		return ResponseEntity.created(uri).body(new TurmaDto(turma));
	}
	
	@GetMapping("/{id}")
	public TurmaDto detalhar(@PathVariable Integer id) {
		Turma turma = turmaRepository.getById(id);
		return new TurmaDto(turma);
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<TurmaDto> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizacaoTurmaForm form){
		Turma turma = form.atualizar(id, turmaRepository);
		
		return ResponseEntity.ok(new TurmaDto(turma));
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<String> remover(@PathVariable Integer id) {
		turmaRepository.deleteById(id);
		
		return ResponseEntity.ok().body("Turma foi removida com sucesso");
	}
	

}
