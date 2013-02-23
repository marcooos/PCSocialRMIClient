package br.com.pcsocial.cliente.visao.consulta;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.ClassesCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ListaBaseUI;
import br.com.pcsocial.servidor.modelo.Classes;

public class ListaDeClassesUI extends ListaBaseUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable gridClassess;
	private ClassesCliente cC;
	private Classes classes;

	@Override
	public void atualizarGrid() {
		cC = new ClassesCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 198;
			gridClassess = new JTable(cC.montarTabela(dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridClassess.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridClassess.getColumnModel().getColumn(0);
			TableColumn col2 = gridClassess.getColumnModel().getColumn(1);
			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			gridClassess.setFillsViewportHeight(true);
			gridClassess.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridClassess);
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public void confirmarSelecao() {
		ClassesCliente cc = new ClassesCliente();
		classes = cc.buscarClassesId((Long) gridClassess.getValueAt(
				gridClassess.getSelectedRow(), 0));
		super.getBuscarBase().dispose();

	}

	@Override
	public void cancelarSelecao() {
		super.getBuscarBase().dispose();
	}

	@Override
	public Object returnaObjeto() {
		return classes;
	}

	@Override
	public void criaObjeto() {
		classes = new Classes();
	}

	@Override
	public String getTitulo() {
		return "Buscar classes de tarifas";
	}
}
