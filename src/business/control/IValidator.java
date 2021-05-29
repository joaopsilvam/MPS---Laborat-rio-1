package business.control;

import exceptions.UserException;
import business.model.User;

public interface IValidator
{
	void validate(User user) throws UserException;
}