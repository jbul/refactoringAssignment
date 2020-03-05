package refactoring.service;

import refactoring.CustomerAccount;

public class AccountService {
	
	private static AccountService instance = new AccountService();
	
	private AccountService() {

	}

	public int randomPin() {

		return (int) (Math.random() * 9000) + 1000;
	}

	public String generateAccountNumber(int customerIndex, int customerAccountSize, boolean isDeposit) {
		String accountType = isDeposit ? "D" : "C";
		return String.valueOf(accountType + (customerIndex + 1) * 10 + (customerAccountSize + 1));
	}

	public void applyFee(CustomerAccount account) {
		double fee = account.isDeposit() ? 25 : 15;
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
