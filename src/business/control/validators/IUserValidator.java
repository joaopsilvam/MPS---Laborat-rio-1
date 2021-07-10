package business.control.validators;

import business.model.User;

import java.util.List;

public interface IUserValidator
{
	void validate(User user, List<String> errors);

	void setNextValidator(IUserValidator nextValidator);
}