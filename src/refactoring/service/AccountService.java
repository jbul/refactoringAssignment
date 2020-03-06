package refactoring.service;

import refactoring.CustomerAccount;
import refactoring.constants.AccountConstants;

public class AccountService {
	
	private static AccountService instance = new AccountService();
	
	private AccountService() {}

	public int randomPin() {
		return (int) (Math.random() * 9000) + 1000;
	}

	public String generateAccountNumber(int customerIndex, int customerAccountSize, boolean isDeposit) {
		String accountType = isDeposit ? AccountConstants.DEPOSIT_ACCOUNT_PREFIX : AccountConstants.CURRENT_ACCOUNT_PREFIX;
		return String.valueOf(accountType + (customerIndex + 1) * 10 + (customerAccountSize + 1));
	}

	public void applyFee(CustomerAccount account) {
		double fee = account.isDeposit() ? AccountConstants.DEPOSIT_ACCOUNT_FEE : AccountConstants.CURRENT_ACCOUNT_FEE;
		account.setBalance(account.getBalance() - fee);
	}

	public void applyInterest(CustomerAccount account, double interest) {
		account.setBalance(account.getBalance() + (account.getBalance() * (interest / 100)));
	}
	
	public void withdrawOperation(CustomerAccount account, double withdraw) {
		account.setBalance(account.getBalance() - withdraw);
	}
	
	public static AccountService getInstance() {
		return instance;
	}

}
