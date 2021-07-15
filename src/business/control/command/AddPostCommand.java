package business.control.command;

import business.control.Facade;
import business.model.Post;

import java.util.List;

public class AddPostCommand extends CommandWithResult<List<String>>{

    private final Facade facade;
    private final Post post;

    public AddPostCommand(Facade facade, Post post){
        this.facade = facade;
        this.post = post;
    }

    @Override
    public void execute() {
        result = facade.addPost(post);
    }
}
