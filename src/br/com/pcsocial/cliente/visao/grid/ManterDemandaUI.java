package br.com.pcsocial.cliente.visao.grid;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.pcsocial.cliente.DemandaCliente;
import br.com.pcsocial.cliente.util.DadosPesquisaGrid;
import br.com.pcsocial.cliente.visao.base.ManterBaseUI;
import br.com.pcsocial.cliente.visao.manter.AdicionarDemandaUI;
import br.com.pcsocial.servidor.modelo.Demanda;

public class ManterDemandaUI extends ManterBaseUI {

	private static final long serialVersionUID = 1L;
	
	private JTable gridDemanda;
	private DemandaCliente mC;
	private Demanda demanda;

	@Override
	public void atualizarGrid() {
		mC = new DemandaCliente();
		DadosPesquisaGrid dp = new DadosPesquisaGrid();
		try {
			int tmCol1 = 40;
			int tmCol2 = 100;
			int tmColOu = 296;
			
			gridDemanda = new JTable(mC.montarTabela(dp.textoPesquisa(super.getDadosPesquisa().getText()),
					dp.valorPesquisa(super.getDadosPesquisa().getText())));
			gridDemanda.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumn col1 = gridDemanda.getColumnModel().getColumn(0);
			TableColumn col2 = gridDemanda.getColumnModel().getColumn(1);
			TableColumn col3 = gridDemanda.getColumnModel().getColumn(2);
			
			col1.setPreferredWidth(tmCol1);
			col2.setPreferredWidth(tmColOu);
			col3.setPreferredWidth(tmCol2);
			
			gridDemanda.setFillsViewportHeight(true);
			gridDemanda.setGridColor(new Color(160, 160, 160));
			super.getScrollPane().setViewportView(gridDemanda);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}

	public Demanda getDemanda() {
		return demanda;
	}

	@Override
	public String getTituloJanela() {
		String titulo = "Cadastro de demandas flutuantes"; 
		return titulo;
	}

	@Override
	public void adicionarCadastro() {
		AdicionarDemandaUI am = new AdicionarDemandaUI();
		am.adicionarDemanda();
	}

	@Override
	public void excluirCadastro() {
		AdicionarDemandaUI am = new AdicionarDemandaUI();
		Long retornoSel;
		retornoSel = (Long) gridDemanda.getValueAt(gridDemanda.getSelectedRow(),
				0);
		am.excluirDemanda(retornoSel);
	}

	@Override
	public void modificarCadastro() {
		AdicionarDemandaUI am = new AdicionarDemandaUI();
		Long retornoSel;
		try {
			retornoSel = (Long) gridDemanda.getValueAt(
					gridDemanda.getSelectedRow(), 0);
			am.alterarDemanda(retornoSel);
		} catch (NullPointerException e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Selecione um registro para edição", "Informação", 0,
					new ImageIcon(getClass().getResource("/gui/icones/acoes/informacao.png")));
		}
	}
}
