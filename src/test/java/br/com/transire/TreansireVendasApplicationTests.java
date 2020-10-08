package br.com.transire;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.transire.model.Venda;
import br.com.transire.repository.ClienteRepository;
import br.com.transire.repository.ProdutoRepository;
import br.com.transire.repository.VendaProdutoRepository;

@SpringBootTest
class TreansireVendasApplicationTests {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	VendaProdutoRepository vendaProdutoRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Test
	void contextLoads() {
//		Cliente c = new Cliente(null, "João");
//		clienteRepository.save(c);
//		c = new Cliente(null, "Maria");
//		clienteRepository.save(c);
//
//		Produto p = new Produto(null, "Feijão", 22.2);
//		produtoRepository.save(p);
//		
//		p = new Produto(null, "Arroz", 24.2);
//		produtoRepository.save(p);
		
		Venda v = new Venda();
		v.setId(2);
		System.out.println(vendaProdutoRepository.findByVenda(v).size());
		
		
	}

}
