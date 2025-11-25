public class ValidateAccountExceptionHandler extends Exception{
	String message;
		
		public ValidateAccountExceptionHandler(String errMessage){
			message = errMessage;
		}
		
		public String getMessage() {
			return message;
		}
}


