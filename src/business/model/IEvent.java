package business.model;

import java.util.Date;
import java.util.HashMap;

public interface IEvent {
    String getName();
    void setName(String name);

    Date getDate();
    void setDate(Date date);

    String getDescription();
    void setDescription(String description);

    HashMap<String, IUser> getUsers();
    void setUsers(HashMap<String, IUser> users);

    String getDetails();
    IEvent copy();
}
