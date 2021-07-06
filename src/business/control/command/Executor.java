package business.control.command;

import java.util.List;

public class Executor {

    public void performOperation(Command command) {
        command.execute();
    }
}
