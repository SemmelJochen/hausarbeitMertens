package view.components.table.editors;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;

import controller.StudentChangeCommand;
import model.Pair;
import model.Student;
import model.table.CustomTableModel;
import view.components.ICellEditor;
import view.components.Table;

public class CustomComboBoxEditor implements ICellEditor {

	private Table table;
	private String[] values = { Student.ACCEPTED_STATE, Student.PENDING_STATE, Student.REJECTED_STATE };
	private JComboBox<String> editor = new JComboBox<String>(values);
	private int col, row;

	public CustomComboBoxEditor(Table table) {
		this.table = table;
	}

	@Override
	public Object getCellEditorValue() {
		if (this.table != null && this.editor != null) {
			CustomTableModel model = (CustomTableModel) this.table.getTableRef().getModel();
			model.setValueAt((String) this.editor.getSelectedItem(), row, col);

			Pair<Object, Object> data = model.getDataAt(row, col);
			String columnName = model.getColumnName(col);
			
			this.table.getController().executeDataUpdate(new StudentChangeCommand(this.table, data, columnName));
		}
		return this.editor.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.col = column;
		this.row = row;

		this.editor.setSelectedItem((String) value);
		
		return this.editor;
	}

}
