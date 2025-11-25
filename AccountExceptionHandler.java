public class AccountExceptionHandler extends Exception {
	String message;
		
	public AccountExceptionHandler(String errMessage){
		message = errMessage;
	}
	
	public String getMessage() {
		return message;
	}
}


