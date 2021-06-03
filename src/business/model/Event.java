package business.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Event implements Serializable {
    private String nome;
    private String descricao;
    private Date data;
    private HashMap<String, User> users;

    public Event(){
        this("", "", new Date(), new HashMap<>());
    }

    public Event(String nome, String descricao, Date data, HashMap<String, User> users){
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
