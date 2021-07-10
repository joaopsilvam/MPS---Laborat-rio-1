package business.control.validators;

import business.model.User;

import java.util.List;

public abstract class UserValidatorBase implements  IUserValidator{

    private IUserValidator nextValidator;

    @Override
    public void setNextValidator(IUserValidator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(User user, List<String> errors) {
        if(nextValidator != null){
            nextValidator.validate(user, errors);
        }
    }
}
