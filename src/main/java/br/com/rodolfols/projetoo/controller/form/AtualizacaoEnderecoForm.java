package br.com.rodolfols.projetoo.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.rodolfols.projetoo.modelo.Endereco;
import br.com.rodolfols.projetoo.repository.EnderecoRepository;

public class AtualizacaoEnderecoForm {

	@NotBlank(message = "O Campo deve ser preenchido")
	@Length(min = 3, message = "O Campo deve ser preenchido no minimo com 4 letras")
	private String logradouro;
	@NotBlank(message = "O Campo deve ser preenchido")
	@Length(min = 4, message = "O Campo deve ser preenchido no minimo com 4 letras")
	private String complemento;
	@NotBlank(message = "O Campo deve ser preenchido")
	@Length(min = 4, message = "O Campo deve ser preenchido no minimo com 4 letras")
	private String bairro;
	@NotBlank(message = "O Campo deve ser preenchido")
	@Length(min = 4, message = "O Campo deve ser preenchido no minimo com 4 letras")
	private String cidade;
	@NotBlank(message = "O Campo deve ser preenchido")
	@Length(min = 2, message = "O Campo deve ser preenchido no minimo com 2 letras")
	private String estado;
			
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

	public Endereco atualizar(String logradouro, EnderecoRepository enderecoRepository) {
		Endereco endereco = enderecoRepository.getById(logradouro);
		
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setComplemento(complemento);
		endereco.setEstado(estado);
		return endereco;
		
	}

}
