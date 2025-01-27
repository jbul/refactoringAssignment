package refactoring.listeners.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import refactoring.Menu;
import refactoring.constants.MessageConstants;

public class DeleteAccountListener implements ActionListener {

	Menu parent;

	public DeleteAccountListener(Menu parent) {
		this.parent = parent;
	}

	public void actionPerformed(ActionEvent ae) {
		boolean loop = true;

		{
			Object customerID = JOptionPane.showInputDialog(parent.frame,
					"Customer ID of Customer from which you wish to delete an account");
			
			parent.customer = parent.getCustomerService().getCustomer(customerID);

			if (parent.customer == null) {
				int reply = JOptionPane.showConfirmDialog(null, null, MessageConstants.USER_NOT_FOUND,
						JOptionPane.YES_NO_OPTION);
				
				if (reply == JOptionPane.YES_OPTION) {
					loop = true;
				} else if (reply == JOptionPane.NO_OPTION) {
					parent.frame.dispose();
					loop = false;

					parent.admin();
				}
			} else {
				// Here I would make the user select an account to delete from a combo box. If
				// the account had a balance of 0 then it would be deleted. (I do not have time
				// to do this)
			}

		}
	}

}
