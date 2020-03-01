package refactoring.service;

import refactoring.CustomerAccount;

public class AccountService {
	
	public AccountService() {
		
	}
	
	public int randomPin() {
		
		return (int) (Math.random() * 9000) + 1000;	
	}
	
	public String generateAccountNumber(int customerIndex, int customerAccountSize, boolean isDeposit ){
		String accountType = isDeposit ? "D" : "C";
		return String.valueOf(accountType + (customerIndex + 1) * 10 + (customerAccountSize + 1));
	}
	
	public void applyFee(CustomerAccount account) {
		double fee = account.isDeposit() ? 25 : 15;
		account.setBalance(account.getBalance() - fee);
	}
	
	
}
