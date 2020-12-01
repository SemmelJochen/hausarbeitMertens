package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import model.ModelContainer;
import model.PeerReviewer;
import model.Person;
import model.Student;

public class Importer {

	private JFileChooser fileChooser;
	private ModelContainer model;

	public Importer() {
		this.fileChooser = new JFileChooser();
		this.model = new ModelContainer();
	}
	
	public void chooseFile() {
		this.fileChooser.showOpenDialog(null);
	}
	
	public void work() {
		this.chooseFile();
		File file = this.fileChooser.getSelectedFile();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.contains("/")) {
		    	   String[] entries = line.split(";");
		    	   String[] names = entries[0].split(", ");
		    	   Person student = new Student(names[1], names[0], entries[1], "email", entries[2], entries[3]);
		    	   System.out.println(student);
		    	   System.out.println("");
		       }
		    }
		}
		catch(Exception e) {
			
		}
		
	}

	
	
	public static void main(String[] args) {
		Importer imp = new Importer();
		imp.work();
	}
}
