
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {

    private ArrayList<Account> accounts;

    public AccountManager() {
        accounts = new ArrayList<>();
        // Seed with example data
        accounts.add(new Account("SeanD", "1111"));
        accounts.add(new Account("EvanB", "2222"));
        accounts.add(new Account("AlessandroB", "3333"));
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
            Account acc = new Account(userName, pin);
            accounts.add(acc);
            System.out.println("Account created successfully!");
            return acc;
        } else {
            System.out.println("Invalid username or PIN. Try again.");
            return null;
        }
    }

    public void deposit(Account a, double money) {
        a.addToBalance(money);
        System.out.println("€" + money + " added to account.");
    }

    public void withdraw(Account a, double money) {
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

