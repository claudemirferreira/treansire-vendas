package br.com.transire.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.transire.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

	@Query("SELECT u FROM Venda u WHERE u.cliente.id = ?1 ")
	Page<Venda> pesquisa(Integer idCliente, Pageable pageable);

}