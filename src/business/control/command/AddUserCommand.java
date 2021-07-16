package business.control.command;

import business.control.Facade;
import business.model.IUser;

import java.util.List;

public class AddUserCommand extends CommandWithResult<List<String>>{

    private final Facade facade;
    private final IUser IUser;

    public AddUserCommand(Facade facade, IUser IUser){
        this.facade = facade;
        this.IUser = IUser;
    }

    @Override
    public void execute() {
        result = facade.addUser(IUser);
    }
}
