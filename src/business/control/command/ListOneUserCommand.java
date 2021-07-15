package business.control.command;

import business.control.Facade;
import business.model.responses.UserResponse;

public class ListOneUserCommand extends CommandWithResult<UserResponse>{
    private final Facade facade;
    private final String login;

    public ListOneUserCommand(Facade facade, String login){
        this.facade = facade;
        this.login = login;
    }

    @Override
    public void execute() {
        result = facade.readUser(login);
    }
}
