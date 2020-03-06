package refactoring.listeners.admin;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import refactoring.ATMCard;
import refactoring.AccountTransaction;
import refactoring.CustomerCurrentAccount;
import refactoring.CustomerDepositAccount;
import refactoring.Menu;
import refactoring.constants.AccountConstants;
import refactoring.constants.Constants;
import refactoring.constants.MessageConstants;
import refactoring.service.AccountService;

public class AccountButtonListener extends AdminActionListener {

	AccountService accountService = AccountService.getInstance();

	public AccountButtonListener(Menu parent) {
		super(parent);
	}

	@Override
	void performAdminAction() {
		// a combo box in an dialog box that asks the admin what type of account they
		// wish to create (deposit/current)
		String[] choices = { AccountConstants.CURRENT_ACCOUNT, AccountConstants.DEPOSIT_ACCOUNT };
		String account = (String) JOptionPane.showInputDialog(null, MessageConstants.CHOOSE_ACCOUNT_TYPE_MESSAGE,
				"Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

		if (account.equals(AccountConstants.CURRENT_ACCOUNT)) {
			// create current account
			boolean valid = true;
			double balance = 0;
			String number = accountService.generateAccountNumber(
					parent.getCustomerService().indexOf(parent.customer),
					parent.customer.getAccounts().size(), false);
		
			ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

			int randomPIN = accountService.randomPin();
			String pin = String.valueOf(randomPIN);

			ATMCard atm = new ATMCard(randomPIN, valid);

			CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance,
					transactionList);

			parent.customer.getAccounts().add(current);
			JOptionPane.showMessageDialog(parent.frame, AccountConstants.ACCOUNT_NUMBER + " = " + number + "\n" + Constants.PIN +" = " + pin,
					AccountConstants.ACCOUNT_CREATED, JOptionPane.INFORMATION_MESSAGE);

			parent.frame.dispose();
			parent.admin();
		}

		if (account.equals(AccountConstants.DEPOSIT_ACCOUNT)) {
			// create deposit account
			double balance = 0, interest = 0;
			String number = accountService.generateAccountNumber(
					parent.getCustomerService().indexOf(parent.customer),
					parent.customer.getAccounts().size(), true);
			
			ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

			CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance,
					transactionList);

			parent.customer.getAccounts().add(deposit);
			JOptionPane.showMessageDialog(parent.frame, AccountConstants.ACCOUNT_NUMBER + " = " + number, AccountConstants.ACCOUNT_CREATED,
					JOptionPane.INFORMATION_MESSAGE);

			parent.frame.dispose();
			parent.admin();
		}

	}

	@Override
	String getMessageString() {
		return MessageConstants.CUSTOMER_ID_ADD_ACCOUNT_MESSAGE ;
	}
	
}

