public class ValidateAccount {

	public static boolean validatePin(String pin) throws ValidateAccountExceptionHandler
	{
		
		if (pin.length() == 4)
		{
			try
			{
				Integer.parseInt(pin);
				return true;
			}
			catch (NumberFormatException nfe)
			{
				return false;
			}
			
		}
		else
		{
			return false;
		}
	}
	
	public static boolean validateUsername(String userName) throws ValidateAccountExceptionHandler
	{
		if (userName.length() > 0 && userName.length() <= 15)
		{
			for (int i = 0; i < userName.length(); i++)
			{
				if (userName.charAt(i) == ' ')
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
