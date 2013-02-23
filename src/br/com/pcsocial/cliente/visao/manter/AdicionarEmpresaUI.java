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

import br.com.pcsocial.cliente.EmpresaCliente;
import br.com.pcsocial.cliente.visao.consulta.ListaDePessoasUI;
import br.com.pcsocial.servidor.modelo.Empresa;
import br.com.pcsocial.servidor.modelo.Pessoa;

public class AdicionarEmpresaUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarEmpresa;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar, btnBuscarPessoa;
	private JPanel panel, panelInfBotoes, panelCentral, panelTipoEmpresa;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbNomeRazaoSocial, lbSobreNomeFantasia, lbCpfCnpj,
			lbCodEmpresaPMS, lbSelecionarPessoa;
	private JTextField tfNomeRazaoSocial, tfSobreNomeFantasia, tfCpfCnpj,
			tfCodEmpresaPMS;
	private Dimension dmsEdit, dmsLabel, dmsEditDois, dmsLabelDois,
			dmsLabelTitulo;
	private JCheckBox jcAtribucaoMatriz, jcAtribucaoFilial;
	private Empresa empresa;
	private Pessoa pessoa;
	private byte janelaAtiva;
	private ListaDePessoasUI listaDePessoas;

	public AdicionarEmpresaUI() {

	}

	public void createAndShowUI(String t, Empresa em, Pessoa p) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);
		dmsEditDois = new Dimension(100, 27);
		dmsLabel = new Dimension(140, 27);
		dmsLabelDois = new Dimension(90, 27);
		dmsLabelTitulo = new Dimension(580, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarEmpresa = new JDialog();
		// Título da Janela
		adicionarEmpresa.setTitle(t);

		// Textos
		lbNomeRazaoSocial = new JLabel("Nome/Razao Social",
				SwingConstants.RIGHT);
		lbNomeRazaoSocial.setPreferredSize(dmsLabel);
		lbSobreNomeFantasia = new JLabel("Sobrenome/Fantasia",
				SwingConstants.RIGHT);
		lbSobreNomeFantasia.setPreferredSize(dmsLabel);
		lbCpfCnpj = new JLabel("CPF/CNPJ", SwingConstants.RIGHT);
		lbCpfCnpj.setPreferredSize(dmsLabel);
		lbCodEmpresaPMS = new JLabel("Cod. PMS", SwingConstants.RIGHT);
		lbCodEmpresaPMS.setPreferredSize(dmsLabel);
		lbSelecionarPessoa = new JLabel(" ", SwingConstants.LEFT);
		lbSelecionarPessoa.setPreferredSize(dmsLabelTitulo);

		// Editores
		tfNomeRazaoSocial = new JTextField();
		tfNomeRazaoSocial.setPreferredSize(dmsEdit);
		tfNomeRazaoSocial.setEditable(false);
		tfSobreNomeFantasia = new JTextField();
		tfSobreNomeFantasia.setPreferredSize(dmsEdit);
		tfSobreNomeFantasia.setEditable(false);
		tfCpfCnpj = new JTextField();
		tfCpfCnpj.setPreferredSize(dmsEdit);
		tfCpfCnpj.setEditable(false);
		tfCodEmpresaPMS = new JTextField();
		tfCodEmpresaPMS.setPreferredSize(dmsEditDois);

		// Check Box
		jcAtribucaoMatriz = new JCheckBox("Matriz");
		jcAtribucaoFilial = new JCheckBox("Filial");

		// Atribuir campos a empresa
		if (janelaAtiva == 0) {
			jcAtribucaoFilial.setSelected(true);
		}
		if (janelaAtiva == 1) {
			try {
				tfNomeRazaoSocial.setText(pessoa.getNomeRazaoSocial());
				tfSobreNomeFantasia.setText(pessoa.getSobreNomeFantasia());
				tfCpfCnpj.setText(pessoa.getCpfCnpj());
			} catch (NullPointerException e) {
				tfNomeRazaoSocial.setText("");
				tfSobreNomeFantasia.setText("");
				tfCpfCnpj.setText("");
			}
			tfCodEmpresaPMS.setText(empresa.getCodempresaStr());
			if (empresa.getAtrEmpresa() == 'M') {
				jcAtribucaoMatriz.setSelected(true);
			}
			if (empresa.getAtrEmpresa() == 'F') {
				jcAtribucaoFilial.setSelected(true);
			}
		}

		// Botoes
		btnConfirmar = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");
		btnBuscarPessoa = new JButton("Buscar Pessoa");

		// Paineis
		panel = new JPanel();
		panelInfBotoes = new JPanel();
		panelTipoEmpresa = new JPanel();
		panelCentral = new JPanel();
		layout = new BorderLayout();
		layoutInfBotoes = new FlowLayout(FlowLayout.LEFT);
		layoutCentral = new FlowLayout(FlowLayout.LEFT);

		// Propriedades dos componentes
		panelInfBotoes.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(160, 160, 160), 1, true));
		panelCentral.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Dados gerais"));
		panelTipoEmpresa.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Atribuições da empresa"));

		// Acoes
		btnConfirmar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/confirmar.png")));
		btnConfirmar.addActionListener(al);
		btnCancelar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/cancelar.png")));
		btnCancelar.addActionListener(al);
		btnBuscarPessoa.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/pesquisa.png")));
		btnBuscarPessoa.addActionListener(al);

		// Definindo ações check box
		jcAtribucaoMatriz.addActionListener(al);
		jcAtribucaoFilial.addActionListener(al);

		// Propriedades dos paineis
		panel.setLayout(layout);
		panelInfBotoes.setLayout(layoutInfBotoes);
		panelCentral.setLayout(layoutCentral);
		panelTipoEmpresa.setLayout(layoutCentral);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panelInfBotoes, BorderLayout.SOUTH);
		panel.add(panelTipoEmpresa, BorderLayout.NORTH);

		// Adicionar componentes ao painel tipo de pessoas
		panelTipoEmpresa.add(jcAtribucaoMatriz);
		panelTipoEmpresa.add(jcAtribucaoFilial);

		// Adicionar componentes ao painel central
		panelCentral.add(btnBuscarPessoa);
		panelCentral.add(lbSelecionarPessoa);
		panelCentral.add(lbNomeRazaoSocial);
		panelCentral.add(tfNomeRazaoSocial);
		panelCentral.add(lbSobreNomeFantasia);
		panelCentral.add(tfSobreNomeFantasia);
		panelCentral.add(lbCpfCnpj);
		panelCentral.add(tfCpfCnpj);
		panelCentral.add(lbCodEmpresaPMS);
		panelCentral.add(tfCodEmpresaPMS);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarEmpresa.setPreferredSize(dmsTela);
		adicionarEmpresa.setSize(dmsTela);
		adicionarEmpresa.setMaximumSize(dmsTela);
		adicionarEmpresa.setMinimumSize(dmsTela);
		adicionarEmpresa.add(panel);
		adicionarEmpresa.setLocationRelativeTo(null);
		adicionarEmpresa.setModal(true);
		adicionarEmpresa.setVisible(true);
		adicionarEmpresa.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	public void adicionarEmpresa() {
		// Instanciar Pessoa
		empresa = new Empresa();
		pessoa = new Pessoa();

		// Criar interface
		createAndShowUI("Adicionar empresa", empresa, pessoa);
		janelaAtiva = 0;

	}

	public void alterarEmpresa(Long id) {
		// Instanciar Pessoa
		empresa = new Empresa();
		pessoa = new Pessoa();
		// Intanciar Pessoa Cliente
		EmpresaCliente ec = new EmpresaCliente();
		empresa = ec.buscarEmpresaId(id);
		pessoa = empresa.getPessoaEmpresa();

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar empresa", empresa, pessoa);
	}

	public void excluirEmpresa(Long id) {
		empresa = new Empresa();
		pessoa = new Pessoa();
		// Intanciar Pessoa Cliente
		EmpresaCliente ec = new EmpresaCliente();
		empresa = ec.buscarEmpresaId(id);
		ec.excluirEmpresa(id);

	}

	public void setLbCodEmpresaPMS(JLabel lbCodEmpresaPMS) {
		this.lbCodEmpresaPMS = lbCodEmpresaPMS;
	}

	public JLabel getLbCodEmpresaPMS() {
		return lbCodEmpresaPMS;
	}

	public void setTfCodEmpresaPMS(JTextField tfCodEmpresaPMS) {
		this.tfCodEmpresaPMS = tfCodEmpresaPMS;
	}

	public JTextField getTfCodEmpresaPMS() {
		return tfCodEmpresaPMS;
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
				EmpresaCliente ec = new EmpresaCliente();
				empresa.setCodempresa(Long.parseLong(tfCodEmpresaPMS.getText()));
				empresa.setPessoaEmpresa(pessoa);
				if (jcAtribucaoFilial.isSelected()) {
					empresa.setAtrEmpresa('F');
				} else if (jcAtribucaoMatriz.isSelected()) {
					empresa.setAtrEmpresa('M');
				} else
					empresa.setAtrEmpresa('M');

				if (empresa.getPessoaEmpresa().getId() == 0) {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Selecione uma pessoa", "Informação", 0,
							new ImageIcon(
									getClass().getResource("/gui/icones/acoes/alerta.png")));
				} else {

					if (janelaAtiva == 0) {
						if (ec.adicionarEmpresa(empresa)) {
							javax.swing.JOptionPane
									.showMessageDialog(
											null,
											"Cadastro realizado com sucesso",
											"Informação",
											0,
											new ImageIcon(
													getClass().getResource("/gui/icones/acoes/informacao.png")));
							empresa = null;
							tfNomeRazaoSocial.setText(null);
							tfSobreNomeFantasia.setText(null);
							tfCpfCnpj.setText(null);
							tfCodEmpresaPMS.setText(null);
						}
					}
					if (janelaAtiva == 1) {
						if (ec.alterarEmpresa(empresa)) {
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
			}
			if (e.getSource().equals(btnCancelar)) {
				if (javax.swing.JOptionPane
						.showConfirmDialog(
								null,
								"Deseja cancelar a operação? \n"
										+ " Todas as informações não salvas serão perdidas",
								"Confirme sua operação ",
								javax.swing.JOptionPane.YES_NO_OPTION, 0,
								new ImageIcon(
										getClass().getResource("/gui/icones/acoes/alerta.png"))) == 0) {
					adicionarEmpresa.dispose();
				}
			}
			if (e.getSource().equals(btnBuscarPessoa)) {
				listaDePessoas = new ListaDePessoasUI();
				pessoa = (Pessoa) listaDePessoas.createAndShowUI();
				tfNomeRazaoSocial.setText(pessoa.getNomeRazaoSocial());
				tfSobreNomeFantasia.setText(pessoa.getSobreNomeFantasia());
				tfCpfCnpj.setText(pessoa.getCpfCnpj());
			}
			if (e.getSource().equals(jcAtribucaoMatriz)) {
				if (jcAtribucaoMatriz.isSelected()) {
					jcAtribucaoFilial.setSelected(false);
				} else {
					jcAtribucaoFilial.setSelected(true);
				}
			}
			if (e.getSource().equals(jcAtribucaoFilial)) {
				if (jcAtribucaoFilial.isSelected()) {
					jcAtribucaoMatriz.setSelected(false);
				} else {
					jcAtribucaoMatriz.setSelected(true);
				}
			}
		}
	};

}
