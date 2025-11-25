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
            accounts = (ArrayList<Account>) oi.readObject();
            oi.close();
            fi.close();
        } catch (Exception e) {
            accounts = new ArrayList<>();
            accounts.add(new Account("SeanD", "1111"));
            accounts.add(new Account("EvanB", "2222"));
            accounts.add(new Account("AlessandroB", "3333"));
        }
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // Save accounts to file
    private void saveAccounts() {
        try {
            FileOutputStream fo = new FileOutputStream("account.ser");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(accounts);
            oo.close();
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ------------------ LOGIN --------------------
    public Account login() throws ValidateAccountExceptionHandler {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String user = sc.next();

        System.out.print("Please enter your PIN: ");
        String pin = sc.next();

        if (!ValidateAccount.validateUsername(user) ||
            !ValidateAccount.validatePin(pin)) {
            throw new ValidateAccountExceptionHandler("Invalid username or PIN format.");
        }

        for (Account a : accounts) {
            if (a.getUsername().equals(user) && a.getPin().equals(pin)) {
                return a;
            }
        }

        throw new ValidateAccountExceptionHandler("Username or PIN incorrect.");
    }

    // ------------------ CREATE ACCOUNT --------------------
    public Account createAccount() throws ValidateAccountExceptionHandler {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String user = sc.next();

        System.out.print("Please enter your PIN: ");
        String pin = sc.next();

        if (!ValidateAccount.validateUsername(user) ||
            !ValidateAccount.validatePin(pin)) {
            throw new ValidateAccountExceptionHandler("Invalid username or PIN.");
        }

        Account acc = new Account(user, pin);
        accounts.add(acc);
        saveAccounts();

        System.out.println("Account created successfully!");
        return acc;
    }

    // ------------------ DEPOSIT --------------------
    public void deposit(Account a, double money) {
        a.addToBalance(money);
        System.out.println("€" + money + " added.");
        saveAccounts();
    }

    // ------------------ WITHDRAW --------------------
    public void withdraw(Account a, double money) {
        if (a.getIsCurrent() && a.getBalance() < money) {
            System.out.println("Insufficient funds in current account!");
            return;
        }
        if (!a.getIsCurrent() && a.getSavings() < money) {
            System.out.println("Insufficient funds in savings!");
            return;
        }

        a.withdrawFromBalance(money);
        System.out.println("€" + money + " withdrawn.");
        saveAccounts();
    }
}

