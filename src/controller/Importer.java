package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.swing.JFileChooser;

import model.ModelContainer;
import model.PeerReviewer;
import model.Student;

public class Importer {

	private JFileChooser fileChooser;

	public Importer() {
		this.fileChooser = new JFileChooser();
	}

	public void chooseFile() {
		this.fileChooser.showOpenDialog(null);
	}
	
	/*
	 * the parameter modelContainer is changed after running the method!
	 */
	public void importCsvInModelContainer(ModelContainer modelContainer) {
		this.chooseFile();
		File file = this.fileChooser.getSelectedFile();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			int i = 0;
			String line = br.readLine();
			while (line != null) {
				if (i > 3) {
					Student student = this.newStudent(line);
					PeerReviewer peerReviewer = this.newPeerReviewer(line);
					peerReviewer.addFirstBachelorThesis(student);

					modelContainer.putPeerReviewer(peerReviewer);
				} else {
					i++;
				}
				line = br.readLine();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public Student newStudent(String line) {
		String[] entries = line.split(";");
		String[] names = entries[0].split(", ");
		return new Student(names[1], names[0], "", entries[1], entries[2], entries[3]);
	}

	public PeerReviewer newPeerReviewer(String line) {
		String[] entries = line.split(";");
		String[] peerReviewerString = entries[4].split(" ");
		if (peerReviewerString[0].equals("Dr.")) {
			return new PeerReviewer(peerReviewerString[0], peerReviewerString[3], peerReviewerString[2], "", -1);
		}
		if (peerReviewerString[0].equals("Prof.")) {
			return new PeerReviewer(peerReviewerString[0] + " " + peerReviewerString[1], peerReviewerString[3],
					peerReviewerString[2], "", -1);
		}

		return new PeerReviewer("", peerReviewerString[1], peerReviewerString[0], "", -1);
	}

	public static void main(String[] args) {
		Importer imp = new Importer();
		ModelContainer m = new ModelContainer();
		imp.importCsvInModelContainer(m);
		m.printPeerReviewers();
		
	}
}
