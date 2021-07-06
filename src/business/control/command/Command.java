package business.control.command;

import business.model.User;
import exceptions.InfraException;

import java.util.List;

public interface Command {
    void execute();

    void undo();
}
