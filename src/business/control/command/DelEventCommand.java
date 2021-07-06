package business.control.command;

import business.control.Facade;

import java.util.List;

public class DelEventCommand extends CommandWithResult<List<String>>{
    private final Facade facade;
    private final String name;

    public DelEventCommand(Facade facade, String name){
        this.facade = facade;
        this.name = name;
    }

    @Override
    public void execute() {
        result = facade.deleteEvent(this.name);
    }
}
