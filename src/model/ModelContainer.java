package model;

import java.util.HashMap;

public class ModelContainer {

	//Integer <- Hashcode
	private HashMap<Integer, PeerReviewer> peerReviewer;
	private HashMap<String, Integer> mappingPeerReviewerNameToKey;
	
	public ModelContainer() {
		this.peerReviewer = new HashMap<Integer, PeerReviewer>();
		this.mappingPeerReviewerNameToKey = new HashMap<String, Integer>();
	}
	
	public void putPeerReviewer(PeerReviewer peerReviewer) {
		int key = peerReviewer.hashCode();
		this.peerReviewer.put(key, peerReviewer);
		this.mappingPeerReviewerNameToKey.put(peerReviewer.getFirstName() + peerReviewer.getName(), key);
	}
}