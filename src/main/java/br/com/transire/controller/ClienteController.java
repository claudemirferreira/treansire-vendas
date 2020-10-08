package br.com.transire.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.transire.dto.ClienteDTO;
import br.com.transire.dto.FiltroPaginacaoDTO;
import br.com.transire.model.Cliente;
import br.com.transire.service.BaseService;
import br.com.transire.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/transire/cliente/")
@CrossOrigin(origins = "*")
@Api(value = "API rest produto")
public class ClienteController extends AbstractController<Cliente, Integer, ClienteDTO> {

	@Autowired
	private ClienteService service;

	@Override
	protected BaseService<Cliente, Integer> getService() {
		return service;
	}

	@Override
	protected Class<ClienteDTO> getDtoClass() {
		return ClienteDTO.class;
	}

	@Override
	protected Class<Cliente> getEntityClass() {
		return Cliente.class;
	}

	@RequestMapping(value = "pesquisar", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "retorna a lista de produtos")
	public Page<Cliente> pesquisar(HttpServletResponse resp, @RequestBody FiltroPaginacaoDTO dto) {
		try {
			return service.pesquisa(dto);
		} catch (Exception e) {
			System.out.println("ocorreu um erro ");
		}
		return null;
	}

}
