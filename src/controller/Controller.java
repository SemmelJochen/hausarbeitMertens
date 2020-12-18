package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observer;

import javax.swing.JOptionPane;

import model.ModelContainer;
import view.views.MainWindow;

public class Controller {

	private FileHandler csvHandler;
	private MainWindow window;
	private CommandController commandController;
	private ModelContainer modelContainer = ModelContainer.getInstance();
	private String filePath;

	public Controller() {
		this.csvHandler = new FileHandler();
		this.csvHandler = new FileHandler();
		this.commandController = new CommandController();
	}

	public void appendDataChangeListener(Observer o) {
		this.modelContainer.addStudentDataChangeObserver(o);
		this.modelContainer.addReviewerDataChangeObserver(o);
	}
	
	public void clear() {
		this.modelContainer.clear();
	}
	
	public FileHandler getFileHandler() {
		return this.csvHandler;
	}

	public void run() {
		this.window = new MainWindow(this);
		this.window.appendDataChangeListeners();
		
		// add lister to listen to window close operation
		this.window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				handleClose();
			}
		});
		window.setVisible(true);
	}

	private void handleClose() {
		if (this.hasUnsavedData()) {
			int message = window.showWarningMessage("Moechten Sie speichern, bevor Sie das Programm schliessen ?\n");
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
	
	public boolean hasSaveFilePath() {
		if(this.filePath != null) {
			return true;
		}else {
			return false;
		}
	}

	public void save(String filePath) {
		this.filePath = filePath;
		if (!filePath.endsWith(".mrtns")) {
			filePath += ".mrtns";
		}
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = new FileOutputStream(filePath);
			oos = new ObjectOutputStream(fos);
			// call our custom write method
			this.modelContainer.writeExternal(oos);
			oos.flush();
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveDefault() {
		this.save(this.filePath);
	}

	public void load(String filePath) {
		this.filePath = filePath;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.window.buildInitialView();
		this.window.appendDataChangeListeners();
		this.window.revalidate();
		this.window.repaint();
	}

	public void runImport() {
		this.csvHandler.importCsvInModelContainer(this.modelContainer);
	}

	public void runExport() {
		this.csvHandler.exportToCsv();
	}

	public void redo() {
		this.commandController.redo();
	}

	public void undo() {
		this.commandController.undo();
	}

	public CommandController getCommandController() {
		return this.commandController;
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.run();
	}

}