package business.control;

import business.model.Post;
import business.model.responses.PostListResponse;
import business.model.responses.PostResponse;
import util.PostException;
import util.InfraException;
import infra.PostPersistence;
import infra.Paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class PostControl {

    public class Memento{

        private final HashMap<String, Post> posts;

        public Memento(HashMap<String, Post> posts){
            this.posts = new HashMap<>();

            for(String key : posts.keySet()){
                Post post = posts.get(key);
                this.posts.put(key, post.copy());
            }
        }
    }

    private HashMap<String, Post> posts;

    public PostControl() throws InfraException{
        posts = new HashMap<>();
        loadData();
    }

    private void loadData() throws InfraException {
        posts = PostPersistence.loadPosts(Paths.POSTS_PATH);
    }

    public void saveData(){
        PostPersistence.savePosts(posts, Paths.POSTS_PATH);
    }

    public List<String> add(Post post){
        List<String> errors = new ArrayList<>();
        try{
            avaliablePost(post.getTitulo());
            posts.put(post.getTitulo(), post);
        }catch(PostException e){
            errors.add(e.getMessage());
        }
        return errors;
    }

    public PostResponse read(String titulo){
        List<String> errors = new ArrayList<>();
        Post post = null;

        try{
            hasPost(titulo);
            post = posts.get(titulo);
        }catch (PostException e){
            errors.add(e.getMessage());
        }

        return new PostResponse(post, errors);
    }

    public PostListResponse readAll(){
        List<String> errors = new ArrayList<>();
        List<Post> posts = new ArrayList<>();

        for(Post post : this.posts.values()){
            posts.add(post);
        }

        return new PostListResponse(posts, errors);
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
