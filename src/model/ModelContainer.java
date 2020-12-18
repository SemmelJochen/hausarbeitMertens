package model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;

import controller.ObservableHashMap;
import controller.ObservableList;

public class ModelContainer implements Externalizable {
	/**
	 * we use Externalizable instead of Serializable because we use ObservableLists
	 * and Maps. Since those are not Serializable and PropertyChangeSupport doesnt
	 * directly work for Lists, we write our own custom serialize methods. To do
	 * that, we convert the ObservaleLists to normal lists, when saving. And when
	 * loading we do this operation backwards.
	 */
	private static final long serialVersionUID = 0xCAFEL;
	private ObservableHashMap<String, PeerReviewer> peerReviewers;
	private ObservableList<Student> students;

	private static final ModelContainer MODELCONTAINER = new ModelContainer();

	public static ModelContainer getInstance() {
		return MODELCONTAINER;
	}

	/*
	 * because of the private constructor it is not possible to have more 
	 * than one instance of ModelContainer, which allows us to read the
	 * ModelContainer at every point of the project
	 */
	private ModelContainer() {
		this.peerReviewers = new ObservableHashMap<String, PeerReviewer>();
		this.students = new ObservableList<Student>();

	}

	public void putPeerReviewer(PeerReviewer peerReviewer) {
		String key = peerReviewer.getFirstName() + peerReviewer.getName();
		PeerReviewer existingPeerReviewer = this.peerReviewers.get(key);

		if (existingPeerReviewer != null) {
			existingPeerReviewer.addBachelorThesesAsFirstReviewer(peerReviewer.getFirstPeerReviewerRoles());
			existingPeerReviewer.addBachelorThesesAsSecondReviewer(peerReviewer.getSecondPeerReviewerRoles());
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

	public ArrayList<PeerReviewer> getPeerReviewers() {
		return new ArrayList<PeerReviewer>(peerReviewers.values());
	}

	public void removePeerReviewer(PeerReviewer p) {
		String key = p.getFirstName() + p.getName();
		this.peerReviewers.remove(key);
	}

	public ObservableList<Student> getStudents() {
		return this.students;
	}

	public void updateReviewer(PeerReviewer oldReviewer, PeerReviewer newReviewer) {
		String key = this.peerReviewers.getKey(oldReviewer);
		this.peerReviewers.replace(key, newReviewer);
	}

	public void clear() {
		this.peerReviewers.clear();
		this.students.clear();
	}

	public void updateStudent(Student oldStudent, Student newStudent) {
		int index = this.students.indexOf(oldStudent);
		
		/**
		 * Students at the peerReviewer are being handled as a whole student.
		 * The problem is, that if we change values at the "original" list of students, the 
		 * redundant list at the peer reviewer is not being updated.
		 * Therefore we look for the changed student at the peerReviewer list and replace it,
		 * with the changed one.
		 */
		this.updateStudentReferences(oldStudent, newStudent);
		
		/**
		 * The same goes in the other direction. If we change the peerReviewer reference at the student,
		 * we also have to check if the new relation is valid. If it is, we have to delete the reference at the 
		 * old peerReviewer and add a new reference to the new selected peerReviewer.
		 */
		this.updateFirstReviewerRelation(oldStudent, newStudent);
		this.updateSecondReviewerRelation(oldStudent, newStudent);
		
		if (index >= 0) {
			this.students.set(index, newStudent);
		}
	}
	
	private void updateStudentReferences(Student oldStudent, Student newStudent) {
		for(PeerReviewer p : this.peerReviewers.values()) {
			
			//update firstRoles list
			if(p.getFirstPeerReviewerRoles().contains(oldStudent)) {
				int index = p.getFirstPeerReviewerRoles().indexOf(oldStudent);
				p.getFirstPeerReviewerRoles().set(index, newStudent);
			}
			
			//update secondRoles list
			if(p.getSecondPeerReviewerRoles().contains(oldStudent)) {
				int index = p.getSecondPeerReviewerRoles().indexOf(oldStudent);
				p.getSecondPeerReviewerRoles().set(index, newStudent);
			}
			
			//update requested list
			if(p.getRequested().contains(oldStudent)) {
				int index = p.getRequested().indexOf(oldStudent);
				p.getRequested().set(index, newStudent);
			}
		}
		
	}


	/*
	 * replaces the oldStudent by newStudent in SecondPeerReviewer 
	 * and replaces the SecondPeerReviewer in Student as requested
	 */
	private void updateSecondReviewerRelation(Student oldStudent, Student newStudent) {
		if (!oldStudent.getSecondPeerReviewerKey().equals(newStudent.getSecondPeerReviewerKey())) {
			if (this.peerReviewers.get(newStudent.getSecondPeerReviewerKey()) != null) {
				this.peerReviewers.get(newStudent.getSecondPeerReviewerKey())
						.addRequest(newStudent);
				if (!oldStudent.getSecondPeerReviewerKey().isBlank())
					this.peerReviewers.get(oldStudent.getSecondPeerReviewerKey())
							.removeBachelorThesisAsSecondReviewer(oldStudent);
			} else {
				this.peerReviewers.get(oldStudent.getSecondPeerReviewerKey())
						.removeBachelorThesisAsSecondReviewer(newStudent);
			}

		}
	}
	/*
	 * replaces the oldStudent by newStudent in firstPeerReviewer 
	 * and replaces the firstPeerReviewer in Student
	 */
	private void updateFirstReviewerRelation(Student oldStudent, Student newStudent) {
		if (!oldStudent.getFirstPeerReviewerKey().equals(newStudent.getFirstPeerReviewerKey())) {
			if (this.peerReviewers.get(newStudent.getFirstPeerReviewerKey()) != null) {
				this.peerReviewers.get(newStudent.getFirstPeerReviewerKey())
						.addBachelorThesisAsFirstReviewer(newStudent);
				if (!oldStudent.getFirstPeerReviewerKey().isBlank())
					this.peerReviewers.get(oldStudent.getFirstPeerReviewerKey())
							.removeBachelorThesisAsFirstReviewer(oldStudent);
			} else {
				this.peerReviewers.get(oldStudent.getFirstPeerReviewerKey())
						.removeBachelorThesisAsFirstReviewer(newStudent);
			}

		}
	}

	// due to serialisation we need to resolve the singleton on read
	public Object readResolve() {
		return getInstance();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(new ArrayList<Student>(this.students));
		out.writeObject(new HashMap<String, PeerReviewer>(this.peerReviewers.convertToHashMap()));
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.students = new ObservableList<Student>((ArrayList<Student>) in.readObject());
		this.peerReviewers = new ObservableHashMap<String, PeerReviewer>((HashMap<String, PeerReviewer>) in.readObject());
	}

	public void addReviewerDataChangeObserver(Observer o) {
		this.peerReviewers.addObserver(o);
	}

	public void addStudentDataChangeObserver(Observer o) {
		this.students.addObserver(o);
	}

	public void removeStudent(Student studentToRemove) {
		this.students.remove(studentToRemove);
	}
}