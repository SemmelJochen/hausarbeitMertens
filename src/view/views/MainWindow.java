package view.views;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import view.components.ObserverMenuItem;

public class MainWindow extends JFrame {

	private JOptionPane exitDialog = new JOptionPane("Möchten Sie speichern, bevor Sie das Programm schließen ?\n",
			JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		super();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(new Dimension(1280, 720));
		this.setJMenuBar(buildMenuBar());
	}

	public JMenuBar buildMenuBar() {
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem, subMenuItem;
		ObserverMenuItem oMenuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("Ansicht");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		// add Uebersicht menu
		submenu = new JMenu("Uebersicht");
		subMenuItem = new JMenuItem("Studentenuebersicht");
		subMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setVisible(false);
				MainWindow.this.setContentPane(new StudentOverview());
				MainWindow.this.setVisible(true);
				
			}
		});
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Erstgutachteruebersicht");
		subMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setVisible(false);
				MainWindow.this.setContentPane(new FirstReviewerOverview());
				MainWindow.this.setVisible(true);
			}
		});
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Zweitgutachteruebersicht");
		subMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setVisible(false);
				MainWindow.this.setContentPane(new SecondReviewerOverview());
				MainWindow.this.setVisible(true);
				
			}
		});
		submenu.add(subMenuItem);
		menu.add(submenu);

		// add G-Einsicht
		menuItem = new JMenuItem("Gutachtereinsicht");
		menu.add(menuItem);

		// add menu for diagramms
		submenu = new JMenu("Diagramme");
		subMenuItem = new JMenuItem("Diagramm 1");
		subMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.setVisible(false);
				MainWindow.this.setContentPane(new DiagrammOverview());
				MainWindow.this.setVisible(true);
			}
		});
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Diagramm 2");
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Diagramm 3");
		submenu.add(subMenuItem);
		menu.add(submenu);

		// Build Edit menu in the menu bar.
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_N);

		// add edit action items
		menuItem = new JMenuItem("Save");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
		menu.add(menuItem);
		menu.addSeparator();
		oMenuItem = new ObserverMenuItem("Undo"); // Action Listener einfuegen
		oMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.META_MASK));
		menu.add(oMenuItem);
		oMenuItem = new ObserverMenuItem("Redo"); // Action Listener einfuegen
		oMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.META_MASK));

		menu.add(oMenuItem);
		menuBar.add(menu);

		return menuBar;
	}

	public int showWarningMessage() {
		String[] buttonLabels = new String[] { "Ja", "Nein", "Abbrechen" };
		String defaultOption = buttonLabels[0];
		Icon icon = UIManager.getIcon("FileView.hardDriveIcon");

		return JOptionPane.showOptionDialog(this,
				"Möchten Sie speichern, bevor Sie das Programm schließen ?\n", "Warnung",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, icon, buttonLabels, defaultOption);
	}

}