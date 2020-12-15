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
import view.components.ReviewerComboBox;

public class CustomCellEditor extends AbstractCellEditor implements TableCellEditor {

	private JTable table;
	private int row, col;
	private CommandController commandController;

	private JTextField editor = new JTextField();
	
	public CustomCellEditor(CommandController commandController) {
		super();
		this.commandController = commandController;
	}

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
				this.commandController.execute(new PeerReviewerChangeCommand(this, data, columnName));

			} else if (model.getMetaData().get(row) instanceof Student) {
				Pair<Object, Object> data = model.getDataAt(row, col);
				String columnName = model.getColumnName(col);
				this.commandController.execute(new StudentChangeCommand(this, data, columnName));

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
		PeerReviewer reviewer = (PeerReviewer) data.getKey();
		PeerReviewer newReviewer = this.tmp(reviewer, data.getValue(), columnName);

		this.backfireChangesToModel(reviewer, newReviewer);
	}
	
	void UndoUpdatePeerReviewer(Pair<Object, Object> data, String columnName) {
		PeerReviewer reviewer = (PeerReviewer) data.getKey();
		PeerReviewer newReviewer = this.tmp(reviewer, data.getValue(), columnName);

		this.backfireChangesToModel(newReviewer, reviewer);
	}
	
	private PeerReviewer tmp(PeerReviewer reviewer, Object data, String columnName) {
		ReviewerColumn column = ReviewerColumn.getEnumForValue(columnName);
		PeerReviewer newReviewer = reviewer.clone();
		if (column == ReviewerColumn.CAPACITY) {
			newReviewer.setCapacity((int) data);
		}
		if (column == ReviewerColumn.E_MAIL) {
			newReviewer.setEmail((String) data);
		}
		if (column == ReviewerColumn.FIRSTNAME) {
			newReviewer.setFirstName((String) data);
		}
		if (column == ReviewerColumn.LASTNAME) {
			newReviewer.setName((String) data);
		}
		if (column == ReviewerColumn.TITLE) {
			newReviewer.setTitle((String) data);
		}
		
		return newReviewer;
	}

	void updateStudent(Pair<Object, Object> data, String columnName) {
		Student student = (Student) data.getKey();
		Student newStudent = this.tmp(student, data.getValue(), columnName);

		this.backfireChangesToModel(student, newStudent);
	}
	
	void UndoUpdateStudent(Pair<Object, Object> data, String columnName) {
		Student student = (Student) data.getKey();
		Student newStudent = this.tmp(student, data.getValue(), columnName);

		this.backfireChangesToModel(newStudent, student);
	}
	
	private Student tmp(Student student, Object data, String columnName) {
		StudentColumn column = StudentColumn.getEnumForValue(columnName);
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
			newStudent.setEmail((String) data);
		}
		if (column == StudentColumn.FIRSTNAME) {
			newStudent.setFirstName((String) data);
		}
		if (column == StudentColumn.LASTNAME) {
			newStudent.setName((String) data);
		}
		if (column == StudentColumn.PRACTICE_PARTNER) {
			newStudent.setPracticePartner((String) data);
		}
		if (column == StudentColumn.STUDENT_GROUP) {
			newStudent.setStudentGroup((String) data);
		}
		if (column == StudentColumn.SUBJECT) {
			newStudent.setSubject((String) data);
		}
		if (column == StudentColumn.REMARK) {
			newStudent.setRemark((String) data);
		}
		
		return newStudent;
	}
}
