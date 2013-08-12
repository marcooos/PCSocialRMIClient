package br.com.pcsocial.cliente.visao.grid;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.RestricoesCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ManterBaseUI;
import br.com.pcsocial.cliente.visao.manter.AdicionarRestricoesUI;
import br.com.pcsocial.servidor.modelo.Restricoes;

public class ManterRestricoesUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;
	
	private JTable gridRestricoes;
	private RestricoesCliente mC;
	private Restricoes restricoes;

	@Override
	public void atualizarGrid() {
		mC = new RestricoesCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 396;
			
			gridRestricoes = new JTable(mC.montarTabela(dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridRestricoes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridRestricoes.getColumnModel().getColumn(0);
			TableColumn col2 = gridRestricoes.getColumnModel().getColumn(1);
			
			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			
			gridRestricoes.setFillsViewportHeight(true);
			gridRestricoes.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridRestricoes);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setRestricoes(Restricoes restricoes) {
		this.restricoes = restricoes;
	}

	public Restricoes getRestricoes() {
		return restricoes;
	}
	
	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de restriçães de tarifas";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarRestricoesUI am = new AdicionarRestricoesUI();
		am.adicionarRestricoes();
	}

	@Override
	public void excluirCadastro() {
		AdicionarRestricoesUI am = new AdicionarRestricoesUI();
		Long retornoSel;
		retornoSel = (Long) gridRestricoes.getValueAt(gridRestricoes.getSelectedRow(),
				0);
		am.excluirRestricoes(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarRestricoesUI am = new AdicionarRestricoesUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridRestricoes.getValueAt(
					gridRestricoes.getSelectedRow(), 0);
			am.alterarRestricoes(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Selecione um registro para edição", "Informação", 0,
					new ImageIcon(getClass().getResource("/gui/icones/acoes/informacao.png")));
		}
	}


}
