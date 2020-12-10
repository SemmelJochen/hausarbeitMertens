package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ModelContainer implements Serializable {


	private static final long serialVersionUID = 0xCAFEL;
	private HashMap<String, PeerReviewer> peerReviewers;
	private HashMap<String, Student> students;
	private static final ModelContainer modelcontainer = new ModelContainer();
	
	public static ModelContainer getModelcontainerInstance() {
		return modelcontainer;
	}
	
	private ModelContainer() {
		this.peerReviewers = new HashMap<String, PeerReviewer>();
		this.students = new HashMap<String, Student>();
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
	
	public void putStudent(Student student) {
		System.out.println(student);
		String key = student.getFirstName() + student.getName();
		if(this.students.get(key) == null) { 
			System.out.println(key);
			System.out.println(student);
			this.students.put(key, student);
		}
	}
	
	public HashMap<String, Student> getStudents(){
		return this.students;
	}
	
	public HashMap<String, PeerReviewer> getPeerReviewer(){
		return this.peerReviewers;
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