package com.digitalSystems.extendsfood.infrastructure.service.report;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.filter.VendaFilter;
import com.digitalSystems.extendsfood.domain.service.VendaQueryService;
import com.digitalSystems.extendsfood.domain.service.VendaReportService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfVendaReportService implements VendaReportService {

	@Autowired
	private VendaQueryService vendaQueryService;

	@Override
	public byte[] emitirVendasDiarias(VendaFilter filtro) {
		try {

			// busca o relatório dentro do projeto
			var inputStream = this.getClass().getResourceAsStream("/relatorios/relatorio-vendas.jasper");
			
			var vendas = vendaQueryService.consultarVendas(filtro);
			
			BigDecimal totalFaturado = vendas.stream()
			        .map(venda -> venda.getTotalFaturado())
			        .reduce(BigDecimal.ZERO, BigDecimal::add);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			// Os parametros que foram usados para gerar esse relatório
			var parametros = new HashMap<String, Object>();
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
			parametros.put("dataInicio", simpleDateFormat.parseObject(filtro.getDataCriacaoInicio().toString()));
			parametros.put("dataFim", simpleDateFormat.parseObject(filtro.getDataCriacaoFim().toString()));
			parametros.put("quantidadeVendas", vendas.size());
			parametros.put("totalFaturado", totalFaturado);


			// Dados para preencher o relatório
			//Implementação DataSource do java bean 
			var dataSource = new JRBeanCollectionDataSource(vendas);

			// O jasperPrint representa o relatório/documento preenchido com os parametros e
			// a fonte de dados que pode ser exportado em diferentes formatos
			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

			//Exporta o jasperPrint no formato PDF
			return JasperExportManager.exportReportToPdf(jasperPrint);

		} catch (Exception e) {
			throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
		}
	}

}