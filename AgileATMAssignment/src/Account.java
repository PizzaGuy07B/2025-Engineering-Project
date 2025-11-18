

public class Account {
	private String username;
	private String pin;
	private double balance;
	private double savings;
	private boolean accountFrozen = false;

	public boolean accountFrozen() {      
        return accountFrozen;
	}

	public void freezeAccount() {            
        accountFrozen = true;
	}

	public void unfreezeAccount() {          
        accountFrozen = false;
	}

	
	public Account(String username, String pin) {
		this.username = username;
		this.pin = pin;
		this.balance = 0;
		this.savings = 0;
	}

	public String getUsername() {
		return username;
	}


	public String getPin() {
		return pin;
	}


	public double getBalance() {
		return balance;
	}

	public void addToBalance(double money) {
		this.balance += money;
	}
	
	public void withdrawFromBalance(double money) {
		this.balance -= money;
	}
	
	public double getSavings() {
		return savings;
	}
	
	public void transferToCurrent(double money)
	{
		savings -= money;
		balance += money;
	}
	
	public void transferToSavings(double money)
	{
		savings += money;
		balance -= money;
	}
}
