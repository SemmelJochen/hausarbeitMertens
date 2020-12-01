package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.KeyStroke;
import model.PeerReviewer;
import model.Student;
import model.TableData;

import controller.Controller;

public class MainWindow extends JFrame {
	
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1280, 720));
		
		//this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setJMenuBar(buildMenuBar());
	}
	
	public JMenuBar buildMenuBar() {
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem, subMenuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();
		
		//Build the first menu.
		menu = new JMenu("Ansicht");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		//add Uebersicht menu
		submenu = new JMenu("Übersicht");
		subMenuItem = new JMenuItem("Studentenübersicht");
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Erstgutachterübersicht");
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Zweitgutachterübersicht");
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
		menuItem = new JMenuItem("Undo");  //Action Listener einfuegen
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_Z, ActionEvent.META_MASK));
		menu.add(menuItem);
		menuItem = new JMenuItem("Redo");  //Action Listener einfuegen
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_Y, ActionEvent.META_MASK));

		menu.add(menuItem);
		
		menuBar.add(menu);
	
		return menuBar;
	}
	
}