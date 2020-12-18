package controller;

public class CommandController {

	/*
	 * The CommandController is only for handling Commands
	 * and shifts the logic away from the common Controller
	 * to simplify the logic.
	 */
	private ObservableCommandStack undoStack, redoStack;
	
	public CommandController() {
		this.undoStack = new ObservableCommandStack();
		this.redoStack = new ObservableCommandStack();
	}
	
	public ObservableCommandStack getUndoStack() {
		return this.undoStack;
	}
	
	public ObservableCommandStack getRedoStack() {
		return this.redoStack;
	}
	
	public void execute(Command command) {
		command.execute();
		this.undoStack.add(command);
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
