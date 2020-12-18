package controller;

public interface Command {

	public void undo();
	public void execute();
}
