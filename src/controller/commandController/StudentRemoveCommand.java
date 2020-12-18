package controller.commandController;

import model.Student;
import view.components.Table;

public class StudentRemoveCommand implements Command {

	private Student studentToAdd;
	private Table table;
	
	public StudentRemoveCommand(Table t, Student s) {
		this.studentToAdd = s;
		this.table = t;
	}
	@Override
	public void undo() {
		this.table.getController().addStudent(this.studentToAdd);			
		
	}

	@Override
	public void execute() {
		this.table.getController().removeStudent(this.studentToAdd);		
		
	}

}
