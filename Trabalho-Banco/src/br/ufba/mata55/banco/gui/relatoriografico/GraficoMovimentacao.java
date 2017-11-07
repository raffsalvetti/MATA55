package br.ufba.mata55.banco.gui.relatoriografico;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import br.ufba.mata55.banco.data.po.Conta;
import br.ufba.mata55.banco.data.po.Movimentacao;
import br.ufba.mata55.banco.data.po.TipoMovimentacao;

/**
 * Classe que renderiza um grafico mostrando a distribuição dos tipos de operações
 * @author raffaello.salvetti
 *
 */
public class GraficoMovimentacao extends BasicRelatorioGraficoForm {

	private static final long serialVersionUID = -5675763752675309462L;

	private List<Conta> contas;
	private static final String titulo = "Gráfico de Movimentações";

	/**
	 * Construtor que recebe um formulário pai e a lista de contas cadastradas no sistema
	 * @param parent Formulário pai
	 * @param contas Contas cadastradas no sistema
	 */
	public GraficoMovimentacao(Component parent, List<Conta> contas) {
		super(parent, titulo);
		this.contas = contas;
	}

	/**
	 * Constroi o gráfico
	 */
	@Override
	protected Component generateContent(JPanel parent) {
		int contadores[] = new int[TipoMovimentacao.values().length];
		for (Conta conta : contas) {
			for (Movimentacao movimentacao : conta.getListMovimentacao()) {
				contadores[movimentacao.getTipoMovimentacao().ordinal()]++;
			}
		}

		DefaultPieDataset dataset = new DefaultPieDataset( );
		for (TipoMovimentacao tm : TipoMovimentacao.values()) {
			dataset.setValue(TipoMovimentacao.values()[tm.ordinal()], contadores[tm.ordinal()]);
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
