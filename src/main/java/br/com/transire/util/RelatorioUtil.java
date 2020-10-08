package br.com.transire.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.transire.model.Produto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Component
public class RelatorioUtil {

	@Autowired
	private DataSource dataSource;

	public RelatorioUtil() {
	}

	public JasperPrint gerarPdf(Produto dto, String relatorio) throws JRException, SQLException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idPedido", dto.getId());
		parametros.put("dto", dto);

		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager
					.compileReport(this.getClass().getResourceAsStream("\\jasper\\" + relatorio));
		} catch (Exception e) {
			jasperReport = JasperCompileManager
					.compileReport(this.getClass().getResourceAsStream("/jasper/" + relatorio));
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());
		return jasperPrint;
	}

}