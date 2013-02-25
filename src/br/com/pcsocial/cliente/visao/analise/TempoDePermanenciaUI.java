package br.com.pcsocial.cliente.visao.analise;

import java.util.ArrayList;
import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import br.com.pcsocial.cliente.HospedagemCliente;
import br.com.pcsocial.cliente.visao.base.AnaliseBaseUI;
import br.com.pcsocial.servidor.modelo.Hospedagem;

public class TempoDePermanenciaUI extends AnaliseBaseUI {

	private static final long serialVersionUID = 1L;

	@Override
	public PieDataset createPieDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		int i = 0;
		int im = 0;
		int ia = 0;
		int in = 0;
		HospedagemCliente hc = new HospedagemCliente();
		List<Hospedagem> listHospedagem = new ArrayList<Hospedagem>();

		List<String> listMercado = new ArrayList<String>();
		List<Long> listQtde = new ArrayList<Long>();

		listHospedagem = hc.getListarHospedagems(super.getjDataInicial()
				.getDate(), super.getjDataFinal().getDate());
		while (i < listHospedagem.size()) {
			while (im <= i) {
				if (!(listMercado.contains(listHospedagem.get(i).getMercado()
						.getDescricao()))) {
					listMercado.add(listHospedagem.get(i).getMercado()
							.getDescricao());
					listQtde.add(listHospedagem.get(i).getDiasHospedado());
				} else {
					while (ia < listQtde.size()) {
						if (listMercado.get(ia).equals(
								listHospedagem.get(i).getMercado()
								.getDescricao())) {
							listQtde.set(ia, (listHospedagem.get(i)
									.getDiasHospedado() + listQtde.get(ia)));
						}
						ia++;
					}
				}
				ia = 0;
				im++;
			}
			i++;
		}
		while (in < listQtde.size()) {
			result.setValue(listMercado.get(in),listQtde.get(in));
			in ++;
		}
		return result;

	}

	@Override
	public XYSeriesCollection createXYDataset(String nomeSerie) {
		XYSeriesCollection result = new XYSeriesCollection();
		int i = 0;
		int im = 0;
		int ia = 0;
		int in = 0;
		HospedagemCliente hc = new HospedagemCliente();
		List<Hospedagem> listHospedagem = new ArrayList<Hospedagem>();

		List<String> listMercado = new ArrayList<String>();
		List<Long> listQtde = new ArrayList<Long>();

		listHospedagem = hc.getListarHospedagems(super.getjDataInicial()
				.getDate(), super.getjDataFinal().getDate());
		while (i < listHospedagem.size()) {
			while (im <= i) {
				if (!(listMercado.contains(listHospedagem.get(i).getMercado()
						.getDescricao()))) {
					listMercado.add(listHospedagem.get(i).getMercado()
							.getDescricao());
					listQtde.add(listHospedagem.get(i).getDiasHospedado());
				} else {
					while (ia < listQtde.size()) {
						if (listMercado.get(ia).equals(
								listHospedagem.get(i).getMercado()
								.getDescricao())) {
							listQtde.set(ia, (listHospedagem.get(i)
									.getDiasHospedado() + listQtde.get(ia)));
						}
						ia++;
					}
				}
				ia = 0;
				im++;
			}
			i++;
		}
		while (in < listQtde.size()) {
			XYSeries series = new XYSeries(listMercado.get(in));
			int id = 0;
			while (id < super.getjDataFinal()
					.getDate().getDate()){
				series.add((super.getjDataInicial()
						.getDate().getDate()+id),listQtde.get(in).doubleValue());
				id++;
			}
			result.addSeries(series);
			in ++;
		}
		/*series.add(1, 1);
		series.add(1, 2);
		series.add(2, 1);
		series.add(3, 9);
		series.add(4, 10);*/
		
		return result;
	}

	@Override
	public CategoryDataset createBarDataset(String nomeSerie) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int i = 0;
		int im = 0;
		int ia = 0;
		int in = 0;
		HospedagemCliente hc = new HospedagemCliente();
		List<Hospedagem> listHospedagem = new ArrayList<Hospedagem>();

		List<String> listMercado = new ArrayList<String>();
		List<Long> listQtde = new ArrayList<Long>();

		listHospedagem = hc.getListarHospedagems(super.getjDataInicial()
				.getDate(), super.getjDataFinal().getDate());
		while (i < listHospedagem.size()) {
			while (im <= i) {
				if (!(listMercado.contains(listHospedagem.get(i).getMercado()
						.getDescricao()))) {
					listMercado.add(listHospedagem.get(i).getMercado()
							.getDescricao());
					listQtde.add(listHospedagem.get(i).getDiasHospedado());
				} else {
					while (ia < listQtde.size()) {
						if (listMercado.get(ia).equals(
								listHospedagem.get(i).getMercado()
								.getDescricao())) {
							listQtde.set(ia, (listHospedagem.get(i)
									.getDiasHospedado() + listQtde.get(ia)));
						}
						ia++;
					}
				}
				ia = 0;
				im++;
			}
			i++;
		}
		while (in < listQtde.size()) {
			dataset.setValue(listQtde.get(in),listMercado.get(in),String.valueOf("de: "+super.getjDataInicial()
				.getDate() +" atŽ "+ super.getjDataFinal().getDate()));
			in ++;
		}
		
		return dataset;
	}

	@Override
	public String getTitulo() {
		return "An‡lise de tempo de permanncia";
	}

	@Override
	public String getTituloGrafico() {
		return "Tempo mŽdio de permanncia";
	}

	@Override
	public String nomeArquivoSalvo() {
		return "TempoDePerm" + String.valueOf(System.currentTimeMillis())
				+ ".jpeg";
	}

	@Override
	public String getLabelX() {
		return "Dias";
	}

	@Override
	public String getLabelY() {
		return "Categorias";
	}

	@Override
	public String getNomeSerie() {
		return "Tempo de permanncia";
	}

}
