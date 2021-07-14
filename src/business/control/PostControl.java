package business.control;

import business.model.INews;
import business.model.responses.NewsListResponse;
import business.model.responses.NewsResponse;
import util.PostException;
import util.InfraException;
import infra.NewsPersistence;
import infra.Paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class PostControl {

    public class Memento{

        private final HashMap<String, INews> posts;

        public Memento(HashMap<String, INews> posts){
            this.posts = new HashMap<>();

            for(String key : posts.keySet()){
                INews INews = posts.get(key);
                this.posts.put(key, INews.copy());
            }
        }
    }

    private HashMap<String, INews> posts;

    public PostControl() throws InfraException{
        posts = new HashMap<>();
        loadData();
    }

    private void loadData() throws InfraException {
        posts = NewsPersistence.loadPosts(Paths.POSTS_PATH);
    }

    public void saveData(){
        NewsPersistence.savePosts(posts, Paths.POSTS_PATH);
    }

    public List<String> add(INews INews){
        List<String> errors = new ArrayList<>();
        try{
            avaliablePost(INews.getTitle());
            posts.put(INews.getTitle(), INews);
        }catch(PostException e){
            errors.add(e.getMessage());
        }
        return errors;
    }

    public NewsResponse read(String titulo){
        List<String> errors = new ArrayList<>();
        INews INews = null;

        try{
            hasPost(titulo);
            INews = posts.get(titulo);
        }catch (PostException e){
            errors.add(e.getMessage());
        }

        return new NewsResponse(INews, errors);
    }

    public NewsListResponse readAll(){
        List<String> errors = new ArrayList<>();
        List<INews> news = new ArrayList<>();

        for(INews _new : this.posts.values()){
            news.add(_new);
        }

        return new NewsListResponse(news, errors);
    }

    public List<String> delete(String titulo){
        List<String> errors = new ArrayList<>();

        try {
            hasPost(titulo);
            posts.remove(titulo);
        }catch (PostException e){
            errors.add(titulo);
        }

        return errors;
    }

    public void restore(PostControl.Memento memento){
        posts = memento.posts;
    }

    public PostControl.Memento backup(){
        return new PostControl.Memento(posts);
    }

    private void avaliablePost(String titulo) throws PostException {
        if(posts.containsKey(titulo)){
            throw new PostException("Já existe uma noticia com este nome");
        }
    }

    private void hasPost(String titulo) throws PostException {
        if(!posts.containsKey(titulo)){
            throw new PostException("Essa noticia não existe");
        }
    }
}
