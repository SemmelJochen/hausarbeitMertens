package view;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import controller.ObservableCommandStack;

public class ObserverButton extends JButton implements Observer {

	public ObserverButton(String titel) {
		//super(title);
		
		this.setEnabled(false);		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		ObservableCommandStack os = (ObservableCommandStack) o;
		if(!os.isEmpty()) {
			this.setEnabled(true);
		}else {
			this.setEnabled(false);
		}		
	}
	
	

}
