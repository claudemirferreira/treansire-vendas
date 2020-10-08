package br.com.transire.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.transire.model.Cliente;
import br.com.transire.model.Produto;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("SELECT u FROM Cliente u WHERE nome like ?1 OR id like ?2 ")
	Page<Cliente> pesquisa(String nome, Integer id, Pageable pageable);

}