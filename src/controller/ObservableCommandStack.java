package controller;

import java.util.Observable;
import java.util.Stack;

public class ObservableCommandStack extends Observable {

	private Stack<Command> commands;

	public ObservableCommandStack() {
		this.commands = new Stack<>();
	}
	
	public void add (Command command) {
		this.commands.add(command);
		this.setChanged();
		this.notifyAll();
	}
	
	public Command pop() {
		Command command = this.commands.pop();
		this.setChanged();
		this.notifyAll();
		
		return command;
	}
}