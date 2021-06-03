package business.model;

import java.util.ArrayList;
import java.util.List;

public class DocumentResponse {

    private Document document;
    private List<String> errors;

    public DocumentResponse(Document document, List<String> errors){
        this.document = document;
        this.errors = errors;
    }

    public DocumentResponse(){
        this(new Document(), new ArrayList<>());
    }

    public Document getDocument() {
        return document;
    }

    public List<String> getErrors() {
        return errors;
    }
}
