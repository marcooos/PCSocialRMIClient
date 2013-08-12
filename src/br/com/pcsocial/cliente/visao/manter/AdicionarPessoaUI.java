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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import br.com.pcsocial.cliente.PessoaCliente;
import br.com.pcsocial.cliente.visao.consulta.ListaDeCidadesUI;
import br.com.pcsocial.cliente.visao.consulta.ListaDeSeguimentoDeMercadoUI;
import br.com.pcsocial.servidor.modelo.Cidade;
import br.com.pcsocial.servidor.modelo.Estado;
import br.com.pcsocial.servidor.modelo.Mercado;
import br.com.pcsocial.servidor.modelo.Pais;
import br.com.pcsocial.servidor.modelo.Pessoa;

public class AdicionarPessoaUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private JDialog adicionarPessoa;
	private Dimension dmsTela;
	private JButton btnConfirmar, btnCancelar, btnBuscarCidades,
			btnBuscarMercado;
	private JPanel panel, panelInfBotoes, panelCentral, panelTipoPessoas;
	private BorderLayout layout;
	private FlowLayout layoutInfBotoes;
	private FlowLayout layoutCentral;
	private JLabel lbEmail, lbSenha, lbNomeRazaoSocial, lbSobreNomeFantasia,
			lbCpfCnpj, lbEndereco, lbEnderecoNumero, lbEnderecoComplemento, lbMercado,
			lbCep, lbBairro, lbCidade, lbEstado, lbPais, lbSelecionarPessoa;
	private JTextField tfEmail, tfNomeRazaoSocial, tfSobreNomeFantasia,
			tfCpfCnpj, tfEndereco, tfEnderecoNumero, tfEnderecoComplemento,
			tfCep, tfBairro, tfCidade, tfEstado, tfPais;
	private JPasswordField pSenha;
	private Dimension dmsEdit, dmsLabel, dmsEditDois, dmsLabelDois,
			dmsLabelPesquisa;
	private JCheckBox jcAtribucaoCliente, jcAtribucaoHospede,
			jcAtribucaoUsuario, jcAtribucaoOperadora;
	private Pessoa pessoa;
	private Cidade cidade;
	private Estado estado;
	private Mercado mercado;
	private Pais pais;
	private byte janelaAtiva;
	private ListaDeCidadesUI listaDeCidades;
	private ListaDeSeguimentoDeMercadoUI listaDeMercados; 

	public AdicionarPessoaUI() {

	}

	public void adicionarPessoa() {
		// Instanciar Pessoa
		pessoa = new Pessoa();
		cidade = new Cidade();
		estado = new Estado();
		pais = new Pais();
		mercado = new Mercado();

		// Criar interface
		createAndShowUI("Adicionar pessoa", pessoa);
		janelaAtiva = 0;
	}

	public void alterarPessoa(Long id) {
		// Instanciar Pessoa
		pessoa = new Pessoa();
		cidade = new Cidade();
		estado = new Estado();
		pais = new Pais();
		mercado = new Mercado();

		// Intanciar Pessoa Cliente
		PessoaCliente pc = new PessoaCliente();
		pessoa = pc.buscarPessoaId(id);
		cidade = pessoa.getCidadeRef();
		estado = cidade.getEstado();
		pais = estado.getPais();
		mercado = pessoa.getSegMercado();

		// Criar interface
		janelaAtiva = 1;
		createAndShowUI("Alterar pessoa", pessoa);
	}

	public void excluirPessoa(Long id) {
		pessoa = new Pessoa();
		// Intanciar Pessoa Cliente
		PessoaCliente pc = new PessoaCliente();
		pessoa = pc.buscarPessoaId(id);
		pc.excluirPessoa(id);

	}

	public void createAndShowUI(String t, Pessoa p) {
		// Propriedades dos componentes da janela
		dmsEdit = new Dimension(620, 27);
		dmsEditDois = new Dimension(285, 27);
		dmsLabel = new Dimension(140, 27);
		dmsLabelDois = new Dimension(90, 27);
		dmsLabelPesquisa = new Dimension(550, 27);

		// Instanciar Janela
		dmsTela = new Dimension(800, 600);
		adicionarPessoa = new JDialog();
		// Tãtulo da Janela
		adicionarPessoa.setTitle(t);

		// Textos
		lbEmail = new JLabel("E-mail", SwingConstants.RIGHT);
		lbEmail.setPreferredSize(dmsLabelDois);
		lbSenha = new JLabel("Senha", SwingConstants.RIGHT);
		lbSenha.setPreferredSize(dmsLabelDois);
		lbNomeRazaoSocial = new JLabel("Nome/Razao Social",
				SwingConstants.RIGHT);
		lbNomeRazaoSocial.setPreferredSize(dmsLabel);
		lbSobreNomeFantasia = new JLabel("Sobrenome/Fantasia",
				SwingConstants.RIGHT);
		lbSobreNomeFantasia.setPreferredSize(dmsLabel);
		lbCpfCnpj = new JLabel("CPF/CNPJ", SwingConstants.RIGHT);
		lbCpfCnpj.setPreferredSize(dmsLabel);
		lbEndereco = new JLabel("Logradouro", SwingConstants.RIGHT);
		lbEndereco.setPreferredSize(dmsLabelDois);
		lbEnderecoNumero = new JLabel("Numero", SwingConstants.RIGHT);
		lbEnderecoNumero.setPreferredSize(dmsLabelDois);
		lbEnderecoComplemento = new JLabel("Complemento", SwingConstants.RIGHT);
		lbEnderecoComplemento.setPreferredSize(dmsLabelDois);
		lbCep = new JLabel("CEP", SwingConstants.RIGHT);
		lbCep.setPreferredSize(dmsLabelDois);
		lbBairro = new JLabel("Bairro", SwingConstants.RIGHT);
		lbBairro.setPreferredSize(dmsLabelDois);
		lbCidade = new JLabel("Cidade", SwingConstants.RIGHT);
		lbCidade.setPreferredSize(dmsLabelDois);
		lbEstado = new JLabel("Estado", SwingConstants.RIGHT);
		lbEstado.setPreferredSize(dmsLabelDois);
		lbPais = new JLabel("Paãs", SwingConstants.RIGHT);
		lbPais.setPreferredSize(dmsLabelDois);
		lbSelecionarPessoa = new JLabel(" ", SwingConstants.RIGHT);
		lbSelecionarPessoa.setPreferredSize(dmsLabelPesquisa);
		lbMercado = new JLabel(" ", SwingConstants.LEFT);
		lbMercado.setPreferredSize(dmsLabelPesquisa);

		// Editores
		tfEmail = new JTextField();
		tfEmail.setPreferredSize(dmsEditDois);
		pSenha = new JPasswordField();
		pSenha.setPreferredSize(dmsEditDois);
		tfNomeRazaoSocial = new JTextField();
		tfNomeRazaoSocial.setPreferredSize(dmsEdit);
		tfSobreNomeFantasia = new JTextField();
		tfSobreNomeFantasia.setPreferredSize(dmsEdit);
		tfCpfCnpj = new JTextField();
		tfCpfCnpj.setPreferredSize(dmsEdit);
		tfEndereco = new JTextField();
		tfEndereco.setPreferredSize(dmsEditDois);
		tfEnderecoNumero = new JTextField();
		tfEnderecoNumero.setPreferredSize(dmsEditDois);
		tfEnderecoComplemento = new JTextField();
		tfEnderecoComplemento.setPreferredSize(dmsEditDois);
		tfCep = new JTextField();
		tfCep.setPreferredSize(dmsEditDois);
		tfBairro = new JTextField();
		tfBairro.setPreferredSize(dmsEditDois);
		tfCidade = new JTextField();
		tfCidade.setPreferredSize(dmsEditDois);
		tfCidade.setEditable(false);
		tfEstado = new JTextField();
		tfEstado.setPreferredSize(dmsEditDois);
		tfEstado.setEditable(false);
		tfPais = new JTextField();
		tfPais.setPreferredSize(dmsEditDois);
		tfPais.setEditable(false);

		// Check Box
		jcAtribucaoCliente = new JCheckBox("Cliente");
		jcAtribucaoHospede = new JCheckBox("Hãspede");
		jcAtribucaoUsuario = new JCheckBox("Usuãrio");
		jcAtribucaoOperadora = new JCheckBox("Operadora");

		// Atribuir campos a pessoa
		if (janelaAtiva == 1) {
			tfNomeRazaoSocial.setText(pessoa.getNomeRazaoSocial());
			tfSobreNomeFantasia.setText(pessoa.getSobreNomeFantasia());
			tfCpfCnpj.setText(pessoa.getCpfCnpj());
			tfEmail.setText(pessoa.getEmail());
			pSenha.setText(pessoa.getSenha());
			tfEndereco.setText(pessoa.getEndereco());
			tfEnderecoNumero.setText(pessoa.getEnderecoNumero());
			tfEnderecoComplemento.setText(pessoa.getEnderecoComplemento());
			try  {
				tfCep.setText(pessoa.getCep());
				tfBairro.setText(pessoa.getBairro());
				tfCidade.setText(cidade.getDescricao());
				tfEstado.setText(estado.getDescricao());
				tfPais.setText(pais.getDescricao());
			} catch (NullPointerException e) {
				tfCep.setText("");
				tfBairro.setText("");
				tfCidade.setText("");
				tfEstado.setText("");
				tfPais.setText("");
			}
			
			if (pessoa.getAtribuicao() == 'C') {
				jcAtribucaoCliente.setSelected(true);
			}
			if (pessoa.getAtribuicao() == 'H') {
				jcAtribucaoHospede.setSelected(true);
			}
			if (pessoa.getAtribuicao() == 'U') {
				jcAtribucaoUsuario.setSelected(true);
			}
			if (pessoa.getAtribuicao() == 'O') {
				jcAtribucaoOperadora.setSelected(true);
			}
			try {
				lbMercado.setText(mercado.getDescricao());
			} catch (NullPointerException e) {
				lbMercado.setText("Nenhum mercado associado");
			}
		}

		// Botoes
		btnConfirmar = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");
		btnBuscarCidades = new JButton("Buscar Cidade");
		btnBuscarMercado = new JButton("Seguimento de Mercado");

		// Paineis
		panel = new JPanel();
		panelInfBotoes = new JPanel();
		panelCentral = new JPanel();
		panelTipoPessoas = new JPanel();
		layout = new BorderLayout();
		layoutInfBotoes = new FlowLayout(FlowLayout.LEFT);
		layoutCentral = new FlowLayout(FlowLayout.LEFT);

		// Propriedades dos componentes
		panelInfBotoes.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(160, 160, 160), 1, true));
		panelCentral.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Dados gerais"));
		panelTipoPessoas.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Atribuiçães da pessoa"));

		// Acoes Check-box
		jcAtribucaoCliente.addActionListener(al);
		jcAtribucaoHospede.addActionListener(al);
		jcAtribucaoUsuario.addActionListener(al);
		jcAtribucaoOperadora.addActionListener(al);

		// Acoes
		btnConfirmar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/confirmar.png")));
		btnConfirmar.addActionListener(al);
		btnCancelar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/cancelar.png")));
		btnCancelar.addActionListener(al);
		btnBuscarCidades.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/pesquisa.png")));
		btnBuscarCidades.addActionListener(al);
		btnBuscarMercado.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/pesquisa.png")));
		btnBuscarMercado.addActionListener(al);

		// Propriedades dos paineis
		panel.setLayout(layout);
		panelInfBotoes.setLayout(layoutInfBotoes);
		panelCentral.setLayout(layoutCentral);
		panelTipoPessoas.setLayout(layoutCentral);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panelInfBotoes, BorderLayout.SOUTH);
		panel.add(panelTipoPessoas, BorderLayout.NORTH);

		// Adicionar componentes ao painel tipo de pessoas
		panelTipoPessoas.add(jcAtribucaoCliente);
		panelTipoPessoas.add(jcAtribucaoHospede);
		panelTipoPessoas.add(jcAtribucaoUsuario);
		panelTipoPessoas.add(jcAtribucaoOperadora);

		// Adicionar componentes ao painel central
		panelCentral.add(lbNomeRazaoSocial);
		panelCentral.add(tfNomeRazaoSocial);
		panelCentral.add(lbSobreNomeFantasia);
		panelCentral.add(tfSobreNomeFantasia);
		panelCentral.add(lbCpfCnpj);
		panelCentral.add(tfCpfCnpj);
		panelCentral.add(lbEmail);
		panelCentral.add(tfEmail);
		panelCentral.add(lbSenha);
		panelCentral.add(pSenha);
		panelCentral.add(lbEndereco);
		panelCentral.add(tfEndereco);
		panelCentral.add(lbEnderecoNumero);
		panelCentral.add(tfEnderecoNumero);
		panelCentral.add(lbEnderecoComplemento);
		panelCentral.add(tfEnderecoComplemento);
		panelCentral.add(lbCep);
		panelCentral.add(tfCep);
		panelCentral.add(btnBuscarCidades);
		panelCentral.add(lbSelecionarPessoa);
		panelCentral.add(lbBairro);
		panelCentral.add(tfBairro);
		panelCentral.add(lbCidade);
		panelCentral.add(tfCidade);
		panelCentral.add(lbEstado);
		panelCentral.add(tfEstado);
		panelCentral.add(lbPais);
		panelCentral.add(tfPais);
		panelCentral.add(btnBuscarMercado);
		panelCentral.add(lbMercado);

		// Adicionar componentes ao painel inferior
		panelInfBotoes.add(btnConfirmar);
		panelInfBotoes.add(btnCancelar);

		// Propriedades da Janela
		adicionarPessoa.setPreferredSize(dmsTela);
		adicionarPessoa.setSize(dmsTela);
		adicionarPessoa.setMaximumSize(dmsTela);
		adicionarPessoa.setMinimumSize(dmsTela);
		adicionarPessoa.add(panel);
		adicionarPessoa.setLocationRelativeTo(null);
		adicionarPessoa.setModal(true);
		adicionarPessoa.setVisible(true);
		adicionarPessoa.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	//
	ActionListener al = new ActionListener() {
		@Override
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnConfirmar)) {
				PessoaCliente pc = new PessoaCliente();
				pessoa.setNomeRazaoSocial(tfNomeRazaoSocial.getText());
				pessoa.setSobreNomeFantasia(tfSobreNomeFantasia.getText());
				pessoa.setCpfCnpj(tfCpfCnpj.getText());
				pessoa.setEmail(tfEmail.getText());
				pessoa.setSenha(pSenha.getText());
				pessoa.setEndereco(tfEndereco.getText());
				pessoa.setEnderecoNumero(tfEnderecoNumero.getText());
				pessoa.setEnderecoComplemento(tfEnderecoComplemento.getText());
				pessoa.setCep(tfCep.getText());
				pessoa.setBairro(tfBairro.getText());
				pessoa.setCidadeRef(cidade);
				if (jcAtribucaoCliente.isSelected()) {
					pessoa.setAtribuicao('C');
				} else if (jcAtribucaoHospede.isSelected()) {
					pessoa.setAtribuicao('H');
				} else if (jcAtribucaoUsuario.isSelected()) {
					pessoa.setAtribuicao('U');
				} else if (jcAtribucaoOperadora.isSelected()) {
					pessoa.setAtribuicao('O');
				} else
					pessoa.setAtribuicao('C');
				pessoa.setSegMercado(mercado);
				
				if (janelaAtiva == 0) {
					if (pc.adicionarPessoa(pessoa)) {
						javax.swing.JOptionPane
								.showMessageDialog(
										null,
										"Cadastro realizado com sucesso",
										"Informação",
										0,
										new ImageIcon(
												getClass().getResource("/gui/icones/acoes/informacao.png")));
						pessoa = null;
						tfNomeRazaoSocial.setText(null);
						tfSobreNomeFantasia.setText(null);
						tfCpfCnpj.setText(null);
						tfEmail.setText(null);
						pSenha.setText(null);
						tfEndereco.setText(null);
						tfEnderecoNumero.setText(null);
						tfEnderecoComplemento.setText(null);
						tfCep.setText(null);
						tfBairro.setText(null);
						tfCidade.setText(null);
						tfEstado.setText(null);
						tfPais.setText(null);
						lbMercado.setText(null);
					}
				}
				if (janelaAtiva == 1) {
					if (pc.alterarPessoa(pessoa)) {
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
					adicionarPessoa.dispose();
				}
			}
			if (e.getSource().equals(btnBuscarCidades)) {
				listaDeCidades = new ListaDeCidadesUI();
				cidade = (Cidade) listaDeCidades.createAndShowUI();
				estado = cidade.getEstado();
				pais = estado.getPais();
				tfCidade.setText(cidade.getDescricao());
				tfEstado.setText(estado.getDescricao());
				tfPais.setText(pais.getDescricao());
			}
			if (e.getSource().equals(btnBuscarMercado)) {
				listaDeMercados = new ListaDeSeguimentoDeMercadoUI();
				mercado = (Mercado) listaDeMercados.createAndShowUI();
				lbMercado.setText(mercado.getDescricao());
			}
			if (e.getSource().equals(jcAtribucaoCliente)) {
				if (jcAtribucaoCliente.isSelected()) {
					jcAtribucaoHospede.setSelected(false);
					jcAtribucaoUsuario.setSelected(false);
					jcAtribucaoOperadora.setSelected(false);
				} else {
					jcAtribucaoCliente.setSelected(true);
				}
			}
			if (e.getSource().equals(jcAtribucaoHospede)) {
				if (jcAtribucaoHospede.isSelected()) {
					jcAtribucaoCliente.setSelected(false);
					jcAtribucaoUsuario.setSelected(false);
					jcAtribucaoOperadora.setSelected(false);
				} else {
					jcAtribucaoCliente.setSelected(true);
				}
			}
			if (e.getSource().equals(jcAtribucaoUsuario)) {
				if (jcAtribucaoUsuario.isSelected()) {
					jcAtribucaoCliente.setSelected(false);
					jcAtribucaoHospede.setSelected(false);
					jcAtribucaoOperadora.setSelected(false);
				} else {
					jcAtribucaoCliente.setSelected(true);
				}
			}
			if (e.getSource().equals(jcAtribucaoOperadora)) {
				if (jcAtribucaoOperadora.isSelected()) {
					jcAtribucaoCliente.setSelected(false);
					jcAtribucaoHospede.setSelected(false);
					jcAtribucaoUsuario.setSelected(false);
				} else {
					jcAtribucaoCliente.setSelected(true);
				}
			}
		}
	};

}
