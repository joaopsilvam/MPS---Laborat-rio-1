package business.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Event implements Serializable {
    private String name;
    private String descricao;
    private Date data;
    private HashMap<String, User> users;

    public Event(){
        this("", "", new Date());
    }
    
    public Event(String name, String descricao, Date data){
        this.name = name;
        this.descricao = descricao;
        this.data = data;
        this.users = new HashMap<>();
    }

    public Event(String name, String descricao, Date data, HashMap<String, User> users){
        this.name = name;
        this.descricao = descricao;
        this.data = data;
        this.users = users;
    }

    public HashMap<String,User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String,User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
