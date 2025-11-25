import java.io.Serializable;

public class Account implements Serializable{
	private String username;
	private String pin;
	private boolean isCurrent;
	private double balance;
	private double savings;
	
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
}
