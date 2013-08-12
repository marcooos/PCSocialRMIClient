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

import br.com.pcsocial.cliente.ReceitaCliente;
import br.com.pcsocial.servidor.modelo.Receita;

public class AdicionarReceitaUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarReceita;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar;
	private JPanel panel, panelInfBotoes, panelCentral;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbDescricao, lbCodEmpresaPMS;
	private JTextField tfDescricao, tfCodEmpresaPMS;
	private Dimension dmsEdit, dmsLabel, dmsEditDois, dmsLabelDois;
	private Receita receita;
	private byte janelaAtiva;

	public AdicionarReceitaUI() {

	}

	public void createAndShowUI(String t, Receita rc) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);
		dmsEditDois = new Dimension(100, 27);
		dmsLabel = new Dimension(140, 27);
		dmsLabelDois = new Dimension(90, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarReceita = new JDialog();
		// Tãtulo da Janela
		adicionarReceita.setTitle(t);

		// Textos
		lbDescricao = new JLabel("Descrição", SwingConstants.RIGHT);
		lbDescricao.setPreferredSize(dmsLabel);
		lbCodEmpresaPMS = new JLabel("Cod. PMS", SwingConstants.RIGHT);
		lbCodEmpresaPMS.setPreferredSize(dmsLabel);

		// Editores
		tfDescricao = new JTextField();
		tfDescricao.setPreferredSize(dmsEdit);
		tfCodEmpresaPMS = new JTextField();
		tfCodEmpresaPMS.setPreferredSize(dmsEditDois);

		// Atribuir campos ao Apartamento
		tfDescricao.setText(receita.getDescricao());
		tfCodEmpresaPMS.setText(receita.getCodPmsStr());

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
		panelCentral.add(lbCodEmpresaPMS);
		panelCentral.add(tfCodEmpresaPMS);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarReceita.setPreferredSize(dmsTela);
		adicionarReceita.setSize(dmsTela);
		adicionarReceita.setMaximumSize(dmsTela);
		adicionarReceita.setMinimumSize(dmsTela);
		adicionarReceita.add(panel);
		adicionarReceita.setLocationRelativeTo(null);
		adicionarReceita.setModal(true);
		adicionarReceita.setVisible(true);
		adicionarReceita.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	public void adicionarReceita() {
		// Instanciar Receita
		receita = new Receita();

		// Criar interface
		createAndShowUI("Adicionar receitas", receita);
		janelaAtiva = 0;

	}

	public void alterarReceita(Long id) {
		// Instanciar Receita
		receita = new Receita();

		// Intanciar Receita Cliente
		ReceitaCliente dc = new ReceitaCliente();
		receita = dc.buscarReceitaId(id);

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar receita", receita);
	}

	public void excluirReceita(Long id) {
		receita = new Receita();

		// Intanciar Receita Cliente
		ReceitaCliente dc = new ReceitaCliente();
		receita = dc.buscarReceitaId(id);
		dc.excluirReceita(id);

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
				ReceitaCliente rc = new ReceitaCliente();
				receita.setDescricao(tfDescricao.getText());
				receita.setCodPms(Long.parseLong(tfCodEmpresaPMS.getText()));

				if (janelaAtiva == 0) {
					if (rc.adicionarReceita(receita)) {
						javax.swing.JOptionPane
								.showMessageDialog(
										null,
										"Cadastro realizado com sucesso",
										"Informação",
										0,
										new ImageIcon(
												getClass().getResource("/gui/icones/acoes/informacao.png")));
						tfDescricao.setText(null);
						tfCodEmpresaPMS.setText(null);
						tfDescricao.setFocusable(true);
					}
				}
				if (janelaAtiva == 1) {
					if (rc.alterarReceita(receita)) {
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
					adicionarReceita.dispose();
				}
			}
		}
	};

}
