package view.components.table.editors;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import model.table.CellEditorType;
import model.table.CustomTableModel;
import view.components.ICellEditor;
import view.components.Table;

public class CustomCellEditor extends AbstractCellEditor implements TableCellEditor {

	private ICellEditor editor;
	private Table table;

	public CustomCellEditor(Table table) {
		this.table = table;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		if (super.isCellEditable(e)) {
			if (e instanceof MouseEvent) {
				MouseEvent me = (MouseEvent) e;
				return me.getClickCount() >= 2;
			}
			if (e instanceof KeyEvent) {
				KeyEvent ke = (KeyEvent) e;
				 return ke.getKeyCode() == KeyEvent.VK_ENTER;
			}
		}
		return false;
	}

	@Override
	public Object getCellEditorValue() {
		return editor.getCellEditorValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable t, Object value, boolean isSelected, int row, int column) {
		if (((CustomTableModel) t.getModel()).getColumnType(column).equals(CellEditorType.CUSTOM_TEXT_EDITOR)) {
			this.editor = new CustomTextCellEditor(this.table);
		} else if (((CustomTableModel) t.getModel()).getColumnType(column)
				.equals(CellEditorType.PEER_REVIEWER_COMBO_BOX_EDITOR)) {
			this.editor = new ReviewerComboBoxCellEditor(this.table);
		} else if (((CustomTableModel) t.getModel()).getColumnType(column)
				.equals(CellEditorType.CUSTOM_CHECK_BOX_EDITOR)) {
			this.editor = new CustomCheckBoxEditor(this.table);
		} 
		
		if(this.editor == null) {
			return null;
		}
		return this.editor.getTableCellEditorComponent(t, value, isSelected, row, column);
	}

}
