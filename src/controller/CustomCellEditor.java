package controller;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import model.CustomTableModel;
import model.ModelContainer;
import model.Pair;
import model.PeerReviewer;
import model.Person;
import model.ReviewerColumn;
import model.Student;
import model.StudentColumn;

public class CustomCellEditor extends AbstractCellEditor implements TableCellEditor {

	private JTable table;
	private int row, col;

	private JTextField editor = new JTextField();

	@Override
	public Object getCellEditorValue() {
		System.out.println("cell editor value: " + this.editor.getText());
		if (this.table != null && this.editor != null) {

			CustomTableModel model = ((CustomTableModel) this.table.getModel());
			model.setValueAt(this.editor.getText(), row, col);

			// type checking
			if (model.getMetaData().get(row) instanceof PeerReviewer) {
				// find reviewer item and update it
				Pair<Object, Object> data = model.getDataAt(row, col);
				String columnName = model.getColumnName(col);
				this.updatePeerReviewer(data, columnName);

			} else if (model.getMetaData().get(row) instanceof Student) {
				Pair<Object, Object> data = model.getDataAt(row, col);
				String columnName = model.getColumnName(col);
				this.updateStudent(data, columnName);

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
		this.table = table;
		this.col = column;
		this.row = row;

		this.editor.setText(String.valueOf(value));

		return this.editor;
	}

	public void backfireChangesToModel(Person oldPerson, Person newPerson) {
		if (oldPerson instanceof PeerReviewer) {
			ModelContainer.getInstance().updateReviewer((PeerReviewer) oldPerson, (PeerReviewer) newPerson);
		} else if (oldPerson instanceof Student) {
			ModelContainer.getInstance().updateStudent((Student) oldPerson, (Student) newPerson);
		}
	}

	void updatePeerReviewer(Pair<Object, Object> data, String columnName) {
		ReviewerColumn column = ReviewerColumn.getEnumForValue(columnName);
		PeerReviewer reviewer = (PeerReviewer) data.getKey();
		PeerReviewer newReviewer = reviewer.clone();
		if (column == ReviewerColumn.CAPACITY) {
			newReviewer.setCapacity((int) data.getValue());
		}
		if (column == ReviewerColumn.E_MAIL) {
			newReviewer.setEmail((String) data.getValue());
		}
		if (column == ReviewerColumn.FIRSTNAME) {
			newReviewer.setFirstName((String) data.getValue());
		}
		if (column == ReviewerColumn.LASTNAME) {
			newReviewer.setName((String) data.getValue());
		}
		if (column == ReviewerColumn.TITLE) {
			newReviewer.setTitle((String) data.getValue());
		}

		this.backfireChangesToModel(reviewer, newReviewer);
	}

	void updateStudent(Pair<Object, Object> data, String columnName) {
		StudentColumn column = StudentColumn.getEnumForValue(columnName);
		Student student = (Student) data.getKey();
		Student newStudent = student.clone();

		if (column == StudentColumn.FIRST_REVIEWER) {
			// TODO findOrCreateNewPeerReviewer
			// newStudent.getFirstPeerReviewer().setFirstName(firstName);
		}
		if (column == StudentColumn.SECOND_REVIEWER) {
			// TODO findOrCreateNewPeerReviewer
			// newStudent.getFirstPeerReviewer().setFirstName(firstName);
		}
		if (column == StudentColumn.E_MAIL) {
			newStudent.setEmail((String) data.getValue());
		}
		if (column == StudentColumn.FIRSTNAME) {
			newStudent.setFirstName((String) data.getValue());
		}
		if (column == StudentColumn.LASTNAME) {
			newStudent.setName((String) data.getValue());
		}
		if (column == StudentColumn.PRACTICE_PARTNER) {
			newStudent.setPracticePartner((String) data.getValue());
		}
		if (column == StudentColumn.STUDENT_GROUP) {
			newStudent.setStudentGroup((String) data.getValue());
		}
		if (column == StudentColumn.SUBJECT) {
			newStudent.setSubject((String) data.getValue());
		}
		if (column == StudentColumn.REMARK) {
			newStudent.setRemark((String) data.getValue());
		}

		this.backfireChangesToModel(student, newStudent);
	}

}
