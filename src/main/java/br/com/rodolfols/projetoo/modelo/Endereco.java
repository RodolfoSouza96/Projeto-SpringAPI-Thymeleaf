package br.com.rodolfols.projetoo.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Endereco {
	
	@Id
	private	String logradouro;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	
	public Endereco() {
	}
	
	public Endereco(String logradouro, String complemento, String bairro, String cidade, String estado) {
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}



	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
