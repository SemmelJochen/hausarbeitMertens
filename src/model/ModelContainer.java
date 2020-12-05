package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ModelContainer implements Serializable {


	private static final long serialVersionUID = 0xCAFEL;
	private HashMap<String, PeerReviewer> peerReviewers;
	private static final ModelContainer modelcontainer = new ModelContainer();
	
	public static ModelContainer getModelcontainerInstance() {
		return modelcontainer;
	}
	
	private ModelContainer() {
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
			
			PeerReviewer p = (PeerReviewer) pair.getValue();
			p.printStudents();
			
			System.out.println("");
			it.remove(); // avoids a ConcurrentModificationException
		}
	}
	
	public void load(ModelContainer modelcontainer) {
		
	}
}