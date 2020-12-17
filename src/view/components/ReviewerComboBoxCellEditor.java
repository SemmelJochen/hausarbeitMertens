package view.components;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ReviewerComboBoxCellEditor extends AbstractCellEditor implements TableCellEditor  {

	private ReviewerComboBox comboBox;
	private Table table;
	
	public ReviewerComboBoxCellEditor(Table table) {
		this.table = table;
		this.comboBox = new ReviewerComboBox();
	}
	
	@Override
	public Object getCellEditorValue() {
		return this.comboBox.getSelectedPeerReviewer();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.comboBox.setSelectedItem(value);
		return this.comboBox;
	}

}
