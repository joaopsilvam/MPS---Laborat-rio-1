package business.control.factories;

import business.control.validators.IUserValidator;
import business.control.validators.LoginUserValidator;
import business.control.validators.PasswordUserValidator;

import java.util.List;
import java.util.ArrayList;

public class UserValidatorFactory {

    public List<IUserValidator> create(){
        List<IUserValidator> validators = new ArrayList<>();

        validators.add(new LoginUserValidator());
        validators.add(new PasswordUserValidator());

        return validators;
    }
}
