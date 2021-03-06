package br.com.pcsocial.cliente.visao.manter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import br.com.pcsocial.cliente.ClassesCliente;
import br.com.pcsocial.servidor.modelo.Classes;

public class AdicionarClassesUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarClasses;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar;
	private JPanel panel, panelInfBotoes, panelCentral;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbDescricao;
	private JTextField tfDescricao;
	private Dimension dmsEdit, dmsLabel, dmsEditDois, dmsLabelDois;
	private Classes classes;
	private byte janelaAtiva;

	public AdicionarClassesUI() {

	}

	public void createAndShowUI(String t, Classes cl) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);
		dmsEditDois = new Dimension(100, 27);
		dmsLabel = new Dimension(140, 27);
		dmsLabelDois = new Dimension(90, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarClasses = new JDialog();
		// Tãtulo da Janela
		adicionarClasses.setTitle(t);

		// Textos
		lbDescricao = new JLabel("Descrição", SwingConstants.RIGHT);
		lbDescricao.setPreferredSize(dmsLabel);
		
		// Editores
		tfDescricao = new JTextField();
		tfDescricao.setPreferredSize(dmsEdit);
		
		
		// Atribuir campos ao Apartamento
			tfDescricao.setText(classes.getDescricao());

		// Botoes
		btnConfirmar = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");

		// Paineis
		panel = new JPanel();
		panelInfBotoes = new JPanel();
		panelCentral = new JPanel();
		layout = new BorderLayout();
		layoutInfBotoes = new FlowLayout(FlowLayout.LEFT);
		layoutCentral = new FlowLayout(FlowLayout.LEFT);

		// Propriedades dos componentes
		panelInfBotoes.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(160, 160, 160), 1, true));
		panelCentral.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Dados gerais"));

		// Acoes
		btnConfirmar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/confirmar.png")));
		btnConfirmar.addActionListener(al);
		btnCancelar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/cancelar.png")));
		btnCancelar.addActionListener(al);

		// Propriedades dos paineis
		panel.setLayout(layout);
		panelInfBotoes.setLayout(layoutInfBotoes);
		panelCentral.setLayout(layoutCentral);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panelInfBotoes, BorderLayout.SOUTH);

		// Adicionar componentes ao painel central
		panelCentral.add(lbDescricao);
		panelCentral.add(tfDescricao);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarClasses.setPreferredSize(dmsTela);
		adicionarClasses.setSize(dmsTela);
		adicionarClasses.setMaximumSize(dmsTela);
		adicionarClasses.setMinimumSize(dmsTela);
		adicionarClasses.add(panel);
		adicionarClasses.setLocationRelativeTo(null);
		adicionarClasses.setModal(true);
		adicionarClasses.setVisible(true);
		adicionarClasses.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	public void adicionarClasses() {
		// Instanciar Classes
		classes = new Classes();

		// Criar interface
		createAndShowUI("Adicionar seguimento de classes", classes);
		janelaAtiva = 0;

	}

	public void alterarClasses(Long id) {
		// Instanciar Classes
		classes = new Classes();

		// Intanciar Classes Cliente
		ClassesCliente cl = new ClassesCliente();
		classes = cl.buscarClassesId(id);

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar seguimento de classes", classes);
	}

	public void excluirClasses(Long id) {
		classes = new Classes();

		// Intanciar Pessoa Cliente
		ClassesCliente ac = new ClassesCliente();
		classes = ac.buscarClassesId(id);
		ac.excluirClasses(id);

	}

	public void setDmsEditDois(Dimension dmsEditDois) {
		this.dmsEditDois = dmsEditDois;
	}

	public Dimension getDmsEditDois() {
		return dmsEditDois;
	}

	public void setDmsLabelDois(Dimension dmsLabelDois) {
		this.dmsLabelDois = dmsLabelDois;
	}

	public Dimension getDmsLabelDois() {
		return dmsLabelDois;
	}

	//
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnConfirmar)) {
				ClassesCliente cl = new ClassesCliente();
				classes.setDescricao(tfDescricao.getText());
				
				if (janelaAtiva == 0) {
					if (cl.adicionarClasses(classes)) {
						javax.swing.JOptionPane
								.showMessageDialog(
										null,
										"Cadastro realizado com sucesso",
										"Informação",
										0,
										new ImageIcon(
												getClass().getResource("/gui/icones/acoes/informacao.png")));
						tfDescricao.setText(null);
					}
				}
				if (janelaAtiva == 1) {
					if (cl.alterarClasses(classes)) {
						javax.swing.JOptionPane
								.showMessageDialog(
										null,
										"Cadastro alterado com sucesso",
										"Informação",
										0,
										new ImageIcon(
												getClass().getResource("/gui/icones/acoes/informacao.png")));
					}

				}
			}
			if (e.getSource().equals(btnCancelar)) {
				if (javax.swing.JOptionPane
						.showConfirmDialog(
								null,
								"Deseja cancelar a operação? \n"
										+ " Todas as informaçães não salvas serão perdidas",
								"Confirme sua operação ",
								javax.swing.JOptionPane.YES_NO_OPTION, 0,
								new ImageIcon(
										getClass().getResource("/gui/icones/acoes/alerta.png"))) == 0) {
					adicionarClasses.dispose();
				}
			}
		}
	};

}