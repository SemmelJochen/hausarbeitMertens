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
		this.revalidate();
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		if (this.slices.isEmpty()) {
			// TODO: Exeptionhandling if list is empty
			return;
		}
		super.paintComponent(g);
		drawPie((Graphics2D) g, new Rectangle(150, 50, 400, 400));
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
			int arcAngle = this.round(slices.get(i).getValue() * 360 / total);
			g.setColor(slices.get(i).getColor());
			g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
			curValue += slices.get(i).getValue();
		}
	}
	
	/*
	 * the round method reduced the missing accurance, caused by casting the double to an int.
	 * The graphic will look smoother, but it isn´t a complete solution.
	 */
	private int round(double toRound) {
		if((toRound - (int) toRound) * 10 >= 5) {
			//mind 0.5
			return (int) (toRound + 1);
		}
		return (int) toRound;
	}

	public List<Slice> getSlices() {
		return this.slices;
	}
}