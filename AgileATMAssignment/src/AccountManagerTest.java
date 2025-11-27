/* import java.util.ArrayList;
 

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
*/

import java.util.ArrayList;
import junit.framework.TestCase;

public class AccountManagerTest extends TestCase {

    private AccountManager manager;

    public void setUp() {
        manager = new AccountManager();
    }

    // ------------------- LOGIN TEST CASES -------------------

    // Test No: 1
    // Objective: Valid login
    // Input(s): username = "SeanD", pin = "1234"
    // Expected Output: Account object returned successfully
    public void testLogin_Valid() {
        Account acc = manager.getAccounts().get(0);
		assertEquals("SeanD", acc.getUsername());
		assertEquals("1234", acc.getPin());
    }

    // Test No: 2
    // Objective: Invalid login - wrong PIN
    // Input(s): username = "SeanD", pin = "9999"
    // Expected Output: Exception message "Username or PIN incorrect."
    public void testLogin_InvalidPin() {
        try {
            boolean found = manager.getAccounts().stream()
                    .anyMatch(a -> a.getUsername().equals("SeanD") && a.getPin().equals("9999"));
            if (!found) {
                throw new ValidateAccountExceptionHandler("Username or PIN incorrect.");
            }
            fail("Exception Expected");
        } catch (ValidateAccountExceptionHandler e) {
            assertEquals("Username or PIN incorrect.", e.getMessage());
        }
    }

    // Test No: 3
    // Objective: Invalid login - invalid username format
    // Input(s): username = "A", pin = "1234" (username too short)
    // Expected Output: Exception message "Invalid username or PIN format."
    public void testLogin_InvalidUsernameFormat() {
        try {
            if (!ValidateAccount.validateUsername("A") || !ValidateAccount.validatePin("1234")) {
                throw new ValidateAccountExceptionHandler("Invalid username or PIN format.");
            }
            fail("Exception Expected");
        } catch (ValidateAccountExceptionHandler e) {
            assertEquals("Invalid username or PIN format.", e.getMessage());
        }
    }

    // ------------------- CREATE ACCOUNT TEST CASES -------------------

    // Test No: 4
    // Objective: Valid account creation
    // Input(s): username = "Alice99", pin = "5678"
    // Expected Output: Account created successfully
    public void testCreateAccount_Valid() {
        try {
            Account acc = manager.createAccount();
            assertNotNull(acc);
            assertEquals("Alice99", acc.getUsername());
            assertEquals("5678", acc.getPin());
        } catch (ValidateAccountExceptionHandler e) {
            fail("No Exception Expected");
        }
    }

    // Test No: 5
    // Objective: Invalid account creation - username too short
    // Input(s): username = "A", pin = "5678"
    // Expected Output: Exception message "Invalid username or PIN."
    public void testCreateAccount_InvalidUsername() {
        try {
            if (!ValidateAccount.validateUsername("A") || !ValidateAccount.validatePin("5678")) {
                throw new ValidateAccountExceptionHandler("Invalid username or PIN.");
            }
            fail("Exception Expected");
        } catch (ValidateAccountExceptionHandler e) {
            assertEquals("Invalid username or PIN.", e.getMessage());
        }
    }

    // Test No: 6
    // Objective: Invalid account creation - PIN too short
    // Input(s): username = "Alice99", pin = "12"
    // Expected Output: Exception message "Invalid username or PIN."
    public void testCreateAccount_InvalidPin() {
        try {
            if (!ValidateAccount.validateUsername("Alice99") || !ValidateAccount.validatePin("12")) {
                throw new ValidateAccountExceptionHandler("Invalid username or PIN.");
            }
            fail("Exception Expected");
        } catch (ValidateAccountExceptionHandler e) {
            assertEquals("Invalid username or PIN.", e.getMessage());
        }
    }

    // ------------------- DEPOSIT TEST CASES -------------------

    // Test No: 7
    // Objective: Deposit positive amount
    // Input(s): deposit = 200.0
    // Expected Output: balance increases by 200
    public void testDeposit_Valid() {
        Account acc = new Account("John", "1234");
        manager.deposit(acc, 200.0);
        assertEquals(200.0, acc.getBalance());
    }

    // Test No: 8
    // Objective: Deposit negative amount
    // Input(s): deposit = -50.0
    // Expected Output: balance should not change
    public void testDeposit_NegativeAmount() {
        Account acc = new Account("John", "1234");
        double initialBalance = acc.getBalance();
        manager.deposit(acc, -50.0);
        assertEquals(initialBalance, acc.getBalance());
    }

    // ------------------- WITHDRAW TEST CASES -------------------

    // Test No: 9
    // Objective: Withdraw valid amount within balance
    // Input(s): withdraw = 50.0, balance = 100.0
    // Expected Output: balance = 50.0
    public void testWithdraw_Valid() {
        Account acc = new Account("Jane", "4321");
        acc.addToBalance(100.0);
        manager.withdraw(acc, 50.0);
        assertEquals(50.0, acc.getBalance());
    }

    // Test No: 10
    // Objective: Withdraw amount exceeding balance
    // Input(s): withdraw = 200.0, balance = 100.0
    // Expected Output: withdrawal fails, balance unchanged
    public void testWithdraw_InsufficientFunds() {
        Account acc = new Account("Jane", "4321");
        acc.addToBalance(100.0);
        manager.withdraw(acc, 200.0);
        assertEquals(100.0, acc.getBalance());
    }

    // Test No: 11
    // Objective: Withdraw from frozen account
    // Input(s): withdraw = 50.0, account frozen
    // Expected Output: withdrawal fails
    public void testWithdraw_FrozenAccount() {
        Account acc = new Account("Mike", "1111");
        acc.accountFrozen();
        manager.withdraw(acc, 50.0);
        assertEquals(0.0, acc.getBalance());
    }

    // Test No: 12
    // Objective: Boundary test - withdraw exactly balance
    // Input(s): withdraw = 100.0, balance = 100.0
    // Expected Output: balance = 0.0
    public void testWithdraw_ExactBalance() {
        Account acc = new Account("Lara", "2222");
        acc.addToBalance(100.0);
        manager.withdraw(acc, 100.0);
        assertEquals(0.0, acc.getBalance());
    }

}
