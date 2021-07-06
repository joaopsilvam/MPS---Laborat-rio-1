package business.control.command;

import business.control.Facade;
import business.model.responses.DocumentResponse;

public class ListOneDocumentCommand extends CommandWithResult<DocumentResponse>{
    private final Facade facade;
    private final String name;

    public ListOneDocumentCommand(Facade facade, String name){
        this.facade = facade;
        this.name = name;
    }

    @Override
    public void execute() {
        result = facade.readDocument(name);
    }
}
