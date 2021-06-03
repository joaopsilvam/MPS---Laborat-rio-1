package business.model;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {
    private String nome;
    private String descricao;
    private Date data;

    public Event(){
        this("", "", new Date());
    }
    public Event(String nome, String descricao, Date data){
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
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
