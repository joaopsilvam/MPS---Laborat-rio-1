package business.control;

import exceptions;

public class LoginValidator implements Validator{

	public void validate(String value) throws UserLoginException
	{
		if(true){//Condição de exceção
			throw new UserLoginException();
		}
	}
}