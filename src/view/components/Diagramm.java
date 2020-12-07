package view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

class Slice {
	double value;
	Color color;

	public Slice(double value, Color color) {
		this.value = value;
		this.color = color;
	}
}

public class Diagramm extends JComponent {

	private static final long serialVersionUID = 1534531035L;
	Slice[] slices = { new Slice(5, Color.black), new Slice(33, Color.green), new Slice(20, Color.yellow),
			new Slice(15, Color.red) };

	public Diagramm() {
		super();
		this.setSize(300, 300);
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawPie((Graphics2D) g, new Rectangle(0, 0, 100, 100), slices);
	}

	public void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
		double total = 0;

		for (int i = 0; i < slices.length; i++) {
			total += slices[i].value;
		}

		double curValue = 0;
		int startAngle = 0;
		for (int i = 0; i < slices.length; i++) {
			startAngle = (int) (curValue * 360 / total);
			int arcAngle = (int) (slices[i].value * 360 / total);
			g.setColor(slices[i].color);
			g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
			curValue += slices[i].value;
		}
	}
}