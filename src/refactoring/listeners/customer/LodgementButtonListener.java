package refactoring.listeners.customer;

import java.util.Date;

import javax.swing.JOptionPane;

import refactoring.AccountTransaction;
import refactoring.Menu;
import refactoring.constants.Constants;
import refactoring.constants.MessageConstants;
import refactoring.constants.TransactionConstants;

public class LodgementButtonListener extends TransactionButtonListener {

	public LodgementButtonListener(Menu parent) {
		super(parent);
	}

	@Override
	public AccountTransaction performTransaction(double number) {
		parent.customerAccount.setBalance(parent.customerAccount.getBalance() + number);
		Date date = new Date();
		String date2 = date.toString();
		String type = TransactionConstants.TYPE_LODGE;
		double amount = number;

		AccountTransaction transaction = new AccountTransaction(date2, type, amount);
		return transaction;
	}

	@Override
	public double getAmount() {
		double amt = 0.0;
		String balanceTest = JOptionPane.showInputDialog(parent.frame, MessageConstants.LODGE_AMOUNT_MESSAGE);
		if (Menu.isNumeric(balanceTest)) {

			amt = Double.parseDouble(balanceTest);
			loop = false;

		} else {
			JOptionPane.showMessageDialog(parent.frame, MessageConstants.INCORRECT_NUMERIC_VALUE, Constants.OOPS,
					JOptionPane.INFORMATION_MESSAGE);
		}
		return amt;

	}

	@Override
	public void showResults(double amount) {
		JOptionPane.showMessageDialog(parent.frame, amount + Constants.EURO + " added do you account!", TransactionConstants.TYPE_LODGE,
				JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(parent.frame, "New balance = " + parent.customerAccount.getBalance() + Constants.EURO,
				TransactionConstants.TYPE_LODGE, JOptionPane.INFORMATION_MESSAGE);

	}

}