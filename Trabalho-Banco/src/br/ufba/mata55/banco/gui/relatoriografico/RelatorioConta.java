package br.ufba.mata55.banco.gui.relatoriografico;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.ufba.mata55.banco.data.bean.RelatorioContaBean;
import br.ufba.mata55.banco.data.dao.ContaDAO;

public class RelatorioConta extends BasicRelatorioGraficoForm {
	private static final long serialVersionUID = -3846056298400856953L;

	public RelatorioConta(Component parent) {
		super(parent, "Relatório de Contas");
	}

	/**
	 * Constroi o relatório
	 */
	@Override
	protected Component generateContent(JPanel parent) {
		JTable tableMovimentacao = new JTable();
		JScrollPane scrollPaneTabelaMovimentacao = new JScrollPane();
		int i = 0, c = 0;
		String columnNames[] = new String[] {"Conta", "Limite", "Saldo", "É especial?", "Criada em", "Status", "Movimentações"};
		
		ContaDAO cdao = new ContaDAO();
		List<RelatorioContaBean> relatorioConta = cdao.relatorioContas();
		Object data[][] = new Object[relatorioConta.size()][columnNames.length];
		for (RelatorioContaBean relatorioContaBean : relatorioConta) {
			data[i][c++] = relatorioContaBean.getNUMERO();
			data[i][c++] = String.format("R$ %.2f", relatorioContaBean.getLIMITE());
			data[i][c++] = String.format("R$ %.2f", relatorioContaBean.getSALDO());
			data[i][c++] = relatorioContaBean.getESPECIAL();
			data[i][c++] = relatorioContaBean.getDATA_CRIACAO();
			data[i][c++] = relatorioContaBean.getSTATUS();
			data[i][c++] = relatorioContaBean.getMOVIMENTACOES();
			c = 0;
			i++;
		}
		
		tableMovimentacao.setModel(new DefaultTableModel(data, columnNames));
		scrollPaneTabelaMovimentacao.setViewportView(tableMovimentacao);
		scrollPaneTabelaMovimentacao.setPreferredSize(new Dimension(parent.getWidth() - 4, parent.getHeight() - 4));
		scrollPaneTabelaMovimentacao.scrollRectToVisible(tableMovimentacao.getCellRect(tableMovimentacao.getRowCount() - 1, 0, true));
		return scrollPaneTabelaMovimentacao;
	}
}
