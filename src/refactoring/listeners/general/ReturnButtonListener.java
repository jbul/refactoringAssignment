package refactoring.listeners.general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import refactoring.Menu;

public class ReturnButtonListener implements ActionListener {
	
	Menu parent;
	
	public ReturnButtonListener(Menu menu) {
		this.parent = menu;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.frame.dispose();
		parent.menuStart();
	}

}
