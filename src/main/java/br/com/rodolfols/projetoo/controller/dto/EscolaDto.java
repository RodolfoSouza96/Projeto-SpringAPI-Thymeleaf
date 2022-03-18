package br.com.rodolfols.projetoo.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.rodolfols.projetoo.modelo.Escola;

public class EscolaDto {

	
	private Integer id;
	private String nome;
	private String enderecoId;
	
	
	public EscolaDto(Escola escola) {
		this.id = escola.getId();
		this.nome = escola.getNome();
		this.enderecoId = escola.getEndereco().getLogradouro();
		
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(String enderecoId) {
		this.enderecoId = enderecoId;
	}

	public static List<EscolaDto> converter(List<Escola> escolas) {
		return escolas.stream().map(EscolaDto::new).collect(Collectors.toList());
	}

}
