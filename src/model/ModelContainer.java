package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ModelContainer {

	private HashMap<String, PeerReviewer> peerReviewers;
	
	public ModelContainer() {
		this.peerReviewers = new HashMap<String, PeerReviewer>();
	}
	
	public void putPeerReviewer(PeerReviewer peerReviewer) {
		String key = peerReviewer.getFirstName() + peerReviewer.getName();
		PeerReviewer existingPeerReviewer = this.peerReviewers.get(key);
		
		if(existingPeerReviewer != null) {
			existingPeerReviewer.addFirstBachelorThesis(peerReviewer.getFirstBachelorThesis());
			existingPeerReviewer.addSecondBachelorThesis(peerReviewer.getSecondBachelorThesis());
		}else {
			this.peerReviewers.put(key, peerReviewer);
		}
	}
	
	public void printPeerReviewers() {
		Iterator it = peerReviewers.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + "\n" + pair.getValue());
			((PeerReviewer) pair.getValue()).printStudents();
			System.out.println("");
			it.remove(); // avoids a ConcurrentModificationException
		}
	}
}