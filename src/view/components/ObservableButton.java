package view.components;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenuItem;

import controller.ObservableCommandStack;

public class ObservableButton extends JMenuItem implements Observer {

	public ObservableButton(String title) {
		super(title);
		
		this.setEnabled(true);		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(true) {
			this.setEnabled(true);
		}else {
			this.setEnabled(false);
		}		
	}
}
