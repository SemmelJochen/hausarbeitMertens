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

		GridLayout layout = new GridLayout();
		layout.setVgap(3);
		layout.setColumns(2);
		layout.setRows(slices.size());
		legend.setLayout(layout);

		for (Slice slice : slices) {

			JPanel legendElement = new JPanel();
			legendElement.setPreferredSize(new Dimension(300, 30));
			legendElement.add(new BulletPoint(slice.getColor()));
			
			JLabel peerReviewerName = this.getJLabel(slice.getName());
			peerReviewerName.setHorizontalTextPosition(SwingConstants.LEFT);		
			peerReviewerName.addMouseListener(new MouseAdapter() {

				public void mouseEntered(MouseEvent event) {
					ArrayList<Slice> slices = pieChart.getSlices();
					for (int i = 0; i < slices.size(); i++) {
						if (slices.get(i).getName().equals(peerReviewerName.getText())) {
							slices.get(i).setIsBrighter(true);
						}
					}
					pieChart.repaint();
				}

				public void mouseExited(MouseEvent event) {
					ArrayList<Slice> slices = pieChart.getSlices();
					for (int i = 0; i < slices.size(); i++) {
						if (slices.get(i).getName().equals(peerReviewerName.getText())) {
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

	private JLabel getJLabel(String name) {
		JLabel result = new JLabel(name);
		result.setBorder(new EmptyBorder(0, 30, 0, 0));
		result.setHorizontalAlignment(SwingConstants.RIGHT);
		result.setHorizontalTextPosition(SwingConstants.RIGHT);
		return result;
	}

	class BulletPoint extends JComponent {

		private Color color;

		public BulletPoint(Color color) {
			super();
			this.color = color;
			this.setPreferredSize(new Dimension(30, 30));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(this.color);
			g.fillArc(15, 7, 15, 15, 0, 360);
		}
	}
}