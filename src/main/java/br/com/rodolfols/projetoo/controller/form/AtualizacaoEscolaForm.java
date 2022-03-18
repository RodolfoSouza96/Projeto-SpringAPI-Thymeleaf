package br.com.rodolfols.projetoo.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.rodolfols.projetoo.modelo.Endereco;
import br.com.rodolfols.projetoo.modelo.Escola;
import br.com.rodolfols.projetoo.repository.EnderecoRepository;
import br.com.rodolfols.projetoo.repository.EscolaRepository;

public class AtualizacaoEscolaForm {

	@NotBlank(message = "O Campo deve ser preenchido")
	@Length(max = 4, message = "O Campo deve ser preenchido no maximo com 4 letras = Exemplos: quintal(quin), avenida(avi)")
	private String nome;
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

	public Escola atualizar(Integer id, EscolaRepository escolaRepository, EnderecoRepository enderecoRepository) {
		Escola escola = escolaRepository.getById(id);
		Endereco endereco = enderecoRepository.getById(idEndereco);
		escola.setNome(nome);
		endereco.setLogradouro(idEndereco);
		
		
		return escola;
		
	}

}
