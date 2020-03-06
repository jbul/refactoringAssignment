package refactoring.listeners.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import refactoring.Menu;
import refactoring.constants.Constants;
import refactoring.constants.MessageConstants;

public abstract class AdminActionListener implements ActionListener {

	Menu parent;

	public AdminActionListener(Menu parent) {
		this.parent = parent;
	}

	public void actionPerformed(ActionEvent ae) {
		parent.frame.dispose();

		if (parent.getCustomerService().isEmpty()) {
			JOptionPane.showMessageDialog(parent.frame, MessageConstants.NO_CUSTOMER_MESSAGE, Constants.OOPS,
					JOptionPane.INFORMATION_MESSAGE);
			parent.frame.dispose();
			parent.admin();

		} else {
			boolean loop = true;

			while (loop) {
				Object customerID = JOptionPane.showInputDialog(parent.frame, getMessageString());
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
					loop = false;
					performAdminAction();
				}
			}
		}
	}

	abstract void performAdminAction();

	abstract String getMessageString();

}
