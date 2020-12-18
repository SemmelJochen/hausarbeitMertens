package controller;

import model.PeerReviewer;
import view.components.Table;

public class PeerReviewerAddCommand implements Command {

	private PeerReviewer peerReviewerToAdd;
	private Table table;
	public PeerReviewerAddCommand(Table t, PeerReviewer p) {
		this.peerReviewerToAdd = p;
		this.table = t;
	}
	@Override
	public void undo() {
		this.table.getController().removePeerReviewer(this.peerReviewerToAdd);
	}

	@Override
	public void execute() {
		this.table.getController().addPeerReviewer(this.peerReviewerToAdd);
	}

}