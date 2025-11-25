
public class AccountManagerExceptionHandler extends Exception{
	String message;
		
		public AccountManagerExceptionHandler(String errMessage){
			message = errMessage;
		}
		
		public String getMessage() {
			return message;
		}
}

