package business.control;

import java.util.List;
import java.util.ArrayList;

public class ValidatorFactory {

    public List<IValidator> create(){
        List<IValidator> validators = new ArrayList<>();

        validators.add(new LoginValidator());
        validators.add(new PasswordValidator());

        return validators;
    }
}
