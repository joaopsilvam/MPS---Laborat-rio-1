package business.control.command;

public interface Command {
    void execute();

    void undo();
}
