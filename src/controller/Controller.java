package controller;

public class Controller {

	private Importer excelImporter;
	private ObservableCommandStack undoStack, redoStack;

	public Controller() {
		this.excelImporter = new Importer();
		this.undoStack = new ObservableCommandStack();
		this.redoStack = new ObservableCommandStack();
	}

	public void redo() {
		Command command = this.redoStack.pop();
		command.execute();
		this.undoStack.add(command);
	}

	public void undo() {
		Command command = this.undoStack.pop();
		command.undo();
		this.redoStack.add(command);
	}
}