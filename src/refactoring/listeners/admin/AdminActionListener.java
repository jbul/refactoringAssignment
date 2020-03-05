package refactoring.listeners.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import refactoring.ATMCard;
import refactoring.AccountTransaction;
import refactoring.CustomerCurrentAccount;
import refactoring.CustomerDepositAccount;
import refactoring.Menu;

public abstract class AdminActionListener implements ActionListener  {

	Menu parent;

	public AdminActionListener(Menu parent) {
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent ae) {
		parent.frame.dispose();

		if (parent.getCustomerService().isEmpty()) {
			JOptionPane.showMessageDialog(parent.frame, "There are no customers yet!", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
			parent.frame.dispose();
			parent.admin();
		} else {
			boolean loop = true;
			while (loop) {
				Object customerID = JOptionPane.showInputDialog(parent.frame, getMessageString());
						//"Customer ID of Customer You Wish to Add an Account to:");

				parent.customer = parent.getCustomerService().getCustomer(customerID);

				/*
				 * for (Customer aCustomer : parent.customerList) {
				 * 
				 * if (aCustomer.getCustomerID().equals(customerID)) { found = true;
				 * parent.customer = aCustomer; } }
				 */

				if (parent.customer == null) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						loop = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						parent.frame.dispose();
						loop = false;
						parent.admin();
					}
				} else {
					loop = false;
					performAdminAction();
				}
			}
		}
	}
	
	abstract void performAdminAction();
	
	abstract String getMessageString();
	
}
