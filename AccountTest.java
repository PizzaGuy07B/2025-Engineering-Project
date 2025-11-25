import junit.framework.TestCase;

public class AccountTest extends TestCase {
	
	//Test No: 1
    //Objective: Test account creation
    //Input(s): username = "John", pin = "1234"
    //Expected Output: Account created successfully

    public void testAccountCreation() {
        try {
            Account acc = new Account("Tony", "1234");
            assertEquals("John", acc.getUsername());
            assertEquals("1234", acc.getPin());
            assertEquals(0.0, acc.getBalance());
        }
        catch (AccountExceptionHandler e) {
            fail("No Exception Expected");
        }
    }

    //Test No: 2
    //Objective: Test deposit increases balance
    //Input(s): deposit = 100.0
    //Expected Output: balance = 100.0

    public void testDeposit() {
        try {
            Account acc = new Account("Tony", "1234");
            acc.addToBalance(100.0);
            assertEquals(100.0, acc.getBalance());
        }
        catch (AccountExceptionHandler e) {
            fail("No Exception Expected");
        }
    }

    //Test No: 3
    //Objective: Test withdraw decreases balance
    //Input(s): withdraw = 50.0
    //Expected Output: balance = 50.0
    
    public void testWithdraw() {
        try {
            Account acc = new Account("Tony", "1234");
            acc.addToBalance(100.0);
            acc.withdrawFromBalance(50.0);
            assertEquals(50.0, acc.getBalance());
        }
        catch (AccountExceptionHandler e) {
            fail("No Exception Expected");
        }
    }

    //Test No: 4
    //Objective: Test withdraw from empty balance
    //Input(s): withdraw = 10.0
    //Expected Output: Exception message "Insufficient funds"

    public void testWithdrawWithoutFunds() {
        try {
            Account acc = new Account("John", "1111");
            if (acc.getBalance() < 10.0) {
                throw new AccountExceptionHandler("Insufficient funds");
            }
            acc.withdrawFromBalance(10.0);
            fail("Exception Expected");
        }
        catch (AccountExceptionHandler e) {
            assertEquals("Insufficient funds", e.getMessage());
        }
    }
}
