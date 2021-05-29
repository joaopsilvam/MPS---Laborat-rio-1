package business.control;

import exceptions.UserPasswordException;
import business.model.User;

public class PasswordValidator implements IValidator{
	
	public void validate(User user) throws UserPasswordException
	{
		String value = user.getPassword();
		boolean sizeInvalid = value.length() < 8 || value.length() > 20;

		if(sizeInvalid){//condição de senha inválida
			throw new UserPasswordException();
		}
	}
}