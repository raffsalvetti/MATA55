package br.ufba.mata55.banco.gui.relatoriografico;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import br.ufba.mata55.banco.data.bean.RelatorioLucroBean;
import br.ufba.mata55.banco.data.dao.MovimentacaoDAO;
import br.ufba.mata55.banco.data.po.TipoMovimentacao;

/**
 * Classe que renderiza um grafico mostrando o lucro do banco com as taxas cobradas por tipo de operações
 * @author raffaello.salvetti
 *
 */
public class GraficoValorMovimentacao extends BasicRelatorioGraficoForm {

	private static final long serialVersionUID = -5675763752675309462L;
	private static final String titulo = "Gráfico de Valor de Movimentação por Contas";

	/**
	 * Construtor que recebe um formulário pai e a lista de contas cadastradas no sistema
	 * @param parent Formulário pai
	 */
	public GraficoValorMovimentacao(Component parent) {
		super(parent, titulo);
	}

	/**
	 * Constroi o gráfico
	 */
	@Override
	protected Component generateContent(JPanel parent) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		MovimentacaoDAO mdao = new MovimentacaoDAO();
		List<RelatorioLucroBean> relatorioLucroBeans = mdao.graficoMovimentacao();
		
		for (RelatorioLucroBean relatorioLucroBean : relatorioLucroBeans) {
			TipoMovimentacao tm = TipoMovimentacao.values()[Integer.parseInt(relatorioLucroBean.getTIPO())];
			dataset.setValue(relatorioLucroBean.getVALOR(), tm.getLabel(), relatorioLucroBean.getNUMERO());
		}
		
		JFreeChart chart = ChartFactory.createBarChart(
				titulo,
				"Número das contas",
				"Valor da movimentação (R$)",
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false);


		ChartPanel cp = new ChartPanel(chart);
		cp.setPreferredSize(new Dimension(parent.getWidth() - 6, parent.getHeight() - 6));
		return cp;
	}
}
