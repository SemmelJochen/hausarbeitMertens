package controller;

import model.Pair;
import view.components.Table;

public class StudentChangeCommand implements Command {

	private Table table;
	private Pair<Object, Object> data;
	private String columnName;
	
	public StudentChangeCommand(Table table, Pair<Object, Object> data, String columnName) {
		this.table = table;
		this.data = data;
		this.columnName = columnName;
	}

	@Override
	public void undo() {
		this.table.getController().undoStudentUpdate(new Pair<Object, Object>(data.getKey(), data.getValue()), columnName);
	}

	@Override
	public void execute() {
		this.table.getController().updateStudent(this.data, this.columnName);
		
	}
}