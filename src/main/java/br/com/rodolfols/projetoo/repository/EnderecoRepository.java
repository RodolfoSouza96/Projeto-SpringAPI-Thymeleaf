package br.com.rodolfols.projetoo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodolfols.projetoo.modelo.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String>{

}
