package br.com.transire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.transire.dto.FiltroPaginacaoDTO;
import br.com.transire.model.Produto;
import br.com.transire.repository.ProdutoRepository;
import br.com.transire.service.ProdutoService;

@Service
public class ProdutoServiceImpl extends AbstractService<Produto, Integer> implements ProdutoService {

	private ProdutoRepository repository;

	@Autowired
	public ProdutoServiceImpl(ProdutoRepository repository) {
		this.repository = repository;
	}

	@Override
	protected CrudRepository<Produto, Integer> getRepository() {
		return repository;
	}

	@Override
	public Page<Produto> pesquisa(FiltroPaginacaoDTO dto) {
		System.out.println(dto.toString());
		Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), Sort.by("nome"));
		return repository.pesquisa(dto.getNome() + "%", dto.getId(), pageable);
	}

}