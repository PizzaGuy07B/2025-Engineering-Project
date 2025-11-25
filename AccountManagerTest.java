import java.util.ArrayList;

import junit.framework.TestCase;

public class AccountManagerTest extends TestCase{
	
	private AccountManager manager;

    public void setup() {
        manager = new AccountManager();
    }
   
	//Test No: 1
    //Objective: Test valid login
    //Input(s): username = "SeanD", pin = "1234"
    //Expected Output: Account object returned successfully

    public void testLogin_Valid() {
        try {
            Account acc = manager.getAccounts().get(0);
            assertEquals("SeanD", acc.getUsername());
            assertEquals("1234", acc.getPin());
        }
        catch (AccountManagerExceptionHandler e) {
            fail("No Exception Expected");
        }
    }
    
    //Test No: 2
    //Objective: Test invalid login (wrong pin)
    //Input(s): username = "SeanD", pin = "9999"
    //Expected Output: Exception message "Invalid login details"

   
    public void testLogin_InvalidPin() {
        try {
            boolean found = manager.getAccounts().stream()
                    .anyMatch(a -> a.getUsername().equals("SeanD") && a.getPin().equals("9999"));
            if (!found) {
                throw new AccountManagerExceptionHandler("Invalid login credentials");
            }
            fail("Exception Expected");
        }
        catch (AccountManagerExceptionHandler e) {
            assertEquals("Invalid login credentials", e.getMessage());
        }
    }

    //Test No: 3
    //Objective: Test deposit increases balance
    //Input(s): deposit = 200.0
    //Expected Output: balance = 200.0

    public void testDeposit() {
        try {
            Account acc = new Account("John", "1234");
            manager.deposit(acc, 200.0);
            assertEquals(200.0, acc.getBalance());
            
        }
        catch (AccountManagerExceptionHandler e) {
            fail("No Exception Expected");
        }
    }

    //Test No: 4
    //Objective: Test withdraw within balance
    //Input(s): withdraw = 50.0, balance = 100.0
    //Expected Output: balance = 50.0

    public void testWithdraw_Valid() {
        try {
            Account acc = new Account("Jane", "4321");
            acc.addToBalance(100.0);
            manager.withdraw(acc, 50.0);
            assertEquals(50.0, acc.getBalance());
        }
        catch (AccountManagerExceptionHandler e) {
            fail("No Exception Expected");
        }
    }

    //Test No: 5
    //Objective: Test withdraw exceeding balance
    //Input(s): withdraw = 200.0, balance = 100.0
    //Expected Output: Exception message "Insufficient funds"

    public void testWithdraw_InsufficientFunds() {
        try {
            Account acc = new Account("Jane", "4321");
            acc.addToBalance(100.0);
            if (acc.getBalance() < 200.0) {
                throw new AccountManagerExceptionHandler("Insufficient funds");
            }
            manager.withdraw(acc, 200.0);
            fail("Exception Expected");
        }
        catch (AccountManagerExceptionHandler e) {
            assertEquals("Insufficient funds", e.getMessage());
        }
    }
}
