package business.control;

import exceptions.UserPasswordException;

public class PasswordValidator implements Validator{
	
	public void validate(String value) throws UserPasswordException
	{
		boolean sizeInvalid = value.length() < 8 || value.length() > 20;

		if(sizeInvalid){//condição de senha inválida
			throw new UserPasswordException();
		}
	}
}