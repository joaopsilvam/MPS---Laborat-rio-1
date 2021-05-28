package business.control;

import exceptions.UserLoginException;

public class LoginValidator implements Validator{

	public void validate(String value) throws UserLoginException
	{
		boolean containsNumber = value.matches(".*\\d.*");
		boolean sizeInvalid = value.isEmpty() || value.length() > 12;

		if(containsNumber || sizeInvalid){//condição de senha inválida
			throw new UserLoginException();
		}
	}
}