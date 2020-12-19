package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

import model.ModelContainer;
import model.PeerReviewer;
import model.Student;

/**
 * 
 * @author felix
 *
 */
public class FileHandler {

	/*
	 * the fileChooser allows us to select a file with an explorer and saves the
	 * file int the attribute file.
	 */
	private File file;

	/*
	 * the header consists of the first rows of the muster.csv and is read during
	 * the import
	 * 
	 * he is needed to rebuild the header while the Students are exported.
	 */
	private String[] header;

	public FileHandler() {
		this.header = new String[4];
	}

	public void setFile(File file) {
		this.file = file;
	}

	/*
	 * the parameter modelContainer is changed after running the method!
	 */
	public void importCsvInModelContainer(ModelContainer modelContainer) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(this.file));
			int i = 0;
			String line = br.readLine();
			while (line != null) {
				if (i >= this.header.length) {
					Student student = this.newStudent(line);
					PeerReviewer firstPeerReviewer = this.newFirstPeerReviewer(line);
					PeerReviewer secondPeerReviewer = this.newSecondPeerReviewer(line);

					if (!firstPeerReviewer.isDummy()) {
						firstPeerReviewer.addBachelorThesisAsFirstReviewer(student);
						modelContainer.putPeerReviewer(firstPeerReviewer);
					}
					String key = firstPeerReviewer.getFirstName() + firstPeerReviewer.getName();
					student.setFirstPeerReviewerKey(key);

					if (!secondPeerReviewer.isDummy()) {
						secondPeerReviewer.addBachelorThesisAsSecondReviewer(student);
						modelContainer.putPeerReviewer(secondPeerReviewer);
					}
					key = secondPeerReviewer.getFirstName() + secondPeerReviewer.getName();
					student.setSecondPeerReviewerKey(key);

					modelContainer.addStudent(student);
				} else {
					this.header[i] = line;
					i++;
				}
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportToCsv() {
		ObservableList<Student> students = ModelContainer.getInstance().getStudents();
		FileWriter writer;
		try {
			writer = new FileWriter(this.file);
			for (int i = 0; i < this.header.length; i++) {
				writer.append(this.header[i]);
				writer.append(System.lineSeparator());
			}

			String seperator = ";";
			for (Student student : students) {
				writer.append(student.getName() + ", " + student.getFirstName() + seperator + student.getStudentGroup()
						+ seperator + student.getPracticePartner() + seperator + student.getSubject() + seperator
						+ this.createPeerReviewerStringForWriting(
								ModelContainer.getInstance().getPeerReviewer(student.getFirstPeerReviewerKey()))
						+ seperator
						+ this.createPeerReviewerStringForWriting(
								ModelContainer.getInstance().getPeerReviewer(student.getSecondPeerReviewerKey()))
						+ seperator + student.getRemark());
				writer.append(System.lineSeparator());
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String createPeerReviewerStringForWriting(PeerReviewer peerReviewer) {
		if (peerReviewer == null) {
			return "";
		}
		return peerReviewer.getTitle() + " " + peerReviewer.getFirstName() + " " + peerReviewer.getName();
	}

	public Student newStudent(String line) {
		String[] entries = line.split(";");
		String[] names = entries[0].split(", ");
		if (names.length == 1) {
			return Student.createDummy();
		}
		if (entries.length == 7) {
			return new Student(names[0], names[1], "", entries[1], entries[2], entries[3], entries[6]);
		}
		return new Student(names[1], names[0], "", entries[1], entries[2], entries[3], "");
	}

	public PeerReviewer newFirstPeerReviewer(String line) {
		String[] peerReviewerString = line.split(";")[4].split(" ");
		if (peerReviewerString.length == 1) {
			return PeerReviewer.createDummy();
		}
		return this.createPeerReviewer(peerReviewerString);
	}

	public PeerReviewer newSecondPeerReviewer(String line) {
		String[] peerReviewerString = line.split(";")[5].split(" ");
		if (peerReviewerString.length == 1) {
			return PeerReviewer.createDummy();
		}
		return this.createPeerReviewer(peerReviewerString);
	}

	private PeerReviewer createPeerReviewer(String[] peerReviewerString) {
		int i = 0;
		String title = "";
		while (peerReviewerString[i].contains(".") && i < peerReviewerString.length) {
			title += peerReviewerString[i] + " ";
			i++;
		}
		title = title.trim();

		String firstnames = "";
		while (i < peerReviewerString.length - 1) {
			firstnames += peerReviewerString[i] + " ";
			i++;
		}
		firstnames = firstnames.trim();
		return new PeerReviewer(title, peerReviewerString[peerReviewerString.length - 1], firstnames, "", -1, "");
	}
}