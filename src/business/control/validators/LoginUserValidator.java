package business.control.validators;

import business.model.User;

import java.util.List;
import java.util.regex.Pattern;

public class LoginUserValidator extends UserValidatorBase {

	private Pattern containsNumberRegex;

	public LoginUserValidator(){
		containsNumberRegex = Pattern.compile(".*\\d.*");
	}

	public void validate(User user, List<String> errors)
	{
		String value = user.getLogin();
		boolean containsNumber = containsNumberRegex.matcher(value).matches();
		boolean sizeInvalid = value.isEmpty() || value.length() > 12;

		if(containsNumber || sizeInvalid){//condição de senha inválida
			errors.add(createErrorMessage(sizeInvalid, containsNumber));
		}

		super.validate(user, errors);
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