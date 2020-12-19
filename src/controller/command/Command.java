package controller.command;

public interface Command {

	public void undo();

	public void execute();
}
