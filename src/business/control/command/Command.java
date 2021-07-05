package business.control.command;

import business.model.User;
import exceptions.InfraException;

import java.util.List;

public interface Command {
    public void execute();
    public List<String> execute(User user);
}
