package business.model.responses;

import business.model.IDocument;

import java.util.ArrayList;
import java.util.List;

public class DocumentResponse implements Response{

    private IDocument document;
    private List<String> errors;

    public DocumentResponse(IDocument document, List<String> errors){
        this.document = document;
        this.errors = errors;
    }
//
//    public DocumentResponse(IDocument document, List<String> errors){
//        this(new IDocument(), new ArrayList<>());
//    }

    public IDocument getDocument() {
        return document;
    }

    public List<String> getErrors() {
        return errors;
    }
}
