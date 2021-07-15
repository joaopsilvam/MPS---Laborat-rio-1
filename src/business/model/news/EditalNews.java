package business.model.news;

import business.model.INews;

import java.util.Date;

public class EditalNews extends NewsBase{

    String link;
    public EditalNews(EditalNews editalNews){
        super(editalNews.getTitle(), editalNews.getDescription(), editalNews.getDate());
        this.link = editalNews.link;
    }

    public EditalNews(){
        super();
        link = "";
    }

    public EditalNews(String title, String description, Date date, String link){
        super(title, description, date);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDetails(){
        return super.getDetails() + "Link: " + link+"\n";
    }

    public INews copy(){
        return new EditalNews(this);
    }
}
