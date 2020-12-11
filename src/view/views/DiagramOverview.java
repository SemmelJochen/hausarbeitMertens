package view.views;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.components.ContentPane;
import view.components.PieChart;

public class DiagramOverview extends ContentPane {

	private static final long serialVersionUID = 57267L;
	PieChart pieChart;
	
	public DiagramOverview() {
		super();
		this.pieChart = new PieChart();
		JPanel content = new JPanel();
		content.add(this.buildLegend());
		content.add(pieChart);
		this.setHeader(new JLabel("Anzahl der Bachelorthesen eines Dozenten relativ zu allen Thesen"));
		this.setContent(content);
	}
	
	public JPanel buildLegend() {
		JPanel legend = new JPanel();
		legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS));
		for(String peerReviewerString: this.pieChart.getSlices().values()) {
			legend.add(new JLabel(peerReviewerString));
		}
		return legend;
	}
}