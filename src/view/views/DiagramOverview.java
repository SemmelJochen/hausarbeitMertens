package view.views;

import javax.swing.JLabel;

import view.components.ContentPane;
import view.components.PieChart;

public class DiagramOverview extends ContentPane {

	private static final long serialVersionUID = 57267L;
	
	public DiagramOverview() {
		super();
		PieChart pieChart = new PieChart();
		
		this.setHeader(new JLabel("Anzahl der Bachelorthesen eines Dozenten relativ zu allen Thesen"));
		this.setContent(pieChart);
	}
}