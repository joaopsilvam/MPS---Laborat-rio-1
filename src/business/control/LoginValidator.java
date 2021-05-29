package business.control;

import exceptions.UserLoginException;
import business.model.User;

public class LoginValidator implements IValidator{

	public void validate(User user) throws UserLoginException
	{
		String value = user.getLogin();
		boolean containsNumber = value.matches(".*\\d.*");
		boolean sizeInvalid = value.isEmpty() || value.length() > 12;

		if(containsNumber || sizeInvalid){//condição de senha inválida
			throw new UserLoginException();
		}
	}
}