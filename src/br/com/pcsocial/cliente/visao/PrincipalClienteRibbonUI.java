package br.com.pcsocial.cliente.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import br.com.pcsocial.cliente.util.DecoratedDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;
import org.pushingpixels.flamingo.api.ribbon.resize.RibbonBandResizePolicy;

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

public class PrincipalClienteRibbonUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private DecoratedDesktopPane desktop;
	private JCommandButton cbHoteis, cbTiposAptos, cbPessoa, cbSegMercado,
			cbTarifas, cbClassesTar, cbRestricoesTar, cbCanais, cbDemandas,
			cbOrigem, cbTemporadas, cbTemp, cbTempPerm, cbOcupSeg, cbPricing,
			cbOcupRest, cbOver, cbRentSeg, cbConsSeg, cbEstadiaMed, cdPreviDem;
	private JRibbon menuRibbon;
	private JRibbonBand menuCadastroHoteis, menuCadastroTarifas,
			menuCadastroPessoas, menuAnalise, menuRelatorios, menuParametros,
			menuAjuda;
	private RibbonTask tsCadastro, tsAnalise, tsRelatorios, tsParametros,
			tsAjuda;
	private JMenuBar menu;
	private JMenuItem menuItem;

	public PrincipalClienteRibbonUI() {
		super("Gestão de receitas");

		// inicializar componentes
		menu = new JMenuBar();
		menuItem = new JMenuItem();

		JMenu fileMenuSistema = new JMenu("Sistema");
		fileMenuSistema.setMnemonic(KeyEvent.VK_S);
		fileMenuSistema.setIcon(new ImageIcon(getClass().getResource(
				"/gui/icones/menu/parametros.png")));

		menu.add(fileMenuSistema);

		menuItem = new JMenuItem("Sair");
		menuItem.setMnemonic(KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.ALT_MASK));
		menuItem.setActionCommand("sair");
		menuItem.setIcon(new ImageIcon(getClass().getResource("/gui/icones/menu/sair.png")));
		menuItem.addActionListener(al);
		fileMenuSistema.add(menuItem);

		this.setJMenuBar(menu);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Abrir Miximizado
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension screenMax = new Dimension(screenSize.width, screenSize.height);

		this.setPreferredSize(screenMax);
		desktop = new DecoratedDesktopPane("/gui/fundo/fundo.png");
		this.add(criarRibbonMenu(), BorderLayout.NORTH);
		this.add(desktop, BorderLayout.CENTER);
		this.pack();
		this.setSize(screenMax);
		this.setLocationRelativeTo(null);
		desktop.setBackground(Color.WHITE);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JRibbon criarRibbonMenu() {

		// Criar Abas
		menuRibbon = new JRibbon();
		menuCadastroHoteis = new JRibbonBand("Hotéis", null);
		menuCadastroTarifas = new JRibbonBand("Tarifas", null);
		menuCadastroPessoas = new JRibbonBand("Pessoas", null);
		menuAnalise = new JRibbonBand("Gráficos e indicadores", null);
		menuRelatorios = new JRibbonBand("", null);
		menuParametros = new JRibbonBand("", null);
		menuAjuda = new JRibbonBand("", null);

		// Botoes menu cadastro
		cbHoteis = new JCommandButton(
				"Hotéis",
				getResizableIconFromResource("/gui/icones/barras/hoteis.png"));
		cbTiposAptos = new JCommandButton(
				"Tipos de apartamento",
				getResizableIconFromResource("/gui/icones/barras/apartamento.png"));
		cbPessoa = new JCommandButton(
				"Pessoas",
				getResizableIconFromResource("/gui/icones/barras/pessoas.png"));
		cbSegMercado = new JCommandButton(
				"Seg. de mercado",
				getResizableIconFromResource("/gui/icones/barras/mercado.png"));
		cbTarifas = new JCommandButton(
				"Tarifas",
				getResizableIconFromResource("/gui/icones/barras/tarifas.png"));
		cbClassesTar = new JCommandButton(
				"Clas. de tarifas",
				getResizableIconFromResource("/gui/icones/menu/classes.png"));
		cbRestricoesTar = new JCommandButton(
				"Rest. de tarifas",
				getResizableIconFromResource("/gui/icones/menu/restricoes.png"));
		cbCanais = new JCommandButton(
				"Canais de distribuição",
				getResizableIconFromResource("/gui/icones/menu/canais.png"));
		cbDemandas = new JCommandButton(
				"Demandas flutuantes",
				getResizableIconFromResource("/gui/icones/menu/demandas.png"));
		cbOrigem = new JCommandButton(
				"Origem de receitas hospedagens",
				getResizableIconFromResource("/gui/icones/menu/receitas.png"));
		cbTemporadas = new JCommandButton(
				"Temporadas",
				getResizableIconFromResource("/gui/icones/barras/temporadas.png"));
		// Temp
		cbTemp = new JCommandButton("Temp");

		// Botoes do menu Análise
		cbTempPerm = new JCommandButton(
				"Tempo de permandência",
				getResizableIconFromResource("/gui/icones/barras/tempoPerm.png"));
		cbOcupSeg = new JCommandButton(
				"Ocupação por segmento",
				getResizableIconFromResource("/gui/icones/barras/ocupSeg.png"));
		cbPricing = new JCommandButton(
				"Princing",
				getResizableIconFromResource("/gui/icones/barras/pricing.png"));
		cbOcupRest = new JCommandButton(
				"Ocupação por restrições de tarifas",
				getResizableIconFromResource("/gui/icones/barras/restricoes.png"));
		cbOver = new JCommandButton(
				"Overbooking",
				getResizableIconFromResource("/gui/icones/barras/over.png"));
		cbRentSeg = new JCommandButton(
				"Rentabilidade por seguimento",
				getResizableIconFromResource("/gui/icones/barras/rentabilidade.png"));
		cbConsSeg = new JCommandButton(
				"Consumo por seguimento",
				getResizableIconFromResource("/gui/icones/barras/consumo.png"));
		cbEstadiaMed = new JCommandButton(
				"Estadia média por segmento",
				getResizableIconFromResource("/gui/icones/barras/estadia.png"));
		cdPreviDem = new JCommandButton(
				"Previsão de demandas flutuantes",
				getResizableIconFromResource("/gui/icones/barras/demandas.png"));
		
		
		
		// Eventos botoes
		cbHoteis.addActionListener(al);
		cbTiposAptos.addActionListener(al);
		cbPessoa.addActionListener(al);
		cbSegMercado.addActionListener(al);
		cbTarifas.addActionListener(al);
		cbClassesTar.addActionListener(al);
		cbRestricoesTar.addActionListener(al);
		cbCanais.addActionListener(al);
		cbDemandas.addActionListener(al);
		cbOrigem.addActionListener(al);
		cbTemporadas.addActionListener(al);
		
		cbTempPerm.addActionListener(al);
		cbOcupSeg.addActionListener(al);
		cbOcupRest.addActionListener(al);
		cbPricing.addActionListener(al);
		cbOver.addActionListener(al);
		cbRentSeg.addActionListener(al);
		cbConsSeg.addActionListener(al);
		cbEstadiaMed.addActionListener(al);
		cdPreviDem.addActionListener(al);

		// Adicionar botoes ao menu cadastro
		menuCadastroHoteis
				.addCommandButton(cbHoteis, RibbonElementPriority.TOP);
		menuCadastroHoteis.addCommandButton(cbTiposAptos,
				RibbonElementPriority.TOP);

		menuCadastroPessoas.addCommandButton(cbPessoa,
				RibbonElementPriority.TOP);
		menuCadastroPessoas.addCommandButton(cbSegMercado,
				RibbonElementPriority.TOP);

		menuCadastroTarifas.addCommandButton(cbTarifas,
				RibbonElementPriority.TOP);
		menuCadastroTarifas.addCommandButton(cbClassesTar,
				RibbonElementPriority.MEDIUM);
		menuCadastroTarifas.addCommandButton(cbRestricoesTar,
				RibbonElementPriority.MEDIUM);
		menuCadastroTarifas.addCommandButton(cbCanais,
				RibbonElementPriority.MEDIUM);
		menuCadastroTarifas.addCommandButton(cbDemandas,
				RibbonElementPriority.MEDIUM);
		menuCadastroTarifas.addCommandButton(cbOrigem,
				RibbonElementPriority.MEDIUM);
		menuCadastroTarifas.addCommandButton(cbTemporadas,
				RibbonElementPriority.TOP);
		
		//Adicionar botoes ao menu análise
		menuAnalise.addCommandButton(cbTempPerm, RibbonElementPriority.TOP);
		menuAnalise.addCommandButton(cbOcupSeg, RibbonElementPriority.TOP);
		menuAnalise.addCommandButton(cbOcupRest, RibbonElementPriority.TOP);
		menuAnalise.addCommandButton(cbPricing, RibbonElementPriority.TOP);
		menuAnalise.addCommandButton(cbOver, RibbonElementPriority.TOP);
		menuAnalise.addCommandButton(cbRentSeg, RibbonElementPriority.TOP);
		menuAnalise.addCommandButton(cbConsSeg, RibbonElementPriority.TOP);
		menuAnalise.addCommandButton(cbEstadiaMed, RibbonElementPriority.TOP);
		menuAnalise.addCommandButton(cdPreviDem, RibbonElementPriority.TOP);
		
		
		menuRelatorios.addCommandButton(cbTemp, RibbonElementPriority.TOP);
		menuParametros.addCommandButton(cbTemp, RibbonElementPriority.TOP);
		menuAjuda.addCommandButton(cbTemp, RibbonElementPriority.TOP);

		// Definição de icones
		menuCadastroHoteis.setResizePolicies(Arrays
				.<RibbonBandResizePolicy> asList(
						new CoreRibbonResizePolicies.Mid2Mid(menuCadastroHoteis
								.getControlPanel()),
						new IconRibbonBandResizePolicy(menuCadastroHoteis
								.getControlPanel())));

		menuCadastroPessoas.setResizePolicies(Arrays
				.<RibbonBandResizePolicy> asList(
						new CoreRibbonResizePolicies.Mid2Mid(
								menuCadastroPessoas.getControlPanel()),
						new IconRibbonBandResizePolicy(menuCadastroPessoas
								.getControlPanel())));

		menuCadastroTarifas.setResizePolicies(Arrays
				.<RibbonBandResizePolicy> asList(
						new CoreRibbonResizePolicies.Mid2Mid(
								menuCadastroTarifas.getControlPanel()),
						new IconRibbonBandResizePolicy(menuCadastroTarifas
								.getControlPanel())));

		menuAnalise.setResizePolicies(Arrays.<RibbonBandResizePolicy> asList(
				new CoreRibbonResizePolicies.Mid2Mid(menuAnalise
						.getControlPanel()), new IconRibbonBandResizePolicy(
						menuAnalise.getControlPanel())));

		menuRelatorios.setResizePolicies(Arrays
				.<RibbonBandResizePolicy> asList(
						new CoreRibbonResizePolicies.Mid2Mid(menuRelatorios
								.getControlPanel()),
						new IconRibbonBandResizePolicy(menuRelatorios
								.getControlPanel())));
		menuParametros.setResizePolicies(Arrays
				.<RibbonBandResizePolicy> asList(
						new CoreRibbonResizePolicies.Mid2Mid(menuParametros
								.getControlPanel()),
						new IconRibbonBandResizePolicy(menuParametros
								.getControlPanel())));
		menuAjuda.setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.Mid2Mid(menuAjuda
						.getControlPanel()), new IconRibbonBandResizePolicy(
						menuAjuda.getControlPanel())));

		tsCadastro = new RibbonTask("Cadastro", menuCadastroHoteis,
				menuCadastroPessoas, menuCadastroTarifas);

		tsAnalise = new RibbonTask("Análises", menuAnalise);
		tsRelatorios = new RibbonTask("Relatórios", menuRelatorios);
		tsParametros = new RibbonTask("Parâmetros", menuParametros);
		tsAjuda = new RibbonTask("Ajuda", menuAjuda);

		menuRibbon.addTask(tsCadastro);
		menuRibbon.addTask(tsAnalise);
		menuRibbon.addTask(tsRelatorios);
		menuRibbon.addTask(tsParametros);
		menuRibbon.addTask(tsAjuda);

		return menuRibbon;

	}

	ActionListener al = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// create internal frame
			if (e.getSource().equals(cbPessoa)) {
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
			if (e.getSource().equals(cbHoteis)) {
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
			if (e.getSource().equals(cbTiposAptos)) {
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
			if (e.getSource().equals(cbSegMercado)) {
				ManterMercadoUI mmUI = new ManterMercadoUI();
				try {
					desktop.add(mmUI.manterBaseUI(desktop));
					mmUI.pack();
					mmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbDemandas)) {
				ManterDemandaUI dmUI = new ManterDemandaUI();
				try {
					desktop.add(dmUI.manterBaseUI(desktop));
					dmUI.pack();
					dmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbTemporadas)) {
				ManterTemporadaUI tmUI = new ManterTemporadaUI();
				try {
					desktop.add(tmUI.manterBaseUI(desktop));
					tmUI.pack();
					tmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbOrigem)) {
				ManterReceitaUI rmUI = new ManterReceitaUI();
				try {
					desktop.add(rmUI.manterBaseUI(desktop));
					rmUI.pack();
					rmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbCanais)) {
				ManterCanaisUI cmUI = new ManterCanaisUI();
				try {
					desktop.add(cmUI.manterBaseUI(desktop));
					cmUI.pack();
					cmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbRestricoesTar)) {
				ManterRestricoesUI rmUI = new ManterRestricoesUI();
				try {
					desktop.add(rmUI.manterBaseUI(desktop));
					rmUI.pack();
					rmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbClassesTar)) {
				ManterClassesUI cmUI = new ManterClassesUI();
				try {
					desktop.add(cmUI.manterBaseUI(desktop));
					cmUI.pack();
					cmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbTarifas)) {
				ManterTarifaUI cmUI = new ManterTarifaUI();
				try {
					desktop.add(cmUI.manterBaseUI(desktop));
					cmUI.pack();
					cmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbTempPerm)) {
				TempoDePermanenciaUI cmUI = new TempoDePermanenciaUI();
				try {
					desktop.add(cmUI.analiseBaseUI(desktop));
					cmUI.pack();
					cmUI.setVisible(true);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource().equals(cbOcupSeg)) {
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

	public ResizableIcon getResizableIconFromResource(String resource) {
		Image image;
		try {
			image = new ImageIcon(getClass().getResource(resource)).getImage();
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
		final int height = image.getHeight(null);
		final int width = image.getWidth(null);
		final ResizableIcon resizeIcon = ImageWrapperResizableIcon.getIcon(
				image, new Dimension(width, height));
		return resizeIcon;

	}

	// ocupacaoSegClasTarifa
	public void createAndShowGUI() {
		PrincipalClienteRibbonUI myParentFrame = new PrincipalClienteRibbonUI();
		myParentFrame.setVisible(true);
	}
}