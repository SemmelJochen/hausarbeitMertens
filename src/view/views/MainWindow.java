package view.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;
import controller.FileHandler.DialogType;
import view.components.ContentPane;
import view.components.ObserverMenuItem;
import view.views.diagrams.AllocationOfReviews;
import view.views.diagrams.CollaborationAsFirstPeerReviewer;
import view.views.diagrams.CollaborationAsSecondPeerReviewer;

public class MainWindow extends JFrame {

	private Controller controller;
	private Overview overview;
	private AllocationOfReviews diagram1;
	private CollaborationAsFirstPeerReviewer diagram2;
	private CollaborationAsSecondPeerReviewer diagram3;
	private DetailedPeerReviewerOverview reviewerOverview;
	private JFileChooser fileChooser;

	/**
	 * Create the frame.
	 */
	public MainWindow(Controller c) {
		super();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(new Dimension(1280, 1000));
		this.controller = c;
		this.fileChooser = new JFileChooser();
		this.setJMenuBar(buildMenuBar());
		this.buildInitialView();
		this.setResizable(false);
	}

	public void setCurrentlyVisible(ContentPane cPane) {
		this.setContentPane(cPane);
		this.revalidate();
		this.repaint();
	}

	public void appendDataChangeListeners() {
		this.controller.appendDataChangeListener(this.overview);
		this.controller.appendDataChangeListener(this.diagram1);
		this.controller.appendDataChangeListener(this.diagram2);
		this.controller.appendDataChangeListener(this.diagram3);
		this.controller.appendDataChangeListener(this.reviewerOverview);

	}

	public void createViews() {
		this.overview = new Overview(this.controller.getCommandController());
		this.diagram1 = new AllocationOfReviews();
		this.diagram2 = new CollaborationAsFirstPeerReviewer();
		this.diagram3 = new CollaborationAsSecondPeerReviewer();
		this.reviewerOverview = new DetailedPeerReviewerOverview(this.controller.getCommandController());
	}

	public void buildInitialView() {
		this.createViews();
		this.setContentPane(overview);
	}

	public JMenuBar buildMenuBar() {
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem, subMenuItem;
		ObserverMenuItem oMenuItem;

		// Create the File bar.
		menuBar = new JMenuBar();

		menu = new JMenu("Datei");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		// create submenu
		menuItem = new JMenuItem("Importieren");
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainWindow.this.chooseFile(DialogType.IMPORT)) {
					MainWindow.this.controller.runImport();
				}
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Exportieren");
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainWindow.this.chooseFile(DialogType.EXPORT)) {
					MainWindow.this.controller.runExport();
				}
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Loeschen aller Daten");
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MainWindow.this.showWarningMessage("Moechten Sie wirklich alle Daten loeschen?\n");
				switch (result) {
				case JOptionPane.YES_OPTION:
					// TODO save
					MainWindow.this.controller.clear();
					break;

				case JOptionPane.NO_OPTION:
					break;

				case JOptionPane.CANCEL_OPTION:
					break;
				}
			}
		});
		menu.add(menuItem);

		// Build the Ansicht menu.
		menu = new JMenu("Ansicht");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		// add Uebersicht menu
		submenu = new JMenu("Uebersicht");
		subMenuItem = new JMenuItem("Studentenuebersicht");
		subMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.overview.setActiveTab(Overview.STUDENT_TAB_ID);
				MainWindow.this.setCurrentlyVisible(MainWindow.this.overview);
			}
		});
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Gutachter Detailansicht");
		subMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.overview.setActiveTab(Overview.REVIEWER_TAB_ID);
				MainWindow.this.setCurrentlyVisible(MainWindow.this.overview);
			}
		});
		submenu.add(subMenuItem);
		menu.add(submenu);

		// add G-Einsicht
		menuItem = new JMenuItem("Gutachtereinsicht");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setCurrentlyVisible(MainWindow.this.reviewerOverview);

			}
		});
		menu.add(menuItem);

		// add menu for diagramms
		submenu = new JMenu("Diagramme");
		subMenuItem = new JMenuItem("Verteilung der Gutachten");
		subMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setCurrentlyVisible(MainWindow.this.diagram1);
			}
		});
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Erstgutachter Zusammenarbeit");
		subMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setCurrentlyVisible(MainWindow.this.diagram2);

			}
		});
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Zweitgutacher Zusammenarbeit");
		subMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setCurrentlyVisible(MainWindow.this.diagram3);

			}
		});
		submenu.add(subMenuItem);
		menu.add(submenu);

		// Build Edit menu in the menu bar.
		menu = new JMenu("Bearbeiten");
		menu.setMnemonic(KeyEvent.VK_N);

		// add edit action items
		menuItem = new JMenuItem("Speichern");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Dozentendatei (.mrtns)", "mrtns");
				chooser.setFileFilter(filter);
				if (MainWindow.this.controller.hasSaveFilePath()) {
					MainWindow.this.controller.saveDefault();
				} else {
					int returnVal = chooser.showSaveDialog(MainWindow.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						System.out
								.println("You chose to save this file: " + chooser.getSelectedFile().getAbsolutePath());
						MainWindow.this.controller.save(chooser.getSelectedFile().getAbsolutePath());
					}
				}
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Laden");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Dozentendatei (.mrtns)", "mrtns");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(MainWindow.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
					MainWindow.this.controller.load(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		menu.add(menuItem);

		menu.addSeparator();
		oMenuItem = new ObserverMenuItem("Undo"); // Action Listener einfuegen
		oMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.META_MASK));
		this.controller.getCommandController().getUndoStack().addPropertyChangeListener(oMenuItem);
		oMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("undo");
				MainWindow.this.controller.undo();
			}
		});
		menu.add(oMenuItem);

		oMenuItem = new ObserverMenuItem("Redo"); // Action Listener einfuegen
		oMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.META_MASK));
		this.controller.getCommandController().getRedoStack().addPropertyChangeListener(oMenuItem);
		oMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.controller.redo();
			}
		});

		menu.add(oMenuItem);
		menuBar.add(menu);

		return menuBar;
	}

	public int showWarningMessage(String message) {
		String[] buttonLabels = new String[] { "Ja", "Nein", "Abbrechen" };
		String defaultOption = buttonLabels[0];
		Icon icon = UIManager.getIcon("FileView.hardDriveIcon");

		return JOptionPane.showOptionDialog(this, message, "Warnung", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE, icon, buttonLabels, defaultOption);
	}

	/*
	 * the method returns false if the dialog gets canceled
	 * 
	 * depending on the dialogType it is
	 */
	public boolean chooseFile(DialogType dialogType) {
		int result = 0;
		FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("CSV-Dateien (*.csv)", "csv");
		this.fileChooser.addChoosableFileFilter(xmlFilter);
		this.fileChooser.setFileFilter(xmlFilter);

		if (dialogType == DialogType.IMPORT) {
			result = this.fileChooser.showOpenDialog(null);
		} else if (dialogType == DialogType.EXPORT) {
			result = this.fileChooser.showSaveDialog(null);
		}

		if (result == JFileChooser.CANCEL_OPTION) {
			return false;
		}

		// JFileChooser.APPROVE_OPTION
		// unreachable code for the CANCEL_OPTION
		this.controller.getFileHandler().setFile(this.fileChooser.getSelectedFile());
		return true;
	}

	enum DialogType {
		IMPORT, EXPORT;
	}
}