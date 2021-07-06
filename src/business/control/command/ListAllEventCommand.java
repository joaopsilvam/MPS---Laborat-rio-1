package business.control.command;

import business.control.Facade;
import business.model.responses.EventListResponse;

public class ListAllEventCommand extends CommandWithResult<EventListResponse>{
    private final Facade facade;

    public ListAllEventCommand(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute() {
        result = facade.readAllEvents();
    }
}
