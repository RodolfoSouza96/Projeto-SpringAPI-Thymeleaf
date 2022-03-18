package br.com.rodolfols.projetoo.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.rodolfols.projetoo.modelo.Turma;
import br.com.rodolfols.projetoo.repository.TurmaRepository;

public class AtualizacaoTurmaForm {

	@NotBlank(message = "O Campo deve ser preenchido")
	@Length(min = 4, message = "O Campo deve ser preenchido no minimo com 4 letras")
	private String nome;
	private Integer capacidade;

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

	
	public Turma atualizar(Integer id, TurmaRepository turmaRepository) {
		Turma turma = turmaRepository.getById(id);
		
		turma.setNome(nome);
		turma.setCapacidade(capacidade);;
		
		return turma;
	}

}
