package view.components;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controller.CommandController;
import controller.CustomCellEditor;
import model.CustomTableModel;
import model.TableData;

public class Table extends JPanel implements TableModelListener {

	private CustomTableModel model;
	private CustomCellEditor cellEditor;
	private JTable table;
	private JButton addButton, removeButton;

	public Table(TableData<?> tableData, CommandController commandController) {
		super();
		// insert data into the tablemodel and create a table
		this.model = new CustomTableModel(tableData);
		this.table = new JTable(this.model) {
			// make column fit to its content
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth((int) Math.max(rendererWidth + getIntercellSpacing().getWidth(),
						tableColumn.getPreferredWidth()));
				return component;
			}
		};
		this.cellEditor = new CustomCellEditor(commandController);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.setCellSelectionEnabled(true);
		this.table.setCellEditor(this.cellEditor);
		this.table.getModel().addTableModelListener(this);
		// style the table

		this.table.setAutoCreateRowSorter(false);
		this.table.setRowHeight(30);

		TableColumnModel column = this.table.getColumnModel();
		column.getColumns().asIterator().forEachRemaining(c -> c.setCellEditor(this.cellEditor));

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(getTableContent());
		this.add(getButtonPanel());

	}

	private JPanel getButtonPanel() {
		JPanel buttonPanel = new JPanel();
		this.addButton = new JButton("Hinzufügen");
		this.removeButton =new JButton("Löschen");
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		return buttonPanel;
	}

	public void refreshData(TableData<?> tableData) {
		this.model.updateData(tableData);
	}

	private JScrollPane getTableContent() {
		JScrollPane pane = new JScrollPane(this.table);
		pane.setPreferredSize(new Dimension(1000, 600));
		return pane;
	}

	public JTable getTableRef() {
		return this.table;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
	}
}
