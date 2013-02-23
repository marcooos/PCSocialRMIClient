package br.com.pcsocial.cliente.visao.grid;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.ClassesCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ManterBaseUI;
import br.com.pcsocial.cliente.visao.manter.AdicionarClassesUI;
import br.com.pcsocial.servidor.modelo.Classes;

public class ManterClassesUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;
	
	private JTable gridClasses;
	private ClassesCliente mC;
	private Classes classes;

	@Override
	public void atualizarGrid() {
		mC = new ClassesCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 396;
			
			gridClasses = new JTable(mC.montarTabela(dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridClasses.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridClasses.getColumnModel().getColumn(0);
			TableColumn col2 = gridClasses.getColumnModel().getColumn(1);
			
			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			
			gridClasses.setFillsViewportHeight(true);
			gridClasses.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridClasses);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public Classes getClasses() {
		return classes;
	}

	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de classes de tarifas"; 
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarClassesUI am = new AdicionarClassesUI();
		am.adicionarClasses();
	}

	@Override
	public void excluirCadastro() {
		AdicionarClassesUI am = new AdicionarClassesUI();
		Long retornoSel;
		retornoSel = (Long) gridClasses.getValueAt(gridClasses.getSelectedRow(),
				0);
		am.excluirClasses(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarClassesUI am = new AdicionarClassesUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridClasses.getValueAt(
					gridClasses.getSelectedRow(), 0);
			am.alterarClasses(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Selecione um registro para edição", "Informação", 0,
					new ImageIcon(getClass().getResource("/gui/icones/acoes/informacao.png")));
		}
	}
}
