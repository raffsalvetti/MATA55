package br.ufba.mata55.banco.gui.relatoriografico;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import br.ufba.mata55.banco.data.bean.RelatorioOperacaoBean;
import br.ufba.mata55.banco.data.dao.MovimentacaoDAO;
import br.ufba.mata55.banco.data.po.TipoMovimentacao;

/**
 * Classe que renderiza um grafico mostrando a distribuição dos tipos de operações
 * @author raffaello.salvetti
 *
 */
public class GraficoTipoMovimentacao extends BasicRelatorioGraficoForm {

	private static final long serialVersionUID = -5675763752675309462L;

	private static final String titulo = "Gráfico de Movimentações";

	/**
	 * Construtor que recebe um formulário pai e a lista de contas cadastradas no sistema
	 * @param parent Formulário pai
	 */
	public GraficoTipoMovimentacao(Component parent) {
		super(parent, titulo);
	}

	/**
	 * Constroi o gráfico
	 */
	@Override
	protected Component generateContent(JPanel parent) {
		DefaultPieDataset dataset = new DefaultPieDataset( );
		MovimentacaoDAO mdao = new MovimentacaoDAO();
		List<RelatorioOperacaoBean> relatorioOperacaoBeans = mdao.relatorioOperacoes();
		for (RelatorioOperacaoBean relatorioOperacaoBean : relatorioOperacaoBeans) {
			dataset.setValue(TipoMovimentacao.values()[relatorioOperacaoBean.getCOD_TIPO_MOVIMENTACAO()], relatorioOperacaoBean.getQUANTIDADE());
		}

		JFreeChart chart = ChartFactory.createPieChart(
				titulo,
				dataset,
				true, true, false);
		ChartPanel cp = new ChartPanel(chart);
		cp.setPreferredSize(new Dimension(parent.getWidth() - 6, parent.getHeight() - 6));
		return cp;
	}
}
