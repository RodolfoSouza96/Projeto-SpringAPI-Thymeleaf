package br.com.rodolfols.projetoo.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.rodolfols.projetoo.modelo.Turma;

public class TurmaDto {

	private Integer id;
	private String nome;
	private Integer capacidade;
	
	public TurmaDto(Turma turma) {
		this.id = turma.getId();
		this.nome = turma.getNome();
		this.capacidade = turma.getCapacidade();
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
	
	public static List<TurmaDto> converter(List<Turma> turmas) {
		return turmas.stream().map(TurmaDto::new).collect(Collectors.toList());
	}
	
	
	
}
