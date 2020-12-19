package controller.commandController;

import model.PeerReviewer;
import view.components.Table;

public class PeerReviewerRemoveCommand implements Command {

	private PeerReviewer peerReviewerToRemove;
	private Table table;

	public PeerReviewerRemoveCommand(Table t, PeerReviewer p) {
		this.peerReviewerToRemove = p;
		this.table = t;
	}

	@Override
	public void undo() {
		this.table.getController().addPeerReviewer(this.peerReviewerToRemove);
	}

	@Override
	public void execute() {
		this.table.getController().removePeerReviewer(this.peerReviewerToRemove);
	}

}
