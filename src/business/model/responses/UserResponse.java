package business.model.responses;

import business.model.User;

import java.util.List;

public class UserResponse implements Response{

    private User user;
    private List<String> errors;

    public UserResponse(User user, List<String> errors){
        this.user = user;
        this.errors = errors;
    }

    public User getUser(){
        return user;
    }

    public List<String> getErrors(){
        return errors;
    }
}
