package br.com.pcsocial.cliente.visao.base;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.Rotation;

import br.com.pcsocial.cliente.util.JDateChooser;

public class AnaliseBaseUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel, panelSuperior, panelSupBotoes, panelInferior,
			panelPeriodo, panelTipoGrafico;
	private BorderLayout layout;
	private GridLayout layoutSup;
	private FlowLayout layoutSupBotoes;
	private FlowLayout layoutInf;
	private JButton cbSalvar;
	private JButton cbCancelar;
	private JButton cbGerarGrafico;
	private JFreeChart chart;
	private JDesktopPane father;
	private JCheckBox jcPizza, jcBarras, jcLinear;
	private JDateChooser jDataInicial, jDataFinal;
	private JLabel lbDataInicial, lbDataFinal;
	private Dimension dmsLabel, dmsEditDois;

	private JScrollPane scrollPane;

	public AnaliseBaseUI() {
		super("", true, true, true, true);
		super.setTitle(getTitulo());
	}

	public JInternalFrame analiseBaseUI(JDesktopPane father)
			throws RemoteException {
		this.father = father;

		// Propriedades da Janela
		Dimension screenSize = father.getSize();
		Dimension screenMax = new Dimension(screenSize.width, screenSize.height);
		this.setPreferredSize(screenMax);
		this.setSize(screenMax);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Intanciar componentes
		dmsLabel = new Dimension(140, 27);
		dmsEditDois = new Dimension(150, 27);
		panel = new JPanel();
		panelSuperior = new JPanel();
		panelInferior = new JPanel();
		panelSupBotoes = new JPanel();
		panelPeriodo = new JPanel();
		panelTipoGrafico = new JPanel();

		layout = new BorderLayout();
		layoutSup = new GridLayout(2, 1);
		layoutSupBotoes = new FlowLayout(FlowLayout.LEFT);
		layoutInf = new FlowLayout(FlowLayout.LEFT);
		cbSalvar = new JButton("Exportar gráfico");
		cbCancelar = new JButton("Cancelar");
		cbGerarGrafico = new JButton("Gerar gráfico");
		scrollPane = new JScrollPane();

		// Instanciar Label
		lbDataInicial = new JLabel("Data inicial", SwingConstants.RIGHT);
		lbDataInicial.setPreferredSize(dmsLabel);
		lbDataFinal = new JLabel("Data final", SwingConstants.RIGHT);
		lbDataFinal.setPreferredSize(dmsLabel);

		// Instanciar Editores
		jDataInicial = new JDateChooser();
		jDataInicial.setPreferredSize(dmsEditDois);
		jDataFinal = new JDateChooser();
		jDataFinal.setPreferredSize(dmsEditDois);

		// Instanciar Check-box
		jcPizza = new JCheckBox("Pizza");
		jcBarras = new JCheckBox("Barras");
		jcLinear = new JCheckBox("Linear");

		// Propriedade check-box
		jcBarras.addActionListener(al);
		jcPizza.addActionListener(al);
		jcLinear.addActionListener(al);

		// Propriedades botoes
		cbSalvar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/salvar.png")));
		// cbSalvar.setVerticalTextPosition(JButton.BOTTOM);
		// cbSalvar.setHorizontalTextPosition(JButton.CENTER);
		cbSalvar.addActionListener(al);

		cbCancelar.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/cancelar.png")));
		// cbCancelar.setVerticalTextPosition(JButton.BOTTOM);
		// cbCancelar.setHorizontalTextPosition(JButton.CENTER);
		cbCancelar.addActionListener(al);

		cbGerarGrafico.setIcon(new ImageIcon(
				getClass().getResource("/gui/icones/acoes/gerarGrafico.png")));
		// cbGerarGrafico.setHorizontalTextPosition(JButton.RIGHT);
		cbGerarGrafico.addActionListener(al);

		panel.setLayout(layout);
		panel.setPreferredSize(new java.awt.Dimension(500, 270));
		panelSuperior.setLayout(layoutSup);
		panelSupBotoes.setLayout(layoutSupBotoes);
		panelInferior.setLayout(layoutInf);
		panelPeriodo.setLayout(layoutInf);
		panelPeriodo.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Selecione o perído"));
		panelTipoGrafico.setLayout(layoutInf);
		panelTipoGrafico.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Selecione o tipo do gráfico"));

		// Adicionar componentes aos paineis
		panelPeriodo.add(lbDataInicial);
		panelPeriodo.add(jDataInicial);
		panelPeriodo.add(lbDataFinal);
		panelPeriodo.add(jDataFinal);
		panelTipoGrafico.add(jcPizza);
		panelTipoGrafico.add(jcBarras);
		panelTipoGrafico.add(jcLinear);
		panelSupBotoes.add(cbGerarGrafico);

		// panelSuperior
		panelSuperior.add(panelPeriodo);
		panelSuperior.add(panelTipoGrafico);
		panelSuperior.add(panelSupBotoes);

		panelInferior.add(cbSalvar);
		panelInferior.add(cbCancelar);

		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(panelSuperior, BorderLayout.NORTH);
		panel.add(panelInferior, BorderLayout.SOUTH);
		panel.setVisible(true);

		this.add(panel);

		return this;

	}

	public void gerarGraficoPizza() {
		PieDataset dataset = createPieDataset();
		// based on the dataset we create the chart
		chart = createChartPizza(dataset, getTituloGrafico());
		// we put the chart into a panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// add it to our application
		// setContentPane(chartPanel);
		scrollPane.setViewportView(chartPanel);

	}
	
	public void gerarGraficoXY() {
		XYSeriesCollection dataset = createXYDataset(getNomeSerie());
		// based on the dataset we create the chart
		chart = createChartLinear(getTituloGrafico(), getLabelX(), getLabelY(), dataset);
		// we put the chart into a panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// add it to our application
		// setContentPane(chartPanel);
		scrollPane.setViewportView(chartPanel);

	}
	
	public void gerarGraficoBarra() {
		CategoryDataset dataset = createBarDataset(getNomeSerie());
		// based on the dataset we create the chart
		chart = createChartBarra(getTituloGrafico(), getLabelY(), getLabelX(), dataset);
		// we put the chart into a panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// add it to our application
		// setContentPane(chartPanel);
		scrollPane.setViewportView(chartPanel);

	}

	public PieDataset createPieDataset() {
		// TODO
		return null;
	}
	
	public XYSeriesCollection createXYDataset(String nomeSerie) {
		// TODO
		return null;
	}
	
	public CategoryDataset createBarDataset(String nomeSerie) {
		// TODO
		return null;
	}

	private JFreeChart createChartPizza(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart3D(
				title, // chart title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		//plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;

	}
	
	private JFreeChart createChartLinear(String title,String labelX,String labelY,XYSeriesCollection dataset) {
		JFreeChart chart = ChartFactory.createXYLineChart(
				title, // Title
				labelX, // x-axis Label
				labelY, // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);

		//XYPlot plot = (XYPlot) chart.getPlot();
		//plot.setForegroundAlpha(0.5f);
		return chart;

	}
	
	private JFreeChart createChartBarra(String title,String labelX,String labelY,CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart3D(
				title, // Title
				labelX, // x-axis Label
				labelY, // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);

		//XYPlot plot = (XYPlot) chart.getPlot();
		//plot.setForegroundAlpha(0.5f);
		return chart;

	}


	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(cbSalvar)) {
				try {
					chart.isNotify();
					try {
						JFileChooser jFileChooser1 = new JFileChooser();
						jFileChooser1.setApproveButtonText("Selecionar");
						jFileChooser1
								.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int returnVal = jFileChooser1.showDialog(father, null);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							try {
								ChartUtilities.saveChartAsJPEG(new File(
										jFileChooser1.getSelectedFile()
												.getAbsolutePath()
												+ "//"
												+ nomeArquivoSalvo()), chart,
										500, 300);
								javax.swing.JOptionPane
										.showMessageDialog(
												null,
												"Seu gráfico foi salvo! \n"
														+ jFileChooser1
																.getSelectedFile()
																.getAbsolutePath()
														+ "//"
														+ nomeArquivoSalvo(),
												"Informação",
												0,
												new ImageIcon(
														getClass().getResource("/gui/icones/acoes/informacao.png")));
							} catch (Exception e1) {
								ChartUtilities.saveChartAsJPEG(new File(
										jFileChooser1.getSelectedFile()
												.getAbsolutePath()
												+ "\\"
												+ nomeArquivoSalvo()), chart,
										500, 300);
								javax.swing.JOptionPane
										.showMessageDialog(
												null,
												"Seu gráfico foi salvo! \n"
														+ jFileChooser1
																.getSelectedFile()
																.getAbsolutePath()
														+ "\\"
														+ nomeArquivoSalvo(),
												"Informação",
												0,
												new ImageIcon(
														getClass().getResource("/gui/icones/acoes/informacao.png")));
							}

						}
					} catch (Exception e2) {
						javax.swing.JOptionPane
								.showMessageDialog(
										null,
										"Problema ao salvar gráfico",
										"Informação",
										0,
										new ImageIcon(
												getClass().getResource("/gui/icones/acoes//informacao.png")));
					}

				} catch (NullPointerException e3) {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Gere um gráfico antes de salvar", "Informação", 0,
							new ImageIcon(
									getClass().getResource("/gui/icones/acoes/informacao.png")));
				}
			}
			if (e.getSource().equals(cbCancelar)) {
				dispose();
			}
			if (e.getSource().equals(cbGerarGrafico)) {
				if (jcPizza.isSelected()){
					gerarGraficoPizza();
				} 
				if (jcLinear.isSelected()) {
					gerarGraficoXY();
				} 
				if (jcBarras.isSelected()){
					gerarGraficoBarra();
				}
				if ((!jcPizza.isSelected()) && (!jcLinear.isSelected()) && (!jcBarras.isSelected())) {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Selecione o tipo de gráfico", "Informação", 0,
							new ImageIcon(
									getClass().getResource("/gui/icones/acoes/informacao.png")));
				}
			}
			if (e.getSource().equals(jcPizza)) {
				if (jcPizza.isSelected()) {
					jcBarras.setSelected(false);
					jcLinear.setSelected(false);
				}
			}
			if (e.getSource().equals(jcBarras)) {
				if (jcBarras.isSelected()) {
					jcPizza.setSelected(false);
					jcLinear.setSelected(false);
				}
			}
			if (e.getSource().equals(jcLinear)) {
				if (jcLinear.isSelected()) {
					jcPizza.setSelected(false);
					jcBarras.setSelected(false);
				}
			}
		}
	};

	public String getTitulo() {
		return null;
	}

	public String getTituloGrafico() {
		return null;
	}
	
	public String getLabelX() {
		return null;
	}
	
	public String getLabelY() {
		return null;
	}

	public String getNomeSerie(){
		return null;
	}
	public String nomeArquivoSalvo() {
		return null;
	}

	public JDateChooser getjDataInicial() {
		return jDataInicial;
	}

	public void setjDataInicial(JDateChooser jDataInicial) {
		this.jDataInicial = jDataInicial;
	}

	public JDateChooser getjDataFinal() {
		return jDataFinal;
	}

	public void setjDataFinal(JDateChooser jDataFinal) {
		this.jDataFinal = jDataFinal;
	}
	
}
