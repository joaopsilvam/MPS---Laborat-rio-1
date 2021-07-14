package business.control.command;

import business.control.Facade;
import business.model.INews;

import java.util.List;

public class AddNewsCommand extends CommandWithResult<List<String>>{

    private final Facade facade;
    private final INews INews;

    public AddNewsCommand(Facade facade, INews INews){
        this.facade = facade;
        this.INews = INews;
    }

    @Override
    public void execute() {
        result = facade.addPost(INews);
    }
}
