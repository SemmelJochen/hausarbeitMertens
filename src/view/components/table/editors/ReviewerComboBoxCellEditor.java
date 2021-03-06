package view.components.table.editors;

import java.awt.Component;

import javax.swing.JTable;

import controller.command.StudentChangeCommand;
import model.ModelContainer;
import model.Pair;
import model.PeerReviewer;
import model.table.CustomTableModel;
import view.components.reviewercombobox.ReviewerComboBox;
import view.components.table.Table;

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
