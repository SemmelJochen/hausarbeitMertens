package controller;

import model.Pair;

public class PeerReviewerChangeCommand implements Command {

	private CustomCellEditor customCellEditor;
	private Pair<Object, Object> data;
	private String columnName;
	
	public PeerReviewerChangeCommand() {
		
	}

	@Override
	public void undo() {
		this.customCellEditor.updatePeerReviewer(new Pair<Object, Object>(data.getValue(), data.getKey()), columnName);
		
	}

	@Override
	public void execute() {
		this.customCellEditor.updatePeerReviewer(this.data, this.columnName);
		
	}
}