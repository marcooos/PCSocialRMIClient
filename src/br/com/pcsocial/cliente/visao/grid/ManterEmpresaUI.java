package br.com.pcsocial.cliente.visao.grid;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.EmpresaCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ManterBaseUI;
import br.com.pcsocial.cliente.visao.manter.AdicionarEmpresaUI;
import br.com.pcsocial.servidor.modelo.Empresa;

public class ManterEmpresaUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;

	private JTable gridEmpresas;
	private EmpresaCliente eP;
	private Empresa empresa;

	@Override
	public void atualizarGrid() {
		eP = new EmpresaCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmColOu = 396;
			gridEmpresas = new JTable(eP.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridEmpresas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridEmpresas.getColumnModel().getColumn(0);
			TableColumn col2 = gridEmpresas.getColumnModel().getColumn(1);
			TableColumn col3 = gridEmpresas.getColumnModel().getColumn(2);
			TableColumn col4 = gridEmpresas.getColumnModel().getColumn(3);

			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			col3.setPreferredWidth(tmColOu);
			col4.setPreferredWidth(tmColOu);

			gridEmpresas.setFillsViewportHeight(true);
			gridEmpresas.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridEmpresas);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de canais de hotéis";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarEmpresaUI am = new AdicionarEmpresaUI();
		am.adicionarEmpresa();
	}

	@Override
	public void excluirCadastro() {
		AdicionarEmpresaUI am = new AdicionarEmpresaUI();
		Long retornoSel;
		retornoSel = (Long) gridEmpresas.getValueAt(gridEmpresas.getSelectedRow(),
				0);
		am.excluirEmpresa(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarEmpresaUI am = new AdicionarEmpresaUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridEmpresas.getValueAt(
					gridEmpresas.getSelectedRow(), 0);
			am.alterarEmpresa(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Selecione um registro para edição", "Informação", 0,
					new ImageIcon(getClass().getResource("/gui/icones/acoes/informacao.png")));
		}
	}

}
