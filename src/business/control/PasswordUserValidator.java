package business.control;

import exceptions.UserPasswordException;
import business.model.User;

import java.util.regex.Pattern;

public class PasswordUserValidator implements IUserValidator {

	private Pattern containsTwoNumbersPattern;//padrão para ao menos 2 números
	private Pattern containsWordCharPattern;//padrão para letras

	public PasswordUserValidator(){
		containsTwoNumbersPattern = Pattern.compile(".*\\d{2,}.*");
		containsWordCharPattern = Pattern.compile(".*[a-zA-Z].*");
	}

	public void validate(User user) throws UserPasswordException
	{
		String value = user.getPassword();
		boolean containsWordChar = containsWordCharPattern.matcher(value).matches();
		boolean containsTwoNumbers = containsTwoNumbersPattern.matcher(value).matches();
		boolean formatValid = containsWordChar && containsTwoNumbers;
		boolean sizeInvalid = value.length() < 8 || value.length() > 20;

		if(sizeInvalid || !formatValid){//condição de senha inválida
			throw new UserPasswordException(createErrorMessage(sizeInvalid, !formatValid));
		}
	}

	private String createErrorMessage(boolean sizeInvalid, boolean formatInvalid){
		String message = "";

		if(sizeInvalid){
			message += "*Tamanho da senha inválido, deve conter de 8 a 20 caracteres";
		}

		if(formatInvalid){
			message += "\n*Formato da senha inválido, deve conter letras e números, e ao menos 2 números";
		}

		return message;
	}
}