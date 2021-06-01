package business.control;

import java.util.List;
import java.util.ArrayList;

public class ValidatorFactory {

    public List<IUserValidator> create(){
        List<IUserValidator> validators = new ArrayList<>();

        validators.add(new LoginUserValidator());
        validators.add(new PasswordUserValidator());

        return validators;
    }
}
