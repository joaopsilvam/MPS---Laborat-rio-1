package business.model;

import exceptions.UserException;
import java.util.List;

public class UserResponse {

    private User user;
    private List<UserException> exceptions;

    public UserResponse(User user, List<UserException> exceptions){
        this.user = user;
        this.exceptions = exceptions;
    }

    public User getUser(){
        return user;
    }

    public List<UserException> getExceptions(){
        return exceptions;
    }
}
