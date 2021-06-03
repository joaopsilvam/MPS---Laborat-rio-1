package business.model;

import java.util.List;

public class UserListResponse {

    private List<User> users;
    private List<String> errors;

    public UserListResponse(List<User> users, List<String> errors){
        this.users = users;
        this.errors = errors;
    }

    public List<User> getUsers(){
        return users;
    }

    public List<String> getErrors() {
        return errors;
    }
}
