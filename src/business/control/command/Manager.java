package business.control.command;

import business.model.User;
import exceptions.InfraException;

import java.util.List;

public class Manager {

    public List<String> performOperation(User user, Command command) {
        return command.execute(user);
    }
}
