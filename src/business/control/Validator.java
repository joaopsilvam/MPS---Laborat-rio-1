package business.control;

import exceptions.*;

public interface Validator
{
	void validate(String value) throws UserLoginException, UserPasswordException;
}