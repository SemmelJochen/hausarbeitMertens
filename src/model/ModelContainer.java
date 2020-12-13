package model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import controller.ObservableList;

public class ModelContainer implements Externalizable {

	private static final long serialVersionUID = 0xCAFEL;
	private HashMap<String, PeerReviewer> peerReviewers;
	private ObservableList<Student> students;

	private static final ModelContainer MODELCONTAINER = new ModelContainer();

	public static ModelContainer getModelcontainerInstance() {
		return MODELCONTAINER;
	}

	private ModelContainer() {
		this.peerReviewers = new HashMap<String, PeerReviewer>();
		this.students = new ObservableList<Student>();

	}

	public void putPeerReviewer(PeerReviewer peerReviewer) {
		String key = peerReviewer.getFirstName() + peerReviewer.getName();
		PeerReviewer existingPeerReviewer = this.peerReviewers.get(key);

		if (existingPeerReviewer != null) {
			existingPeerReviewer.addBachelorThesesAsFirstReviewer(peerReviewer.getFirstBachelorThesis());
			existingPeerReviewer.addBachelorThesesAsSecondReviewer(peerReviewer.getSecondBachelorThesis());
		} else {
			this.peerReviewers.put(key, peerReviewer);
		}
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public Student getStudent(int i) {
		return this.students.get(i);
	}

	public PeerReviewer getPeerReviewer(String key) {
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
		for (PeerReviewer peerReviewer : r) {

		}
	}

	public ArrayList<PeerReviewer> getPeerReviewers() {
		return new ArrayList<PeerReviewer>(peerReviewers.values());
	}

	public ObservableList<Student> getStudens() {
		return this.students;
	}

	public void load(ModelContainer modelcontainer) {
		// TODO
	}

	public void setPeerReviewers(HashMap<String, PeerReviewer> peerReviewers) {
		this.peerReviewers = peerReviewers;
	}

	public void setStudents(List<Student> students) {
		this.students = (ObservableList<Student>) students;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}
}