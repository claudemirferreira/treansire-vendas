package br.com.transire.util;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	public JasperPrint gerarPdf(String relatorio) throws JRException, SQLException {

		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager
					.compileReport(this.getClass().getResourceAsStream("\\jasper\\" + relatorio));
		} catch (Exception e) {
			jasperReport = JasperCompileManager
					.compileReport(this.getClass().getResourceAsStream("/jasper/" + relatorio));
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());
		return jasperPrint;
	}

}