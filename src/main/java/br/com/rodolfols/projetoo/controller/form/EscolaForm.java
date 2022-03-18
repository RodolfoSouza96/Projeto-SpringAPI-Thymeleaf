package br.com.rodolfols.projetoo.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.rodolfols.projetoo.modelo.Endereco;
import br.com.rodolfols.projetoo.modelo.Escola;
import br.com.rodolfols.projetoo.repository.EnderecoRepository;

public class EscolaForm {

	@NotBlank(message = "O Campo deve ser preenchido")
	@Length(min = 4, message = "O Campo deve ser preenchido no minimo com 4 letras")
	private String nome;
	@NotBlank(message = "O Campo deve ser preenchido")
	@Length(min = 3, message = "O Campo deve ser preenchido no minimo com 4 letras")
	private String idEndereco;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(String idEndereco) {
		this.idEndereco = idEndereco;
	}

	public Escola converter(EscolaForm escolaform, EnderecoRepository enderecoRepository) {
		Escola escola = new Escola();
		
		Endereco endereco = enderecoRepository.getById(idEndereco);
		escola.setEndereco(endereco);
		
		return new Escola(nome, endereco);
	}
	
}
