package business.control.command;

import business.control.Facade;
import business.model.responses.NewsResponse;

public class ListOneNewsCommand extends CommandWithResult<NewsResponse>{
    private final Facade facade;
    private final String titulo;

    public ListOneNewsCommand(Facade facade, String titulo){
        this.facade = facade;
        this.titulo = titulo;
    }

    @Override
    public void execute() {
        result = facade.readPost(titulo);
    }
}
