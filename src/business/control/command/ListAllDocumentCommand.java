package business.control.command;

import business.control.Facade;
import business.model.responses.DocumentListReponse;

public class ListAllDocumentCommand extends CommandWithResult<DocumentListReponse>{
    private final Facade facade;

    public ListAllDocumentCommand(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute() {
        result = facade.readAllDocuments();
    }
}
