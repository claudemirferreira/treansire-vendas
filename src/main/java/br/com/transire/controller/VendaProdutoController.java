package br.com.transire.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.transire.dto.FiltroPaginacaoDTO;
import br.com.transire.dto.VendaProdutoDTO;
import br.com.transire.model.Venda;
import br.com.transire.model.VendaProduto;
import br.com.transire.response.Response;
import br.com.transire.service.BaseService;
import br.com.transire.service.VendaProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/transire/venda-produto/")
@CrossOrigin(origins = "*")
@Api(value = "API rest produto")
public class VendaProdutoController extends AbstractController<VendaProduto, Integer, VendaProdutoDTO> {

	@Autowired
	private VendaProdutoService service;

	@Override
	protected BaseService<VendaProduto, Integer> getService() {
		return service;
	}

	@Override
	protected Class<VendaProdutoDTO> getDtoClass() {
		return VendaProdutoDTO.class;
	}

	@Override
	protected Class<VendaProduto> getEntityClass() {
		return VendaProduto.class;
	}

	@RequestMapping(value = "pesquisar", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "retorna a lista de produtos")
	public Page<VendaProduto> pesquisar(HttpServletResponse resp, @RequestBody FiltroPaginacaoDTO dto) {
		try {
			return null;// service.pesquisa(dto);
		} catch (Exception e) {
			System.out.println("ocorreu um erro ");
		}
		return null;
	}
	
	@RequestMapping(value = "incluir", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<List<VendaProdutoDTO>>> saveItem(@RequestBody VendaProdutoDTO obj) {
		Response<List<VendaProdutoDTO>> response = new Response<List<VendaProdutoDTO>>();
		try {
			VendaProduto vendaProduto = getService().save(toEntity(obj));
			List<VendaProduto> lista = service.findByVenda(vendaProduto.getVenda());
			response.setContent(toDto(lista));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@RequestMapping(value = "venda/{id}", method = RequestMethod.DELETE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<List<VendaProdutoDTO>>> saveItem(@PathVariable("id") Integer id) {
		Response<List<VendaProdutoDTO>> response = new Response<List<VendaProdutoDTO>>();
		try {
			Venda v = new Venda();
			v.setId(id);
			List<VendaProduto> lista = service.findByVenda(v);
			response.setContent(toDto(lista));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(response);
		}
	}

}
