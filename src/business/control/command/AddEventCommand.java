package business.control.command;

import business.control.Facade;
import business.model.Event;
import business.model.User;

import java.util.List;

public class AddEventCommand extends CommandWithResult<List<String>>{

    private final Facade facade;
    private final Event event;

    public AddEventCommand(Facade facade, Event event){
        this.facade = facade;
        this.event = event;
    }

    @Override
    public void execute() {
        result = facade.addEvent(event);
    }
}
