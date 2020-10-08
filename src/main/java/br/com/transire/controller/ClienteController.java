package br.com.transire.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.transire.dto.ClienteDTO;
import br.com.transire.dto.FiltroPaginacaoDTO;
import br.com.transire.model.Cliente;
import br.com.transire.service.BaseService;
import br.com.transire.service.ClienteService;
import br.com.transire.util.RelatorioUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/transire/cliente/")
@CrossOrigin(origins = "*")
@Api(value = "API rest produto")
public class ClienteController extends AbstractController<Cliente, Integer, ClienteDTO> {

	@Autowired
	private RelatorioUtil relatorioUtil;

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

	@RequestMapping(value = "melhores", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "retorna a lista de produtos")
	public List<Cliente> listaClientes(HttpServletResponse resp) {
		try {
			return service.listaClientes();
		} catch (Exception e) {
			System.out.println("ocorreu um erro ");
		}
		return null;
	}

	@RequestMapping(value = "pdf/", method = RequestMethod.GET)
	public ResponseEntity<byte[]> pedidoPdf(HttpServletResponse response)
			throws JRException, SQLException, IOException {
		System.out.println("########## getPDF");
		try {

			JasperPrint jasperPrint = relatorioUtil.gerarPdf("cliente.jrxml");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=Relatorio.pdf");
			// Faz a exportação do relatório para o HttpServletResponse
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] output = JasperExportManager.exportReportToPdf(jasperPrint);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			String filename = "pedido.pdf";
			headers.setContentDispositionFormData(filename, filename);
			ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(output, headers, HttpStatus.OK);

			return responseEntity;
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
		return null;
	}

}
