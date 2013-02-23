package br.com.pcsocial.cliente.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import br.com.pcsocial.cliente.visao.analise.OcupacaoSegClasTarifaUI;
import br.com.pcsocial.cliente.visao.analise.TempoDePermanenciaUI;
import br.com.pcsocial.cliente.visao.grid.ManterCanaisUI;
import br.com.pcsocial.cliente.visao.grid.ManterClassesUI;
import br.com.pcsocial.cliente.visao.grid.ManterDemandaUI;
import br.com.pcsocial.cliente.visao.grid.ManterEmpresaUI;
import br.com.pcsocial.cliente.visao.grid.ManterMercadoUI;
import br.com.pcsocial.cliente.visao.grid.ManterPessoaUI;
import br.com.pcsocial.cliente.visao.grid.ManterReceitaUI;
import br.com.pcsocial.cliente.visao.grid.ManterRestricoesUI;
import br.com.pcsocial.cliente.visao.grid.ManterTarifaUI;
import br.com.pcsocial.cliente.visao.grid.ManterTemporadaUI;
import br.com.pcsocial.cliente.visao.grid.ManterTiposApartamentoUI;

public class PrincipalClienteUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktop;
	private JMenuItem menuItem;
	private JToolBar toolbar;
	private JButton cadastroPessoasTB, cadastroTemporadasTB, cadastroTarifasTB,
			sairTB;

	public PrincipalClienteUI() {
		super("Gestão de receitas");

		// inicializar componentes
		menuItem = new JMenuItem();
		toolbar = new JToolBar();
		cadastroPessoasTB = new JButton();
		cadastroTemporadasTB = new JButton();
		cadastroTarifasTB = new JButton();
		sairTB = new JButton();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Abrir Miximizado
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension screenMax = new Dimension(screenSize.width, screenSize.height);

		this.setPreferredSize(screenMax);
		desktop = new JDesktopPane();
		this.setJMenuBar(createMenuBar());
		this.add(toolbar, BorderLayout.NORTH);
		// TesteRibbon
		//this.add(criarRibbonMenu(),BorderLayout.NORTH);
		this.add(desktop, BorderLayout.CENTER);
		this.pack();
		this.setSize(screenMax);
		this.setLocationRelativeTo(null);
		// this.setBackground(getForeground());
		desktop.setBackground(getBackground());
		toolbar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(
				160, 160, 160), 1, true));
		toolbar.setFloatable(false);

		// Assocoes botoes toolbar
		toolbar.add(cadastroPessoasTB);
		cadastroPessoasTB.addActionListener(al);
		cadastroPessoasTB
				.setToolTipText("Realizar manutenção no cadastro de pessoas");
		cadastroPessoasTB.setActionCommand("pessoas");
		cadastroPessoasTB.setIcon(new ImageIcon(
				"src//gui//icones//barras//pessoas.png"));

		toolbar.add(cadastroTarifasTB);
		cadastroTarifasTB.addActionListener(al);
		cadastroTarifasTB
				.setToolTipText("Realizar manutenção no cadastro de tarifas");
		cadastroTarifasTB.setActionCommand("tarifas");
		cadastroTarifasTB.setIcon(new ImageIcon(
				"src//gui//icones//barras//tarifas.png"));

		toolbar.add(cadastroTemporadasTB);
		cadastroTemporadasTB.addActionListener(al);
		cadastroTemporadasTB
				.setToolTipText("Realizar manutenção nas temporadas");
		cadastroTemporadasTB.setActionCommand("temporadas");
		cadastroTemporadasTB.setIcon(new ImageIcon(
				"src//gui//icones//barras//temporadas.png"));

		toolbar.addSeparator();

		toolbar.add(sairTB);
		sairTB.addActionListener(al);
		sairTB.setActionCommand("sair");
		sairTB.setToolTipText("Sair da aplicação");
		sairTB.setIcon(new ImageIcon("src//gui//icones//barras//sair.png"));
	}

	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// Primeiro item de menu.
		JMenu fileMenuCadastro = new JMenu("Cadastros");
		fileMenuCadastro.setMnemonic(KeyEvent.VK_C);
		fileMenuCadastro.setIcon(new ImageIcon(
				"src//gui//icones//menu//cadastros.png"));

		// Segundo item de menu.
		JMenu fileMenuAnalise = new JMenu("Análises");
		fileMenuAnalise.setMnemonic(KeyEvent.VK_A);
		fileMenuAnalise.setIcon(new ImageIcon(
				"src//gui//icones//menu//analises.png"));

		// Terceiro item de menu.
		JMenu fileMenuRelatorios = new JMenu("Relatórios");
		fileMenuRelatorios.setMnemonic(KeyEvent.VK_R);
		fileMenuRelatorios.setIcon(new ImageIcon(
				"src//gui//icones//menu//relatorios.png"));

		// Quarto item de menu.
		JMenu fileMenuParametros = new JMenu("Parâmetros");
		fileMenuParametros.setMnemonic(KeyEvent.VK_P);
		fileMenuParametros.setIcon(new ImageIcon(
				"src//gui//icones//menu//parametros.png"));

		// Quinto item de menu.
		JMenu fileMenuAjuda = new JMenu("Ajuda");
		fileMenuAjuda.setMnemonic(KeyEvent.VK_A);
		fileMenuAjuda
				.setIcon(new ImageIcon("src//gui//icones//menu//ajuda.png"));

		// Montar Menu
		menuBar.add(fileMenuCadastro);
		menuBar.add(fileMenuAnalise);
		menuBar.add(fileMenuRelatorios);
		menuBar.add(fileMenuParametros);
		menuBar.add(fileMenuAjuda);

		// Item de menu cadastro
		menuItem = new JMenuItem("Hotéis");
		menuItem.setMnemonic(KeyEvent.VK_H);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("hoteis");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//empresas.png"));
		menuItem.addActionListener(al);
		fileMenuCadastro.add(menuItem);

		// Item de menu cadastro
		menuItem = new JMenuItem("Tipos de apartamento");
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("apartamentos");
		menuItem.setIcon(new ImageIcon(
				"src//gui//icones//menu//apartamentos.png"));
		menuItem.addActionListener(al);
		fileMenuCadastro.add(menuItem);
		// Separador
		fileMenuCadastro.addSeparator();

		// Item de menu cadastro.
		menuItem = new JMenuItem("Pessoas");
		menuItem.setMnemonic(KeyEvent.VK_P);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("pessoas");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//pessoasM.png"));
		menuItem.addActionListener(al);
		fileMenuCadastro.add(menuItem);

		// Item de menu cadastro.
		menuItem = new JMenuItem("Segmentos de mercado");
		menuItem.setMnemonic(KeyEvent.VK_M);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("mercado");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//mercado.png"));
		menuItem.addActionListener(al);
		fileMenuCadastro.add(menuItem);
		// Separdor
		fileMenuCadastro.addSeparator();

		// Item de menu cadastro.
		menuItem = new JMenuItem("Tarifas");
		menuItem.setMnemonic(KeyEvent.VK_R);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("tarifas");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//tarifas.png"));
		menuItem.addActionListener(al);
		fileMenuCadastro.add(menuItem);

		// Item de menu cadastro.
		menuItem = new JMenuItem("Classes de tarifas");
		menuItem.setMnemonic(KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("classes");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//classes.png"));
		menuItem.addActionListener(al);
		fileMenuCadastro.add(menuItem);

		// Item de menu cadastro.
		menuItem = new JMenuItem("Restrições de tarifas");
		menuItem.setMnemonic(KeyEvent.VK_C);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("restricoes");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//restricoes.png"));
		menuItem.addActionListener(al);
		fileMenuCadastro.add(menuItem);

		// Separdor
		fileMenuCadastro.addSeparator();

		// Item de menu cadastro
		menuItem = new JMenuItem("Canais de Distribuição");
		menuItem.setMnemonic(KeyEvent.VK_D);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("canais");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//canais.png"));
		menuItem.addActionListener(al);
		fileMenuCadastro.add(menuItem);

		// Item de menu cadastro
		menuItem = new JMenuItem("Demandas flutuantes");
		menuItem.setMnemonic(KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("demandas");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//demandas.png"));
		menuItem.addActionListener(al);
		fileMenuCadastro.add(menuItem);

		// Item de menu cadastro
		menuItem = new JMenuItem("Origem de receita");
		menuItem.setMnemonic(KeyEvent.VK_R);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("receitas");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//receitas.png"));
		menuItem.addActionListener(al);
		fileMenuCadastro.add(menuItem);

		// Item de menu cadastro
		menuItem = new JMenuItem("Temporadas");
		menuItem.setMnemonic(KeyEvent.VK_O);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("temporadas");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//temporadas.png"));
		menuItem.addActionListener(al);
		fileMenuCadastro.add(menuItem);

		// Item de menu análise
		menuItem = new JMenuItem("Tempo de permanência");
		menuItem.setMnemonic(KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("tempoPermanencia");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//itens.png"));
		menuItem.addActionListener(al);
		fileMenuAnalise.add(menuItem);

		// Item de menu análise
		menuItem = new JMenuItem("Ocupaçao por segmento/classes de tarifas");
		menuItem.setMnemonic(KeyEvent.VK_O);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("ocupacaoSegClasTarifa");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//itens.png"));
		menuItem.addActionListener(al);
		fileMenuAnalise.add(menuItem);

		// Item de menu análise
		menuItem = new JMenuItem("Pricing");
		menuItem.setMnemonic(KeyEvent.VK_P);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("pricing");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//itens.png"));
		menuItem.addActionListener(al);
		fileMenuAnalise.add(menuItem);

		// Item de menu análise
		menuItem = new JMenuItem("Ocupação por restrições de tarifas");
		menuItem.setMnemonic(KeyEvent.VK_R);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("ocupacaoRestTarifas");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//itens.png"));
		menuItem.addActionListener(al);
		fileMenuAnalise.add(menuItem);

		// Item de menu análise
		menuItem = new JMenuItem("Overbooking");
		menuItem.setMnemonic(KeyEvent.VK_K);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("overbooking");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//itens.png"));
		menuItem.addActionListener(al);
		fileMenuAnalise.add(menuItem);

		// Item de menu análise
		menuItem = new JMenuItem("Rentabilidade por segmento");
		menuItem.setMnemonic(KeyEvent.VK_D);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("rentabilidade");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//itens.png"));
		menuItem.addActionListener(al);
		fileMenuAnalise.add(menuItem);

		// Item de menu análise
		menuItem = new JMenuItem("Consumo por segmento exceto diárias");
		menuItem.setMnemonic(KeyEvent.VK_G);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("consumo");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//itens.png"));
		menuItem.addActionListener(al);
		fileMenuAnalise.add(menuItem);

		// Item de menu análise
		menuItem = new JMenuItem("Estadia média por segmento");
		menuItem.setMnemonic(KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("estadia");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//itens.png"));
		menuItem.addActionListener(al);
		fileMenuAnalise.add(menuItem);

		// Item de menu análise
		menuItem = new JMenuItem("Previsão de demandas flutuantes");
		menuItem.setMnemonic(KeyEvent.VK_F);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("previsaoDemanda");
		menuItem.setIcon(new ImageIcon("src//gui//icones//menu//itens.png"));
		menuItem.addActionListener(al);
		fileMenuAnalise.add(menuItem);

		return menuBar;
	}

	ActionListener al = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// create internal frame
			if ("pessoas".equals(e.getActionCommand())) {
				ManterPessoaUI mpUI = new ManterPessoaUI();
				try {
					// mpUI = new ManterPessoaUI(desktop);
					desktop.add(mpUI.manterBaseUI(desktop));
					mpUI.pack();
					mpUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("hoteis".equals(e.getActionCommand())) {
				ManterEmpresaUI meUI = new ManterEmpresaUI();
				try {
					// mpUI = new ManterPessoaUI(desktop);
					desktop.add(meUI.manterBaseUI(desktop));
					meUI.pack();
					meUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("apartamentos".equals(e.getActionCommand())) {
				ManterTiposApartamentoUI meUI = new ManterTiposApartamentoUI();
				try {
					// mpUI = new ManterPessoaUI(desktop);
					desktop.add(meUI.manterBaseUI(desktop));
					meUI.pack();
					meUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("mercado".equals(e.getActionCommand())) {
				ManterMercadoUI mmUI = new ManterMercadoUI();
				try {
					desktop.add(mmUI.manterBaseUI(desktop));
					mmUI.pack();
					mmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("demandas".equals(e.getActionCommand())) {
				ManterDemandaUI dmUI = new ManterDemandaUI();
				try {
					desktop.add(dmUI.manterBaseUI(desktop));
					dmUI.pack();
					dmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("temporadas".equals(e.getActionCommand())) {
				ManterTemporadaUI tmUI = new ManterTemporadaUI();
				try {
					desktop.add(tmUI.manterBaseUI(desktop));
					tmUI.pack();
					tmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("receitas".equals(e.getActionCommand())) {
				ManterReceitaUI rmUI = new ManterReceitaUI();
				try {
					desktop.add(rmUI.manterBaseUI(desktop));
					rmUI.pack();
					rmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("canais".equals(e.getActionCommand())) {
				ManterCanaisUI cmUI = new ManterCanaisUI();
				try {
					desktop.add(cmUI.manterBaseUI(desktop));
					cmUI.pack();
					cmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("restricoes".equals(e.getActionCommand())) {
				ManterRestricoesUI rmUI = new ManterRestricoesUI();
				try {
					desktop.add(rmUI.manterBaseUI(desktop));
					rmUI.pack();
					rmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("classes".equals(e.getActionCommand())) {
				ManterClassesUI cmUI = new ManterClassesUI();
				try {
					desktop.add(cmUI.manterBaseUI(desktop));
					cmUI.pack();
					cmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("tarifas".equals(e.getActionCommand())) {
				ManterTarifaUI cmUI = new ManterTarifaUI();
				try {
					desktop.add(cmUI.manterBaseUI(desktop));
					cmUI.pack();
					cmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("tempoPermanencia".equals(e.getActionCommand())) {
				TempoDePermanenciaUI cmUI = new TempoDePermanenciaUI();
				try {
					desktop.add(cmUI.analiseBaseUI(desktop));
					cmUI.pack();
					cmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("ocupacaoSegClasTarifa".equals(e.getActionCommand())) {
				OcupacaoSegClasTarifaUI cmUI = new OcupacaoSegClasTarifaUI();
				try {
					desktop.add(cmUI.analiseBaseUI(desktop));
					cmUI.pack();
					cmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if ("sair".equals(e.getActionCommand())) {
				System.exit(0);
			}
		}
	};

	/*public JRibbon criarRibbonMenu() {
		JRibbon menuRibbon = new JRibbon();
		JRibbonBand band1 = new JRibbonBand("Hello", null);
		JRibbonBand band2 = new JRibbonBand("world!", null);
		
		JCommandButton button1 = new JCommandButton("Square", getResizableIconFromResource("src//gui//icones//barras//pessoas.png"));
		JCommandButton button2 = new JCommandButton("Circle", getResizableIconFromResource("src//gui//icones//barras//pessoas.png"));
		JCommandButton button3 = new JCommandButton("Triangle", getResizableIconFromResource("src//gui//icones//barras//pessoas.png"));
		JCommandButton button4 = new JCommandButton("Star", getResizableIconFromResource("src//gui//icones//barras//pessoas.png"));
		 
		band1.addCommandButton(button1, RibbonElementPriority.TOP);
		band1.addCommandButton(button2, RibbonElementPriority.MEDIUM);
		band1.addCommandButton(button3, RibbonElementPriority.MEDIUM);
		band1.addCommandButton(button4, RibbonElementPriority.MEDIUM);

		band1.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(band1.getControlPanel()),
				  new IconRibbonBandResizePolicy(band1.getControlPanel())));
		
		band2.setResizePolicies((List) Arrays
				.asList(new IconRibbonBandResizePolicy(band1.getControlPanel())));

		RibbonTask task1 = new RibbonTask("One", band1);
		RibbonTask task2 = new RibbonTask("Two", band2);

		menuRibbon.addTask(task1);
		menuRibbon.addTask(task2);

		return menuRibbon;

	}

	public ResizableIcon getResizableIconFromResource(String resource) {
		//ResizableIcon icon = ImageWrapperResizableIcon.getIcon(PrincipalClienteUI.class.getClassLoader().getResource(resource), new Dimension(48, 48));	
		//return icon;
		Image image;
		try {
			image = ImageIO.read(new File(resource));
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
		final int height = image.getHeight(null);
		final int width = image.getWidth(null);
		final ResizableIcon resizeIcon = ImageWrapperResizableIcon.getIcon(
				image, new Dimension(width, height));
		return resizeIcon;
		
	}*/

	// ocupacaoSegClasTarifa
	public void createAndShowGUI() {
		PrincipalClienteUI myParentFrame = new PrincipalClienteUI();
		myParentFrame.setVisible(true);
	}
}