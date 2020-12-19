package view.components.piechart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PieChartLegend extends JPanel {

	private PieChart chart;

	public PieChartLegend(List<Slice> slices, PieChart chart) {
		this.chart = chart;
		this.updateLegend(slices);
	}

	public void updateLegend(List<Slice> updatedSlices) {
		this.removeAll();
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		for (Slice slice : updatedSlices) {

			JPanel legendElement = new JPanel();
			legendElement.setLayout(new BoxLayout(legendElement, BoxLayout.X_AXIS));
			legendElement.add(new BulletPoint(slice.getColor()));

			JLabel peerReviewerName = new JLabel(slice.getName() + " - " + slice.getValue());
			peerReviewerName.setPreferredSize(new Dimension(250, 30));

			peerReviewerName.addMouseListener(new MouseAdapter() {

				public void mouseEntered(MouseEvent event) {
					List<Slice> slices = updatedSlices;
					for (int i = 0; i < slices.size(); i++) {
						if (peerReviewerName.getText()
								.contains(slices.get(i).getName() + " - " + slices.get(i).getValue())) {
							slices.get(i).setIsBrighter(true);
						}
					}
					chart.repaint();
				}

				public void mouseExited(MouseEvent event) {
					List<Slice> slices = updatedSlices;
					for (int i = 0; i < slices.size(); i++) {
						if (peerReviewerName.getText()
								.contains(slices.get(i).getName() + " - " + slices.get(i).getValue())) {
							slices.get(i).setIsBrighter(false);
						}
					}
					chart.repaint();
				}
			});
			legendElement.add(peerReviewerName);
			this.add(legendElement);
			this.setAlignmentX(LEFT_ALIGNMENT);
		}
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
