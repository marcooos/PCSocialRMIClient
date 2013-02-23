package br.com.pcsocial.cliente.visao.grid;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.ApartamentoCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ManterBaseUI;
import br.com.pcsocial.cliente.visao.manter.AdicionarTiposApartamentoUI;
import br.com.pcsocial.servidor.modelo.Apartamento;

public class ManterTiposApartamentoUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;

	private JTable gridApartamento;
	private ApartamentoCliente aC;
	private Apartamento apartamento;

	@Override
	public void atualizarGrid() {
		aC = new ApartamentoCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 196;
			gridApartamento = new JTable(aC.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridApartamento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridApartamento.getColumnModel().getColumn(0);
			TableColumn col2 = gridApartamento.getColumnModel().getColumn(1);
			TableColumn col3 = gridApartamento.getColumnModel().getColumn(2);
			TableColumn col4 = gridApartamento.getColumnModel().getColumn(3);
			TableColumn col5 = gridApartamento.getColumnModel().getColumn(4);
			TableColumn col6 = gridApartamento.getColumnModel().getColumn(5);

			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			col3.setPreferredWidth(tmColOu);
			col4.setPreferredWidth(tmColOu);
			col5.setPreferredWidth(tmColOu);
			col6.setPreferredWidth(tmColOu);

			gridApartamento.setFillsViewportHeight(true);
			gridApartamento.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridApartamento);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de tipos de apartamentos";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarTiposApartamentoUI am = new AdicionarTiposApartamentoUI();
		am.adicionarTiposApartamento();
	}

	@Override
	public void excluirCadastro() {
		AdicionarTiposApartamentoUI am = new AdicionarTiposApartamentoUI();
		Long retornoSel;
		retornoSel = (Long) gridApartamento.getValueAt(gridApartamento.getSelectedRow(),
				0);
		am.excluirApartamento(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarTiposApartamentoUI am = new AdicionarTiposApartamentoUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridApartamento.getValueAt(
					gridApartamento.getSelectedRow(), 0);
			am.alterarApartamento(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Selecione um registro para edição", "Informação", 0,
					new ImageIcon(getClass().getResource("/gui/icones/acoes/informacao.png")));
		}
	}

}
