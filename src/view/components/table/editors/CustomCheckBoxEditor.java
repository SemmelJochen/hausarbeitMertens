package view.components.table.editors;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;

import view.components.ICellEditor;
import view.components.Table;

public class CustomCheckBoxEditor implements ICellEditor {

	private Table table;
	private JCheckBox editor = new JCheckBox();
	private int col, row;
	
	public CustomCheckBoxEditor(Table table) {
		this.table = table;
	}
	
	@Override
	public Object getCellEditorValue() {
		
		return this.editor.isSelected();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.col = column;
		this.row = row;

		this.editor.setSelected((boolean) value); 

		return this.editor;
	}

}
