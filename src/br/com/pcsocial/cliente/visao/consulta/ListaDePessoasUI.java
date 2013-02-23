package br.com.pcsocial.cliente.visao.consulta;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.PessoaCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ListaBaseUI;
import br.com.pcsocial.servidor.modelo.Pessoa;

public class ListaDePessoasUI extends ListaBaseUI {

	private static final long serialVersionUID = 1L;
	private JTable gridPessoas;
	private PessoaCliente pC;
	private Pessoa pessoa;

	@Override
	public void atualizarGrid() {
		pC = new PessoaCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 298;
			gridPessoas = new JTable(pC.montarTabela(dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridPessoas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridPessoas.getColumnModel().getColumn(0);
			TableColumn col2 = gridPessoas.getColumnModel().getColumn(1);
			TableColumn col3 = gridPessoas.getColumnModel().getColumn(2);
			TableColumn col4 = gridPessoas.getColumnModel().getColumn(3);
			TableColumn col5 = gridPessoas.getColumnModel().getColumn(4);
			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			col3.setPreferredWidth(tmColOu);
			col4.setPreferredWidth(tmColOu);
			col5.setPreferredWidth(tmColOu);
			gridPessoas.setFillsViewportHeight(true);
			gridPessoas.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridPessoas);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public void criaObjeto() {
		pessoa = new Pessoa();
	}

	@Override
	public String getTitulo() {
		return "Buscar pessoas";
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	@Override
	public void confirmarSelecao() {
		PessoaCliente pc = new PessoaCliente();
		pessoa = pc.buscarPessoaId((Long) gridPessoas.getValueAt(
				gridPessoas.getSelectedRow(), 0));
		super.getBuscarBase().dispose();
	}

	@Override
	public void cancelarSelecao() {
		super.getBuscarBase().dispose();
	}

	@Override
	public Object returnaObjeto() {
		return pessoa;
	}
}