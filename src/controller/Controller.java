package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.PeerReviewer;
import model.Student;
import model.TableData;

import view.components.ContentPane;
import view.components.ObserverMenuItem;
import view.components.TabbedPane;
import view.components.Table;
import view.views.FirstReviewerOverview;
import view.views.MainWindow;
import view.views.SecondReviewerOverview;
import view.views.StudentOverview;

public class Controller {

	private Importer csvImporter;
	private ObservableCommandStack undoStack, redoStack;
	private StudentOverview studentOverview;
	private FirstReviewerOverview firstReviewer;
	private SecondReviewerOverview secondReviewer;
	private TabbedPane tabbedPane;
	
	public void setStudentOverview(StudentOverview studentOverview) {
		this.studentOverview = studentOverview;
	}
	
	public void setFirstReviewer (FirstReviewerOverview firstReviewer) {
		this.firstReviewer = firstReviewer;
	}
	
	public void setSecondReviewer (SecondReviewerOverview secondReviewer) {
		this.secondReviewer = secondReviewer;
	}	
	
	public void setTabbedPane(TabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	public void addUndoMenuItem(ObserverMenuItem ob) {
		this.undoStack.addObserver(ob);
	}
	public void addRedoMenuItem(ObserverMenuItem ob) {
		this.redoStack.addObserver(ob);
	}

	public Controller() {
		this.csvImporter = new Importer();
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
		students.add(new Student("Peter", "Guenther", "peter@guenther.de", "WI62/19", "Microsoft", "Datenstrutkuren"));
		students.add(new Student("Schimmer", "Ralf", "schimmer@ralf.de", "WI62/19", "euronics", "Infrastrukturen"));
		
		List<PeerReviewer> reviewer = new ArrayList<PeerReviewer>();
		reviewer.add(new PeerReviewer("Prof.", "Schmitz", "Karl", "karl@schmitz.de", 20));
		
		
		TableData data = TableData.builder()
				.withColumn("Vorname", students.stream().map(e -> e.getFirstName()).collect(Collectors.toList()))//
				.withColumn("Nachname", students.stream().map(e -> e.getName()).collect(Collectors.toList()))//
				.withColumn("Thema", students.stream().map(e -> e.getSubject()).collect(Collectors.toList()))//
				.withColumn("Praxispartner", students.stream().map(e -> e.getPracticePartner()).collect(Collectors.toList()))//
				.withColumn("Studiengruppe", students.stream().map(e -> e.getStudentGroup()).collect(Collectors.toList()))//
				.withColumn("Kapazitaet vom Pruefer", reviewer.stream().map(e -> e.getCapacity()).collect(Collectors.toList()))//
				.build();
		
		return data;
	}
	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		Controller controller = new Controller();
		TabbedPane tabbedPane = new TabbedPane();
		
		StudentOverview studentOverview = new StudentOverview();
		FirstReviewerOverview firstReviewer = new FirstReviewerOverview();
		SecondReviewerOverview secondReviewer = new SecondReviewerOverview();
		
		Table table = new Table(controller.createSampleTableData());
		ContentPane contentPane = new ContentPane(new JLabel("DemoDataTable") ,table.getContent());
		
		//Table table1 = new Table(studentOverview.studentsOverview());
		//ContentPane contentPane = new ContentPane(new JLabel("Studentenuebersicht") ,table1.getContent());
		
		//Table table2 = new Table(firstReviewer.firstReviewerOverview());
		//ContentPane contentPane = new ContentPane(new JLabel("Erstgutachteruebersicht") ,table2.getContent());
		
		//Table table3 = new Table(secondReviewer.secondReviewerOverview());
		//ContentPane contentPane = new ContentPane(new JLabel("Zweitgutachteruebersicht") ,table3.getContent());
		
		//tabbedPane.setStudentTable();
		window.setContentPane(contentPane);
		//tabbedPane.setVisible(true);
		window.setVisible(true);
	}
}