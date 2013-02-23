package br.com.pcsocial.cliente.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.pcsocial.cliente.ConexaoServidorCliente;
import br.com.pcsocial.cliente.PessoaCliente;
import br.com.pcsocial.cliente.util.PropertiesLoaderImpl;

public class ValidarLoginUI {
	private JLabel lbEmail, lbSenha, lbServidor;
	private JPasswordField tfSenha;
	private JTextField tfEmail, tfServidor;
	private JButton btnLogar, btnSair;
	private JLabel imgLogin_user;
	private JFrame dialog;
	private JPanel painelInferior, painelCentral;
	private BorderLayout layout;
	private FlowLayout layoutInferior;
	private FlowLayout layoutCentral;
	private Dimension dmsLogin;
	private Dimension dmsEdit;
	private Dimension dmsLabel;

	public ValidarLoginUI() {
		new ConexaoServidorCliente();
	}

	void createAndShowGUI() {
		
		
		//Instanciar componentes
		dialog = new JFrame();
		lbEmail = new JLabel("E-mail", SwingConstants.RIGHT);
		lbSenha = new JLabel("Senha", SwingConstants.RIGHT);
		lbServidor = new JLabel("Servidor", SwingConstants.RIGHT);
		tfSenha = new JPasswordField();
		tfEmail = new JTextField();
		tfServidor = new JTextField();
		tfServidor.setText(PropertiesLoaderImpl.getValor("servidor"));
		btnLogar = new JButton("Realizar login");
		btnSair = new JButton("Cancelar login");
		imgLogin_user = new JLabel(new ImageIcon(getClass().getResource(
				"/gui/icones/acoes/login_user.png")));
		painelInferior = new JPanel();
		painelCentral = new JPanel();
		layout = new BorderLayout();
		layoutInferior = new FlowLayout();
		layoutCentral = new FlowLayout();
		
		dmsLogin = new Dimension(450, 307);
		dmsEdit = new Dimension(340, 27);
		dmsLabel = new Dimension(50, 27);

		
		//Propriedades da Janela
		dialog.setUndecorated(false);
		dialog.setPreferredSize(dmsLogin);
		dialog.setSize(dmsLogin);
		dialog.setMaximumSize(dmsLogin);
		dialog.setMinimumSize(dmsLogin);
		dialog.setTitle("Efetuar login");
		dialog.setLayout(layout);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		
		//Propriedades componentes
		btnLogar.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/acoes/confirmar.png")));
		btnSair.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/acoes/cancelar.png")));

		// painelSuperior.setLayout(layoutSuperior);
		painelCentral.setLayout(layoutCentral);
		painelCentral.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(160, 160, 160), 1, true));
		painelInferior.setLayout(layoutInferior);
		painelInferior.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(160, 160, 160), 1, true));

		// Propriedades componentes central
		tfEmail.setPreferredSize(dmsEdit);
		tfSenha.setPreferredSize(dmsEdit);
		lbEmail.setPreferredSize(dmsLabel);
		lbSenha.setPreferredSize(dmsLabel);
		
		tfServidor.setPreferredSize(dmsEdit);

		// Componentes painel central
		painelCentral.add(imgLogin_user);

		painelCentral.add(lbEmail);
		painelCentral.add(tfEmail).setFocusable(true);
		painelCentral.add(lbSenha);
		painelCentral.add(tfSenha);
		painelCentral.add(lbServidor);
		painelCentral.add(tfServidor);

		// Painel inferior FlowLayout
		// painelInferior.setAlignmentY(JFrame.RIGHT_ALIGNMENT);
		painelInferior.add(btnLogar);
		painelInferior.add(btnSair);
		

		dialog.add(painelCentral, BorderLayout.CENTER);
		dialog.add(painelInferior, BorderLayout.SOUTH);
		dialog.setVisible(true);

		// Eventos botões
		btnLogar.addActionListener(al);
		btnSair.addActionListener(al);
	}

	ActionListener al = new ActionListener() {
		@Override
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			PropertiesLoaderImpl.setValor("servidor", tfServidor.getText());
			ConexaoServidorCliente.setEnderecoServidor(tfServidor.getText());
			if (e.getSource().equals(btnLogar)) {
				PessoaCliente pc = new PessoaCliente();
				if (pc.validarLogin(tfEmail.getText(), tfSenha.getText())) {
					//PrincipalClienteUI pcUI = new PrincipalClienteUI();
					PrincipalClienteRibbonUI pcUI = new PrincipalClienteRibbonUI();
					pcUI.createAndShowGUI();
					dialog.dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"E-mail ou senha inválido", null, 0, new ImageIcon(getClass().getResource(
									"/gui/icones/acoes/cancelar.png")));
				}
			}
			if (e.getSource().equals(btnSair)) {
				if (javax.swing.JOptionPane
						.showConfirmDialog(
								null,
								"Deseja cancelar o Login",
								"Confirme sua operação ",
								javax.swing.JOptionPane.YES_NO_OPTION,
								0,
								new ImageIcon(getClass().getResource(
										"/gui/icones/acoes/informacao.png"))) == 0) {
					System.exit(0);
				}
			}
		}
	};
}
