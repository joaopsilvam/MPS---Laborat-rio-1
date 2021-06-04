package business.model;

import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable {

    private String data;
    private String name;
    private Date postDate;

    public Document(String data, String name, Date date){
        this.data = data;
        this.name = name;
        this.data = data;
    }

    public Document() {
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
}
