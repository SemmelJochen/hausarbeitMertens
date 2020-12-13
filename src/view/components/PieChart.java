package view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JComponent;

public class PieChart extends JComponent {

	private static final long serialVersionUID = 1534531035L;
	private List<Slice> slices;

	public PieChart(List<Slice> slices, Dimension dimension) {
		super();
		this.slices = slices;
		this.setPreferredSize(dimension);
		this.repaint();
	}

	public void udateSlices(List<Slice> slices) {
		this.slices = slices;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (this.slices.isEmpty()) {
			// TODO: Exeptionhandling if list is empty
			return;
		}
		super.paintComponent(g);
		drawPie((Graphics2D) g, new Rectangle(150, 50, 350, 350));
	}

	public void drawPie(Graphics2D g, Rectangle area) {

		double total = 0;

		for (int i = 0; i < slices.size(); i++) {
			total += slices.get(i).getValue();
		}

		double curValue = 0;
		int startAngle = 0;
		for (int i = 0; i < slices.size(); i++) {
			startAngle = (int) (curValue * 360 / total);
			int arcAngle = (int) (slices.get(i).getValue() * 360 / total);
			g.setColor(slices.get(i).getColor());
			g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
			curValue += slices.get(i).getValue();
		}
	}

	public List<Slice> getSlices() {
		return this.slices;
	}
}