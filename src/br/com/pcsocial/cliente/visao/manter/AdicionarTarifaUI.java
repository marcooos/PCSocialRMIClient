package br.com.pcsocial.cliente.visao.manter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import br.com.pcsocial.cliente.RestricoesCliente;
import br.com.pcsocial.cliente.TarifaCliente;
import br.com.pcsocial.cliente.visao.consulta.ListaDeClassesUI;
import br.com.pcsocial.servidor.modelo.Classes;
import br.com.pcsocial.servidor.modelo.Restricoes;
import br.com.pcsocial.servidor.modelo.Tarifa;

public class AdicionarTarifaUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarTarifa;
	private JButton btnConfirmar, btnCancelar, btnBuscarClasses;
	private JPanel panel, panelInfBotoes, panelCentral, panelVendaOnline,
			panelRestricoes;
	private ScrollPane scrollpanel;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbDescricao, lbCodPms, lbSeparador, lbClasse;
	private JTextField tfDescricao, tfCodPms;
	private Dimension dmsTela, dmsLabelSeparador, dmsEdit, dmsLabel,
			dmsEditDois, dmsLabelDois, dmsLabelScrool;
	private JCheckBox jcVendaOnline, jcTarifaVariavel;
	private Tarifa tarifa;
	private Classes classes;
	private Restricoes restricoes;
	private RestricoesCliente restricoesTarifa;
	@SuppressWarnings("rawtypes")
	private List restricoesTarifaL, jcRestricoesL, listaRestTarifas;
	private byte janelaAtiva;
	private ListaDeClassesUI listaDeClasses;

	public AdicionarTarifaUI() {
		restricoes = new Restricoes();
		restricoesTarifa = new RestricoesCliente();
		restricoesTarifaL = new ArrayList<Restricoes>();
		jcRestricoesL = new ArrayList<JCheckBox>();
		listaRestTarifas = new ArrayList<Restricoes>();

	}
	
	public void adicionarTarifa() {
		// Instanciar Tarifa
		tarifa = new Tarifa();
		classes = new Classes();

		// Criar interface
		createAndShowUI("Adicionar tarifa", tarifa);
		janelaAtiva = 0;

	}

	public void alterarTarifa(Long id) {
		// Instanciar Tarifa
		tarifa = new Tarifa();
		classes = new Classes();

		// Intanciar Pessoa Cliente
		TarifaCliente ac = new TarifaCliente();
		tarifa = ac.buscarTarifaId(id);
		classes = tarifa.getClasses();

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar tarifa", tarifa);
	}

	public void excluirTarifa(Long id) {
		tarifa = new Tarifa();

		// Intanciar Pessoa Cliente
		TarifaCliente ac = new TarifaCliente();
		tarifa = ac.buscarTarifaId(id);
		ac.excluirTarifa(id);

	}

	@SuppressWarnings("unchecked")
	public void createAndShowUI(String t, Tarifa tr) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);
		dmsEditDois = new Dimension(100, 27);
		dmsLabel = new Dimension(140, 27);
		dmsLabelDois = new Dimension(90, 27);
		dmsLabelSeparador = new Dimension(790, 27);
		dmsLabelScrool = new Dimension(768, 305);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarTarifa = new JDialog();
		// Tãtulo da Janela
		adicionarTarifa.setTitle(t);

		// Textos
		lbDescricao = new JLabel("Descrição", SwingConstants.RIGHT);
		lbDescricao.setPreferredSize(dmsLabel);
		lbCodPms = new JLabel("Cãd. Pms", SwingConstants.RIGHT);
		lbCodPms.setPreferredSize(dmsLabel);
		lbClasse = new JLabel("", SwingConstants.LEFT);
		lbClasse.setPreferredSize(dmsLabel);
		lbSeparador = new JLabel("", SwingConstants.LEFT);
		lbSeparador.setPreferredSize(dmsLabelSeparador);

		// Editores
		tfDescricao = new JTextField();
		tfDescricao.setPreferredSize(dmsEdit);
		tfCodPms = new JTextField();
		tfCodPms.setPreferredSize(dmsEditDois);

		// Check Box
		jcVendaOnline = new JCheckBox("Venda on-line");
		jcTarifaVariavel = new JCheckBox("Tarifa on-line variãvel");
		// Lista de restriçães
		restricoesTarifaL = restricoesTarifa.listaDeRestricoes();
		int i = 0;
		int f = restricoesTarifaL.size();
		while (i < f) {
			restricoes = (Restricoes) restricoesTarifaL.get(i);
			jcRestricoesL.add(new JCheckBox(restricoes.getDescricao()));
			i++;
		}

		// Atribuir campos ao Tarifa
		if (janelaAtiva == 1) {
			if (tarifa.getVendaOnline() == 'S') {
				jcVendaOnline.setSelected(true);
			} else
				jcVendaOnline.setSelected(false);
			if (tarifa.getTarifaVarial() == 'S') {
				jcTarifaVariavel.setSelected(true);
			} else
				jcTarifaVariavel.setSelected(false);
			tfDescricao.setText(tarifa.getDescricao());
			tfCodPms.setText(tarifa.getCodPmsStr());
			try {
				lbClasse.setText(classes.getDescricao());
			} catch (NullPointerException e ) {
				lbClasse.setText("Tarifa não classificada");
			}
			listaRestTarifas = tarifa.getRestricoes();
			Restricoes restCompara = new Restricoes();
			i = 0;
			int ft = listaRestTarifas.size();
			while (i < f) {
				JCheckBox jc = (JCheckBox) jcRestricoesL.get(i);
				restricoes = (Restricoes) restricoesTarifaL.get(i);
				int it = 0;
				while (it < ft) {
					restCompara = (Restricoes) listaRestTarifas.get(it);
					if (restCompara.getId() == restricoes.getId()) {
						jc.setSelected(true);
					}
					it++;
				}
				i++;
			}

		}

		// Botoes
		btnConfirmar = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");
		btnBuscarClasses = new JButton("Classificar tarifa");

		// Paineis
		panel = new JPanel();
		panelInfBotoes = new JPanel();
		panelCentral = new JPanel();
		panelVendaOnline = new JPanel();
		panelRestricoes = new JPanel();
		layout = new BorderLayout();
		layoutInfBotoes = new FlowLayout(FlowLayout.LEFT);
		layoutCentral = new FlowLayout(FlowLayout.LEFT);
		scrollpanel = new ScrollPane();

		// Propriedades dos componentes
		panelInfBotoes.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(160, 160, 160), 1, true));
		panelCentral.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Dados gerais"));
		panelVendaOnline.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Realiza venda on-line"));
		panelRestricoes.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Restriçães das Tarifas"));

		// Acoes
		btnConfirmar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/confirmar.png")));
		btnConfirmar.addActionListener(al);
		btnCancelar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/cancelar.png")));
		btnCancelar.addActionListener(al);
		btnBuscarClasses.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/pesquisa.png")));
		btnBuscarClasses.addActionListener(al);

		jcTarifaVariavel.addActionListener(al);
		jcVendaOnline.addActionListener(al);

		// Propriedades dos paineis
		scrollpanel.setPreferredSize(dmsLabelScrool);
		panel.setLayout(layout);
		panelInfBotoes.setLayout(layoutInfBotoes);
		panelCentral.setLayout(layoutCentral);
		panelVendaOnline.setLayout(layoutCentral);
		panelRestricoes.setLayout(layoutCentral);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panelInfBotoes, BorderLayout.SOUTH);
		panel.add(panelVendaOnline, BorderLayout.NORTH);

		// Adicionar componentes ao painel venda on-line
		panelVendaOnline.add(jcVendaOnline);
		panelVendaOnline.add(jcTarifaVariavel);

		// Adicionar componentes ao painel central
		panelCentral.add(lbDescricao);
		panelCentral.add(tfDescricao);
		panelCentral.add(lbCodPms);
		panelCentral.add(tfCodPms);
		panelCentral.add(btnBuscarClasses);
		panelCentral.add(lbClasse);
		panelCentral.add(lbSeparador);
		panelCentral.add(scrollpanel);
		i = 0;
		f = jcRestricoesL.size();
		while (i < f) {
			panelRestricoes.add((JCheckBox) jcRestricoesL.get(i));
			i++;
		}
		scrollpanel.add(panelRestricoes);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarTarifa.setPreferredSize(dmsTela);
		adicionarTarifa.setSize(dmsTela);
		adicionarTarifa.setMaximumSize(dmsTela);
		adicionarTarifa.setMinimumSize(dmsTela);
		adicionarTarifa.add(panel);
		adicionarTarifa.setLocationRelativeTo(null);
		adicionarTarifa.setModal(true);
		adicionarTarifa.setVisible(true);
		adicionarTarifa.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

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

	public void setListaDeClasses(ListaDeClassesUI listaDeClasses) {
		this.listaDeClasses = listaDeClasses;
	}

	public ListaDeClassesUI getListaDeClasses() {
		return listaDeClasses;
	}

	//
	ActionListener al = new ActionListener() {
		@Override
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnConfirmar)) {
				TarifaCliente ac = new TarifaCliente();
				tarifa.setDescricao(tfDescricao.getText());
				tarifa.setCodPms(Long.parseLong(tfCodPms.getText()));
				tarifa.setClasses(classes);
				if (jcVendaOnline.isSelected()) {
					tarifa.setVendaOnline('S');
				} else
					tarifa.setVendaOnline('R');
				if (jcTarifaVariavel.isSelected()) {
					tarifa.setTarifaVarial('S');
				} else
					tarifa.setTarifaVarial('R');

				int i = 0;
				int f = jcRestricoesL.size();
				listaRestTarifas.clear();
				while (i < f) {
					JCheckBox jc = (JCheckBox) jcRestricoesL.get(i);
					if (jc.isSelected()) {
						listaRestTarifas.add(restricoesTarifaL.get(i));
					}
					i++;
				}
				if (janelaAtiva == 0) {
					tarifa.setRestricoes(listaRestTarifas);
					if (ac.adicionarTarifa(tarifa)) {
						javax.swing.JOptionPane
								.showMessageDialog(
										null,
										"Cadastro realizado com sucesso",
										"Informação",
										0,
										new ImageIcon(
												getClass().getResource("/gui/icones/acoes/informacao.png")));
						tarifa = null;
						tfDescricao.setText(null);
						tfCodPms.setText(null);
						jcVendaOnline.setSelected(false);
						jcTarifaVariavel.setSelected(false);
						lbClasse.setText("");
						listaRestTarifas = new ArrayList<Restricoes>();
						i = 0;
						f = jcRestricoesL.size();
						while (i < f) {
							JCheckBox jc = (JCheckBox) jcRestricoesL.get(i);
							if (jc.isSelected()) {
								listaRestTarifas.add(restricoesTarifaL.get(i));
							}
							i++;
						}

						i = 0;
						f = jcRestricoesL.size();
						while (i < f) {
							JCheckBox jc = (JCheckBox) jcRestricoesL.get(i);
							jc.setSelected(false);
							i++;
						}

					}
				}
				if (janelaAtiva == 1) {
					tarifa.setRestricoes(null);
					ac.alterarTarifa(tarifa);
					tarifa.setRestricoes(listaRestTarifas);
					if (ac.alterarTarifa(tarifa)) {
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
					adicionarTarifa.dispose();
				}
			}
			if (e.getSource().equals(btnBuscarClasses)) {
				listaDeClasses = new ListaDeClassesUI();
				classes = (Classes) listaDeClasses.createAndShowUI();
				lbClasse.setText(classes.getDescricao());
			}
			if (e.getSource().equals(jcVendaOnline)) {
				if (jcVendaOnline.isSelected()) {
					jcTarifaVariavel.setSelected(false);
				}
			}
			if (e.getSource().equals(jcTarifaVariavel)) {
				if (jcTarifaVariavel.isSelected()) {
					jcVendaOnline.setSelected(false);
				}
			}
		}
	};

}