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
import view.components.ContentPane;
import view.components.ObserverMenuItem;

public class MainWindow extends JFrame {

	private Controller controller;
	private Overview overview;
	private Diagram1 diagram1;
	private Diagram2 diagram2;
	private Diagram3 diagram3;
	private DetailedPeerReviewerOverview reviewerOverview;

	/**
	 * Create the frame.
	 */
	public MainWindow(Controller c) {
		super();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(new Dimension(1280, 1000));
		this.controller = c;
		this.setJMenuBar(buildMenuBar());
		this.buildInitialView();
		this.appendStudentDataChangeListeners();
		this.appendReviewerDataChangeListeners();
		this.setResizable(false);
	}

	public void setCurrentlyVisible(ContentPane cPane) {
		this.setContentPane(cPane);
		this.revalidate();
		this.repaint();
	}

	public void appendStudentDataChangeListeners() {
		this.controller.appendStudentChangeListeners(this.overview);
		this.controller.appendStudentChangeListeners(this.diagram1);
		this.controller.appendStudentChangeListeners(this.diagram2);
		this.controller.appendStudentChangeListeners(this.diagram3);

	}

	public void appendReviewerDataChangeListeners() {
		this.controller.appendReviewerChangeListeners(this.overview);
		this.controller.appendReviewerChangeListeners(this.diagram1);
		this.controller.appendReviewerChangeListeners(this.diagram2);
		this.controller.appendReviewerChangeListeners(this.diagram3);
		this.controller.appendReviewerChangeListeners(this.reviewerOverview);
	}
	
	public void removeStudentDataChangeListeners() {
		this.controller.removeStudentChangeListeners(this.overview);
		this.controller.removeStudentChangeListeners(this.diagram1);

	}

	public void removeReviewerDataChangeListeners() {
		this.controller.removeReviewerChangeListeners(this.overview);
		this.controller.removeReviewerChangeListeners(this.diagram2);
		this.controller.removeReviewerChangeListeners(this.reviewerOverview);
	}

	public void createViews() {
		this.overview = new Overview(this.controller.getCommandController());
		this.diagram1 = new Diagram1();
		this.diagram2 = new Diagram2();
		this.diagram3 = new Diagram3();
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
				MainWindow.this.controller.runImport();
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Exportieren");
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.controller.runExport();
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Lösche alle Daten");
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
		subMenuItem = new JMenuItem("Diagramm 1");
		subMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setCurrentlyVisible(MainWindow.this.diagram1);
			}
		});
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Diagramm 2");
		subMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setCurrentlyVisible(MainWindow.this.diagram2);

			}
		});
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Diagramm 3");
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
				if(MainWindow.this.controller.hasSaveFilePath()) {
					MainWindow.this.controller.saveDefault();
				}else {
					int returnVal = chooser.showSaveDialog(MainWindow.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						System.out.println("You chose to save this file: " + chooser.getSelectedFile().getAbsolutePath());
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
		oMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.META_MASK));
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

		return JOptionPane.showOptionDialog(this, message,
				"Warnung", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, icon, buttonLabels,
				defaultOption);
	}

}