package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;

import model.ModelContainer;
import model.PeerReviewer;

class Slice {
	private double value;
	private Color color;
	private String dozent;

	public Slice(double value, Color color, String name) {
		this.value = value;
		this.color = color;
		this.dozent = name;
	}

	public double getValue() {
		return this.value;
	}

	public Color getColor() {
		return this.color;
	}

	public String getName() {
		return this.dozent;
	}
}

public class PieChart extends JComponent {

	private static final long serialVersionUID = 1534531035L;
	ArrayList<Slice> slices;

	public PieChart() {
		super();
		this.slices = new ArrayList<Slice>();
		this.createSliceData();
		this.setPreferredSize(new Dimension(500, 500));
		this.repaint();
	}

	private void createSliceData() {
		for (PeerReviewer peerReviewer : ModelContainer.getInstance().getPeerReviewers()) {
			this.slices.add(new Slice(peerReviewer.getCountFirstBachelorThesises(),
					new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)),
					peerReviewer.getFirstName() + " " + peerReviewer.getName()));
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (this.slices.isEmpty()) {
			// TODO: Exeptionhandling if list is empty
			System.out.println("EMPTY");
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

	public HashMap<Color, String> getSlices() {
		HashMap<Color, String> result = new HashMap<Color, String>();
		for (int i = 0; i < this.slices.size(); i++) {
			if (this.slices.get(i).getValue() > 0) {
				result.put(this.slices.get(i).getColor(), this.slices.get(i).getName());
			}
		}
		return result;
	}
}