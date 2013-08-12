package br.com.pcsocial.cliente.visao.grid;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.MercadoCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ManterBaseUI;
import br.com.pcsocial.cliente.visao.manter.AdicionarMercadoUI;
import br.com.pcsocial.servidor.modelo.Mercado;

public class ManterMercadoUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;

	private JTable gridMercado;
	private MercadoCliente mC;
	private Mercado mercado;

	@Override
	public void atualizarGrid() {
		mC = new MercadoCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 396;

			gridMercado = new JTable(mC.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridMercado.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridMercado.getColumnModel().getColumn(0);
			TableColumn col2 = gridMercado.getColumnModel().getColumn(1);

			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);

			gridMercado.setFillsViewportHeight(true);
			gridMercado.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridMercado);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
	}

	public Mercado getMercado() {
		return mercado;
	}
	
	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de segmento de mercado";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarMercadoUI am = new AdicionarMercadoUI();
		am.adicionarMercado();
	}

	@Override
	public void excluirCadastro() {
		AdicionarMercadoUI am = new AdicionarMercadoUI();
		Long retornoSel;
		retornoSel = (Long) gridMercado.getValueAt(gridMercado.getSelectedRow(),
				0);
		am.excluirMercado(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarMercadoUI am = new AdicionarMercadoUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridMercado.getValueAt(
					gridMercado.getSelectedRow(), 0);
			am.alterarMercado(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Selecione um registro para edição", "Informação", 0,
					new ImageIcon(getClass().getResource("/gui/icones/acoes/informacao.png")));
		}
	}

}
