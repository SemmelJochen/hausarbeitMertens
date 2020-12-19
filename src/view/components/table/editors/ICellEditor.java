package view.components.table.editors;

import java.awt.Component;

import javax.swing.JTable;

/**
 * interface for our custom CellEditors. Very similar to the TableCellEditor
 * from Swing, but with less load of methods.
 * 
 * @author josua
 *
 */
public interface ICellEditor {

	public Object getCellEditorValue();

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column);
}
