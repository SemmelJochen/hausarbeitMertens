package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	
	private JPanel header = new JPanel();
	private JPanel content = new JPanel();
	
	public ContentPanel(String title) {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel siteTitle = new JLabel(title);
		header.add(siteTitle);
		content.add(new JButton("Test"));
		
		
		this.add(header, BorderLayout.PAGE_START);
		this.add(content, BorderLayout.CENTER);
	}
	
	public void setContent(Component c) {
		this.content.add(c);
	}
	
	public void setHeader(Component c) {
		this.content.add(c);
	}
}
