package view.components;

import java.awt.Component;

import javax.swing.JTable;

import controller.StudentChangeCommand;
import model.CustomTableModel;
import model.ModelContainer;
import model.Pair;
import model.PeerReviewer;

public class ReviewerComboBoxCellEditor implements ICellEditor {

	private ReviewerComboBox editor;
	private Table table;
	private int row, col;

	public ReviewerComboBoxCellEditor(Table table) {
		this.table = table;
		this.editor = new ReviewerComboBox(ModelContainer.getInstance().getPeerReviewers());
	}

	@Override
	public Object getCellEditorValue() {
		PeerReviewer reviewer = this.editor.getSelectedPeerReviewer();
		String key = reviewer.getFirstName() + reviewer.getName();

		CustomTableModel model = (CustomTableModel) this.table.getTableRef().getModel();
		model.setValueAt(key, row, col);
		Pair<Object, Object> data = model.getDataAt(row, col);
		String columnName = model.getColumnName(col);
		this.table.getController().executeDataUpdate(new StudentChangeCommand(this.table, data, columnName));

		return key;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.row = row;
		this.col = column;
		PeerReviewer val = (PeerReviewer) ModelContainer.getInstance().getPeerReviewer((String) value);
		this.editor.setSelectedItem(val);
		return this.editor;
	}

}
