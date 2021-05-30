package business.control;

import exceptions.UserLoginException;
import business.model.User;

import java.util.regex.Pattern;

public class LoginValidator implements IValidator{

	private Pattern containsNumberRegex;

	public LoginValidator(){
		containsNumberRegex = Pattern.compile(".*\\d.*");
	}

	public void validate(User user) throws UserLoginException
	{
		String value = user.getLogin();
		boolean containsNumber = containsNumberRegex.matcher(value).matches();
		boolean sizeInvalid = value.isEmpty() || value.length() > 12;

		if(containsNumber || sizeInvalid){//condição de senha inválida
			throw new UserLoginException();
		}
	}
}