package refactoring.listeners.admin;

import javax.swing.JOptionPane;

import refactoring.Menu;
import refactoring.constants.Constants;

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
					Constants.OOPS, JOptionPane.INFORMATION_MESSAGE);
		} else {
			parent.getCustomerService().remove(parent.customer);
			JOptionPane.showMessageDialog(parent.frame, "Customer Deleted ", Constants.SUCCESS,
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	@Override
	String getMessageString() {
		return "Customer ID of Customer You Wish to Delete:";
	}

}

