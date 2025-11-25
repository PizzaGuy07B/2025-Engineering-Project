package agile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {

    private ArrayList<Account> accounts;

    public AccountManager() {
        try {
        	FileInputStream fi = new FileInputStream("account.ser");
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			accounts = (ArrayList<Account>)oi.readObject();
        }
        catch (Exception e)
        {
        	accounts = new ArrayList<Account>();
        }
    }

    public Account login() throws ValidateAccountExceptionHandler {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String userName = sc.next();

        System.out.print("Please enter your PIN: ");
        String pin = sc.next();

        if (ValidateAccount.validateUsername(userName) && ValidateAccount.validatePin(pin)) {
            for (Account a : accounts) {
                if (a.getUsername().equals(userName) && a.getPin().equals(pin)) {
                    return a;
                }
            }
            System.out.println("Username or password was incorrect.");
        } else {
            System.out.println("Invalid username or PIN format.");
        }
        return null;
    }

    public Account createAccount() throws ValidateAccountExceptionHandler {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String userName = sc.next();

        System.out.print("Please enter your PIN: ");
        String pin = sc.next();

        if (ValidateAccount.validateUsername(userName) && ValidateAccount.validatePin(pin)) {
        	for (Account a : accounts) {
                if (a.getUsername().equals(userName)) {
                	System.out.println("Username already exists. Try again.");
                    return null;
                }
            }
        	Account acc = new Account(userName, pin);
            accounts.add(acc);
            System.out.println("Account created successfully!");
            
            try {
            	FileOutputStream fo = new FileOutputStream("account.ser");
    			ObjectOutputStream oo = new ObjectOutputStream(fo);
    			oo.writeObject(accounts);
            }
            catch (Exception e)
            {
            	e.printStackTrace();
            }
            
            return acc;
        } else {
            System.out.println("Invalid username or PIN. Try again.");
            return null;
        }
    }

    public void deposit(Account a, double money) {
        if (a.accountFrozen = true)
        {
            System.out.println("Your account is frozen, deposit failed.");
            return;
        }
        a.addToBalance(money);
        System.out.println("€" + money + " added to account.");
    }

    public void withdraw(Account a, double money) {
        if (a.accountFrozen = true)
        {
            System.out.println("Your account is frozen, withdrawal failed.");
            return;
        }
        if (a.getBalance() >= money) {
            a.withdrawFromBalance(money);
            System.out.println("€" + money + " withdrawn from account.");
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}

