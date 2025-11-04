import junit.framework.TestCase;

public class ValidateAccountTest extends TestCase{
	
	//Test No: 1
    //Objective: Test valid username (length within limit, no spaces)
    //Input(s): user = "SeanD"
    //Expected Output: True
    public void testValidateUser_Valid() {
        try {
            boolean result = ValidateAccount.validateUsername("SeanD");
            assertEquals(true, result);
        } catch (ValidateAccountExceptionHandler e) {
            fail("No Exception Expected");
        }
    }

    //Test No: 2
    //Objective: Test invalid username with space
    //Input(s): user = "Sean D"
    //Expected Output: Exception with message "Username contains spaces"
    
    public void testValidateUser_HasSpace() {
        try {
            ValidateAccount.validateUsername("Sean D");
            fail("Exception Expected");
        } catch (ValidateAccountExceptionHandler e) {
            assertEquals("Username contains spaces", e.getMessage());
        }
    }

    //Test No: 3
    //Objective: Test username too long
    //Input(s): user = "averylongusernamehere"
    //Expected Output: Exception "Username length must not be greater than 15 characters"
    
    public void testValidateUser_TooLong() {
        try {
            ValidateAccount.validateUsername("averylongusernamehere");
            fail("Exception Expected");
        } catch (ValidateAccountExceptionHandler e) {
            assertEquals("Username length invalid", e.getMessage());
        }
    }

    //Test No: 4
    //Objective: Test valid PIN
    //Input(s): pin = "1234"
    //Expected Output: True
    
    public void testValidatePin_Valid() {
        try {
            boolean result = ValidateAccount.validatePin("1234");
            assertEquals(true, result);
        } catch (ValidateAccountExceptionHandler e) {
            fail("No Exception Expected");
        }
    }

    //Test No: 5
    //Objective: Test PIN too short
    //Input(s): pin = "123"
    //Expected Output: Exception "PIN must be 4 digits"
    
    public void testValidatePin_TooShort() {
        try {
            ValidateAccount.validatePin("123");
            fail("Exception Expected");
        } catch (ValidateAccountExceptionHandler e) {
            assertEquals("PIN must be 4 digits", e.getMessage());
        }
    }

    //Test No: 6
    //Objective: Test PIN with letters
    //Input(s): pin = "12345"
    //Expected Output: Exception "PIN must be 4 digits"
    
    public void testValidatePin_Letters() {
        try {
            ValidateAccount.validatePin("12345");
            fail("Exception Expected");
        } catch (ValidateAccountExceptionHandler e) {
            assertEquals("PIN must be 4 digits", e.getMessage());
        }
    }
}
