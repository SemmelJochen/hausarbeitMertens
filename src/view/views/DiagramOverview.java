package view.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS));
		HashMap<Color, String> map = this.pieChart.getSlices();
		Set<Color> keySet = map.keySet();
		for(Color color: keySet ){
			JPanel legendElement = new JPanel();
			legendElement.setLayout(new BoxLayout(legendElement, BoxLayout.X_AXIS));
			legendElement.add(new Triangle(color));
			
			if(map.get(color).contains("Wiebke")) {
				System.out.println(map.get(color));
				System.out.println(color);
			}
			
			legendElement.add(this.getJLabel(map.get(color)), BorderLayout.CENTER);
			
			legend.add(legendElement);
		}
		return legend;
	}

	private JLabel getJLabel(String name) {
		JLabel result = new JLabel(name);
		result.setBorder(new EmptyBorder(0, 10, 5, 0));
		return result;
	}

	class Triangle extends JComponent {

		private Color color;

		public Triangle(Color color) {
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