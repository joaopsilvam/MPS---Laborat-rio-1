package business.control.command;

import business.control.Facade;
import business.model.IDocument;

import java.util.List;

public class AddDocumentCommand extends CommandWithResult<List<String>>{

    private final Facade facade;
    private final IDocument document;

    public AddDocumentCommand(Facade facade, IDocument document){
        this.facade = facade;
        this.document = document;
    }

    @Override
    public void execute() {
        result = facade.addDocument(document);
    }
}
