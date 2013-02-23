package br.com.pcsocial.cliente.visao.grid;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.TemporadaCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ManterBaseUI;
import br.com.pcsocial.cliente.visao.manter.AdicionarTemporadaUI;
import br.com.pcsocial.servidor.modelo.Temporada;

public class ManterTemporadaUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;
	
	private JTable gridTemporada;
	private TemporadaCliente tC;
	private Temporada temporada;
	
	@Override
	public void atualizarGrid() {
		tC = new TemporadaCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 366;
			
			gridTemporada = new JTable(tC.montarTabela(dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridTemporada.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridTemporada.getColumnModel().getColumn(0);
			TableColumn col2 = gridTemporada.getColumnModel().getColumn(1);
			TableColumn col3 = gridTemporada.getColumnModel().getColumn(2);
			TableColumn col4 = gridTemporada.getColumnModel().getColumn(3);
			TableColumn col5 = gridTemporada.getColumnModel().getColumn(4);
			
			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			col3.setPreferredWidth(tmColOu);
			col4.setPreferredWidth(tmColOu);
			col5.setPreferredWidth(tmCol1);
			
			gridTemporada.setFillsViewportHeight(true);
			gridTemporada.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridTemporada);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}

	public Temporada getTemporada() {
		return temporada;
	}
	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de temporadas";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarTemporadaUI am = new AdicionarTemporadaUI();
		am.adicionarTemporada();
	}

	@Override
	public void excluirCadastro() {
		AdicionarTemporadaUI am = new AdicionarTemporadaUI();
		Long retornoSel;
		retornoSel = (Long) gridTemporada.getValueAt(gridTemporada.getSelectedRow(),
				0);
		am.excluirTemporada(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarTemporadaUI am = new AdicionarTemporadaUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridTemporada.getValueAt(
					gridTemporada.getSelectedRow(), 0);
			am.alterarTemporada(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Selecione um registro para edição", "Informação", 0,
					new ImageIcon(getClass().getResource("/gui/icones/acoes/informacao.png")));
		}
	}

}

