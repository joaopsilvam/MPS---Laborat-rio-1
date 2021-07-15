package business.control.validators;

import business.model.IUser;

import java.util.List;

public abstract class UserValidatorBase implements  IUserValidator{

    private IUserValidator nextValidator;

    @Override
    public void setNextValidator(IUserValidator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(IUser IUser, List<String> errors) {
        if(nextValidator != null){
            nextValidator.validate(IUser, errors);
        }
    }
}
