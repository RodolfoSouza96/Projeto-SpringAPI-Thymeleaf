package br.com.rodolfols.projetoo.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.rodolfols.projetoo.modelo.Aluno;
import br.com.rodolfols.projetoo.repository.AlunoRepository;

public class AtualizacaoAlunoFormThymeleaf {

	@NotNull
	private Integer id;
	@NotBlank(message = "O Campo deve ser preenchido")
	@Length(min = 4, message = "O Campo deve ser preenchido no minimo com 4 letras")
	private String nome;
	@Past
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull
	private LocalDate dataDeNascimento;
	
	
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
	
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	
	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public Aluno atualizar(Integer id, AlunoRepository alunoRepository) {
	    Aluno aluno = alunoRepository.getById(id);
	    
	    aluno.setNome(nome);
		aluno.setDataDeNascimento(dataDeNascimento);
	    
	    return aluno;
	}
	
	
	
	
}
