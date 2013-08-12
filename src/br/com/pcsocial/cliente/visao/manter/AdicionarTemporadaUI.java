package br.com.pcsocial.cliente.visao.manter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import br.com.pcsocial.cliente.TemporadaCliente;
import br.com.pcsocial.cliente.util.JDateChooser;
import br.com.pcsocial.servidor.modelo.Temporada;

public class AdicionarTemporadaUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarTemporada;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar;
	private JPanel panel, panelInfBotoes, panelCentral, panelVendaAtiva;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbDescricao, lbDataInicial, lbDataFinal;
	private JTextField tfDescricao;
	private JDateChooser jDataInicial, jDataFinal;
	private Dimension dmsEdit, dmsLabel, dmsEditDois, dmsLabelDois;
	private Temporada temporada;
	private JCheckBox jCVendaAtiva;
	private byte janelaAtiva;

	public AdicionarTemporadaUI() {

	}

	public void createAndShowUI(String t, Temporada dm) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);
		dmsEditDois = new Dimension(150, 27);
		dmsLabel = new Dimension(140, 27);
		dmsLabelDois = new Dimension(90, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarTemporada = new JDialog();
		// Tãtulo da Janela
		adicionarTemporada.setTitle(t);

		// Textos
		lbDescricao = new JLabel("Descrição", SwingConstants.RIGHT);
		lbDescricao.setPreferredSize(dmsLabel);
		lbDataInicial = new JLabel("Data inicial", SwingConstants.RIGHT);
		lbDataInicial.setPreferredSize(dmsLabel);
		lbDataFinal = new JLabel("Data final", SwingConstants.RIGHT);
		lbDataFinal.setPreferredSize(dmsLabel);

		// Editores
		tfDescricao = new JTextField();
		tfDescricao.setPreferredSize(dmsEdit);
		jDataInicial = new JDateChooser();
		jDataInicial.setPreferredSize(dmsEditDois);
		jDataFinal = new JDateChooser();
		jDataFinal.setPreferredSize(dmsEditDois);

		// Check-box
		jCVendaAtiva = new JCheckBox("Venda ativa");

		// Atribuir campos ao Apartamento
		if (janelaAtiva == 1) {
			if (temporada.getVendaAtiva() == 'S') {
				jCVendaAtiva.setSelected(true);
			} else
				jCVendaAtiva.setSelected(false);
			tfDescricao.setText(temporada.getDescricao());
			jDataInicial.setDate(temporada.getDataInicial());
			jDataFinal.setDate(temporada.getDataFinal());
		}

		// Botoes
		btnConfirmar = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");

		// Paineis
		panel = new JPanel();
		panelInfBotoes = new JPanel();
		panelCentral = new JPanel();
		panelVendaAtiva = new JPanel();
		layout = new BorderLayout();
		layoutInfBotoes = new FlowLayout(FlowLayout.LEFT);
		layoutCentral = new FlowLayout(FlowLayout.LEFT);

		// Propriedades dos componentes
		panelInfBotoes.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(160, 160, 160), 1, true));
		panelCentral.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Dados gerais"));
		panelVendaAtiva.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Disponãvel para venda"));

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
		panelVendaAtiva.setLayout(layoutCentral);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panelInfBotoes, BorderLayout.SOUTH);
		panel.add(panelVendaAtiva, BorderLayout.NORTH);

		// Adicionar componentes ao painel venda
		panelVendaAtiva.add(jCVendaAtiva);

		// Adicionar componentes ao painel central
		panelCentral.add(lbDescricao);
		panelCentral.add(tfDescricao);
		panelCentral.add(lbDataInicial);
		panelCentral.add(jDataInicial);
		panelCentral.add(lbDataFinal);
		panelCentral.add(jDataFinal);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarTemporada.setPreferredSize(dmsTela);
		adicionarTemporada.setSize(dmsTela);
		adicionarTemporada.setMaximumSize(dmsTela);
		adicionarTemporada.setMinimumSize(dmsTela);
		adicionarTemporada.add(panel);
		adicionarTemporada.setLocationRelativeTo(null);
		adicionarTemporada.setModal(true);
		adicionarTemporada.setVisible(true);
		adicionarTemporada.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	public void adicionarTemporada() {
		// Instanciar Temporada
		temporada = new Temporada();

		// Criar interface
		createAndShowUI("Adicionar temporadas", temporada);
		janelaAtiva = 0;

	}

	public void alterarTemporada(Long id) {
		// Instanciar Temporada
		temporada = new Temporada();

		// Intanciar Temporada Cliente
		TemporadaCliente dc = new TemporadaCliente();
		temporada = dc.buscarTemporadaId(id);

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar temporada", temporada);
	}

	public void excluirTemporada(Long id) {
		temporada = new Temporada();

		// Intanciar Temporada Cliente
		TemporadaCliente dc = new TemporadaCliente();
		temporada = dc.buscarTemporadaId(id);
		dc.excluirTemporada(id);

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
				TemporadaCliente dm = new TemporadaCliente();
				Date dataInicial;
				dataInicial = jDataInicial.getDate();
				temporada.setDataInicial(dataInicial);
				Date dataFinal;
				dataFinal = jDataFinal.getDate();
				temporada.setDataFinal(dataFinal);
				temporada.setDescricao(tfDescricao.getText());
				if (jCVendaAtiva.isSelected()) {
					temporada.setVendaAtiva('S');
				} else
					temporada.setVendaAtiva('S');

				if (janelaAtiva == 0) {
					if (dm.adicionarTemporada(temporada)) {
						javax.swing.JOptionPane
								.showMessageDialog(
										null,
										"Cadastro realizado com sucesso",
										"Informação",
										0,
										new ImageIcon(
												getClass().getResource("/gui/icones/acoes/informacao.png")));
						tfDescricao.setText(null);
						jDataInicial.setDate(null);
						jDataFinal.setDate(null);
						jCVendaAtiva.setSelected(false);

					}
				}
				if (janelaAtiva == 1) {
					if (dm.alterarTemporada(temporada)) {
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
					adicionarTemporada.dispose();
				}
			}
		}
	};

}