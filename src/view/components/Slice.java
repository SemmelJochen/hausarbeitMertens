package view.components;

import java.awt.Color;

import javax.swing.JComponent;

public class Slice extends JComponent {

	private boolean isBrighter;
	private int value;
	private Color color;
	private String dozent;

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
			return this.color.brighter().brighter();
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
