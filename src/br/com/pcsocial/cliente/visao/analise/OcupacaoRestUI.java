package br.com.pcsocial.cliente.visao.analise;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYSeriesCollection;

import br.com.pcsocial.cliente.HospedagemCliente;
import br.com.pcsocial.cliente.visao.base.AnaliseBaseUI;
import br.com.pcsocial.servidor.modelo.Hospedagem;
import br.com.pcsocial.servidor.modelo.TempoDePermanencia;

public class OcupacaoRestUI extends AnaliseBaseUI {

	private static final long serialVersionUID = 1L;
	private int in = 0;
	private int i = 0;
	private boolean up = false;
	private HospedagemCliente hc = new HospedagemCliente();
	private List<Hospedagem> listHospedagem = new ArrayList<Hospedagem>();
	private TempoDePermanencia tdp;
	private List<TempoDePermanencia> listTdp = new ArrayList<TempoDePermanencia>();

	@Override
	public PieDataset createPieDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		if (listTdp.isEmpty()) {
			this.montaList();
		} else
			in = 0;

		while (in < listTdp.size()) {
			result.setValue(listTdp.get(in).getDescMercado(), listTdp.get(in)
					.getDiasHospedados());
			in++;
		}
		return result;

	}

	@Override
	public XYSeriesCollection createXYDataset(String nomeSerie) {
		/*
		 * XYSeriesCollection result = new XYSeriesCollection();
		 * 
		 * if (listTdp.isEmpty()) { this.montaList(); } else in = 0;
		 * 
		 * while (in < listTdp.size()) { XYSeries series = new
		 * XYSeries(listTdp.get(in).getDescMercado()); int id = 0; if
		 * (super.getjDataInicial().getDate().getMonth() < super
		 * .getjDataFinal().getDate().getMonth()) { id =
		 * super.getjDataInicial().getDate().getMonth(); while
		 * (super.getjDataInicial().getDate().getMonth() >=
		 * super.getjDataFinal().getDate().getMonth()) { series.add( (id),
		 * listTdp.get(in).getDiasHospedados()); id++; }
		 * result.addSeries(series); in++; } else {
		 * 
		 * } while (id < super.getjDataFinal().getDate().getDate()) {
		 * series.add((super.getjDataInicial().getDate().getDate() + id),
		 * listTdp.get(in).getDiasHospedados()); id++; }
		 * result.addSeries(series); in++; }
		 */
		return null;
	}
	
	public void gerarGraficoXY()  {
		javax.swing.JOptionPane.showMessageDialog(
				null,
				"Gráfico linear não está disponível para este tipo de análise",
				"Informação",
				0,
				new ImageIcon(getClass().getResource(
						"/gui/icones/acoes/informacao.png")));
	}

	@Override
	public CategoryDataset createBarDataset(String nomeSerie) {
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		if (listTdp.isEmpty()) {
			this.montaList();
		} else
			in = 0;
		while (in < listTdp.size()) {
			result.setValue(listTdp.get(in).getDiasHospedados(), listTdp
					.get(in).getDescMercado(), String.valueOf("de: "
					+ super.getjDataInicial().getDate() + " até "
					+ super.getjDataFinal().getDate()));
			in++;
		}
		return result;
	}

	@Override
	public String getTitulo() {
		return "Análise de ocupação por restrições de tarifas";
	}

	@Override
	public String getTituloGrafico() {
		return "Ocupação por restrições";
	}

	@Override
	public String nomeArquivoSalvo() {
		return "Ocupação" + String.valueOf(System.currentTimeMillis())
				+ ".jpeg";
	}

	@Override
	public String getLabelX() {
		return "Hospedagens";
	}

	@Override
	public String getLabelY() {
		return "Restrições";
	}

	@Override
	public String getNomeSerie() {
		return "Hospedagens";
	}

	public void montaList() {
		in = 0;
		i = 0;
		up = false;
		listHospedagem = hc.getListarHospedagem(super.getjDataInicial()
				.getDate(), super.getjDataFinal().getDate());
		while (in < listHospedagem.size()) {
			if (listTdp.isEmpty()) {
				tdp = new TempoDePermanencia();
				tdp.setDescMercado(listHospedagem.get(in).getMercado()
						.getDescricao());
				tdp.setDiasHospedados(1);
				listTdp.add(tdp);
				tdp = null;
			} else {
				while (i < listTdp.size()) {
					if (listHospedagem.get(in).getMercado().getDescricao()
							.equals(listTdp.get(i).getDescMercado())) {
						tdp = listTdp.get(i);
						tdp.setDiasHospedados(tdp.getDiasHospedados()
								+ 1);
						listTdp.set(i, tdp);
						up = true;
						i++;
					}
					i++;
				}
				if (up == false) {
					tdp = new TempoDePermanencia();
					tdp.setDescMercado(listHospedagem.get(in).getMercado()
							.getDescricao());
					tdp.setDiasHospedados(1);
					listTdp.add(tdp);
					tdp = null;
				}

			}
			i = 0;
			in++;
		}
		in = 0;
	}

}