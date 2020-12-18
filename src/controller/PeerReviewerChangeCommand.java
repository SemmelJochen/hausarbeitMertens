package controller;

import model.Pair;
import view.components.Table;

public class PeerReviewerChangeCommand implements Command {

	private Table table;
	private Pair<Object, Object> data;
	private String columnName;
	
	public PeerReviewerChangeCommand(Table table, Pair<Object, Object> data, String columnName) {
		this.table = table;
		this.data = data;
		this.columnName = columnName;
	}

	@Override
	public void undo() {
		this.table.getController().undoPeerReviewerUpdate(this.data, this.columnName);
		
	}

	@Override
	public void execute() {
		this.table.getController().updatePeerReviewer(this.data, this.columnName);
		
	}
}