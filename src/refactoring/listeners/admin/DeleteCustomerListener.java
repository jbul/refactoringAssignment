package refactoring.listeners.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import refactoring.Customer;
import refactoring.Menu;

public class DeleteCustomerListener extends AdminActionListener {

	Menu parent;

	public DeleteCustomerListener(Menu parent) {
		super(parent);
	}

	

	@Override
	void performAdminAction() {
		if (parent.customer.getAccounts().size() > 0) {
			JOptionPane.showMessageDialog(parent.frame,
					"This customer has accounts. \n You must delete a customer's accounts before deleting a customer ",
					"Oops!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			parent.getCustomerService().remove(parent.customer);
			JOptionPane.showMessageDialog(parent.frame, "Customer Deleted ", "Success.",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}



	@Override
	String getMessageString() {
		// TODO Auto-generated method stub
		return "Customer ID of Customer You Wish to Delete:";
	}

}

/*
 * public void actionPerformed(ActionEvent ae) { boolean found = true, loop =
 * true;
 * 
 * if (customerList.isEmpty()) { JOptionPane.showMessageDialog(null,
 * "There are currently no customers to display. "); dispose(); admin(); } else
 * { { Object customerID = JOptionPane.showInputDialog(f,
 * "Customer ID of Customer You Wish to Delete:");
 * 
 * for (Customer aCustomer : customerList) {
 * 
 * if (aCustomer.getCustomerID().equals(customerID)) { found = true; customer =
 * aCustomer; loop = false; } }
 * 
 * if (found == false) { int reply = JOptionPane.showConfirmDialog(null, null,
 * "User not found. Try again?", JOptionPane.YES_NO_OPTION); if (reply ==
 * JOptionPane.YES_OPTION) { loop = true; } else if (reply ==
 * JOptionPane.NO_OPTION) { f.dispose(); loop = false;
 * 
 * admin(); } } else { if (customer.getAccounts().size() > 0) {
 * JOptionPane.showMessageDialog(f,
 * "This customer has accounts. \n You must delete a customer's accounts before deleting a customer "
 * , "Oops!", JOptionPane.INFORMATION_MESSAGE); } else {
 * customerList.remove(customer); JOptionPane.showMessageDialog(f,
 * "Customer Deleted ", "Success.", JOptionPane.INFORMATION_MESSAGE); } }
 * 
 * } } }
 */
