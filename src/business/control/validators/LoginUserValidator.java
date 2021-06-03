package business.control.validators;

import exceptions.UserLoginException;
import business.model.User;

import java.util.regex.Pattern;

public class LoginUserValidator implements IUserValidator {

	private Pattern containsNumberRegex;

	public LoginUserValidator(){
		containsNumberRegex = Pattern.compile(".*\\d.*");
	}

	public void validate(User user) throws UserLoginException
	{
		String value = user.getLogin();
		boolean containsNumber = containsNumberRegex.matcher(value).matches();
		boolean sizeInvalid = value.isEmpty() || value.length() > 12;

		if(containsNumber || sizeInvalid){//condição de senha inválida
			throw new UserLoginException(createErrorMessage(sizeInvalid, containsNumber));
		}
	}

	private String createErrorMessage(boolean sizeInvalid, boolean formatInvalid){
		String message = "";

		if(sizeInvalid){
			message += "*Tamanho do usuário inválido, deve conter de 1 a 12 caracteres";
		}

		if(formatInvalid){
			message += "\n*Usuário não deve conter números";
		}

		return message;
	}
}