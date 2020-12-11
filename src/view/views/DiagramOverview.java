package view.views;

import javax.swing.JLabel;

import view.components.ContentPane;
import view.components.PieChart;

public class DiagramOverview extends ContentPane {

	private static final long serialVersionUID = 57267L;
	
	public DiagramOverview() {
		super();
		PieChart diagramm = new PieChart();
		
		this.setHeader(new JLabel("Diagramm und so"));
		this.setContent(diagramm);
		diagramm.repaint();
		diagramm.setVisible(true);
	}
}