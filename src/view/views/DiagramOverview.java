package view.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ModelContainer;
import view.components.ContentPane;
import view.components.PieChart;
import view.components.Slice;

public class DiagramOverview extends ContentPane implements Observer {

	private static final long serialVersionUID = 57267L;
	private PieChart pieChart;

	public DiagramOverview() {
		super();

		this.pieChart = new PieChart(createSliceData(), new Dimension(500, 500));
		JPanel content = new JPanel();
		content.add(this.buildLegend());
		content.add(pieChart);
		this.setHeader(new JLabel("Anzahl der Bachelorthesen eines Dozenten relativ zu allen Thesen"));
		this.setContent(content);
	}

	public JPanel buildLegend() {
		JPanel legend = new JPanel();
		List<Slice> slices = this.pieChart.getSlices();

		BoxLayout layout = new BoxLayout(legend, BoxLayout.Y_AXIS);
		legend.setLayout(layout);

		for (Slice slice : slices) {

			JPanel legendElement = new JPanel();
			legendElement.setLayout(new BoxLayout(legendElement, BoxLayout.X_AXIS));
			legendElement.add(new BulletPoint(slice.getColor()));
			
			JLabel peerReviewerName = new JLabel(slice.getName() + " - " + slice.getValue());
			peerReviewerName.setPreferredSize(new Dimension(250, 30));

			peerReviewerName.addMouseListener(new MouseAdapter() {

				public void mouseEntered(MouseEvent event) {
					List<Slice> slices = pieChart.getSlices();
					for (int i = 0; i < slices.size(); i++) {
						if (peerReviewerName.getText().contains(slices.get(i).getName())) {
							slices.get(i).setIsBrighter(true);
						}
					}
					pieChart.repaint();
				}

				public void mouseExited(MouseEvent event) {
					List<Slice> slices = pieChart.getSlices();
					for (int i = 0; i < slices.size(); i++) {
						if (peerReviewerName.getText().contains(slices.get(i).getName())) {
							slices.get(i).setIsBrighter(false);
						}
					}
					pieChart.repaint();
				}
			});
			legendElement.add(peerReviewerName);

			legend.add(legendElement);
			legend.setAlignmentX(LEFT_ALIGNMENT);
		}
		return legend;
	}

	public List<Slice> createSliceData() {
		List<Slice> slices = new ArrayList<Slice>();
		ModelContainer.getInstance().getPeerReviewers().forEach(peerReviewer -> {
			slices.add(new Slice(peerReviewer.getCountFirstBachelorThesises(),
					new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)),
					peerReviewer.getFirstName() + " " + peerReviewer.getName()));
		});
		return slices;
	}


	@Override
	public void update(Observable o, Object arg) {
		this.pieChart.udateSlices(createSliceData());
	}

	class BulletPoint extends JComponent {

		private Color color;

		public BulletPoint(Color color) {
			super();
			this.color = color;
			this.setPreferredSize(new Dimension(40, 30));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(this.color);
			g.fillArc(15, 7, 15, 15, 0, 360);
		}
	}

}