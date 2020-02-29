package refactoring.listeners.admin;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import refactoring.Customer;
import refactoring.CustomerCurrentAccount;
import refactoring.CustomerDepositAccount;
import refactoring.Menu;

public class BankChargesButtonListener implements ActionListener {
	
	Menu parent;

	public BankChargesButtonListener(Menu parent) {
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent ae) {

		boolean loop = true;

		boolean found = false;

		if (parent.customerList.isEmpty()) {
			JOptionPane.showMessageDialog(parent.f, "There are no customers yet!", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
			parent.f.dispose();
			parent.admin();

		} else {
			while (loop) {
				Object customerID = JOptionPane.showInputDialog(parent.f,
						"Customer ID of Customer You Wish to Apply Charges to:");

				for (Customer aCustomer : parent.customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						parent.customer = aCustomer;
						loop = false;
					}
				}

				if (found == false) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						loop = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						parent.f.dispose();
						loop = false;

						parent.admin();
					}
				} else {
					parent.f.dispose();
					parent.f = new JFrame("Administrator Menu");
					parent.f.setSize(400, 300);
					parent.f.setLocation(200, 200);
					parent.f.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					parent.f.setVisible(true);

					JComboBox<String> box = new JComboBox<String>();
					for (int i = 0; i < parent.customer.getAccounts().size(); i++) {

						box.addItem(parent.customer.getAccounts().get(i).getNumber());
					}

					box.getSelectedItem();

					JPanel boxPanel = new JPanel();
					boxPanel.add(box);

					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Charge");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = parent.f.getContentPane();
					content.setLayout(new GridLayout(2, 1));

					content.add(boxPanel);
					content.add(buttonPanel);

					if (parent.customer.getAccounts().isEmpty()) {
						JOptionPane.showMessageDialog(parent.f,
								"This customer has no accounts! \n The admin must add acounts to this customer.",
								"Oops!", JOptionPane.INFORMATION_MESSAGE);
						parent.f.dispose();
						parent.admin();
					} else {

						for (int i = 0; i < parent.customer.getAccounts().size(); i++) {
							if (parent.customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
								parent.acc = parent.customer.getAccounts().get(i);
							}
						}

						continueButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								String euro = "\u20ac";

								if (parent.acc instanceof CustomerDepositAccount) {

									JOptionPane.showMessageDialog(parent.f,
											"25" + euro + " deposit account fee aplied.", "",
											JOptionPane.INFORMATION_MESSAGE);
									parent.acc.setBalance(parent.acc.getBalance() - 25);
									JOptionPane.showMessageDialog(parent.f, "New balance = " + parent.acc.getBalance(),
											"Success!", JOptionPane.INFORMATION_MESSAGE);
								}

								if (parent.acc instanceof CustomerCurrentAccount) {

									JOptionPane.showMessageDialog(parent.f,
											"15" + euro + " current account fee aplied.", "",
											JOptionPane.INFORMATION_MESSAGE);
									parent.acc.setBalance(parent.acc.getBalance() - 25);
									JOptionPane.showMessageDialog(parent.f, "New balance = " + parent.acc.getBalance(),
											"Success!", JOptionPane.INFORMATION_MESSAGE);
								}

								parent.f.dispose();
								parent.admin();
							}
						});

						returnButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								parent.f.dispose();
								parent.menuStart();
							}
						});

					}
				}
			}
		}

	}

}

/*
 * public void actionPerformed(ActionEvent ae) {
 * 
 * boolean loop = true;
 * 
 * boolean found = false;
 * 
 * if (customerList.isEmpty()) { JOptionPane.showMessageDialog(f,
 * "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
 * f.dispose(); admin();
 * 
 * } else { while (loop) { Object customerID = JOptionPane.showInputDialog(f,
 * "Customer ID of Customer You Wish to Apply Charges to:");
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
 * admin(); } } else { f.dispose(); f = new JFrame("Administrator Menu");
 * f.setSize(400, 300); f.setLocation(200, 200); f.addWindowListener(new
 * WindowAdapter() { public void windowClosing(WindowEvent we) { System.exit(0);
 * } }); f.setVisible(true);
 * 
 * JComboBox<String> box = new JComboBox<String>(); for (int i = 0; i <
 * customer.getAccounts().size(); i++) {
 * 
 * box.addItem(customer.getAccounts().get(i).getNumber()); }
 * 
 * box.getSelectedItem();
 * 
 * JPanel boxPanel = new JPanel(); boxPanel.add(box);
 * 
 * JPanel buttonPanel = new JPanel(); JButton continueButton = new
 * JButton("Apply Charge"); JButton returnButton = new JButton("Return");
 * buttonPanel.add(continueButton); buttonPanel.add(returnButton); Container
 * content = f.getContentPane(); content.setLayout(new GridLayout(2, 1));
 * 
 * content.add(boxPanel); content.add(buttonPanel);
 * 
 * if (customer.getAccounts().isEmpty()) { JOptionPane.showMessageDialog(f,
 * "This customer has no accounts! \n The admin must add acounts to this customer."
 * , "Oops!", JOptionPane.INFORMATION_MESSAGE); f.dispose(); admin(); } else {
 * 
 * for (int i = 0; i < customer.getAccounts().size(); i++) { if
 * (customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) { acc =
 * customer.getAccounts().get(i); } }
 * 
 * continueButton.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent ae) { String euro = "\u20ac";
 * 
 * if (acc instanceof CustomerDepositAccount) {
 * 
 * JOptionPane.showMessageDialog(f, "25" + euro +
 * " deposit account fee aplied.", "", JOptionPane.INFORMATION_MESSAGE);
 * acc.setBalance(acc.getBalance() - 25); JOptionPane.showMessageDialog(f,
 * "New balance = " + acc.getBalance(), "Success!",
 * JOptionPane.INFORMATION_MESSAGE); }
 * 
 * if (acc instanceof CustomerCurrentAccount) {
 * 
 * JOptionPane.showMessageDialog(f, "15" + euro +
 * " current account fee aplied.", "", JOptionPane.INFORMATION_MESSAGE);
 * acc.setBalance(acc.getBalance() - 25); JOptionPane.showMessageDialog(f,
 * "New balance = " + acc.getBalance(), "Success!",
 * JOptionPane.INFORMATION_MESSAGE); }
 * 
 * f.dispose(); admin(); } });
 * 
 * returnButton.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent ae) { f.dispose(); menuStart(); } });
 * 
 * } } } }
 * 
 * }
 */
