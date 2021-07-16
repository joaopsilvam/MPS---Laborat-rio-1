package business.model.responses;

import business.model.IDocument;

import java.util.List;

public class DocumentListReponse {

    private List<IDocument> documents;
    private List<String> errors;

    public DocumentListReponse(List<IDocument> documents, List<String> errors){
        this.documents = documents;
        this.errors = errors;
    }

    public List<IDocument> getDocuments() {
        return documents;
    }

    public List<String> getErrors() {
        return errors;
    }
}
