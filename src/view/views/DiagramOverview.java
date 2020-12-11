package view.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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
		HashMap<Color, String> map = this.pieChart.getSlices();
		Set<Color> keySet = map.keySet();
		
		GridLayout layout = new GridLayout();
		layout.setVgap(2);
		layout.setColumns(2);
		layout.setRows(keySet.size());
		legend.setLayout(layout);
		
		for(Color color: keySet ){
			
			JPanel legendElement = new JPanel();
			legendElement.setLayout(new BoxLayout(legendElement, BoxLayout.X_AXIS));
			legendElement.add(new BulletPoint(color));
			
			if(map.get(color).contains("Wiebke")) {
				System.out.println(map.get(color));
				System.out.println(color);
			}
			
			legendElement.add(this.getJLabel(map.get(color)));

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
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(this.color);
			g.fillArc(0, 0, 10, 10, 0, 360);
		}
	}
}