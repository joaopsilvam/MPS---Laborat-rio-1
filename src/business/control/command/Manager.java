package business.control.command;

import business.model.User;
import exceptions.InfraException;

import java.util.List;

public class Manager {

    public void performOperation(User user, Command command) {
        command.execute(user);
    }
}
