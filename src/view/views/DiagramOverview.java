package view.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.components.ContentPane;
import view.components.PieChart;
import view.components.Slice;

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
		ArrayList<Slice> slices = this.pieChart.getSlices();

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
					ArrayList<Slice> slices = pieChart.getSlices();
					for (int i = 0; i < slices.size(); i++) {
						if (peerReviewerName.getText().contains(slices.get(i).getName())) {
							slices.get(i).setIsBrighter(true);
						}
					}
					pieChart.repaint();
				}

				public void mouseExited(MouseEvent event) {
					ArrayList<Slice> slices = pieChart.getSlices();
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