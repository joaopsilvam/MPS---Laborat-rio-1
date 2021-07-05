package business.control.command;

public abstract class CommandWithResult<T> implements Command{

    protected T result;

    final public T getResult(){
        return result;
    }
}
