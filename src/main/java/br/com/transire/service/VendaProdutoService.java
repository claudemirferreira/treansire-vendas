package br.com.transire.service;

import java.util.List;

import br.com.transire.model.Venda;
import br.com.transire.model.VendaProduto;

public interface VendaProdutoService extends BaseService<VendaProduto, Integer> {
	
	List<VendaProduto> findByVenda(Venda venda);

}
