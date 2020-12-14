package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import model.ModelContainer;
import model.PeerReviewer;
import model.Student;
import model.TableData;
import view.components.ObserverMenuItem;
import view.views.MainWindow;

public class Controller {

	private FileHandler csvHandler;
	private ObservableCommandStack undoStack, redoStack;
	private MainWindow window;
	private ModelContainer modelContainer = ModelContainer.getInstance();

	public void addUndoMenuItem(ObserverMenuItem ob) {
		this.undoStack.addObserver(ob);
	}

	public void addRedoMenuItem(ObserverMenuItem ob) {
		this.redoStack.addObserver(ob);
	}

	public void addDataChangeObserver(Observer o) {
		this.modelContainer.getStudents().addObserver(o);
	}

	public Controller() {
		this.csvHandler = new FileHandler();
		this.undoStack = new ObservableCommandStack();
		this.redoStack = new ObservableCommandStack();

	}

	public void run() {
		this.window = new MainWindow(this);

		// add lister to listen to window close operation
		this.window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				// TODO when closing the window, save the data
				handleClose();
			}
		});

//		Table table = new Table(this.createSampleTableData());

		window.setVisible(true);
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
	 * 
	 * @return {@link TableData}
	 * @deprecated
	 */
	public TableData createSampleTableData() {
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("Kalle", "Heino", "kalle@heino.de", "WI62/19", "zeb", "Netzwerke", "LOL"));
		students.add(new Student("Peter", "Guenther", "peter@guenther.de", "WI62/19", "Microsoft", "Datenstrutkuren", ":O"));
		students.add(new Student("Schimmer", "Ralf", "schimmer@ralf.de", "WI62/19", "euronics", "Infrastrukturen", "GJE"));

		List<PeerReviewer> reviewer = new ArrayList<PeerReviewer>();
		reviewer.add(new PeerReviewer("Prof.", "Schmitz", "Karl", "karl@schmitz.de", 20));

		TableData data = TableData.builder()
				.withColumn("Vorname", students.stream().map(e -> e.getFirstName()).collect(Collectors.toList()))//
				.withColumn("Nachname", students.stream().map(e -> e.getName()).collect(Collectors.toList()))//
				.withColumn("Thema", students.stream().map(e -> e.getSubject()).collect(Collectors.toList()))//
				.withColumn("Praxispartner",
						students.stream().map(e -> e.getPracticePartner()).collect(Collectors.toList()))//
				.withColumn("Studiengruppe",
						students.stream().map(e -> e.getStudentGroup()).collect(Collectors.toList()))//
				.withColumn("Kapazitaet vom Pruefer",
						reviewer.stream().map(e -> e.getCapacity()).collect(Collectors.toList()))//
				.withColumn("Bemerkung",
						students.stream().map(e -> e.getRemark()).collect(Collectors.toList()))//
				.build();

		return data;
	}

	private void handleClose() {
		if (this.hasUnsavedData()) {
			int message = window.showWarningMessage();
			switch (message) {
			case JOptionPane.YES_OPTION:
				// TODO save
				this.window.dispose();
				break;

			case JOptionPane.NO_OPTION:
				this.window.dispose();
				break;

			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
	}

	private boolean hasUnsavedData() {
		// TODO check if there's still something unsaved
		return true;
	}

	public void save(String filePath) {
		if (!filePath.endsWith(".mrtns")) {
			filePath += ".mrtns";
		}
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = new FileOutputStream(filePath);
			oos = new ObjectOutputStream(fos);
			//call our custom write method
			this.modelContainer.writeExternal(oos);
			oos.flush();
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void load(String filePath) {
		this.window.setVisible(false);
		if (!filePath.endsWith(".mrtns")) {
			filePath += ".mrtns";
		}
		FileInputStream fis;
		ObjectInputStream ois;
		try {
			fis = new FileInputStream(filePath);
			ois = new ObjectInputStream(fis);
			this.modelContainer.readExternal(ois);
			ois.close();
			fis.close();

			/**
			 * TODO add other actions to do from here - clear MainWindow contentPane and add
			 * new content - new modelContainer
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.window.buildInitialView();
		this.window.appendDataChangeListeners();
		this.window.setVisible(true);
	}

	public void runImport() {
		this.csvHandler.importCsvInModelContainer(this.modelContainer);
	}

	public void runExport() {
		this.csvHandler.exportToCsv();
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.run();
	}

}