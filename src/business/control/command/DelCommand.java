package business.control.command;

import business.control.Facade;
import business.model.User;
import exceptions.InfraException;

import java.util.List;

public class DelCommand extends CommandWithResult<List<String>>{
    private final Facade facade;
    private final User user;

    public DelCommand(Facade facade, User user){
        this.facade = facade;
        this.user = user;
    }

    @Override
    public void execute() {
        result = facade.deleteUser(user.getLogin());
    }
}
