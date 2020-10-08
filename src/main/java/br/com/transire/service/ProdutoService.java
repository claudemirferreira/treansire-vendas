package br.com.transire.service;

import org.springframework.data.domain.Page;

import br.com.transire.dto.FiltroPaginacaoDTO;
import br.com.transire.model.Produto;

public interface ProdutoService extends BaseService<Produto, Integer> {

	public Page<Produto> pesquisa(FiltroPaginacaoDTO dto);

}
