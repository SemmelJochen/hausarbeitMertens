package view.components.table.editors;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import controller.commandController.PeerReviewerChangeCommand;
import controller.commandController.StudentChangeCommand;
import model.Pair;
import model.PeerReviewer;
import model.Student;
import model.table.CustomTableModel;
import view.components.ICellEditor;
import view.components.Table;

public class CustomTextCellEditor implements ICellEditor {

	private Table table;
	private int row, col;

	private JTextField editor = new JTextField();
	
	public CustomTextCellEditor(Table table) {
		this.table = table;
	}
	
	@Override
	public Object getCellEditorValue() {
		if (this.table != null && this.editor != null) {

			CustomTableModel model = (CustomTableModel) this.table.getTableRef().getModel();
			model.setValueAt(this.editor.getText(), row, col);

			// type checking
			if (model.getType() ==  PeerReviewer.class) {
				// find reviewer item and update it
				Pair<Object, Object> data = model.getDataAt(row, col);
				String columnName = model.getColumnName(col);
				this.table.getController().executeDataUpdate(new PeerReviewerChangeCommand(this.table, data, columnName));

			} else if (model.getType() == Student.class) {
				Pair<Object, Object> data = model.getDataAt(row, col);
				String columnName = model.getColumnName(col);
				this.table.getController().executeDataUpdate(new StudentChangeCommand(this.table, data, columnName));

			} else {
				try {
					throw new ClassNotFoundException("the entry you are trying to change is non existent");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return (Object) this.editor.getText();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.col = column;
		this.row = row;

		this.editor.setText(String.valueOf(value));

		return this.editor;
	}

	
}
