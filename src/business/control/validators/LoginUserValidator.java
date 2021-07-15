package business.control.validators;

import business.model.IUser;

import java.util.List;
import java.util.regex.Pattern;

public class LoginUserValidator extends UserValidatorBase {

	private Pattern containsNumberRegex;

	public LoginUserValidator(){
		containsNumberRegex = Pattern.compile(".*\\d.*");
	}

	public void validate(IUser IUser, List<String> errors)
	{
		String value = IUser.getLogin();
		boolean containsNumber = containsNumberRegex.matcher(value).matches();
		boolean sizeInvalid = value.isEmpty() || value.length() > 12;

		if(containsNumber || sizeInvalid){//condição de senha inválida
			errors.add(createErrorMessage(sizeInvalid, containsNumber));
		}

		super.validate(IUser, errors);
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