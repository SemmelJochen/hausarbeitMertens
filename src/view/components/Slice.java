package view.components;

import java.awt.Color;

import javax.swing.JComponent;

public class Slice extends JComponent {

	private int value;
	private Color color;
	private String dozent;
	
	/*
	 * isBrighter is true, if the mouse hovers over the JLabel in the diagram.
	 * it makes it easier to find the specific slice in the PieChart	 
	 */
	private boolean isBrighter;
	

	public Slice(int value, Color color, String name) {
		this.isBrighter = false;
		this.value = value;
		this.color = color;
		this.dozent = name;
	}

	public int getValue() {
		return this.value;
	}

	public Color getColor() {
		if(this.isBrighter) {
			return this.color.brighter();
		}
		return this.color;
	}

	public String getName() {
		return this.dozent;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setIsBrighter(boolean isBrighter) {
		this.isBrighter = isBrighter;
	}
	
	public void updateValue(int value) {
		this.value = value;
	}
}
