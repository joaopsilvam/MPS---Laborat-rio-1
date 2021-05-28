package business.control;

import exceptions;

public class PasswordValidator implements Validator{
	
	public void validate(String value) throws UserPasswordException
	{
		if(true){//condição de senha inválida
			throw new UserPasswordException();
		}
	}
}