package br.com.rodolfols.projetoo.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.rodolfols.projetoo.modelo.Endereco;

public class EnderecoDto {

	private String logradouro;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	
	public EnderecoDto(Endereco endereco) {
		this.logradouro = endereco.getLogradouro();
		this.complemento = endereco.getComplemento();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public static List<EnderecoDto> converter(List<Endereco> enderecos) {
		return enderecos.stream().map(EnderecoDto::new).collect(Collectors.toList());
	}	
	
	
}
