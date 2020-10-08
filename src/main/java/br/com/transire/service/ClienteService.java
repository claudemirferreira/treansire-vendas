package br.com.transire.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.transire.dto.FiltroPaginacaoDTO;
import br.com.transire.model.Cliente;

public interface ClienteService extends BaseService<Cliente, Integer> {

	public Page<Cliente> pesquisa(FiltroPaginacaoDTO dto);
	
	List<Cliente> listaClientes();

}
