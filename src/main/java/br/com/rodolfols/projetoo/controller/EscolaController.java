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

import br.com.rodolfols.projetoo.controller.dto.EscolaDto;
import br.com.rodolfols.projetoo.controller.form.AtualizacaoEscolaForm;
import br.com.rodolfols.projetoo.controller.form.EscolaForm;
import br.com.rodolfols.projetoo.modelo.Escola;
import br.com.rodolfols.projetoo.repository.EnderecoRepository;
import br.com.rodolfols.projetoo.repository.EscolaRepository;

@RestController
@RequestMapping("/escola")
public class EscolaController {
	
	@Autowired
	private EscolaRepository escolaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping
	public List<EscolaDto> lista(){
		List<Escola> escola = escolaRepository.findAll();
		return EscolaDto.converter(escola);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EscolaDto> cadastrar(@RequestBody @Valid EscolaForm form, UriComponentsBuilder uriBuilder) {
		Escola escola = form.converter(form, enderecoRepository);
		escolaRepository.save(escola);
		
		URI uri = uriBuilder.path("/escola/{id}").buildAndExpand(escola.getId()).toUri();
		return ResponseEntity.created(uri).body(new EscolaDto(escola));
	}
	
	@GetMapping("/{id}")
	public EscolaDto detalhar(@PathVariable Integer id) {
		Escola escola = escolaRepository.getById(id);
		return new EscolaDto(escola);
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<EscolaDto> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizacaoEscolaForm form){
		Escola escola = form.atualizar(id, escolaRepository, enderecoRepository);
		
		return ResponseEntity.ok(new EscolaDto(escola));
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<String> remover(@PathVariable Integer id) {
		escolaRepository.deleteById(id);
		
		return ResponseEntity.ok().body("Escola foi removido com sucesso");
	}
	

}
