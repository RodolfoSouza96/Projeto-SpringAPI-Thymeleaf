package br.com.rodolfols.projetoo.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.rodolfols.projetoo.modelo.Aluno;

public class AlunoDto {

	private Integer id;
	private String nome;
	private LocalDate dataDeNascimento;
	
	public AlunoDto(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.dataDeNascimento = aluno.getDataDeNascimento();
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public static List<AlunoDto> converter(List<Aluno> alunos) {
		return alunos.stream().map(AlunoDto::new).collect(Collectors.toList());
	}	
}
