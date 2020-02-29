package refactoring.listeners.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import refactoring.ATMCard;
import refactoring.AccountTransaction;
import refactoring.Customer;
import refactoring.CustomerCurrentAccount;
import refactoring.CustomerDepositAccount;
import refactoring.Menu;

public class AccountButtonListener implements ActionListener{
	
	Menu parent;
	
	public AccountButtonListener(Menu parent) {
		this.parent = parent;
	}


	public void actionPerformed(ActionEvent ae) {
		parent.frame.dispose();

		if (parent.customerList.isEmpty()) {
			JOptionPane.showMessageDialog(parent.frame, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
			parent.frame.dispose();
			parent.admin();
		} else {
			boolean loop = true;

			boolean found = false;

			while (loop) {
				Object customerID = JOptionPane.showInputDialog(parent.frame,
						"Customer ID of Customer You Wish to Add an Account to:");

				for (Customer aCustomer : parent.customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						parent.customer = aCustomer;
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
					loop = false;
					// a combo box in an dialog box that asks the admin what type of account they
					// wish to create (deposit/current)
					String[] choices = { "Current Account", "Deposit Account" };
					String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
							"Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

					if (account.equals("Current Account")) {
						// create current account
						boolean valid = true;
						double balance = 0;
						String number = String.valueOf(
								"C" + (parent.customerList.indexOf(parent.customer) + 1) * 10 + (parent.customer.getAccounts().size() + 1));// this
																														// simple algorithm generates the account number
						ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
						int randomPIN = (int) (Math.random() * 9000) + 1000;
						String pin = String.valueOf(randomPIN);

						ATMCard atm = new ATMCard(randomPIN, valid);

						CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance,
								transactionList);

						parent.customer.getAccounts().add(current);
						JOptionPane.showMessageDialog(parent.frame, "Account number = " + number + "\n PIN = " + pin,
								"Account created.", JOptionPane.INFORMATION_MESSAGE);

						parent.frame.dispose();
						parent.admin();
					}

					if (account.equals("Deposit Account")) {
						// create deposit account

						double balance = 0, interest = 0;
						String number = String.valueOf(
								"D" + (parent.customerList.indexOf(parent.customer) + 1) * 10 + (parent.customer.getAccounts().size() + 1));// this
																														// simple
																														// algorithm
																														// generates
																														// the
																														// account
																														// number
						ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

						CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance,
								transactionList);

						parent.customer.getAccounts().add(deposit);
						JOptionPane.showMessageDialog(parent.frame, "Account number = " + number, "Account created.",
								JOptionPane.INFORMATION_MESSAGE);

						parent.frame.dispose();
						parent.admin();
					}

				}
			}
		}
	}

}

//Replaces in Menu class
/* 
 * public void actionPerformed(ActionEvent ae) { f.dispose();
 * 
 * if (customerList.isEmpty()) { JOptionPane.showMessageDialog(f,
 * "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
 * f.dispose(); admin(); } else { boolean loop = true;
 * 
 * boolean found = false;
 * 
 * while (loop) { Object customerID = JOptionPane.showInputDialog(f,
 * "Customer ID of Customer You Wish to Add an Account to:");
 * 
 * for (Customer aCustomer : customerList) {
 * 
 * if (aCustomer.getCustomerID().equals(customerID)) { found = true; customer =
 * aCustomer; } }
 * 
 * if (found == false) { int reply = JOptionPane.showConfirmDialog(null, null,
 * "User not found. Try again?", JOptionPane.YES_NO_OPTION); if (reply ==
 * JOptionPane.YES_OPTION) { loop = true; } else if (reply ==
 * JOptionPane.NO_OPTION) { f.dispose(); loop = false;
 * 
 * admin(); } } else { loop = false; // a combo box in an dialog box that asks
 * the admin what type of account they // wish to create (deposit/current)
 * String[] choices = { "Current Account", "Deposit Account" }; String account =
 * (String) JOptionPane.showInputDialog(null, "Please choose account type",
 * "Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);
 * 
 * if (account.equals("Current Account")) { // create current account boolean
 * valid = true; double balance = 0; String number = String.valueOf("C" +
 * (customerList.indexOf(customer) + 1) * 10 + (customer.getAccounts().size() +
 * 1));// this simple algorithm generates the // account number
 * ArrayList<AccountTransaction> transactionList = new
 * ArrayList<AccountTransaction>(); int randomPIN = (int) (Math.random() * 9000)
 * + 1000; String pin = String.valueOf(randomPIN);
 * 
 * ATMCard atm = new ATMCard(randomPIN, valid);
 * 
 * CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number,
 * balance, transactionList);
 * 
 * customer.getAccounts().add(current); JOptionPane.showMessageDialog(f,
 * "Account number = " + number + "\n PIN = " + pin, "Account created.",
 * JOptionPane.INFORMATION_MESSAGE);
 * 
 * f.dispose(); admin(); }
 * 
 * if (account.equals("Deposit Account")) { // create deposit account
 * 
 * double balance = 0, interest = 0; String number = String.valueOf("D" +
 * (customerList.indexOf(customer) + 1) * 10 + (customer.getAccounts().size() +
 * 1));// this simple algorithm generates the // account number
 * ArrayList<AccountTransaction> transactionList = new
 * ArrayList<AccountTransaction>();
 * 
 * CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number,
 * balance, transactionList);
 * 
 * customer.getAccounts().add(deposit); JOptionPane.showMessageDialog(f,
 * "Account number = " + number, "Account created.",
 * JOptionPane.INFORMATION_MESSAGE);
 * 
 * f.dispose(); admin(); }
 * 
 * } } } }
 */
