package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
					PeerReviewer firstPeerReviewer = this.newFirstPeerReviewer(line);
					PeerReviewer secondPeerReviewer = this.newSecondPeerReviewer(line);
					firstPeerReviewer.addFirstBachelorThesis(student);
					modelContainer.putPeerReviewer(firstPeerReviewer);
					
					if(secondPeerReviewer != null) {
						secondPeerReviewer.addSecondBachelorThesis(student);
						modelContainer.putPeerReviewer(secondPeerReviewer);
					}
				} else {
					i++;
				}
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Student newStudent(String line) {
		String[] entries = line.split(";");
		String[] names = entries[0].split(", ");
		return new Student(names[1], names[0], "", entries[1], entries[2], entries[3]);
	}

	public PeerReviewer newFirstPeerReviewer(String line) {
		String[] entries = line.split(";");
		String[] peerReviewerString = entries[4].split(" ");
		
		return this.createPeerReviewer(peerReviewerString);
		
	}
	
	public PeerReviewer newSecondPeerReviewer(String line) {
		String[] entries = line.split(";");
		System.out.println(entries[5]);
		String[] peerReviewerString = entries[5].split(" ");
		if(peerReviewerString.length == 1) return null;
		
		return this.createPeerReviewer(peerReviewerString);
	}
	
	private PeerReviewer createPeerReviewer(String[] peerReviewerString) {
		if (peerReviewerString[0].equals("Dr.")) {
			return new PeerReviewer(peerReviewerString[0], peerReviewerString[3], peerReviewerString[2], "", -1);
		}
		if (peerReviewerString[0].equals("Prof.")) {
			return new PeerReviewer(peerReviewerString[0] + " " + peerReviewerString[1], peerReviewerString[3],
					peerReviewerString[2], "", -1);
		}
		return new PeerReviewer("", peerReviewerString[1], peerReviewerString[0], "", -1);
	}
}