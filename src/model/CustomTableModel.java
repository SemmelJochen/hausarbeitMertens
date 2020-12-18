package model;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


public class CustomTableModel extends AbstractTableModel {

	private String[] columnNames;
	private List<?> metaData;
	private CellEditorType[] columnTypes;
	private List<Object>[] data;
	private Class<?> type;

	public CustomTableModel(TableData<?> tableData) {
		this.columnNames = tableData.getColumnNames();
		this.columnTypes = tableData.getColumnTypes();
		this.data = tableData.getContent();
		this.metaData = tableData.getMetaData();
		this.type = tableData.getType();
		super.fireTableDataChanged();
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
	
	public String[] getColumnnNames() {
		return this.columnNames;
	}

	public Object getValueAt(int row, int col) {
		return this.data[col].get(row);
	}
	
	public boolean hasData() {
		return this.data.length > 0;
	}
	
	public Class<?> getType() {
		return this.type;
	}
	
	public CellEditorType getColumnType(int col) {
		return this.columnTypes[col];
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return   firstObject returns the metaData object and second the value that changed
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
		for(List<Object> l : this.data) {
			l.clear();
		}
		this.data = tableData.getContent();
		this.metaData = tableData.getMetaData();
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
