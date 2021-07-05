package business.control.command;

import business.control.Facade;
import business.model.User;
import exceptions.InfraException;

import java.util.List;

public class AddCommand implements Command{
    private Facade facade;

    public AddCommand(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute() {
    }

    @Override
    public void execute(User user) {
        facade.addUser(user);
    }
}
