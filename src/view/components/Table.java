package view.components;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;

import controller.CustomCellEditor;
import model.CustomTableModel;
import model.TableData;

public class Table implements TableModelListener {

	private CustomTableModel model;
	private CustomCellEditor cellEditor;
	private JTable table;

	public Table(TableData<?> tableData) {
		super();
		// insert data into the tablemodel and create a table
		this.model = new CustomTableModel(tableData);
		this.table = new JTable(this.model);
		this.cellEditor = new CustomCellEditor();

		this.table.setCellSelectionEnabled(true);
		this.table.setCellEditor(this.cellEditor);
		this.table.getModel().addTableModelListener(this);
		// style the table

		this.table.setAutoCreateRowSorter(true);
		this.table.setRowHeight(30);

		TableColumnModel column = this.table.getColumnModel();
		column.getColumns().asIterator().forEachRemaining(c -> c.setCellEditor(this.cellEditor));
		column.getColumns().asIterator().forEachRemaining(c -> c.setPreferredWidth(100));

	}

	public void refreshData(TableData<?> tableData) {
		this.model.updateData(tableData);
	}

	public JScrollPane getContent() {
		JScrollPane pane = new JScrollPane(this.table);
		pane.setPreferredSize(new Dimension(800, 600));
		return pane;
	}

	public JTable getTableRef() {
		return this.table;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
	}
}
