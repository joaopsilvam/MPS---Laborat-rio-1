package business.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

public class Post implements Serializable {
    private String titulo;
    private String descricao;
    private Date data;

    public Post(){
        this("", "", new Date());
    }

    public Post(Post post){
        titulo = post.titulo;
        descricao = post.descricao;
        data = post.data;
    }

    public Post(String titulo, String descricao, Date data){
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Post clonar(){
        return new Post(this);
    }
}
