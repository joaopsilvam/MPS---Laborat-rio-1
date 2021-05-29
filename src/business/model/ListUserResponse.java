package business.model;

import java.util.List;

public class ListUserResponse {

    private List<User> users;

    public ListUserResponse(List<User> users){
        this.users = users;
    }

    public List<User> getUsers(){
        return users;
    }
}
