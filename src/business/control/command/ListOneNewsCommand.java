package business.control.command;

import business.control.Facade;
import business.model.responses.PostResponse;

public class ListOnePostCommand extends CommandWithResult<PostResponse>{
    private final Facade facade;
    private final String titulo;

    public ListOnePostCommand(Facade facade, String titulo){
        this.facade = facade;
        this.titulo = titulo;
    }

    @Override
    public void execute() {
        result = facade.readPost(titulo);
    }
}
