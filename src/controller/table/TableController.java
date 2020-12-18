package controller.table;

import controller.Command;
import controller.CommandController;
import model.ModelContainer;
import model.Pair;
import model.PeerReviewer;
import model.Person;
import model.Student;
import view.components.Table;
import view.views.tables.ReviewerColumn;
import view.views.tables.StudentColumn;

public class TableController {

	private Table table;
	private CommandController commandCtrl;
	
	public TableController(Table t, CommandController c) {
		this.table = t;
		this.commandCtrl = c;
	}
	
	public void backfireChangesToModel(Person oldPerson, Person newPerson) {

		if (oldPerson instanceof PeerReviewer) {
			ModelContainer.getInstance().updateReviewer((PeerReviewer) oldPerson, (PeerReviewer) newPerson);
		} else if (oldPerson instanceof Student) {
			ModelContainer.getInstance().updateStudent((Student) oldPerson, (Student) newPerson);
		}
	}

	public void executeDataUpdate(Command command) {
		this.commandCtrl.execute(command);
	}
	
	public void addPeerReviewer(PeerReviewer p) {
		ModelContainer.getInstance().putPeerReviewer(p);
	}
	
	public void removePeerReviewer(PeerReviewer p) {
		ModelContainer.getInstance().removePeerReviewer(p);
	}

	public void removeStudent(Student studentToRemove) {
		ModelContainer.getInstance().removeStudent(studentToRemove);
	}

	public void addStudent(Student studentToAdd) {
		ModelContainer.getInstance().addStudent(studentToAdd);		
	}
	
	public void updatePeerReviewer(Pair<Object, Object> data, String columnName) {
		PeerReviewer reviewer = (PeerReviewer) data.getKey();
		PeerReviewer newReviewer = this.buildNewPeerReviewer(reviewer, data.getValue(), columnName);

		this.backfireChangesToModel(reviewer, newReviewer);
	}
	
	public void updateStudent(Pair<Object, Object> data, String columnName) {
		Student student = (Student) data.getKey();
		Student newStudent = this.buildNewStudent(student, data.getValue(), columnName);
		
		this.backfireChangesToModel(student, newStudent);
	}
	
	public void undoPeerReviewerUpdate(Pair<Object, Object> data, String columnName) {
		PeerReviewer reviewer = (PeerReviewer) data.getKey();
		PeerReviewer newReviewer = this.buildNewPeerReviewer(reviewer, data.getValue(), columnName);

		this.backfireChangesToModel(newReviewer, reviewer);
	}
	
	public void undoStudentUpdate(Pair<Object, Object> data, String columnName) {
		Student student = (Student) data.getKey();
		Student newStudent = this.buildNewStudent(student, data.getValue(), columnName);

		this.backfireChangesToModel(newStudent, student);
	}
	
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
		if( column == ReviewerColumn.SUBJECTS) {
			newReviewer.setSubjects((String) data);
		}
		if (column == ReviewerColumn.TITLE) {
			newReviewer.setTitle((String) data);
		}
		
		return newReviewer;
	}
	
	private Student buildNewStudent(Student student, Object data, String columnName) {
		StudentColumn column = StudentColumn.getEnumForValue(columnName);
		Student newStudent = student.clone();

		if (column == StudentColumn.FIRST_REVIEWER_KEY) {
			newStudent.setFirstPeerReviewerKey((String) data); 
		}
		if (column == StudentColumn.SECOND_REVIEWER_KEY) {
			newStudent.setSecondPeerReviewerKey((String) data); 
		}
		if(column == StudentColumn.SECOND_REVIEWER_STATE) {
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
