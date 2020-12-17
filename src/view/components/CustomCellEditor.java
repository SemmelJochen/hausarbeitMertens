package view.components;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import controller.PeerReviewerChangeCommand;
import controller.StudentChangeCommand;
import model.CustomTableModel;
import model.Pair;
import model.PeerReviewer;
import model.Student;

public class CustomCellEditor extends AbstractCellEditor implements TableCellEditor {

	private Table table;
	private int row, col;

	private JTextField editor = new JTextField();
	
	public CustomCellEditor(Table table) {
		super();
		this.table = table;

	}

	@Override
	public boolean isCellEditable(EventObject e) {
		if(super.isCellEditable(e)) {
			if (e instanceof MouseEvent) {
                MouseEvent me = (MouseEvent) e;
                return me.getClickCount() >= 2;
            }
			if (e instanceof KeyEvent) {
                KeyEvent ke = (KeyEvent) e;
                return ke.isMetaDown();
            }
		}
		return false;
	}
	
	@Override
	public Object getCellEditorValue() {
		System.out.println("cell editor value: " + this.editor.getText());
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
					// TODO Auto-generated catch block
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
