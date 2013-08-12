package br.com.pcsocial.cliente.visao.grid;

import java.awt.Color;

import java.rmi.RemoteException;

import javax.swing.ImageIcon;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.CanaisCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ManterBaseUI;
import br.com.pcsocial.cliente.visao.manter.AdicionarCanaisUI;
import br.com.pcsocial.servidor.modelo.Canais;

public class ManterCanaisUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;

	private JTable gridCanais;
	private CanaisCliente mC;
	private Canais canais;

	@Override
	public void atualizarGrid() {
		mC = new CanaisCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 396;

			gridCanais = new JTable(mC.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridCanais.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridCanais.getColumnModel().getColumn(0);
			TableColumn col2 = gridCanais.getColumnModel().getColumn(1);

			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);

			gridCanais.setFillsViewportHeight(true);
			gridCanais.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridCanais);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setCanais(Canais canais) {
		this.canais = canais;
	}

	public Canais getCanais() {
		return canais;
	}

	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de canais de distribuição";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarCanaisUI am = new AdicionarCanaisUI();
		am.adicionarCanais();
	}

	@Override
	public void excluirCadastro() {
		AdicionarCanaisUI am = new AdicionarCanaisUI();
		Long retornoSel;
		retornoSel = (Long) gridCanais.getValueAt(gridCanais.getSelectedRow(),
				0);
		am.excluirCanais(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarCanaisUI am = new AdicionarCanaisUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridCanais.getValueAt(
					gridCanais.getSelectedRow(), 0);
			am.alterarCanais(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Selecione um registro para edição", "Informação", 0,
					new ImageIcon(getClass().getResource("/gui/icones/acoes/informacao.png")));
		}
	}
}
