package business.model;

import exceptions.UserException;

import java.util.List;

public class UserListResponse {

    private List<User> users;
    private List<UserException> exceptions;

    public UserListResponse(List<User> users, List<UserException> exceptions){
        this.users = users;
        this.exceptions = exceptions;
    }

    public List<User> getUsers(){
        return users;
    }

    public List<UserException> getExceptions() {
        return exceptions;
    }
}
