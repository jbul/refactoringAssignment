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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import refactoring.Customer;
import refactoring.Menu;

public class InterestButtonListener implements ActionListener{
	
	Menu parent;

	public InterestButtonListener(Menu parent) {
		this.parent = parent;
	}
	
	
	public void actionPerformed(ActionEvent ae) {

		boolean loop = true;

		boolean found = false;

		if (parent.customerList.isEmpty()) {
			JOptionPane.showMessageDialog(parent.frame, "There are no customers yet!", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
			parent.frame.dispose();
			parent.admin();

		} else {
			while (loop) {
				Object customerID = JOptionPane.showInputDialog(parent.frame,
						"Customer ID of Customer You Wish to Apply Interest to:");

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
						parent.frame.dispose();
						loop = false;

						parent.admin();
					}
				} else {
					parent.frame.dispose();
					parent.frame = new JFrame("Administrator Menu");
					parent.frame.setSize(400, 300);
					parent.frame.setLocation(200, 200);
					parent.frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					parent.frame.setVisible(true);

					JComboBox<String> box = new JComboBox<String>();
					for (int i = 0; i < parent.customer.getAccounts().size(); i++) {

						box.addItem(parent.customer.getAccounts().get(i).getNumber());
					}

					box.getSelectedItem();

					JPanel boxPanel = new JPanel();

					JLabel label = new JLabel("Select an account to apply interest to:");
					boxPanel.add(label);
					boxPanel.add(box);
					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Interest");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = parent.frame.getContentPane();
					content.setLayout(new GridLayout(2, 1));

					content.add(boxPanel);
					content.add(buttonPanel);

					if (parent.customer.getAccounts().isEmpty()) {
						JOptionPane.showMessageDialog(parent.frame,
								"This customer has no accounts! \n The admin must add acounts to this customer.",
								"Oops!", JOptionPane.INFORMATION_MESSAGE);
						parent.frame.dispose();
						parent.admin();
					} else {

						for (int i = 0; i < parent.customer.getAccounts().size(); i++) {
							if (parent.customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
								parent.customerAccount = parent.customer.getAccounts().get(i);
							}
						}

						continueButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								String euro = "\u20ac";
								double interest = 0;
								boolean loop = true;

								while (loop) {
									String interestString = JOptionPane.showInputDialog(parent.frame,
											"Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");// the
																																																					// isNumeric
																																																					// method
																																																					// tests
																																																					// to
																																																					// see
																																																					// if
																																																					// the
																																																					// string
																																																					// entered
																																																					// was
																																																					// numeric.
									if (parent.isNumeric(interestString)) {

										interest = Double.parseDouble(interestString);
										loop = false;

										parent.customerAccount.setBalance(
												parent.customerAccount.getBalance() + (parent.customerAccount.getBalance() * (interest / 100)));

										JOptionPane.showMessageDialog(parent.frame,
												interest + "% interest applied. \n new balance = "
														+ parent.customerAccount.getBalance() + euro,
												"Success!", JOptionPane.INFORMATION_MESSAGE);
									}

									else {
										JOptionPane.showMessageDialog(parent.frame, "You must enter a numerical value!",
												"Oops!", JOptionPane.INFORMATION_MESSAGE);
									}

								}

								parent.frame.dispose();
								parent.admin();
							}
						});

						returnButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								parent.frame.dispose();
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
 * "Customer ID of Customer You Wish to Apply Interest to:");
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
 * JPanel boxPanel = new JPanel();
 * 
 * JLabel label = new JLabel("Select an account to apply interest to:");
 * boxPanel.add(label); boxPanel.add(box); JPanel buttonPanel = new JPanel();
 * JButton continueButton = new JButton("Apply Interest"); JButton returnButton
 * = new JButton("Return"); buttonPanel.add(continueButton);
 * buttonPanel.add(returnButton); Container content = f.getContentPane();
 * content.setLayout(new GridLayout(2, 1));
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
 * actionPerformed(ActionEvent ae) { String euro = "\u20ac"; double interest =
 * 0; boolean loop = true;
 * 
 * while (loop) { String interestString = JOptionPane.showInputDialog(f,
 * "Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'"
 * );// the // isNumeric // method // tests // to // see // if // the // string
 * // entered // was // numeric. if (isNumeric(interestString)) {
 * 
 * interest = Double.parseDouble(interestString); loop = false;
 * 
 * acc.setBalance( acc.getBalance() + (acc.getBalance() * (interest / 100)));
 * 
 * JOptionPane.showMessageDialog(f, interest +
 * "% interest applied. \n new balance = " + acc.getBalance() + euro,
 * "Success!", JOptionPane.INFORMATION_MESSAGE); }
 * 
 * else { JOptionPane.showMessageDialog(f, "You must enter a numerical value!",
 * "Oops!", JOptionPane.INFORMATION_MESSAGE); }
 * 
 * }
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
