package business.control.command;

import business.control.Facade;
import business.model.User;

import java.util.List;

public class AddUserCommand extends CommandWithResult<List<String>>{

    private final Facade facade;
    private final User user;

    public AddUserCommand(Facade facade, User user){
        this.facade = facade;
        this.user = user;
    }

    @Override
    public void execute() {
        result = facade.addUser(user);
    }
}
