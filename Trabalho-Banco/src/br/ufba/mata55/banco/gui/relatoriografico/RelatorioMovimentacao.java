package br.ufba.mata55.banco.gui.relatoriografico;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.ufba.mata55.banco.data.bean.RelatorioMovimentacaoBean;
import br.ufba.mata55.banco.data.dao.ContaDAO;
import br.ufba.mata55.banco.data.po.TipoMovimentacao;

/**
 * Classe que renderiza um relatório mostrando todos os movimentos do banco
 * @author raffaello.salvetti
 *
 */
public class RelatorioMovimentacao extends BasicRelatorioGraficoForm {

	private static final long serialVersionUID = -6512541402466001365L;
	
	/**
	 * Construtor que recebe um formulário pai e a lista de contas cadastradas no sistema
	 * @param parent Formulário pai
	 */
	public RelatorioMovimentacao(Component parent) {
		super(parent, "Relatório de Movimentações");
	}

	/**
	 * Constroi o relatório
	 */
	@Override
	protected Component generateContent(JPanel parent) {
		JTable tableMovimentacao = new JTable();
		JScrollPane scrollPaneTabelaMovimentacao = new JScrollPane();
		int i = 0, c = 0;
		String columnNames[] = new String[] {"Conta", "Tipo de Movimentação", "Data", "Descrição", "Valor"};
		
		ContaDAO cdao = new ContaDAO();
		List<RelatorioMovimentacaoBean> relatorioMovimentacao = cdao.relatorioMovimentacoes();
		Object data[][] = new Object[relatorioMovimentacao.size()][columnNames.length];
		for (RelatorioMovimentacaoBean relatorioMovimentacaoBean : relatorioMovimentacao) {
			data[i][c++] = relatorioMovimentacaoBean.getCONTA();
			data[i][c++] = TipoMovimentacao.values()[relatorioMovimentacaoBean.getCOD_TIPO_MOVIMENTACAO()].getLabel();
			data[i][c++] = relatorioMovimentacaoBean.getDATA();
			if(relatorioMovimentacaoBean.getDESCRICAO_ADICIONAL() == null || relatorioMovimentacaoBean.getDESCRICAO_ADICIONAL().isEmpty()){
				data[i][c++] = relatorioMovimentacaoBean.getDESCRICAO();	
			} else {
				data[i][c++] = relatorioMovimentacaoBean.getDESCRICAO() + " - " + relatorioMovimentacaoBean.getDESCRICAO_ADICIONAL();
			}
			data[i][c++] = String.format("R$ %.2f", relatorioMovimentacaoBean.getVALOR());
			c = 0;
			i++;
		}
		
		tableMovimentacao.setModel(new DefaultTableModel(data, columnNames));
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableMovimentacao.getModel());
		tableMovimentacao.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		 
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		 
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		
		scrollPaneTabelaMovimentacao.setViewportView(tableMovimentacao);
		scrollPaneTabelaMovimentacao.setPreferredSize(new Dimension(parent.getWidth() - 4, parent.getHeight() - 4));
		scrollPaneTabelaMovimentacao.scrollRectToVisible(tableMovimentacao.getCellRect(tableMovimentacao.getRowCount() - 1, 0, true));
		return scrollPaneTabelaMovimentacao;
	}
}
