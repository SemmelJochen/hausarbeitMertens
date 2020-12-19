package controller.command;

import model.Student;
import view.components.table.Table;

public class StudentAddCommand implements Command {

	private Student studentToAdd;
	private Table table;

	public StudentAddCommand(Table t, Student s) {
		this.studentToAdd = s;
		this.table = t;
	}

	@Override
	public void undo() {
		this.table.getController().removeStudent(this.studentToAdd);
	}

	@Override
	public void execute() {
		this.table.getController().addStudent(this.studentToAdd);
	}

}
