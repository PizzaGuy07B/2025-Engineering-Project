package agile;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class main {
	private ArrayList<Account> accounts;
	
	public main() {
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
	
    public static void main(String[] args) {

        AccountManager manager = new AccountManager();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");

        Account userAccount = null;

        // ---------------- LOGIN LOOP ----------------
        while (userAccount == null) {
            try {
                System.out.println("\n1. Login");
                System.out.println("2. Create Account");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        userAccount = manager.login();
                        break;

                    case 2:
                        userAccount = manager.createAccount();
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
            catch (ValidateAccountExceptionHandler e) {
                System.out.println("Validation Error: " + e.getMessage());
            }
            catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("Welcome, " + userAccount.getUsername() + "!");

        boolean runningATM = true;

        // ---------------- ACCOUNT SELECTION LOOP ----------------
        while (runningATM) {

            System.out.println("\nSelect Account:");
            System.out.println("1. Current Account");
            System.out.println("2. Savings Account");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int accType = sc.nextInt();

            if (accType == 3) {
                System.out.println("Thank you for using the ATM!");
                break;
            }

            if (accType != 1 && accType != 2) {
                System.out.println("Invalid account type.");
                continue;
            }

            // Set which account is active
            userAccount.setIsCurrent(accType == 1);

            boolean onAccountMenu = true;

            while (onAccountMenu) {
              System.out.println("\n--- " + (userAccount.getIsCurrent() ? "Current" : "Savings") + " Account ---");
              System.out.println("1. Deposit");
              System.out.println("2. Withdraw");
              System.out.println("3. Check Balance");
              System.out.println("4. Transfer to Other Account");
              System.out.println("5. Back");
              System.out.print("Choose: ");
	        if (current.isAdmin())
	        {
	        	System.out.println("\n--- Admin Panel ---");
		        System.out.println("6. Select Account (Account Selected:"+selectedAccount.getUsername()+")");
		        System.out.println("7. Change Username");
		        System.out.println("8. Change PIN");
		        System.out.println("9. Toggle Admin");
		        System.out.println("10. Toggle Freeze");
		        System.out.println("11. Delete Account");
	        }
	        System.out.print("Choose: ");
	        int choice = sc.nextInt();
	        switch (choice) {
            case 1: // DEPOSIT
                        System.out.print("Enter amount: ");
                        manager.deposit(userAccount, sc.nextDouble());
                        break;

                    case 2: // WITHDRAW
                        System.out.print("Enter amount: ");
                        manager.withdraw(userAccount, sc.nextDouble());
                        break;

                    case 3: // CHECK BALANCE
                        if (userAccount.getIsCurrent()) {
                            System.out.println("Current Balance: €" + userAccount.getBalance());
                        } else {
                            System.out.println("Savings Balance: €" + userAccount.getSavings());
                        }
                        break;

                    case 4: // TRANSFER
                        System.out.print("Enter amount to transfer: ");
                        double amt = sc.nextDouble();

                        if (userAccount.getIsCurrent()) {
                            if (userAccount.getBalance() >= amt) {
                                userAccount.transferToSavings(amt);
                                System.out.println("Transferred €" + amt + " to Savings.");
                            } else {
                                System.out.println("Insufficient funds.");
                            }
                        } else {
                            if (userAccount.getSavings() >= amt) {
                                userAccount.transferToCurrent(amt);
                                System.out.println("Transferred €" + amt + " to Current.");
                            } else {
                                System.out.println("Insufficient funds.");
                            }
                        }
                        break;

                    case 5:
                        onAccountMenu = false;
                        break;
            case 6:
            	if (current.isAdmin())
            	{
            		System.out.println("Enter account to edit: ");
                    String choice2 = sc.next();
                    for (Account a : account.getAccounts()) {
                        if (a.getUsername().equals(choice2)) {
                            selectedAccount = a;
                            System.out.println("Account set to: "+selectedAccount.getUsername());
                        }
                    }
            	}
            	else
            	{
            		System.out.println("Invalid choice.");
            	}
            	break;
            case 7:
            	if (current.isAdmin())
            	{
            		System.out.println("Enter new username: ");
                    String choice2 = sc.next();
                    selectedAccount.setUsername(choice2);
                    System.out.println("Username set to: "+selectedAccount.getUsername());
            	}
            	else
            	{
            		System.out.println("Invalid choice.");
            	}
            	break;
            case 8:
            	if (current.isAdmin())
            	{
            		System.out.println("Enter new PIN: ");
                    String choice2 = sc.next();
                    selectedAccount.setPin(choice2);
                    System.out.println("PIN set to: "+selectedAccount.getPin());
            	}
            	else
            	{
            		System.out.println("Invalid choice.");
            	}
            	break;
            case 9:
            	if (current.isAdmin())
            	{
            		selectedAccount.setAdmin(!current.isAdmin());
            		System.out.println("Admin Toggled!.");
            	}
            	else
            	{
            		System.out.println("Invalid choice.");
            	}
            	break;
            case 10:
            	if (current.isAdmin())
            	{
            		if (current.accountFrozen())
                {
                  current.unfreeze();
                  System.out.println("Your account is now UNFROZEN.");
                  break;
                }
                else
                {
                  current.freeze();
                  System.out.println("Your account is now FROZEN.");
                  break;
                }
            	}
            	else
            	{
            		System.out.println("Invalid choice.");
            	}
            	break;
            case 11:
            	if (current.isAdmin())
            	{
            		System.out.println("Not Implemented");
            	}
            	else
            	{
            		System.out.println("Invalid choice.");
            	}
            	break;
            default:
                System.out.println("Invalid choice.");
        }
    }
	}
}
