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

import br.com.pcsocial.cliente.RestricoesCliente;
import br.com.pcsocial.servidor.modelo.Restricoes;

public class AdicionarRestricoesUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarRestricoes;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar;
	private JPanel panel, panelInfBotoes, panelCentral;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbDescricao;
	private JTextField tfDescricao;
	private Dimension dmsEdit, dmsLabel, dmsEditDois, dmsLabelDois;
	private Restricoes restricoes;
	private byte janelaAtiva;

	public AdicionarRestricoesUI() {

	}

	public void createAndShowUI(String t, Restricoes rs) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);
		dmsEditDois = new Dimension(100, 27);
		dmsLabel = new Dimension(140, 27);
		dmsLabelDois = new Dimension(90, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarRestricoes = new JDialog();
		// Tãtulo da Janela
		adicionarRestricoes.setTitle(t);

		// Textos
		lbDescricao = new JLabel("Descrição", SwingConstants.RIGHT);
		lbDescricao.setPreferredSize(dmsLabel);
		
		// Editores
		tfDescricao = new JTextField();
		tfDescricao.setPreferredSize(dmsEdit);
		
		
		// Atribuir campos as Restricoes
			tfDescricao.setText(restricoes.getDescricao());

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
		adicionarRestricoes.setPreferredSize(dmsTela);
		adicionarRestricoes.setSize(dmsTela);
		adicionarRestricoes.setMaximumSize(dmsTela);
		adicionarRestricoes.setMinimumSize(dmsTela);
		adicionarRestricoes.add(panel);
		adicionarRestricoes.setLocationRelativeTo(null);
		adicionarRestricoes.setModal(true);
		adicionarRestricoes.setVisible(true);
		adicionarRestricoes.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	public void adicionarRestricoes() {
		// Instanciar Restricoes
		restricoes = new Restricoes();

		// Criar interface
		createAndShowUI("Adicionar seguimento de restricoes", restricoes);
		janelaAtiva = 0;

	}

	public void alterarRestricoes(Long id) {
		// Instanciar Restricoes
		restricoes = new Restricoes();

		// Intanciar Restricoes Cliente
		RestricoesCliente rs = new RestricoesCliente();
		restricoes = rs.buscarRestricoesId(id);

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar seguimento de restricoes", restricoes);
	}

	public void excluirRestricoes(Long id) {
		restricoes = new Restricoes();

		// Intanciar Pessoa Cliente
		RestricoesCliente ac = new RestricoesCliente();
		restricoes = ac.buscarRestricoesId(id);
		ac.excluirRestricoes(id);

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
				RestricoesCliente rs = new RestricoesCliente();
				restricoes.setDescricao(tfDescricao.getText());
				
				if (janelaAtiva == 0) {
					if (rs.adicionarRestricoes(restricoes)) {
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
					if (rs.alterarRestricoes(restricoes)) {
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
					adicionarRestricoes.dispose();
				}
			}
		}
	};

}