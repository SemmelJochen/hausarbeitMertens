package view.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ContentPane extends JPanel {

	private JPanel header = new JPanel();
	private JPanel content = new JPanel();

	// constructor for initialize the component with no content
	public ContentPane() {
		//create new window with no content (empty panel)
		this(new JPanel(), new JPanel());
	}

	// constructor for initialize component with content
	public ContentPane(Component headerComponent, Component contentComponent) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.setHeader(headerComponent);
		this.setContent(contentComponent);

		this.add(this.header, BorderLayout.PAGE_START);
		this.add(this.content, BorderLayout.CENTER);

	}

	public void setContent(Component c) {
		this.content.setVisible(false);
		this.content.removeAll();
		this.content.add(c);
		this.content.setVisible(true);
	}

	public void setHeader(Component c) {
		this.header.setVisible(false);
		this.header.removeAll();
		this.header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		this.header.add(c);
		this.header.setVisible(true);
	}
}
