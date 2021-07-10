package business.control.factories;

import business.control.validators.IUserValidator;
import business.control.validators.LoginUserValidator;
import business.control.validators.PasswordUserValidator;

import java.util.List;
import java.util.ArrayList;

public class UserValidatorFactory {

    public static IUserValidator create(){
        IUserValidator validator;

        validator = new LoginUserValidator();
        validator.setNextValidator(new PasswordUserValidator());

        return validator;
    }
}
