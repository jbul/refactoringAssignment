package refactoring.listeners.customer;

import java.util.Date;

import javax.swing.JOptionPane;

import refactoring.AccountTransaction;
import refactoring.Menu;

public class LodgementButtonListener extends TransactionButtonListener {

	public LodgementButtonListener(Menu parent) {
		super(parent);
	}

	@Override
	public AccountTransaction performTransaction(double number) {
		parent.customerAccount.setBalance(parent.customerAccount.getBalance() + number);
		Date date = new Date();
		String date2 = date.toString();
		String type = "Lodgement";
		double amount = number;

		AccountTransaction transaction = new AccountTransaction(date2, type, amount);
		return transaction;
	}

	@Override
	public double getAmount() {
		double amt = 0.0;
		String balanceTest = JOptionPane.showInputDialog(parent.frame, "Enter amount you wish to lodge:");
		if (Menu.isNumeric(balanceTest)) {

			amt = Double.parseDouble(balanceTest);
			loop = false;

		} else {
			JOptionPane.showMessageDialog(parent.frame, "You must enter a numerical value!", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return amt;

	}

	@Override
	public void showResults(double amount) {
		String euro = "\u20ac";
		JOptionPane.showMessageDialog(parent.frame, amount + euro + " added do you account!", "Lodgement",
				JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(parent.frame, "New balance = " + parent.customerAccount.getBalance() + euro,
				"Lodgement", JOptionPane.INFORMATION_MESSAGE);

	}

	// Menu parent;

	/*
	 * public LodgementButtonListener(Menu parent) { this.parent = parent; }
	 */
	/*
	 * public void actionPerformed(ActionEvent ae) {
	 * 
	 * checkPin();
	 */

	/*
	 * boolean loop = true; boolean on = true; double balance = 0;
	 * 
	 * if (parent.customerAccount instanceof CustomerCurrentAccount) { int count =
	 * 3; int checkPin = ((CustomerCurrentAccount)
	 * parent.customerAccount).getAtm().getPin(); loop = true;
	 * 
	 * while (loop) { if (count == 0) { JOptionPane.showMessageDialog(parent.frame,
	 * "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
	 * JOptionPane.INFORMATION_MESSAGE); ((CustomerCurrentAccount)
	 * parent.customerAccount).getAtm().setValid(false);
	 * parent.customer(parent.cust); loop = false; on = false; }
	 * 
	 * String Pin = JOptionPane.showInputDialog(parent.frame, "Enter 4 digit PIN;");
	 * int i = Integer.parseInt(Pin);
	 * 
	 * if (on) { if (checkPin == i) { loop = false;
	 * JOptionPane.showMessageDialog(parent.frame, "Pin entry successful", "Pin",
	 * JOptionPane.INFORMATION_MESSAGE);
	 * 
	 * } else { count--; JOptionPane.showMessageDialog(parent.frame,
	 * "Incorrect pin. " + count + " attempts remaining.", "Pin",
	 * JOptionPane.INFORMATION_MESSAGE); }
	 * 
	 * } }
	 * 
	 * }
	 */

	/*
	 * double amt = 0.0; if (on == true) { String balanceTest =
	 * JOptionPane.showInputDialog(parent.frame, "Enter amount you wish to lodge:");
	 * if (Menu.isNumeric(balanceTest)) {
	 * 
	 * amt = Double.parseDouble(balanceTest); loop = false;
	 * 
	 * } else { JOptionPane.showMessageDialog(parent.frame,
	 * "You must enter a numerical value!", "Oops!",
	 * JOptionPane.INFORMATION_MESSAGE); }
	 */

	/*
	 * String euro = "\u20ac";
	 * parent.customerAccount.setBalance(parent.customerAccount.getBalance() + amt);
	 * // String date = new //
	 * SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	 * Date date = new Date(); String date2 = date.toString(); String type =
	 * "Lodgement"; double amount = amt;
	 * 
	 * AccountTransaction transaction = new AccountTransaction(date2, type, amount);
	 * parent.customerAccount.getTransactionList().add(transaction);
	 */

	/*
	 * JOptionPane.showMessageDialog(parent.frame, balance + euro +
	 * " added do you account!", "Lodgement", JOptionPane.INFORMATION_MESSAGE);
	 * JOptionPane.showMessageDialog(parent.frame, "New balance = " +
	 * parent.customerAccount.getBalance() + euro, "Lodgement",
	 * JOptionPane.INFORMATION_MESSAGE);
	 */
	// }

//	@Override
//	public AccountTransaction performTransaction(double amt) {
//		transaction(amt, "Enter amount you wish to lodge:");
//
//		parent.customerAccount.setBalance(parent.customerAccount.getBalance() + amt);
//		Date date = new Date();
//		String date2 = date.toString();
//		String type = "Lodgement";
//		double amount = amt;
//		 AccountTransaction transaction = new AccountTransaction(date2, type, amount);
//		return transaction; 
//	}

}

/*
 * public void actionPerformed(ActionEvent ae) { boolean loop = true; boolean on
 * = true; double balance = 0;
 * 
 * if (acc instanceof CustomerCurrentAccount) { int count = 3; int checkPin =
 * ((CustomerCurrentAccount) acc).getAtm().getPin(); loop = true;
 * 
 * while (loop) { if (count == 0) { JOptionPane.showMessageDialog(f,
 * "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
 * JOptionPane.INFORMATION_MESSAGE); ((CustomerCurrentAccount)
 * acc).getAtm().setValid(false); customer(e); loop = false; on = false; }
 * 
 * String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;"); int i =
 * Integer.parseInt(Pin);
 * 
 * if (on) { if (checkPin == i) { loop = false; JOptionPane.showMessageDialog(f,
 * "Pin entry successful", "Pin", JOptionPane.INFORMATION_MESSAGE);
 * 
 * } else { count--; JOptionPane.showMessageDialog(f, "Incorrect pin. " + count
 * + " attempts remaining.", "Pin", JOptionPane.INFORMATION_MESSAGE); }
 * 
 * } }
 * 
 * } if (on == true) { String balanceTest = JOptionPane.showInputDialog(f,
 * "Enter amount you wish to lodge:"); if (isNumeric(balanceTest)) {
 * 
 * balance = Double.parseDouble(balanceTest); loop = false;
 * 
 * } else { JOptionPane.showMessageDialog(f,
 * "You must enter a numerical value!", "Oops!",
 * JOptionPane.INFORMATION_MESSAGE); }
 * 
 * String euro = "\u20ac"; acc.setBalance(acc.getBalance() + balance); // String
 * date = new //
 * SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
 * Date date = new Date(); String date2 = date.toString(); String type =
 * "Lodgement"; double amount = balance;
 * 
 * AccountTransaction transaction = new AccountTransaction(date2, type, amount);
 * acc.getTransactionList().add(transaction);
 * 
 * JOptionPane.showMessageDialog(f, balance + euro + " added do you account!",
 * "Lodgement", JOptionPane.INFORMATION_MESSAGE);
 * JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro,
 * "Lodgement", JOptionPane.INFORMATION_MESSAGE); }
 * 
 * }
 */
