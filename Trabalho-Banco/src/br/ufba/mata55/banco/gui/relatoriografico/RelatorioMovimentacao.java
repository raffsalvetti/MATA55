package br.ufba.mata55.banco.gui.relatoriografico;

import java.awt.Component;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
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

import br.ufba.mata55.banco.data.po.Conta;
import br.ufba.mata55.banco.data.po.Movimentacao;

/**
 * Classe que renderiza um relatório mostrando todos os movimentos do banco
 * @author raffaello.salvetti
 *
 */
public class RelatorioMovimentacao extends BasicRelatorioGraficoForm {

	private static final long serialVersionUID = -6512541402466001365L;
	
	private List<Conta> contas;
	
	/**
	 * Construtor que recebe um formulário pai e a lista de contas cadastradas no sistema
	 * @param parent Formulário pai
	 * @param contas Contas cadastradas no sistema
	 */
	public RelatorioMovimentacao(Component parent, List<Conta> contas) {
		super(parent, "Relatório de Movimentações");
		this.contas = contas;
	}

	/**
	 * Constroi o relatório
	 */
	@Override
	protected Component generateContent(JPanel parent) {
		JTable tableMovimentacao = new JTable();
		JScrollPane scrollPaneTabelaMovimentacao = new JScrollPane();
		int linhas = 0, i = 0, c = 0;
		String columnNames[] = new String[] {"Conta", "Tipo de Movimentação", "Data", "Descrição", "Valor"};
		for (Conta conta : contas) {
			linhas += conta.getListMovimentacao().size();
		}
		Object data[][] = new Object[linhas][columnNames.length];
		for (Conta conta : contas) {
			for (Movimentacao m : conta.getListMovimentacao()) {
				data[i][c++] = conta.getNumero();
				data[i][c++] = m.getTipoMovimentacao();
				data[i][c++] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(m.getData());
				data[i][c++] = m.getDescricao();
				data[i][c++] = String.format("R$ %.2f", m.getValor());
				c = 0;
				i++;
			}
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
