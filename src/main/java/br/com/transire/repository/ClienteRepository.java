package br.com.transire.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.transire.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("SELECT u FROM Cliente u WHERE nome like ?1 OR id like ?2 ")
	Page<Cliente> pesquisa(String nome, Integer id, Pageable pageable);

	@Query(value = "SELECT a.id_cliente, b.nome, sum(valor) valor FROM venda a, cliente b\n"
			+ "where a.id_cliente = b.id_cliente\n" + "group by id_cliente\n"
			+ "order by total desc;", nativeQuery = true)

	List<Cliente> listaClientes();

}