package business.model.news;

import business.model.INews;

import java.io.Serializable;
import java.util.Date;

public abstract class NewsBase implements INews, Serializable {
    private String title;
    private String description;
    private Date date;

    public NewsBase(){
        this("", "", new Date());
    }

    public NewsBase(INews INews){
        title = INews.getTitle();
        description = INews.getDescription();
        date = INews.getDate();
    }

    public NewsBase(String title, String description, Date date){
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "Titulo: " + title + '\n' +
                "Data: " + date + '\n' +
                "Descrição: " + description + '\n';
    }
}
