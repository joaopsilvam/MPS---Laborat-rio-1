package business.model;

import exceptions.UserException;

import java.util.List;

public class ListUserResponse {

    private List<User> users;
    private List<UserException> exceptions;

    public ListUserResponse(List<User> users, List<UserException> exceptions){
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
