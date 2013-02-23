package br.com.pcsocial.cliente.visao.grid;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.ReceitaCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ManterBaseUI;
import br.com.pcsocial.cliente.visao.manter.AdicionarReceitaUI;
import br.com.pcsocial.servidor.modelo.Receita;

public class ManterReceitaUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;

	private JTable gridReceita;
	private ReceitaCliente mC;
	private Receita receita;

	@Override
	public void atualizarGrid() {
		mC = new ReceitaCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmCol2 = 100;
			int tmColOu = 256;

			gridReceita = new JTable(mC.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridReceita.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridReceita.getColumnModel().getColumn(0);
			TableColumn col2 = gridReceita.getColumnModel().getColumn(1);
			TableColumn col3 = gridReceita.getColumnModel().getColumn(2);

			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			col3.setPreferredWidth(tmCol2);

			gridReceita.setFillsViewportHeight(true);
			gridReceita.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridReceita);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Receita getReceita() {
		return receita;
	}
	
	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de origens de receitas";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarReceitaUI am = new AdicionarReceitaUI();
		am.adicionarReceita();
	}

	@Override
	public void excluirCadastro() {
		AdicionarReceitaUI am = new AdicionarReceitaUI();
		Long retornoSel;
		retornoSel = (Long) gridReceita.getValueAt(gridReceita.getSelectedRow(),
				0);
		am.excluirReceita(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarReceitaUI am = new AdicionarReceitaUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridReceita.getValueAt(
					gridReceita.getSelectedRow(), 0);
			am.alterarReceita(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Selecione um registro para edição", "Informação", 0,
					new ImageIcon(getClass().getResource("/gui/icones/acoes/informacao.png")));
		}
	}


}
