package br.com.rodolfols.projetoo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Turma {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer capacidade;
	
	public Turma(){
	}
	
	public Turma(String nome, Integer capacidade) {
		this.nome = nome;
		this.capacidade = capacidade;
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
	
	public Integer getCapacidade() {
		return capacidade;
	}
	
	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
	
	
	
}
