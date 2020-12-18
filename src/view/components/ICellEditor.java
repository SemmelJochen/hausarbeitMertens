package view.components;

import java.awt.Component;

import javax.swing.JTable;

public interface ICellEditor {

	public Object getCellEditorValue();
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column);
}
