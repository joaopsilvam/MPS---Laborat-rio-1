package business.model.responses;

import business.model.Post;

import java.util.List;

public class PostListResponse {

    private List<Post> posts;
    private List<String> errors;

    public PostListResponse(List<Post> posts, List<String> errors){
        this.posts = posts;
        this.errors = errors;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<String> getErrors() {
        return errors;
    }
}
