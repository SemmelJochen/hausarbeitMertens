package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;

import model.PeerReviewer;
import model.Student;
import model.TableData;
import view.ContentPane;
import view.MainWindow;
import view.Table;

public class Controller {

	private Importer excelImporter;
	private ObservableCommandStack undoStack, redoStack;

	public Controller() {
		this.excelImporter = new Importer();
		this.undoStack = new ObservableCommandStack();
		this.redoStack = new ObservableCommandStack();
	}

	public void redo() {
		Command command = this.redoStack.pop();
		command.execute();
		this.undoStack.add(command);
	}

	public void undo() {
		Command command = this.undoStack.pop();
		command.undo();
		this.redoStack.add(command);
	}
	
	
	/**
	 * temporary method to fill data
	 * @return {@link TableData}
	 * @deprecated
	 */
	public TableData createSampleTableData(){
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("Kalle", "Heino", "kalle@heino.de", "WI62/19", "zeb", "Netzwerke"));
		students.add(new Student("Peter", "Günther", "peter@guenther.de", "WI62/19", "Microsoft", "Datenstrutkuren"));
		students.add(new Student("Schimmer", "Ralf", "schimmer@ralf.de", "WI62/19", "euronics", "Infrastrukturen"));
		
		List<PeerReviewer> reviewer = new ArrayList<PeerReviewer>();
		reviewer.add(new PeerReviewer("Prof.", "Schmitz", "Karl", "karl@schmitz.de", 20));
		
		
		TableData data = TableData.builder()
				.withColumn("Vorname", students.stream().map(e -> e.getFirstName()).collect(Collectors.toList()))//
				.withColumn("Nachname", students.stream().map(e -> e.getName()).collect(Collectors.toList()))//
				.withColumn("Thema", students.stream().map(e -> e.getSubject()).collect(Collectors.toList()))//
				.withColumn("Praxispartner", students.stream().map(e -> e.getPracticePartner()).collect(Collectors.toList()))//
				.withColumn("Studiengruppe", students.stream().map(e -> e.getStudentGroup()).collect(Collectors.toList()))//
				.withColumn("Kapazität vom Prüfer", reviewer.stream().map(e -> e.getCapacity()).collect(Collectors.toList()))//
				.build();
		
		return data;
	}
	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		Controller controller = new Controller();
		
		Table table = new Table(controller.createSampleTableData());
		ContentPane contentPane = new ContentPane(new JLabel("DemoDataTable") ,table.getContent());
		
		window.setContentPane(contentPane);
		window.setVisible(true);
	}
}