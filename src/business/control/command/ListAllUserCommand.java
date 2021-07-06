package business.control.command;

import business.control.Facade;
import business.model.responses.UserListResponse;

public class ListAllUserCommand extends CommandWithResult<UserListResponse>{
    private final Facade facade;

    public ListAllUserCommand(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute() {
        result = facade.readAllUsers();
    }
}
