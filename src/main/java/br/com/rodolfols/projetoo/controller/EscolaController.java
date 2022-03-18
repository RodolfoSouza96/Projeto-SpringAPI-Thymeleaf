package br.com.rodolfols.projetoo.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
	@Cacheable(value="listaDeEscolas")
	public Page<EscolaDto> lista(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10)Pageable paginacao){
		Page<Escola> escola = escolaRepository.findAll(paginacao);
		return EscolaDto.converter(escola);
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="listaDeEscolas", allEntries = true)
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
	@CacheEvict(value="listaDeEscolas", allEntries = true)
	public ResponseEntity<EscolaDto> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizacaoEscolaForm form){
		Escola escola = form.atualizar(id, escolaRepository, enderecoRepository);
		
		return ResponseEntity.ok(new EscolaDto(escola));
	}
	
	@DeleteMapping("{id}")
	@Transactional
	@CacheEvict(value="listaDeEscolas", allEntries = true)
	public ResponseEntity<String> remover(@PathVariable Integer id) {
		escolaRepository.deleteById(id);
		
		return ResponseEntity.ok().body("Escola foi removido com sucesso");
	}
	

}
