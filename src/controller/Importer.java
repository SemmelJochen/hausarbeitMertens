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
	private File file;

	public Importer() {
		this.fileChooser = new JFileChooser();
	}

	public void chooseFile() {
		this.fileChooser.showOpenDialog(null);
		this.file = this.fileChooser.getSelectedFile();
	}
	
	/*
	 * the parameter modelContainer is changed after running the method!
	 */
	public void importCsvInModelContainer(ModelContainer modelContainer) {
		this.chooseFile();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(this.file));
			int i = 0;
			String line = br.readLine();
			while (line != null) {
				if (i > 3) {
					Student student = this.newStudent(line);
					PeerReviewer firstPeerReviewer = this.newFirstPeerReviewer(line);
					PeerReviewer secondPeerReviewer = this.newSecondPeerReviewer(line);
					firstPeerReviewer.addFirstBachelorThesis(student);
					student.setFirstPeerReviewer(firstPeerReviewer);
					
					if(secondPeerReviewer != null) {
						secondPeerReviewer.addSecondBachelorThesis(student);
						modelContainer.putPeerReviewer(secondPeerReviewer);
						student.setSecondPeerReviewer(secondPeerReviewer);
					}
					
					modelContainer.putPeerReviewer(firstPeerReviewer);
					modelContainer.putStudent(student);
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
		Student result = new Student(names[1], names[0], "", entries[1], entries[2], entries[3]);
		return result;
	}

	public PeerReviewer newFirstPeerReviewer(String line) {
		String[] peerReviewerString = line.split(";")[4].split(" ");
		return this.createPeerReviewer(peerReviewerString);
		
	}
	
	public PeerReviewer newSecondPeerReviewer(String line) {
		String[] peerReviewerString = line.split(";")[5].split(" ");
		if(peerReviewerString.length == 1) return null;
		return this.createPeerReviewer(peerReviewerString);
	}
	
	private PeerReviewer createPeerReviewer(String[] peerReviewerString) {
		int i = 0;
		String title = "";
		while(peerReviewerString[i].contains(".") && i < peerReviewerString.length) {
			title += peerReviewerString[i] + " ";
			i++;
		}
		title = title.trim();
		
		String firstnames = "";
		while(i < peerReviewerString.length-1) {
			firstnames += peerReviewerString[i] + " ";
			i++;
		}
		firstnames = firstnames.trim();
		return new PeerReviewer(title, peerReviewerString[peerReviewerString.length-1], firstnames, "", -1);
	}
}