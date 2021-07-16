package business.control.validators;

import business.model.IUser;

import java.util.List;

public interface IUserValidator
{
	void validate(IUser IUser, List<String> errors);

	void setNextValidator(IUserValidator nextValidator);
}