package business.control.command;

import business.control.Facade;
import business.model.responses.NewsListResponse;

public class ListAllNewsCommand extends CommandWithResult<NewsListResponse>{
    private final Facade facade;

    public ListAllNewsCommand(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute() {
        result = facade.readAllPosts();
    }
}
