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
	
	public void updateStudent(Student oldStudent, Student newStudent) {
		System.out.println(oldStudent);
		System.out.println();
		System.out.println(newStudent);
		int index = this.students.indexOf(oldStudent);
		System.out.println("--------------------------");
		for(Student s: this.students) {
			if(s.getFirstName().equals("Josua"))
			System.out.println(s);
		}
		this.students.set(index, newStudent);
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
	
	public void removeStudentDataChangeObserver(Observer o) {
		this.students.deleteObserver(o);
	}
	
	public void removeReviewerDataChangeObserver(Observer o) {
		this.peerReviewers.deleteObserver(o);
	}
	
	public void fireChange() {
		this.peerReviewers.hasChanged();
		this.peerReviewers.notifyObservers();
		this.students.hasChanged();
		this.students.notifyObservers();
	}

	public void removeStudent(Student studentToRemove) {
		this.students.remove(studentToRemove);
	}
}