package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContentPane extends JPanel {

	private JPanel header = new JPanel();
	private JPanel content = new JPanel();

	// constructor for initialize the component with no content
	public ContentPane() {
		// create new window with no content (empty panel)
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
		this.header.setFont(new Font("Arial", 0, 44));
		//this.designHeader();
		this.header.setVisible(true);
	}
	
	public void designHeader() {
		//Font font = new Font("Arial", Font.BOLD, 44);
		//this.header.setFont(new Font("Arial", Font.BOLD, 44));

		//JLabel text = new JLabel();
		//text.setLocation(15, 15);
		//text.setSize(500, 150);
		//text.setForeground(Color.red);
		//text.setFont(font);
		//this.header.add(text);
		//this.header.setVisible(true);
	}
}
