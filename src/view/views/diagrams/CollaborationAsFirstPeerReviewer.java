package view.views.diagrams;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import view.components.piechart.PieChart;
import view.components.piechart.PieChartLegend;
import view.components.piechart.Slice;
import view.components.reviewercombobox.ReviewerComboBox;

public class CollaborationAsFirstPeerReviewer extends ContentPane implements Observer, PropertyChangeListener {

	protected ReviewerComboBox reviewerComboBox;
	protected PieChart pieChart;
	protected PieChartLegend legend;

	public CollaborationAsFirstPeerReviewer() {
		super();
		this.reviewerComboBox = new ReviewerComboBox(ModelContainer.getInstance().getPeerReviewers());
		this.reviewerComboBox.addCustomPropertyChangeListener(this);

		List<Slice> sliceData = createSliceData();
		this.pieChart = new PieChart(sliceData, new Dimension(600, 600));
		this.legend = new PieChartLegend(sliceData, this.pieChart);

		JPanel content = new JPanel();
		content.add(this.reviewerComboBox);
		content.add(this.legend);
		content.add(this.pieChart);

		this.setHeader("Zusammenarbeit als Erstgutachter");
		this.setContent(content);
	}

	public List<Slice> createSliceData() {
		List<Slice> slices = new ArrayList<Slice>();
		PeerReviewer mertens = this.reviewerComboBox.getSelectedPeerReviewer();
		if (mertens == null) {
			return slices;
		}

		HashMap<String, Integer> result = new HashMap<String, Integer>();
		for (Student student : mertens.getFirstPeerReviewerRoles()) {
			PeerReviewer secondPeerReviewer = ModelContainer.getInstance()
					.getPeerReviewer(student.getSecondPeerReviewerKey());
			if (secondPeerReviewer != null) {
				String key = secondPeerReviewer.getFirstName() + " " + secondPeerReviewer.getName();
				if (result.get(key) == null) {
					result.put(key, 1);
				} else {
					result.put(key, result.get(key) + 1);
				}
			}
		}

		Set<String> keySet = result.keySet();
		for (String key : keySet) {
			slices.add(new Slice(result.get(key),
					new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)),
					key));
		}
		return slices;
	}

	@Override
	public void update(Observable o, Object arg) {
		this.reviewerComboBox.updateComboBoxModel(ModelContainer.getInstance().getPeerReviewers());
		this.update();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.update();
	}

	private void update() {
		List<Slice> updatedSlices = createSliceData();
		this.pieChart.udateSlices(updatedSlices);
		this.legend.updateLegend(updatedSlices);
	}
}
