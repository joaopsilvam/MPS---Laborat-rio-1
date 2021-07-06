package business.control.command;

import business.control.Facade;
import business.model.Document;
import business.model.Event;

import java.util.List;

public class AddDocumentCommand extends CommandWithResult<List<String>>{

    private final Facade facade;
    private final Document document;

    public AddDocumentCommand(Facade facade, Document document){
        this.facade = facade;
        this.document = document;
    }

    @Override
    public void execute() {
        result = facade.addDocument(document);
    }
}
