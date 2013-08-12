package br.com.pcsocial.cliente.visao.manter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import br.com.pcsocial.cliente.ApartamentoCliente;
import br.com.pcsocial.servidor.modelo.Apartamento;

public class AdicionarTiposApartamentoUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarTiposApartamento;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar;
	private JPanel panel, panelInfBotoes, panelCentral, panelVendaOnline;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbDescricao, lbDescricaoAbreviada, lbCodPms, lbQtdePool, lbQtdeOutPool;
	private JTextField tfDescricao, tfDescricaoAbreviado, tfCodPms, tfQtdePool, tfQtdeOutPool;
	private Dimension dmsEdit, dmsLabel, dmsEditDois, dmsLabelDois;
	private JCheckBox jcVendaOnline;
	private Apartamento apartamento;
	private byte janelaAtiva;

	public AdicionarTiposApartamentoUI() {

	}

	public void createAndShowUI(String t, Apartamento ap) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);
		dmsEditDois = new Dimension(100, 27);
		dmsLabel = new Dimension(140, 27);
		dmsLabelDois = new Dimension(90, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarTiposApartamento = new JDialog();
		// Tãtulo da Janela
		adicionarTiposApartamento.setTitle(t);

		// Textos
		lbDescricao = new JLabel("Descrição", SwingConstants.RIGHT);
		lbDescricao.setPreferredSize(dmsLabel);
		lbDescricaoAbreviada = new JLabel("Abreviacao", SwingConstants.RIGHT);
		lbDescricaoAbreviada.setPreferredSize(dmsLabel);
		lbCodPms = new JLabel("Cãd. Pms", SwingConstants.RIGHT);
		lbCodPms.setPreferredSize(dmsLabel);
		lbQtdePool = new JLabel("Qtd. Pool", SwingConstants.RIGHT);
		lbQtdePool.setPreferredSize(dmsLabel);
		lbQtdeOutPool = new JLabel("Qtd. fora do Pool", SwingConstants.RIGHT);
		lbQtdeOutPool.setPreferredSize(dmsLabel);

		// Editores
		tfDescricao = new JTextField();
		tfDescricao.setPreferredSize(dmsEdit);
		tfDescricaoAbreviado = new JTextField();
		tfDescricaoAbreviado.setPreferredSize(dmsEdit);
		tfCodPms = new JTextField();
		tfCodPms.setPreferredSize(dmsEditDois);
		tfQtdePool = new JTextField();
		tfQtdePool.setPreferredSize(dmsEditDois);
		tfQtdeOutPool = new JTextField();
		tfQtdeOutPool.setPreferredSize(dmsEditDois);
		
		// Check Box
		jcVendaOnline = new JCheckBox("Venda on-line");

		// Atribuir campos ao Apartamento
		if (janelaAtiva == 1) {
			if (apartamento.getOnline() == 'S') {
				jcVendaOnline.setSelected(true);
			} else
				jcVendaOnline.setSelected(false);
			tfDescricao.setText(apartamento.getDescricao());
			tfDescricaoAbreviado.setText(apartamento.getDescricaoAbreviada());
			tfCodPms.setText(apartamento.getCodTipoPmsStr());
			tfQtdePool.setText(apartamento.getQtdePoolStr());
			tfQtdeOutPool.setText(apartamento.getQtdeOutPoolStr());

		}

		// Botoes
		btnConfirmar = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");

		// Paineis
		panel = new JPanel();
		panelInfBotoes = new JPanel();
		panelCentral = new JPanel();
		panelVendaOnline = new JPanel();
		layout = new BorderLayout();
		layoutInfBotoes = new FlowLayout(FlowLayout.LEFT);
		layoutCentral = new FlowLayout(FlowLayout.LEFT);

		// Propriedades dos componentes
		panelInfBotoes.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(160, 160, 160), 1, true));
		panelCentral.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Dados gerais"));
		panelVendaOnline.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Realiza venda on-line"));


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
		panelVendaOnline.setLayout(layoutCentral);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panelInfBotoes, BorderLayout.SOUTH);
		panel.add(panelVendaOnline, BorderLayout.NORTH);

		// Adicionar componentes ao painel venda on-line
		panelVendaOnline.add(jcVendaOnline);

		// Adicionar componentes ao painel central
		panelCentral.add(lbDescricao);
		panelCentral.add(tfDescricao);
		panelCentral.add(lbDescricaoAbreviada);
		panelCentral.add(tfDescricaoAbreviado);
		panelCentral.add(lbQtdePool);
		panelCentral.add(tfQtdePool);
		panelCentral.add(lbQtdeOutPool);
		panelCentral.add(tfQtdeOutPool);
		panelCentral.add(lbCodPms);
		panelCentral.add(tfCodPms);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarTiposApartamento.setPreferredSize(dmsTela);
		adicionarTiposApartamento.setSize(dmsTela);
		adicionarTiposApartamento.setMaximumSize(dmsTela);
		adicionarTiposApartamento.setMinimumSize(dmsTela);
		adicionarTiposApartamento.add(panel);
		adicionarTiposApartamento.setLocationRelativeTo(null);
		adicionarTiposApartamento.setModal(true);
		adicionarTiposApartamento.setVisible(true);
		adicionarTiposApartamento
				.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	public void adicionarTiposApartamento() {
		// Instanciar Apartamento
		apartamento = new Apartamento();

		// Criar interface
		createAndShowUI("Adicionar tipo de apartamento", apartamento);
		janelaAtiva = 0;

	}

	public void alterarApartamento(Long id) {
		// Instanciar Apartamento
		apartamento = new Apartamento();

		// Intanciar Pessoa Cliente
		ApartamentoCliente ac = new ApartamentoCliente();
		apartamento = ac.buscarApartamentoId(id);

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar tipo de apartamento", apartamento);
	}

	public void excluirApartamento(Long id) {
		apartamento = new Apartamento();

		// Intanciar Pessoa Cliente
		ApartamentoCliente ac = new ApartamentoCliente();
		apartamento = ac.buscarApartamentoId(id);
		ac.excluirApartamento(id);

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
				ApartamentoCliente ac = new ApartamentoCliente();
				apartamento.setDescricao(tfDescricao.getText());
				apartamento.setDescricaoAbreviada(tfDescricaoAbreviado.getText());
				apartamento.setCodTipoPms(Long.parseLong(tfCodPms.getText()));
				apartamento.setQuantidadePool(Long.parseLong(tfQtdePool.getText()));
				apartamento.setQuantidadeOutPool(Long.parseLong(tfQtdeOutPool.getText()));
				
				if (jcVendaOnline.isSelected()) {
					apartamento.setOnline('S');
				} else
					apartamento.setOnline('N');
				
				if (janelaAtiva == 0) {
					if (ac.adicionarTipoApartamento(apartamento)) {
						javax.swing.JOptionPane
								.showMessageDialog(
										null,
										"Cadastro realizado com sucesso",
										"Informação",
										0,
										new ImageIcon(
												getClass().getResource("/gui/icones/acoes/informacao.png")));
						apartamento = null;
						tfDescricao.setText(null);
						tfDescricaoAbreviado.setText(null);
						tfCodPms.setText(null);
						tfQtdePool.setText(null);
						tfQtdeOutPool.setText(null);
						jcVendaOnline.setSelected(false);
					}
				}
				if (janelaAtiva == 1) {
					if (ac.alterarApartamento(apartamento)) {
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
					adicionarTiposApartamento.dispose();
				}
			}
		}
	};

}