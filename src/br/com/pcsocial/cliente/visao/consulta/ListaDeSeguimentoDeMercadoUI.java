package br.com.pcsocial.cliente.visao.consulta;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.MercadoCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ListaBaseUI;
import br.com.pcsocial.servidor.modelo.Mercado;

public class ListaDeSeguimentoDeMercadoUI extends ListaBaseUI {

	private static final long serialVersionUID = 1L;
	private JTable gridMercados;
	private MercadoCliente cC;
	private Mercado mercado;

	@Override
	public void atualizarGrid() {
		cC = new MercadoCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 198;
			gridMercados = new JTable(cC.montarTabela(dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridMercados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridMercados.getColumnModel().getColumn(0);
			TableColumn col2 = gridMercados.getColumnModel().getColumn(1);
			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			gridMercados.setFillsViewportHeight(true);
			gridMercados.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridMercados);

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
	public void confirmarSelecao() {
		MercadoCliente cc = new MercadoCliente();
		mercado = cc.buscarMercadoId((Long) gridMercados.getValueAt(
				gridMercados.getSelectedRow(), 0));
		super.getBuscarBase().dispose();
	}

	@Override
	public void cancelarSelecao() {
		super.getBuscarBase().dispose();
	}

	@Override
	public Object returnaObjeto() {
		return mercado;
	}

	@Override
	public void criaObjeto() {
		mercado = new Mercado();
	}

	@Override
	public String getTitulo() {
		return "Buscar seguimento de Mercado";
	}
}
