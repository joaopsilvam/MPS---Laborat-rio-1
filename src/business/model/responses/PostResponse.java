package business.model.responses;

import business.model.Post;

import java.util.List;

public class PostResponse implements Response{

    private Post post;
    private List<String> errors;

    public PostResponse(Post post, List<String> errors){
        this.post = post;
        this.errors = errors;
    }

    public Post getPost() {
        return post;
    }

    public List<String> getErrors() {
        return errors;
    }
}
