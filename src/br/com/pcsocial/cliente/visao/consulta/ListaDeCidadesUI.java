package br.com.pcsocial.cliente.visao.consulta;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.CidadeCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ListaBaseUI;
import br.com.pcsocial.servidor.modelo.Cidade;

public class ListaDeCidadesUI extends ListaBaseUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable gridCidades;
	
	private CidadeCliente cC;
	private Cidade cidade;

	@Override
	public void atualizarGrid() {
		cC = new CidadeCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 198;
			gridCidades = new JTable(cC.montarTabela(dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridCidades.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridCidades.getColumnModel().getColumn(0);
			TableColumn col2 = gridCidades.getColumnModel().getColumn(1);
			TableColumn col3 = gridCidades.getColumnModel().getColumn(2);
			TableColumn col4 = gridCidades.getColumnModel().getColumn(3);
			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			col3.setPreferredWidth(tmColOu);
			col4.setPreferredWidth(tmColOu);
			gridCidades.setFillsViewportHeight(true);
			gridCidades.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridCidades);
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public void confirmarSelecao() {
		CidadeCliente cc = new CidadeCliente();
		cidade = cc.buscarCidadeId((Long) gridCidades.getValueAt(
				gridCidades.getSelectedRow(), 0));
		super.getBuscarBase().dispose();
	}

	@Override
	public void cancelarSelecao() {
		super.getBuscarBase().dispose();
	}

	@Override
	public Object returnaObjeto() {
		return cidade;
	}
	
	@Override
	public void criaObjeto() {
		cidade = new Cidade();
	}
	
	@Override
	public String getTitulo() {
		return "Buscar cidades";
	}

}
