package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContentPane extends JPanel {

	private JPanel header = new JPanel();
	private JPanel content = new JPanel();

	// constructor for initialize the component with no content
	public ContentPane() {
		// create new window with no content (empty panel)
		this("", new JPanel());
	}

	// constructor for initialize component with content
	public ContentPane(String title, Component contentComponent) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.setHeader(title);
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

	public void setHeader(String title) {
		this.header.setVisible(false);
		this.header.removeAll();
		this.header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		this.header.add(this.designHeader(title));
		this.header.setVisible(true);
	}

	public JLabel designHeader(String title) {
		JLabel text = new JLabel(title);
		text.setLocation(15, 15);
		text.setSize(500, 150);
		text.setForeground(Color.black);
		text.setFont(new Font("Arial", Font.BOLD, 38));
		this.header.add(text);
		return text;
	}
}
