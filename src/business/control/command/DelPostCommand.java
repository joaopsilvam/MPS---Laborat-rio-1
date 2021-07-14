package business.control.command;

import business.control.Facade;

import java.util.List;

public class DelPostCommand extends CommandWithResult<List<String>>{
    private final Facade facade;
    private final String titulo;

    public DelPostCommand(Facade facade, String titulo){
        this.facade = facade;
        this.titulo = titulo;
    }

    @Override
    public void execute() {
        result = facade.deletePost(this.titulo);
    }
}
