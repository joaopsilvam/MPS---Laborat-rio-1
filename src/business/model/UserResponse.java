package business.model;

import java.util.List;

public class UserResponse {

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
