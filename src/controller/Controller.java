package controller;

import view.ObserverButton;

public class Controller {
	
	private ObservableCommandStack undoStack = new ObservableCommandStack();
	private ObservableCommandStack redoStack = new ObservableCommandStack();
	
	public void undo() {
		
	}
	
    public void redo() {
		
	}
	
	
	public void addUndoButton(ObserverButton ob) {
		this.undoStack.addObserver(ob);
	}
	
	public void addRedoButton(ObserverButton ob) {
		this.redoStack.addObserver(ob);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
