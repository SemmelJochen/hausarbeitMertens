package view.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ModelContainer;
import model.PeerReviewer;
import model.Student;
import view.components.ContentPane;
import view.components.PieChart;
import view.components.PieChartLegend;
import view.components.ReviewerComboBox;
import view.components.Slice;

public class Diagram2 extends ContentPane implements Observer {

	private ReviewerComboBox reviewerComboBox;
	private PieChart pieChart;
	private PieChartLegend legend;

	public Diagram2() {
		super();
		this.reviewerComboBox = new ReviewerComboBox(ModelContainer.getInstance().getPeerReviewers());
		List<Slice> sliceData = createSliceData();
		this.pieChart = new PieChart(sliceData, new Dimension(500, 500));
		this.legend = new PieChartLegend(sliceData, this.pieChart);
		
		JPanel content = new JPanel();
		content.add(this.reviewerComboBox);
		content.add(this.legend);
		content.add(this.pieChart);
		
		this.setHeader(new JLabel("Wer arbeitet mit wem :O"));
		this.setContent(content);
	}

	public List<Slice> createSliceData() {
		List<Slice> slices = new ArrayList<Slice>();
		PeerReviewer mertens = this.reviewerComboBox.getSelectedPeerReviewer();
		if(mertens == null) {
			return slices;
		}
		
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		for(Student student: mertens.getFirstBachelorThesis()) {
			PeerReviewer tmp = student.getSecondPeerReviewer();
			String key = tmp.getFirstName() + " " + tmp.getName();
			if(result.get(key) == null){
				result.put(key, 1);
			}else {
				result.put(key, result.get(key) + 1);
			}
		}
		
		Set<String> keySet = result.keySet();
		for(String key: keySet) {
			slices.add(new Slice(result.get(key), 
					new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)), 
					key));
		}
		
		return slices;
	}


	@Override
	public void update(Observable o, Object arg) {
		this.reviewerComboBox.updateComboBoxModel(ModelContainer.getInstance().getPeerReviewers());
		List<Slice> updatedSlices = createSliceData();
		this.pieChart.udateSlices(updatedSlices);
		this.legend.updateLegend(updatedSlices);
	}


}
