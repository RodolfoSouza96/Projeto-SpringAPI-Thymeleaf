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

import br.com.rodolfols.projetoo.controller.dto.EnderecoDto;
import br.com.rodolfols.projetoo.controller.form.AtualizacaoEnderecoForm;
import br.com.rodolfols.projetoo.controller.form.EnderecoForm;
import br.com.rodolfols.projetoo.modelo.Endereco;
import br.com.rodolfols.projetoo.repository.EnderecoRepository;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping
	public List<EnderecoDto> lista(){
		List<Endereco> endereco = enderecoRepository.findAll();
		return EnderecoDto.converter(endereco);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EnderecoDto> cadastrar(@RequestBody @Valid EnderecoForm form, UriComponentsBuilder uriBuilder) {
		Endereco endereco = form.converter();
		enderecoRepository.save(endereco);
		
		URI uri = uriBuilder.path("/endereco/{logradouro}").buildAndExpand(endereco.getLogradouro()).toUri();
		return ResponseEntity.created(uri).body(new EnderecoDto(endereco));
	}
	
	@GetMapping("/{logradouro}")
	public EnderecoDto detalhar(@PathVariable String logradouro) {
		Endereco endereco = enderecoRepository.getById(logradouro);
		return new EnderecoDto(endereco);
	}
	
	@PutMapping("{logradouro}")
	@Transactional
	public ResponseEntity<EnderecoDto> atualizar(@PathVariable String logradouro, @RequestBody @Valid AtualizacaoEnderecoForm form){
		Endereco endereco = form.atualizar(logradouro, enderecoRepository);
		
		return ResponseEntity.ok(new EnderecoDto(endereco));
	}
	
	@DeleteMapping("{logradouro}")
	@Transactional
	public ResponseEntity<String> remover(@PathVariable String logradouro) {
		enderecoRepository.deleteById(logradouro);
		
		return ResponseEntity.ok().body("Aluno foi removido com sucesso");
	}
	

}
