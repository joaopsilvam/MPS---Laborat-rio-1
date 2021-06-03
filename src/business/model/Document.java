package business.model;

public class Document {
    private String data;

    public Document(String data){
        this.data = data;
    }

    public Document() {
        this("");
    }

    public String getData(){
        return this.data;
    }

    public void setData(String data){
        this.data = data;
    }
}
