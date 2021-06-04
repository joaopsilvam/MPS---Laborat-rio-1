package business.model.responses;

import business.model.Document;

import java.util.List;

public class DocumentListReponse {

    private List<Document> documents;
    private List<String> errors;

    public DocumentListReponse(List<Document> documents, List<String> errors){
        this.documents = documents;
        this.errors = errors;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public List<String> getErrors() {
        return errors;
    }
}
