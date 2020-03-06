package refactoring.listeners.customer;

import java.util.Date;
import javax.swing.JOptionPane;
import refactoring.AccountTransaction;
import refactoring.Menu;
import refactoring.constants.Constants;
import refactoring.constants.MessageConstants;
import refactoring.constants.TransactionConstants;
import refactoring.service.AccountService;

public class WithdrawButtonListener extends TransactionButtonListener {

	AccountService accountService = AccountService.getInstance();

	public WithdrawButtonListener(Menu parent) {
		super(parent);
	}

	@Override
	public double getAmount() {
		double withdraw = 0.0;
		String balanceTest = JOptionPane.showInputDialog(parent.frame, MessageConstants.WITHDRAW_AMOUNT_MESSAGE);

		if (Menu.isNumeric(balanceTest)) {

			withdraw = Double.parseDouble(balanceTest);
			loop = false;

		} else {
			JOptionPane.showMessageDialog(parent.frame, MessageConstants.INCORRECT_NUMERIC_VALUE, Constants.OOPS,
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (withdraw > 500) {
			JOptionPane.showMessageDialog(parent.frame, TransactionConstants.MAX_WITHDRAW_VALUE, Constants.OOPS,
					JOptionPane.INFORMATION_MESSAGE);
			withdraw = 0;
		}

		if (withdraw > parent.customerAccount.getBalance()) {
			JOptionPane.showMessageDialog(parent.frame, TransactionConstants.INSUFFICIENT_FUNDS,Constants.OOPS,
					JOptionPane.INFORMATION_MESSAGE);
			withdraw = 0;
		}

		return withdraw;
	}

	@Override
	public AccountTransaction performTransaction(double withdraw) {

		accountService.withdrawOperation(parent.customerAccount, withdraw);
		Date date = new Date();
		String date2 = date.toString();

		String type = "Withdraw";
		double amount = withdraw;

		AccountTransaction transaction = new AccountTransaction(date2, type, amount);
		return transaction;
	}

	@Override
	public void showResults(double amount) {
		JOptionPane.showMessageDialog(parent.frame, amount + Constants.EURO + " withdrawn.", TransactionConstants.TYPE_WITHDRAW,
				JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(parent.frame, "New balance = " + parent.customerAccount.getBalance() + Constants.EURO,
				TransactionConstants.TYPE_WITHDRAW, JOptionPane.INFORMATION_MESSAGE);
	}

}
