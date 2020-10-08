package br.com.transire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.transire.dto.FiltroPaginacaoDTO;
import br.com.transire.model.Venda;
import br.com.transire.model.VendaProduto;
import br.com.transire.repository.VendaProdutoRepository;
import br.com.transire.repository.VendaRepository;
import br.com.transire.service.VendaService;

@Service
public class VendaServiceImpl extends AbstractService<Venda, Integer> implements VendaService {

	private VendaRepository repository;

	@Autowired
	private VendaProdutoRepository vendaProdutoRepository;

	@Autowired
	public VendaServiceImpl(VendaRepository repository) {
		this.repository = repository;
	}

	@Override
	protected CrudRepository<Venda, Integer> getRepository() {
		return repository;
	}

	@Override
	public Page<Venda> pesquisa(FiltroPaginacaoDTO dto) {
		System.out.println(dto.toString());
		Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), Sort.by("nome"));
		return repository.findAll(pageable);
	}

	@Override
	public Venda getByIdPg(Integer id) {
		Venda pedido = repository.findById(id).get();
		List<VendaProduto> lista = vendaProdutoRepository.findByVenda(new Venda(id));
		double total = lista.stream().mapToDouble(x -> x.getValor()).sum();

		pedido.setValor(total);

		return pedido;
	}

}