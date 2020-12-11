package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ModelContainer implements Serializable {


	private static final long serialVersionUID = 0xCAFEL;
	private HashMap<String, PeerReviewer> peerReviewers;
	private ArrayList<Student>students;
	private static final ModelContainer modelcontainer = new ModelContainer();
	
	public static ModelContainer getModelcontainerInstance() {
		return modelcontainer;
	}
	
	private ModelContainer() {
		this.peerReviewers = new HashMap<String, PeerReviewer>();
		this.students = new ArrayList<Student>();
	}
	
	public void putPeerReviewer(PeerReviewer peerReviewer) {
		String key = peerReviewer.getFirstName() + peerReviewer.getName();
		PeerReviewer existingPeerReviewer = this.peerReviewers.get(key);
		
		if(existingPeerReviewer != null) {
			existingPeerReviewer.addBachelorThesesAsFirstReviewer(peerReviewer.getFirstBachelorThesis());
			existingPeerReviewer.addBachelorThesesAsSecondReviewer(peerReviewer.getSecondBachelorThesis());
		}else {
			this.peerReviewers.put(key, peerReviewer);
		}
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public Student getStudent(int i){
		return this.students.get(i);
	}
	
	public PeerReviewer getPeerReviewer(String key){
		return this.peerReviewers.get(key);
	}
	
	public void printPeerReview() {
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
	
	public void printPeerReviewers() {
		ArrayList<PeerReviewer> r = (ArrayList<PeerReviewer>) this.peerReviewers.values();
		for(PeerReviewer peerReviewer: r) {
			
		}
	}
	
	public ArrayList<PeerReviewer> getPeerReviewers(){
		return new ArrayList<PeerReviewer>(peerReviewers.values());
	}
	
	public ArrayList<Student> getStudens(){
		return this.students;
	}
	
	public void load(ModelContainer modelcontainer) {
		// TODO
	}
}