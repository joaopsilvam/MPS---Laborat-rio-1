package business.control.command;

import business.control.Facade;
import business.model.responses.PostListResponse;

public class ListAllPostCommand extends CommandWithResult<PostListResponse>{
    private final Facade facade;

    public ListAllPostCommand(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute() {
        result = facade.readAllPosts();
    }
}
