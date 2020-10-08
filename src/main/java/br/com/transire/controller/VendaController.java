package br.com.transire.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletResponse;

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
import br.com.transire.dto.VendaDTO;
import br.com.transire.model.Venda;
import br.com.transire.response.Response;
import br.com.transire.service.BaseService;
import br.com.transire.service.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/transire/venda/")
@CrossOrigin(origins = "*")
@Api(value = "API rest venda")
public class VendaController extends AbstractController<Venda, Integer, VendaDTO> {

	@Autowired
	private VendaService service;

	@Override
	protected BaseService<Venda, Integer> getService() {
		return service;
	}

	@Override
	protected Class<VendaDTO> getDtoClass() {
		return VendaDTO.class;
	}

	@Override
	protected Class<Venda> getEntityClass() {
		return Venda.class;
	}

	@RequestMapping(value = "pesquisar", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "retorna a lista de produtos")
	public Page<Venda> pesquisar(HttpServletResponse resp, @RequestBody FiltroPaginacaoDTO dto) {
		try {
			return service.pesquisa(dto);
		} catch (Exception e) {
			System.out.println("ocorreu um erro ");
		}
		return null;
	}
	
	@RequestMapping(value = "/{id}/fechar", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<VendaDTO>> findPedidoTotal(@PathVariable("id") Integer id) {
		Response<VendaDTO> response = new Response<VendaDTO>();
		try {
			VendaDTO dto = toDto(service.getByIdPg(id));
			response.setContent(dto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
	}

}
