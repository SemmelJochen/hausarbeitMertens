package controller.table;

import controller.commandController.Command;
import controller.commandController.CommandController;
import model.ModelContainer;
import model.Pair;
import model.PeerReviewer;
import model.Person;
import model.Student;
import model.table.ReviewerColumn;
import model.table.StudentColumn;
import view.components.Table;

public class TableController {

	private CommandController commandCtrl;

	public TableController(CommandController c) {
		this.commandCtrl = c;
	}

	/**
	 * commit changes at a Student/PeerReviewer to the ModelContainer.
	 * 
	 * @param oldPerson - oldValue
	 * @param newPerson - newValue
	 */
	private void backfireChangesToModel(Person oldPerson, Person newPerson) {

		if (oldPerson instanceof PeerReviewer) {
			ModelContainer.getInstance().updateReviewer((PeerReviewer) oldPerson, (PeerReviewer) newPerson);
		} else if (oldPerson instanceof Student) {
			ModelContainer.getInstance().updateStudent((Student) oldPerson, (Student) newPerson);
		}
	}

	public void executeDataUpdate(Command command) {
		this.commandCtrl.execute(command);
	}

	/**
	 * All add and remove actions that can be done to the student and peerReviewer
	 */
	public void addPeerReviewer(PeerReviewer peerReviewerToAdd) {
		ModelContainer.getInstance().putPeerReviewer(peerReviewerToAdd);
	}

	public void removePeerReviewer(PeerReviewer peerReviewerToRemove) {
		ModelContainer.getInstance().removePeerReviewer(peerReviewerToRemove);
	}

	public void removeStudent(Student studentToRemove) {
		ModelContainer.getInstance().removeStudent(studentToRemove);
	}

	public void addStudent(Student studentToAdd) {
		ModelContainer.getInstance().addStudent(studentToAdd);
	}

	/**
	 * method to commit any changes made at the peerReviewer made in the table to
	 * the model.
	 * 
	 * @param data       - Pair<Class of the peerReviewer, data object that changed
	 *                   in the table>
	 * @param columnName - name of the column that the value has changed in
	 */
	public void updatePeerReviewer(Pair<Object, Object> data, String columnName) {
		PeerReviewer reviewer = (PeerReviewer) data.getKey();
		PeerReviewer newReviewer = this.buildNewPeerReviewer(reviewer, data.getValue(), columnName);

		this.backfireChangesToModel(reviewer, newReviewer);
	}

	/**
	 * undo changes at the peerReviewer. necessary for the undo/redo stack
	 * 
	 * @param data       - Pair<Class of the peerReviewer, data object that changed
	 *                   in the table>
	 * @param columnName - name of the column that the value has changed in
	 */
	public void undoPeerReviewerUpdate(Pair<Object, Object> data, String columnName) {
		PeerReviewer reviewer = (PeerReviewer) data.getKey();
		PeerReviewer newReviewer = this.buildNewPeerReviewer(reviewer, data.getValue(), columnName);

		this.backfireChangesToModel(newReviewer, reviewer);
	}

	/**
	 * method to commit any changes at the student made in the table to the model.
	 * 
	 * @param data       - Pair<Class of the peerReviewer, data object that changed
	 *                   in the table>
	 * @param columnName - name of the column that the value has changed in
	 */
	public void updateStudent(Pair<Object, Object> data, String columnName) {
		Student student = (Student) data.getKey();
		Student newStudent = this.buildNewStudent(student, data.getValue(), columnName);

		this.backfireChangesToModel(student, newStudent);
	}

	/**
	 * undo changes at the student. necessary for the undo/redo stack
	 * 
	 * @param data       - Pair<Class of the peerReviewer, data object that changed
	 *                   in the table>
	 * @param columnName - name of the column that the value has changed in
	 */
	public void undoStudentUpdate(Pair<Object, Object> data, String columnName) {
		Student student = (Student) data.getKey();
		Student newStudent = this.buildNewStudent(student, data.getValue(), columnName);

		this.backfireChangesToModel(newStudent, student);
	}

	/**
	 * method to rebuild a peerReviewer with the changed input from the table
	 * 
	 * @param reviewer   - reviewer object of the row
	 * @param data       - object that represents the value that changed
	 * @param columnName - name of the column that the value was changed
	 * @return
	 */
	private PeerReviewer buildNewPeerReviewer(PeerReviewer reviewer, Object data, String columnName) {
		ReviewerColumn column = ReviewerColumn.getEnumForValue(columnName);
		PeerReviewer newReviewer = reviewer.clone();

		if (column == ReviewerColumn.CAPACITY) {
			newReviewer.setCapacity((int) Integer.parseInt((String) data));
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
		if (column == ReviewerColumn.SUBJECTS) {
			System.out.println("changing subjects");
			newReviewer.setSubjects((String) data);
		}
		if (column == ReviewerColumn.TITLE) {
			newReviewer.setTitle((String) data);
		}

		return newReviewer;
	}

	/**
	 * method to rebuild a student with the changed input from the table
	 * 
	 * @param student    - student object of the row
	 * @param data       - object that represents the value that changed
	 * @param columnName - name of the column that the value was changed
	 * @return
	 */
	private Student buildNewStudent(Student student, Object data, String columnName) {
		StudentColumn column = StudentColumn.getEnumForValue(columnName);
		Student newStudent = student.clone();

		if (column == StudentColumn.FIRST_REVIEWER_KEY) {
			newStudent.setFirstPeerReviewerKey((String) data);
		}
		if (column == StudentColumn.SECOND_REVIEWER_KEY) {
			newStudent.setSecondPeerReviewerKey((String) data);
		}
		if (column == StudentColumn.SECOND_REVIEWER_STATE) {
			newStudent.setSecondPeerReviewerState((String) data);
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
