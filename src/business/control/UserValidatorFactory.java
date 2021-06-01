package business.control;

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
