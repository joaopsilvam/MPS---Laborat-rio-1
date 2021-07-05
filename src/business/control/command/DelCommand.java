package business.control.command;

import business.control.Facade;
import business.model.User;
import exceptions.InfraException;

import java.util.List;

public class DelCommand implements Command{
    private Facade facade;

    public DelCommand(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute() {
    }

    @Override
    public List<String> execute(User user){
        return facade.deleteUser(user.getLogin());
    }
}
