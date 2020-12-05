package view.components;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import model.TableData;

public class Table {

	private CustomTableModel model;
	private JTable table;

	public Table(TableData tableData) {
		super();
		// insert data into the tablemodel and create a table
		this.model = new CustomTableModel(tableData);
		this.table = new JTable(this.model);

		// style the table
		this.table.setRowHeight(30);
		
		TableColumnModel column = this.table.getColumnModel();
		column.getColumns().asIterator().forEachRemaining(c -> c.setPreferredWidth(100));
		
	}
	public JScrollPane getContent() {
		JScrollPane pane = new JScrollPane(this.table);
		pane.setPreferredSize(new Dimension(800, 600));
		return pane;
	}

	class CustomTableModel extends AbstractTableModel {

		private String[] columnNames;
//	    private Object[][] data; 
		private List<Object>[] data;

		public CustomTableModel(TableData tableData) {
			this.columnNames = tableData.getColumnNames();
			this.data = tableData.getContent();
		}

		public int getColumnCount() {
			return this.data.length;
		}

		public int getRowCount() {
			return this.data[0].size();
		}

		public String getColumnName(int col) {
			return this.columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return this.data[col].get(row);
		}

		// data change event
		public void setValueAt(String value, int row, int col) {
			this.data[col].set(row, value);
			fireTableCellUpdated(row, col);
		}

	}
}
