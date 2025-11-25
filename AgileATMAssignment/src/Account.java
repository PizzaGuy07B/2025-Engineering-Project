
package agile;

import java.io.Serializable;

public class Account implements Serializable{
	private String username;
	private String pin;
	private double balance;
	private double savings;
  boolean admin;
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
		isCurrent = false;
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

    public double getSavings() {
        return savings;
    }

    // ----- SELECT ACCOUNT TYPE -----
    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public boolean getIsCurrent() {
        return isCurrent;
    }

    // ----- DEPOSIT -----
    public void addToBalance(double money) {
        if (isCurrent) {
            this.balance += money;
        } else {
            this.savings += money;
        }
    }

    // ----- WITHDRAW -----
    public void withdrawFromBalance(double money) {
        if (isCurrent) {
            this.balance -= money;
        } else {
            this.savings -= money;
        }
    }

    // ----- TRANSFERS -----
    public void transferToCurrent(double money) {
        if (savings >= money) {
            savings -= money;
            balance += money;
        }
    }

    public void transferToSavings(double money) {
        if (balance >= money) {
            savings += money;
            balance -= money;
        }
    }
	
	public void setUsername(String username) throws ValidateAccountExceptionHandler
	{
		if (ValidateAccount.validateUsername(username))
		{
			this.username = username;
			System.out.println("Username Changed: "+username);
		}
		else
		{
			System.out.println("Invalid Entry");
		}
	}
	
	public void setPin(String pin) throws ValidateAccountExceptionHandler
	{
		if (ValidateAccount.validatePin(pin))
		{
			this.pin = pin;
			System.out.println("PIN Changed: "+pin);
		}
		else
		{
			System.out.println("Invalid Entry");
		}
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
}
