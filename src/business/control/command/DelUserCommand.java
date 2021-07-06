package business.control.command;

import business.control.Facade;
import business.model.User;
import exceptions.InfraException;

import java.util.List;

public class DelUserCommand extends CommandWithResult<List<String>>{
    private final Facade facade;
    private final String login;

    public DelUserCommand(Facade facade, String login){
        this.facade = facade;
        this.login = login;
    }

    @Override
    public void execute() {
        result = facade.deleteUser(this.login);
    }
}
