package view.components;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenuItem;

import controller.ObservableCommandStack;

public class ObserverMenuItem extends JMenuItem implements Observer {

	public ObserverMenuItem(String title) {
		super(title);
		
		this.setEnabled(true);		
	}

	@Override
	public void update(Observable o, Object arg) {
		ObservableCommandStack os = (ObservableCommandStack) o;
		if(!os.isEmpty()) {
			this.setEnabled(true);
		}else {
			this.setEnabled(false);
		}		
	}
}
