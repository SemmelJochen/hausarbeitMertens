package model;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {

	private String[] columnNames;
	private List<?> metaData;
	private List<Object>[] data;

	public CustomTableModel(TableData<?> tableData) {
		this.columnNames = tableData.getColumnNames();
		this.data = tableData.getContent();
		this.metaData = tableData.getMetaData();
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

	/**
	 * 
	 * @param row
	 * @param col
	 * @return firstObject returns the metaData object and second the value in table
	 */
	public Pair<Object, Object> getDataAt(int row, int col) {
		return new Pair<Object, Object>(this.metaData.get(row), this.data[col].get(row));
	}

	public List<?> getMetaData() {
		return this.metaData;
	}

	// data change event
	public void setValueAt(String value, int row, int col) {
		this.data[col].set(row, value);
		super.fireTableCellUpdated(row, col);
	}

	public void updateData(TableData<?> tableData) {
		this.data = tableData.getContent();
		super.fireTableDataChanged();
	}

	public void addTableModelListener(TableModelListener l) {
		super.addTableModelListener(l);

	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

}
