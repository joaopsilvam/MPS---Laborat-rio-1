package business.model.events;

import business.model.IEvent;
import business.model.IUser;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public abstract class EventBase implements IEvent, Serializable{
    private String name;
    private String description;
    private Date date;
    private HashMap<String, IUser> users;

    public EventBase(){
        this("", "", new Date());
    }

    public EventBase(EventBase event){
        name = event.getName();
        description = event.getDescription();
        date = event.getDate();
        users = (HashMap<String, IUser>) event.getUsers().clone();
    }

    public EventBase(String name, String description, Date date){
        this.name = name;
        this.description = description;
        this.date = date;
        this.users = new HashMap<>();
    }

    public EventBase(String name, String description, Date data, HashMap<String, IUser> users){
        this.name = name;
        this.description = description;
        this.date = data;
        this.users = users;
    }

    public HashMap<String, IUser> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, IUser> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails(){
        return "Nome: " + name + '\n' +
                "Data: " + date + '\n' +
                "Descrição: " + description + '\n';
    }
}
