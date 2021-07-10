package business.control.validators;

import business.model.User;

import java.util.List;
import java.util.regex.Pattern;

public class PasswordUserValidator extends UserValidatorBase{

	private Pattern containsTwoNumbersPattern;//padrão para ao menos 2 números
	private Pattern containsWordCharPattern;//padrão para letras

	public PasswordUserValidator(){
		containsTwoNumbersPattern = Pattern.compile(".*\\d{2,}.*");
		containsWordCharPattern = Pattern.compile(".*[a-zA-Z].*");
	}

	public void validate(User user, List<String> errors)
	{
		String value = user.getPassword();
		boolean containsWordChar = containsWordCharPattern.matcher(value).matches();
		boolean containsTwoNumbers = containsTwoNumbersPattern.matcher(value).matches();
		boolean formatValid = containsWordChar && containsTwoNumbers;
		boolean sizeInvalid = value.length() < 8 || value.length() > 20;

		if(sizeInvalid || !formatValid){//condição de senha inválida
			errors.add(createErrorMessage(sizeInvalid, !formatValid));
		}

		super.validate(user, errors);
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