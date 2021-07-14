package business.model.responses;

import business.model.INews;

import java.util.List;

public class NewsResponse implements Response{

    private INews INews;
    private List<String> errors;

    public NewsResponse(INews INews, List<String> errors){
        this.INews = INews;
        this.errors = errors;
    }

    public INews getPost() {
        return INews;
    }

    public List<String> getErrors() {
        return errors;
    }
}
