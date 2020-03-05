package refactoring.listeners.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import refactoring.AccountTransaction;
import refactoring.CustomerCurrentAccount;
import refactoring.Menu;

public abstract class TransactionButtonListener implements ActionListener {

	boolean loop = true;
	boolean on = true;
	double balance = 0;
	Menu parent;
	//double amount = 0;

	public TransactionButtonListener(Menu parent) {
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Check user pin
		checkPin();
		
		// Get amount of transaction
		double amount = getAmount();
		
		// Perform transaction and add it to transaction list
		AccountTransaction t = performTransaction(amount);
		parent.customerAccount.getTransactionList().add(t);
		
		// Show results of transaction
		showResults(amount);
	}




	public void checkPin() {

		if (parent.customerAccount instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) parent.customerAccount).getAtm().getPin();
			loop = true;

			while (loop) {
				if (count == 0) {
					JOptionPane.showMessageDialog(parent.frame, "Pin entered incorrectly 3 times. ATM card locked.",
							"Pin", JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount) parent.customerAccount).getAtm().setValid(false);
					parent.customer(parent.cust);
					loop = false;
					on = false;
				}

				String Pin = JOptionPane.showInputDialog(parent.frame, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);

				if (on) {
					if (checkPin == i) {
						loop = false;
						JOptionPane.showMessageDialog(parent.frame, "Pin entry successful", "Pin",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						count--;
						JOptionPane.showMessageDialog(parent.frame, "Incorrect pin. " + count + " attempts remaining.",
								"Pin", JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}
		}

	}
	
	public abstract AccountTransaction performTransaction(double number);
	
	public abstract double getAmount();
	
	public abstract void showResults(double amount);

}
