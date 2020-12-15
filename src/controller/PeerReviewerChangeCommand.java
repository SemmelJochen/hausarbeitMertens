package controller;

import model.Pair;

public class PeerReviewerChangeCommand implements Command {

	private CustomCellEditor customCellEditor;
	private Pair<Object, Object> data;
	private String columnName;
	
	public PeerReviewerChangeCommand(CustomCellEditor customCellEditor, Pair<Object, Object> data, String columnName) {
		this.customCellEditor = customCellEditor;
		this.data = data;
		this.columnName = columnName;
	}

	@Override
	public void undo() {
		this.customCellEditor.UndoUpdatePeerReviewer(new Pair<Object, Object>(data.getKey(), data.getValue()), columnName);
		
	}

	@Override
	public void execute() {
		this.customCellEditor.updatePeerReviewer(this.data, this.columnName);
		
	}
}