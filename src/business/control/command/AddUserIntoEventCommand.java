package business.control.command;

import business.control.Facade;
import business.model.User;

import java.util.List;

public class AddUserIntoEventCommand extends CommandWithResult<List<String>>{
    private final Facade facade;
    private final String login;
    private final String eventName;

    public AddUserIntoEventCommand(Facade facade, String login, String eventName){
        this.facade = facade;
        this.login = login;
        this.eventName = eventName;
    }

    @Override
    public void execute() {
        result = facade.addUserIntoEvent(this.login, this.eventName);
    }
}
