package business.control.command;

import business.control.Facade;
import business.model.IEvent;

import java.util.List;

public class AddEventCommand extends CommandWithResult<List<String>>{

    private final Facade facade;
    private final IEvent event;

    public AddEventCommand(Facade facade, IEvent event){
        this.facade = facade;
        this.event = event;
    }

    @Override
    public void execute() {
        result = facade.addEvent(event);
    }
}
