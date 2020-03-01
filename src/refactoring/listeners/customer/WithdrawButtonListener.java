package refactoring.listeners.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import refactoring.AccountTransaction;
import refactoring.CustomerCurrentAccount;
import refactoring.Menu;
import refactoring.service.AccountService;

public class WithdrawButtonListener implements ActionListener {

	Menu parent;
	AccountService accountService = new AccountService();

	public WithdrawButtonListener(Menu parent) {
		this.parent = parent;
	}

	public void actionPerformed(ActionEvent ae) {
		boolean loop = true;
		boolean on = true;
		double withdraw = 0;

		if (parent.customerAccount instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) parent.customerAccount).getAtm().getPin();
			loop = true;

			while (loop) {
				if (count == 0) {
					JOptionPane.showMessageDialog(parent.frame, "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
							JOptionPane.INFORMATION_MESSAGE);
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
		if (on == true) {
			String balanceTest = JOptionPane.showInputDialog(parent.frame, "Enter amount you wish to withdraw (max 500):");
			if (Menu.isNumeric(balanceTest)) {

				withdraw = Double.parseDouble(balanceTest);
				loop = false;

			} else {
				JOptionPane.showMessageDialog(parent.frame, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (withdraw > 500) {
				JOptionPane.showMessageDialog(parent.frame, "500 is the maximum you can withdraw at a time.", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}
			if (withdraw > parent.customerAccount.getBalance()) {
				JOptionPane.showMessageDialog(parent.frame, "Insufficient funds.", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}

			String euro = "\u20ac";
			accountService.withdrawOperation(parent.customerAccount, withdraw);
			//parent.customerAccount.setBalance(accountService.withdrawOperation(parent.customerAccount.getBalance(), withdraw));
					//parent.customerAccount.getBalance() - withdraw);
			// recording transaction:
			// String date = new
			// SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			Date date = new Date();
			String date2 = date.toString();

			String type = "Withdraw";
			double amount = withdraw;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			parent.customerAccount.getTransactionList().add(transaction);

			JOptionPane.showMessageDialog(parent.frame, withdraw + euro + " withdrawn.", "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(parent.frame, "New balance = " + parent.customerAccount.getBalance() + euro, "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
