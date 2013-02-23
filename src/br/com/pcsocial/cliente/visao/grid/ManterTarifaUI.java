package br.com.pcsocial.cliente.visao.grid;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.TarifaCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ManterBaseUI;
import br.com.pcsocial.cliente.visao.manter.AdicionarTarifaUI;
import br.com.pcsocial.servidor.modelo.Tarifa;

public class ManterTarifaUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;

	private JTable gridTarifa;
	private TarifaCliente mC;
	private Tarifa tarifa;

	@Override
	public void atualizarGrid() {
		mC = new TarifaCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmCol2 = 100;
			int tmColOu = 296;

			gridTarifa = new JTable(mC.montarTabela(
					dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridTarifa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridTarifa.getColumnModel().getColumn(0);
			TableColumn col2 = gridTarifa.getColumnModel().getColumn(1);
			TableColumn col3 = gridTarifa.getColumnModel().getColumn(2);
			TableColumn col4 = gridTarifa.getColumnModel().getColumn(3);
			TableColumn col5 = gridTarifa.getColumnModel().getColumn(4);

			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			col3.setPreferredWidth(tmCol2);
			col4.setPreferredWidth(tmCol2);
			col5.setPreferredWidth(tmCol2);

			gridTarifa.setFillsViewportHeight(true);
			gridTarifa.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridTarifa);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de tarifas";
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarTarifaUI am = new AdicionarTarifaUI();
		am.adicionarTarifa();
	}

	@Override
	public void excluirCadastro() {
		AdicionarTarifaUI am = new AdicionarTarifaUI();
		Long retornoSel;
		retornoSel = (Long) gridTarifa.getValueAt(gridTarifa.getSelectedRow(),
				0);
		am.excluirTarifa(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarTarifaUI am = new AdicionarTarifaUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridTarifa.getValueAt(
					gridTarifa.getSelectedRow(), 0);
			am.alterarTarifa(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Selecione um registro para edição", "Informação", 0,
					new ImageIcon(getClass().getResource("/gui/icones/acoes/informacao.png")));
		}
	}

}
