
package agile;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		AccountManager account = new AccountManager();
	    Scanner sc = new Scanner(System.in);
	
	    System.out.println("Welcome to the ATM!");
	    
	    Account current = null;

        // Login loop
        while (current == null) {
            try {
                System.out.println("\n1. Login");
                System.out.println("2. Create Account");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        current = account.login();
                        break;
                    case 2:
                        current = account.createAccount();
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
            catch (ValidateAccountExceptionHandler e) {
                System.out.println("Validation Error: " + e.getMessage());
            }
            //catch (AccountManagerExceptionHandler e) {
            //    System.out.println("Account Manager Error: " + e.getMessage());
            //}
            catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }

        System.out.println("Welcome, " + current.getUsername() + "!");
        boolean runningAtm = true;
        
		while (runningAtm) {
	        System.out.println("\n--- Menu ---");
	        System.out.println("1. Withdraw");
	        System.out.println("2. Deposit");
	        System.out.println("3. Check Balance");
	        System.out.println("4. Exit");
	        System.out.print("Choose: ");
	        int choice = sc.nextInt();
	        switch (choice) {
            case 1:
                System.out.print("Enter amount to withdraw: ");
                double w = sc.nextDouble();
                account.withdraw(current, w);
                break;
            case 2:
                System.out.print("Enter amount to deposit: ");
                double d = sc.nextDouble();
                account.deposit(current, d);
                break;
            case 3:
                System.out.println("Your balance is: €" + current.getBalance());
                break;
            case 4:
            	runningAtm = false;
            	try {
                	FileOutputStream fo = new FileOutputStream("account.ser");
        			ObjectOutputStream oo = new ObjectOutputStream(fo);
        			oo.writeObject(account.getAccounts());
                }
                catch (Exception e)
                {
                	e.printStackTrace();
                }
                System.out.println("Thank you for using the ATM!");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
	}
}
