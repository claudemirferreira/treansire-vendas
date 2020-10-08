package br.com.transire.service;

import org.springframework.data.domain.Page;

import br.com.transire.dto.FiltroPaginacaoDTO;
import br.com.transire.model.Venda;

public interface VendaService extends BaseService<Venda, Integer> {

	public Page<Venda> pesquisa(FiltroPaginacaoDTO dto);
	
	public Venda getByIdPg(Integer id);

}
