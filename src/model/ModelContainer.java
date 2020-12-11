package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
	
	public Student getStudent(String key){
		return this.students.get(key);
	}
	
	public PeerReviewer getPeerReviewer(String key){
		return this.peerReviewers.get(key);
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
	
	public ArrayList<PeerReviewer> getPeerReviewers(){
		ArrayList<PeerReviewer> result = new ArrayList<PeerReviewer>();
		Set<String> keySet = this.peerReviewers.keySet();
		for(String key: keySet) {
			result.add(this.getPeerReviewer(key));
		}
		return result;
	}
	
	public ArrayList<Student> getStudens(){
		ArrayList<Student> result = new ArrayList<Student>();
		Set<String> keySet = this.peerReviewers.keySet();
		for(String key: keySet) {
			result.add(this.getStudent(key));
		}
		return result;
	}
	
	public void load(ModelContainer modelcontainer) {
		// TODO
	}
}