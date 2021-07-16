package business.model.responses;

import business.model.IUser;

import java.util.List;

public class UserListResponse {

    private List<IUser> IUsers;
    private List<String> errors;

    public UserListResponse(List<IUser> IUsers, List<String> errors){
        this.IUsers = IUsers;
        this.errors = errors;
    }

    public List<IUser> getUsers(){
        return IUsers;
    }

    public List<String> getErrors() {
        return errors;
    }
}
