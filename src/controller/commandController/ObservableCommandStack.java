package controller.commandController;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

public class ObservableCommandStack {

	private Stack<Command> commands;
	private PropertyChangeSupport propertyChangeSupport;

	public ObservableCommandStack() {
		this.commands = new Stack<>();
		this.propertyChangeSupport = new PropertyChangeSupport(this.commands);
	}

	public void add(Command command) {
		boolean oldValue = this.isEmpty();
		this.commands.add(command);
		this.propertyChangeSupport.firePropertyChange("isEmpty", oldValue, this.isEmpty());
	}

	public Command pop() {
		boolean oldValue = this.isEmpty();
		Command command = this.commands.pop();
		this.propertyChangeSupport.firePropertyChange("isEmpty", oldValue, this.isEmpty());

		return command;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public boolean isEmpty() {
		return this.commands.isEmpty();
	}
}