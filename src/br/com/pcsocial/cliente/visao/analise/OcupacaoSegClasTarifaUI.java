package br.com.pcsocial.cliente.visao.analise;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import br.com.pcsocial.cliente.visao.base.AnaliseBaseUI;

public class OcupacaoSegClasTarifaUI extends AnaliseBaseUI {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	public PieDataset createPieDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("IE", 29);
		result.setValue("Chrome", 20);
		result.setValue("FireFox", 51);
		return result;

	}

	@Override
	public String getTitulo() {
		return "Análise de ocupação por seguimento e classes de tarifas";
	}
	
	@Override
	public String getTituloGrafico() {
		return "Seguimento / Classes de tarifas";
	}
	
	@Override
	public String nomeArquivoSalvo() {
		return "OcupSegClasTarifa"+String.valueOf(System.currentTimeMillis())+".jpeg";
	}
}
