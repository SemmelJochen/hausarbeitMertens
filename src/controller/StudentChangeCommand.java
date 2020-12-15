package controller;

import model.Pair;

public class StudentChangeCommand implements Command {

	private CustomCellEditor customCellEditor;
	private Pair<Object, Object> data;
	private String columnName;
	
	public StudentChangeCommand() {
		
	}

	@Override
	public void undo() {
		this.customCellEditor.updateStudent(new Pair<Object, Object>(data.getValue(), data.getKey()), columnName);
		
	}

	@Override
	public void execute() {
		this.customCellEditor.updateStudent(this.data, this.columnName);
		
	}
}