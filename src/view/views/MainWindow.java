package view.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import model.PeerReviewer;
import model.Student;
import model.TableData;
import view.components.ObserverMenuItem;
import controller.Controller;

public class MainWindow extends JFrame {
	
	private JOptionPane exitDialog = new JOptionPane(
		    "The only way to close this dialog is by\n"
		    	    + "pressing one of the following buttons.\n"
		    	    + "Do you understand?",
		    	    JOptionPane.QUESTION_MESSAGE,
		    	    JOptionPane.YES_NO_OPTION);

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1280, 720));
		
		//this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setJMenuBar(buildMenuBar());
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				//TODO when closing the window, save the data
				MainWindow.this.exitDialog.setVisible(true);
			}
		});
	}
	
	public JMenuBar buildMenuBar() {
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem, subMenuItem;
		ObserverMenuItem oMenuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();
		
		//Build the first menu.
		menu = new JMenu("Ansicht");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		//add Uebersicht menu
		submenu = new JMenu("Uebersicht");
		subMenuItem = new JMenuItem("Studentenuebersicht");
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Erstgutachteruebersicht");
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Zweitgutachteruebersicht");
		submenu.add(subMenuItem);
		menu.add(submenu);

		//add G-Einsicht
		menuItem = new JMenuItem("Gutachtereinsicht");
		menu.add(menuItem);
		
		submenu = new JMenu("Diagramme");
		subMenuItem = new JMenuItem("Diagramm 1");
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Diagramm 2");
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Diagramm 3");
		submenu.add(subMenuItem);
		menu.add(submenu);


		//Build Edit menu in the menu bar.
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_N);
		
		//add edit action items 
		menuItem = new JMenuItem("Save");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.META_MASK));
		menu.add(menuItem);
		menu.addSeparator();
		oMenuItem = new ObserverMenuItem("Undo");  //Action Listener einfuegen
		oMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_Z, ActionEvent.META_MASK));
		menu.add(oMenuItem);
		oMenuItem = new ObserverMenuItem("Redo");  //Action Listener einfuegen
		oMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_Y, ActionEvent.META_MASK));

		menu.add(oMenuItem);
		
		menuBar.add(menu);
	
		return menuBar;
	}
	
}