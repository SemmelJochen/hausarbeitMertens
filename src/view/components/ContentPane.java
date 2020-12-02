package view.components;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.views.MainWindow;

public class ContentPane extends JPanel {
	
	private JPanel header = new JPanel();
	private JPanel content = new JPanel();
	private MainWindow mainWindow;
	
	
	//constructor for test purposes
	public ContentPane(String title) {
		//TODO add MigLayout -- external Lib
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel siteTitle = new JLabel(title);
		header.add(siteTitle);
		content.add(new JButton("Test"));
		
		
		this.add(this.header, BorderLayout.PAGE_START);
		this.add(this.content, BorderLayout.CENTER);
	}
	
	//constructor for initialize the component with no content
	public ContentPane() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(this.header, BorderLayout.PAGE_START);
		this.add(this.content, BorderLayout.CENTER);
	}
	
	//constructor for initialize component with content
	public ContentPane(Component headerComponent, Component contentComponent) {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setHeader(headerComponent);
		this.setContent(contentComponent);
		
		this.add(this.header, BorderLayout.PAGE_START);
		this.add(this.content, BorderLayout.CENTER);
	
	}
	
	public void setContent(Component c) {
		this.content.removeAll();
		this.content.add(c);
	}
	
	public void setHeader(Component c) {
		this.header.removeAll();
		this.header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		this.header.add(c);
	}
}