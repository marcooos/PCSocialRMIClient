package br.com.pcsocial.cliente.visao.base;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ManterBaseUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel, panelSuperior, panelSupBotoes, panelInferior;
	private BorderLayout layout;
	private GridLayout layoutSup;
	private FlowLayout layoutSupBotoes;
	private FlowLayout layoutInf;
	private JButton cbAdicionar;
	private JButton cbModificar;
	private JButton cbExcluir;
	private JButton cbCancelar;
	private JButton cbPesquisa;
	private JTextField dadosPesquisa;
	private JScrollPane scrollPane;

	public ManterBaseUI() {
		super("", true, true, true, true);
		super.setTitle(getTituloJanela());
	}

	public JInternalFrame manterBaseUI(JDesktopPane father)
			throws RemoteException {

		// Propriedades da Janela
		Dimension screenSize = father.getSize();
		Dimension screenMax = new Dimension(screenSize.width, screenSize.height);
		this.setPreferredSize(screenMax);
		this.setSize(screenMax);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Intanciar componentes
		panel = new JPanel();
		panelSuperior = new JPanel();
		panelInferior = new JPanel();
		panelSupBotoes = new JPanel();
		layout = new BorderLayout();
		layoutSup = new GridLayout(2, 1);
		layoutSupBotoes = new FlowLayout(FlowLayout.LEFT);
		layoutInf = new FlowLayout(FlowLayout.LEFT);
		cbAdicionar = new JButton("Adicionar");
		cbModificar = new JButton("Modificar");
		cbExcluir = new JButton("Excluir");
		cbCancelar = new JButton("Cancelar");
		cbPesquisa = new JButton("Pesquisar");
		dadosPesquisa = new JTextField();
		scrollPane = new JScrollPane();

		// Propriedades botoes
		cbAdicionar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/adicionar.png")));
		// cbAdicionar.setVerticalTextPosition(JButton.BOTTOM);
		// cbAdicionar.setHorizontalTextPosition(JButton.CENTER);
		cbAdicionar.addActionListener(al);

		cbModificar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/alterar.png")));
		// cbModificar.setVerticalTextPosition(JButton.BOTTOM);
		// cbModificar.setHorizontalTextPosition(JButton.CENTER);
		cbModificar.addActionListener(al);

		cbExcluir
				.setIcon(new ImageIcon(getClass().getResource("/gui/icones/acoes/excluir.png")));
		// cbExcluir.setVerticalTextPosition(JButton.BOTTOM);
		// cbExcluir.setHorizontalTextPosition(JButton.CENTER);
		cbExcluir.addActionListener(al);

		cbCancelar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/cancelar.png")));
		// cbCancelar.setVerticalTextPosition(JButton.BOTTOM);
		// cbCancelar.setHorizontalTextPosition(JButton.CENTER);
		cbCancelar.addActionListener(al);

		cbPesquisa.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/pesquisa.png")));
		// cbPesquisa.setHorizontalTextPosition(JButton.RIGHT);
		cbPesquisa.addActionListener(al);

		panel.setLayout(layout);
		panelSuperior.setLayout(layoutSup);
		panelSupBotoes.setLayout(layoutSupBotoes);
		panelInferior.setLayout(layoutInf);

		panelSupBotoes.add(cbPesquisa);

		panelSuperior.add(dadosPesquisa);
		panelSuperior.add(panelSupBotoes);

		panelInferior.add(cbAdicionar);
		panelInferior.add(cbModificar);
		panelInferior.add(cbExcluir);
		panelInferior.add(cbCancelar);

		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(panelSuperior, BorderLayout.NORTH);
		panel.add(panelInferior, BorderLayout.SOUTH);
		panel.setVisible(true);

		this.add(panel);

		return this;

	}

	public void atualizarGrid() {
		// TODO
	}

	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(cbAdicionar)) {
				adicionarCadastro();
				atualizarGrid();
			}
			if (e.getSource().equals(cbModificar)) {
				modificarCadastro();
				atualizarGrid();
			}
			if (e.getSource().equals(cbCancelar)) {
				dispose();
			}
			if (e.getSource().equals(cbExcluir)) {
				if (javax.swing.JOptionPane.showConfirmDialog(null,
						"Deseja excluir o cadastro?", "Confirme sua operação ",
						javax.swing.JOptionPane.YES_NO_OPTION, 0,
						new ImageIcon(getClass().getResource("/gui/icones/acoes/alerta.png"))) == 0) {
					excluirCadastro();
					atualizarGrid();
				}
			}
			if (e.getSource().equals(cbPesquisa)) {
				atualizarGrid();
			}
		}
	};

	public String getTituloJanela() {
		String titulo = "Cadastro base"; 
		return titulo;
	}

	public void adicionarCadastro() {
		//TODO
	}

	public void excluirCadastro() {
		//TODO
	}

	public void modificarCadastro() {
		//TODO
	}

	public JTextField getDadosPesquisa() {
		return dadosPesquisa;
	}

	public void setDadosPesquisa(JTextField dadosPesquisa) {
		this.dadosPesquisa = dadosPesquisa;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

}
