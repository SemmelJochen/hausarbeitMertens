package view.components;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import model.CellEditorType;
import model.CustomTableModel;

public class CustomCellEditor extends AbstractCellEditor implements TableCellEditor {

	private ICellEditor editor;
	private Table table;
	

	public CustomCellEditor(Table table) {
		this.table = table;
//		if (cellType.equals(CellEditorType.CUSTOM_TEXT_EDITOR)) {
//			editor = new CustomTextCellEditor(table);
//		} else if (cellType.equals(CellEditorType.PEER_REVIEWER_COMBO_BOX)) {
//			editor = new ReviewerComboBoxCellEditor(table);
//		}
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
                //return ke.isMetaDown();
            }
		}
		return false;
	}
	
	@Override
	public Object getCellEditorValue() {
		return editor.getCellEditorValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if(((CustomTableModel) table.getModel()).getColumnType(column).equals(CellEditorType.CUSTOM_TEXT_EDITOR)) {
			this.editor = new CustomTextCellEditor(this.table);
		}
		if(((CustomTableModel) table.getModel()).getColumnType(column).equals(CellEditorType.PEER_REVIEWER_COMBO_BOX)) {
			this.editor = new ReviewerComboBoxCellEditor(this.table);
		}
		return this.editor.getTableCellEditorComponent(table, value, isSelected, row, column);
	}

}
