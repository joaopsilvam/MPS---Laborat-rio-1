package business.model.responses;

import business.model.INews;

import java.util.List;

public class PostListResponse {

    private List<INews> INews;
    private List<String> errors;

    public PostListResponse(List<INews> INews, List<String> errors){
        this.INews = INews;
        this.errors = errors;
    }

    public List<INews> getPosts() {
        return INews;
    }

    public List<String> getErrors() {
        return errors;
    }
}
