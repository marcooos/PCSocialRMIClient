package br.com.pcsocial.cliente.visao.base;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ListaBaseUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel, panelSuperior, panelSupBotoes, panelInferior;
	private BorderLayout layout;
	private GridLayout layoutSup;
	private FlowLayout layoutSupBotoes;
	private FlowLayout layoutInf;
	private JButton cbConfirmar;
	private JButton cbCancelar;
	private JButton cbPesquisa;

	private JTextField dadosPesquisa;
	private JScrollPane scrollPane;

	private JDialog buscarBase;

	public ListaBaseUI() {

	}

	public Object createAndShowUI() {
		// Instanciar Janela
		Dimension dmsTela = new Dimension(800, 600);
		buscarBase = new JDialog();
		buscarBase.setTitle(getTitulo());
		criaObjeto();

		// Intanciar componentes
		panel = new JPanel();
		panelSuperior = new JPanel();
		panelInferior = new JPanel();
		panelSupBotoes = new JPanel();
		layout = new BorderLayout();
		layoutSup = new GridLayout(2, 1);
		layoutSupBotoes = new FlowLayout(FlowLayout.LEFT);
		layoutInf = new FlowLayout(FlowLayout.LEFT);
		cbConfirmar = new JButton("Confirmar");
		cbCancelar = new JButton("Cancelar");
		cbPesquisa = new JButton("Pesquisar");
		dadosPesquisa = new JTextField();
		scrollPane = new JScrollPane();

		// Propriedades botoes
		cbConfirmar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/confirmar.png")));
		cbConfirmar.addActionListener(al);

		cbCancelar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/cancelar.png")));
		cbCancelar.addActionListener(al);

		cbPesquisa.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/pesquisa.png")));
		cbPesquisa.addActionListener(al);

		panel.setLayout(layout);
		panelSuperior.setLayout(layoutSup);
		panelSupBotoes.setLayout(layoutSupBotoes);
		panelInferior.setLayout(layoutInf);

		panelSupBotoes.add(cbPesquisa);

		panelSuperior.add(dadosPesquisa);
		panelSuperior.add(panelSupBotoes);

		panelInferior.add(cbConfirmar);
		panelInferior.add(cbCancelar);

		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(panelSuperior, BorderLayout.NORTH);
		panel.add(panelInferior, BorderLayout.SOUTH);
		panel.setVisible(true);

		// Propriedades da Janela
		buscarBase.setPreferredSize(dmsTela);
		buscarBase.setSize(dmsTela);
		buscarBase.setMaximumSize(dmsTela);
		buscarBase.setMinimumSize(dmsTela);
		buscarBase.add(panel);
		buscarBase.setLocationRelativeTo(null);
		buscarBase.setModal(true);
		buscarBase.setVisible(true);
		buscarBase.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		return returnaObjeto();
	}

	public void atualizarGrid() {
		// TODO
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JDialog getBuscarBase() {
		return buscarBase;
	}

	public void setBuscarBase(JDialog buscarBase) {
		this.buscarBase = buscarBase;
	}

	public JTextField getDadosPesquisa() {
		return dadosPesquisa;
	}

	public void setDadosPesquisa(JTextField dadosPesquisa) {
		this.dadosPesquisa = dadosPesquisa;
	}

	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(cbConfirmar)) {
				confirmarSelecao();
			}
			if (e.getSource().equals(cbCancelar)) {
				cancelarSelecao();
			}
			if (e.getSource().equals(cbPesquisa)) {
				atualizarGrid();
			}
		}
	};

	public void confirmarSelecao() {
		// TODO
	}

	public void cancelarSelecao() {
		// TODO
	}

	public Object returnaObjeto() {
		return null;
	}

	public void criaObjeto() {
		// TODO
	}

	public String getTitulo() {
		return null;
	}
}
