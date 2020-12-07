package view.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.components.ContentPane;
import view.components.Diagramm;

public class DiagrammOverview extends ContentPane {

	private static final long serialVersionUID = 57267L;
	
	public DiagrammOverview() {
		super();
		Diagramm diagramm = new Diagramm();
		
		this.setHeader(new JLabel("Diagramm und so"));
		this.setContent(diagramm);
		diagramm.repaint();
		diagramm.setVisible(true);
	}

	public static void main(String[] a) {
		JFrame frame = new JFrame();
		frame.setSize(1000, 750);
		Diagramm d = new Diagramm();
		
		JPanel panel = new JPanel();
		panel.add(d);
		panel.add(new JButton("Test"));
		panel.setOpaque(true);
		
		panel.setVisible(true);
		d.setVisible(true);
		frame.add(panel);
		frame.setVisible(true);
	}
}