package view.components;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JMenuItem;

public class ObserverMenuItem extends JMenuItem implements PropertyChangeListener {

	public ObserverMenuItem(String title) {
		super(title);
		
		this.setEnabled(false);		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.setEnabled(!(boolean) evt.getNewValue());
	}
}