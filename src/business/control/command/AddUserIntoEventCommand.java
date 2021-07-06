package business.control.command;

import business.control.EventControl;
import business.control.Facade;

import java.util.List;

public class AddUserIntoEventCommand extends CommandWithResult<List<String>>{
    private final Facade facade;
    private final String login;
    private final String eventName;

    private EventControl.Memento backup;

    public AddUserIntoEventCommand(Facade facade, String login, String eventName){
        this.facade = facade;
        this.login = login;
        this.eventName = eventName;
    }

    @Override
    public void execute() {
        backup = facade.backupEventControl();
        result = facade.addUserIntoEvent(this.login, this.eventName);
    }

    @Override
    public void undo(){
        if(backup != null){
            facade.restoreEventControl(backup);
        }
    }
}
