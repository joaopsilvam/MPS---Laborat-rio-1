package business.control.command;

import business.control.Facade;
import business.model.responses.EventResponse;

public class ListOneEventCommand extends CommandWithResult<EventResponse>{
    private final Facade facade;
    private final String name;

    public ListOneEventCommand(Facade facade, String name){
        this.facade = facade;
        this.name = name;
    }

    @Override
    public void execute() {
        result = facade.readEvent(name);
    }
}
