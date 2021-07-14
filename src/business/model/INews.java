package business.model;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
    private String titulo;
    private String descricao;
    private Date data;

    public News(){
        this("", "", new Date());
    }

    public News(News news){
        titulo = news.titulo;
        descricao = news.descricao;
        data = news.data;
    }

    public News(String titulo, String descricao, Date data){
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

    public News copy(){
        return new News(this);
    }
}
