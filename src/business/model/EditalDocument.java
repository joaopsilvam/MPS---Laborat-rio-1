package business.model;

import java.io.Serializable;
import java.util.Date;

public class EditalDocument implements IDocument, Serializable {

    private String data;
    private String name;
    private Date postDate;
    private Situation situation;

    public EditalDocument(String data, String name, Date date){
        this.data = data;
        this.name = name;
        this.postDate = date;
        this.situation = Situation.PUBLISHED;
    }

    public EditalDocument() {
        this("", "", new Date());
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getData(){
        return this.data;
    }

    public void setData(String data){
        this.data = data;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Situation getSituation(){
        return this.situation;
    }

    public void setSituation(Situation situation){
        this.situation = situation;
    }

    @Override
    public String getDetails() {
        String text = "Título do documento: "+this.name;
        text+= " -- Data de publicação: "+this.postDate;
        text+= " -- Status: "+this.situation;
        return text;
    }
}
