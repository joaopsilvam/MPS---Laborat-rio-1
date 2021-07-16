package business.model.responses;

import business.model.IUser;

import java.util.List;

public class UserResponse implements Response{

    private IUser IUser;
    private List<String> errors;

    public UserResponse(IUser IUser, List<String> errors){
        this.IUser = IUser;
        this.errors = errors;
    }

    public IUser getUser(){
        return IUser;
    }

    public List<String> getErrors(){
        return errors;
    }
}
