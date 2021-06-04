package business.control;

import business.model.Document;
import business.model.responses.DocumentListReponse;
import business.model.responses.DocumentResponse;
import exceptions.DocumentException;
import exceptions.InfraException;
import infra.DocumentPersistence;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocumentControl {

    private HashMap<String, Document> documents;

    public DocumentControl() throws InfraException{
        documents = new HashMap<>();
        loadData();
    }

    private void loadData() throws InfraException {
        documents = DocumentPersistence.loadDocuments("documents.dat");
    }

    public void saveData(){
        DocumentPersistence.saveDocuments(documents, "documents.dat");
    }

    public List<String> add(Document data){

        List<String> errors = new ArrayList<>();

        try{
            avaliableDocument(data.getName());
            documents.put(data.getName(), data);
        }
        catch (DocumentException e){
            errors.add(e.getMessage());
        }

        return errors;
    }

    public DocumentResponse read(String name){
        Document document = null;
        List<String> errors = new ArrayList<>();

        try{
            hasDocument(name);
            document = documents.get(name);

        }catch (DocumentException e){
            errors.add(e.getMessage());
        }

        return new DocumentResponse(document, errors);
    }

    public DocumentListReponse readAll(){
        List<Document> documents = new ArrayList<>();

        for(Document document : this.documents.values()){
            documents.add(document);
        }

        return new DocumentListReponse(documents, new ArrayList<>());
    }

    public List<String> delete(String name){
        List<String> errors = new ArrayList<>();

        try {
            hasDocument(name);
            documents.remove(name);
        }
        catch (Exception e){
            errors.add(e.getMessage());
        }

        return errors;
    }

    private void avaliableDocument(String documentName) throws DocumentException {
        if(documents.containsKey(documentName)){
            throw new DocumentException("Já existe um documento com este nome");
        }
    }

    private void hasDocument(String documentName) throws DocumentException {
        if(!documents.containsKey(documentName)){
            throw new DocumentException("Esse documento não existe");
        }
    }
}
