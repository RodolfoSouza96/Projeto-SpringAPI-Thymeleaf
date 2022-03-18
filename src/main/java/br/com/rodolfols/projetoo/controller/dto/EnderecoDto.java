package br.com.rodolfols.projetoo.controller.dto;

import org.springframework.data.domain.Page;

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
	
	public static Page<EnderecoDto> converter(Page<Endereco> enderecos) {
		return enderecos.map(EnderecoDto::new);
	}	
	
	
}
