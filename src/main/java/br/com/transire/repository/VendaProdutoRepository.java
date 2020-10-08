package br.com.transire.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.transire.model.Venda;
import br.com.transire.model.VendaProduto;

public interface VendaProdutoRepository extends JpaRepository<VendaProduto, Integer> {

	@Query("SELECT u FROM VendaProduto u WHERE nome like ?1 OR id like ?2 ")
	Page<VendaProduto> pesquisa(String nome, Integer id, Pageable pageable);
	
	@Query("SELECT u FROM VendaProduto u WHERE u.venda = ?1 ")
	List<VendaProduto> findByVenda(Venda venda);

}