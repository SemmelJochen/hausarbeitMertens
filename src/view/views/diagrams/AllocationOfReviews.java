package view.views.diagrams;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.ModelContainer;
import view.components.ContentPane;
import view.components.PieChart;
import view.components.PieChartLegend;
import view.components.Slice;

public class AllocationOfReviews extends ContentPane implements Observer {

	private static final long serialVersionUID = 57267L;
	private PieChart pieChart;
	private PieChartLegend legend;

	public AllocationOfReviews() {
		super();
		List<Slice> sliceData = createSliceData();
		this.pieChart = new PieChart(sliceData, new Dimension(600, 600));
		this.legend = new PieChartLegend(sliceData, this.pieChart);
		JPanel content = new JPanel();
		content.add(legend);
		content.add(pieChart);
		this.setHeader("Anzahl der Gutachten eines Dozenten relativ zu allen Gutachten");
		this.setContent(content);
	}

	public List<Slice> createSliceData() {
		List<Slice> slices = new ArrayList<Slice>();
		ModelContainer.getInstance().getPeerReviewers().forEach(peerReviewer -> {
			if (peerReviewer.getBachelorThesisesCount() > 0) {
				slices.add(new Slice(peerReviewer.getBachelorThesisesCount(),
						new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
								(int) (Math.random() * 255)),
						peerReviewer.getFirstName() + " " + peerReviewer.getName()));
			}
		});
		return slices;
	}

	@Override
	public void update(Observable o, Object arg) {
		List<Slice> updatedSlices = createSliceData();
		this.pieChart.udateSlices(updatedSlices);
		this.legend.updateLegend(updatedSlices);
	}

}