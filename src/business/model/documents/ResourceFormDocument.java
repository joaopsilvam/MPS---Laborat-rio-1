package business.model.documents;

import business.model.IDocument;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class ResourceFormDocument implements IDocument, Serializable {

    private String data;
    private String name;
    private Date postDate;
    private String processCode;

    public ResourceFormDocument(String data, String name, Date date){
        this.data = data;
        this.name = name;
        this.postDate = date;
        this.processCode = "PC - "+ new Random().nextDouble();
    }

    public ResourceFormDocument() {
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

    @Override
    public String getDetails() {
        String text = "Título do documento: "+this.name;
        text+= " -- Data de publicação: "+this.postDate;
        text+= " -- Código do processo: "+this.processCode;
        return text;
    }
}
