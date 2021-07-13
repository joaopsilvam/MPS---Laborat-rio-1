package business.control;

import business.configuration.ApplicationConfiguration;
import business.control.factories.ReportManagerFactory;
import business.control.reportmanagers.ReportManagerBase;
import business.model.Document;
import business.model.Event;
import business.model.Post;
import business.model.User;
import business.model.responses.*;
import util.EventException;
import util.PostException;
import util.InfraException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Facade {

    private static Facade instance = null;

    public static Facade getInstance() throws InfraException{
        if(instance == null){
            instance = new Facade();
        }

        return instance;
    }

    private UserControl userControl;
    private DocumentControl documentControl;
    private EventControl eventControl;
    private PostControl postControl;
    private UserStatisticControl userStatisticControl;

    private Facade() throws InfraException{
        ApplicationConfiguration.load();

        this.userControl = new UserControl();
        this.documentControl = new DocumentControl();
        this.eventControl = new EventControl();
        this.postControl = new PostControl();
        this.userStatisticControl = new UserStatisticControl();
    }

    public void saveData(){
        this.userControl.saveData();
        this.documentControl.saveData();
        this.eventControl.saveData();
        this.postControl.saveDate();
        this.userStatisticControl.saveData();
    }

    public List<String> addUserIntoEvent(String userLogin, String eventName){
        List<String> errors = new ArrayList<>();

        UserResponse userResponse = userControl.read(userLogin);
        List<String> userErrors = userResponse.getErrors();
        errors.addAll(userErrors);

        EventResponse eventResponse = eventControl.read(eventName);
        List<String> eventErrors = eventResponse.getErrors();
        errors.addAll(eventErrors);

        if(userErrors.isEmpty() && eventErrors.isEmpty()){
            Event event = eventResponse.getEvent();
            User user = userResponse.getUser();

            try{
                avaliableUserInEvent(userLogin, event);
                HashMap<String, User> users = event.getUsers();
                users.put(userLogin, user);
            }
            catch (EventException e){
                errors.add(e.getMessage());
            }
        }

        return errors;
    }

    private void avaliableUserInEvent(String userLogin, Event event) throws EventException {
        if(event.getUsers().containsKey(userLogin)){
            throw new EventException("Usuário já está cadastrado neste evento");
        }
    }

    public List<String> addUser(User user) {
        return this.userControl.add(user);
    }

    public UserListResponse readAllUsers() {
        return this.userControl.readAll();
    }

    public UserResponse readUser(String login) {
        return this.userControl.read(login);
    }

    public List<String> deleteUser(String login) {
        return this.userControl.delete(login);
    }

    public List<String> addDocument(Document data) {
        return this.documentControl.add(data);
    }

    public DocumentResponse readDocument(String name) {
        return this.documentControl.read(name);
    }

    public DocumentListReponse readAllDocuments() {
        return this.documentControl.readAll();
    }

    public List<String> deleteDocument(String name) { return this.documentControl.delete(name); }

    public List<String> addEvent(Event event) {
        return this.eventControl.add(event);
    }

    public EventResponse readEvent(String name) {
        return this.eventControl.read(name);
    }

    public EventListResponse readAllEvents() {
        return this.eventControl.readAll();
    }

    public List<String> deleteEvent(String name) {
        return this.eventControl.delete(name);
    }

    public List<String> addPost(Post post) {
        return this.postControl.add(post);
    }

    public PostResponse readPost(String titulo) {
        return this.postControl.read(titulo);
    }

    public PostListResponse readAllPosts() {
        return this.postControl.readAll();
    }

    public List<String> deletePost(String titulo) {
        return this.postControl.delete(titulo);
    }

    public UserListResponse listAllUsersOnEvent(String nameEvent) {
        EventResponse response = this.eventControl.read(nameEvent);
        Event event = response.getEvent();
        List<String> errors = response.getErrors();
        List<User> users = new ArrayList<>();

        if(!errors.isEmpty()){
            return new UserListResponse(new ArrayList<>(), errors);
        }

        for(User user : event.getUsers().values()){
            users.add(user);
        }

        return new UserListResponse(users, errors);
    }

    public void login(User user) throws InfraException{
        this.userControl.login(user);
        this.userStatisticControl.registerLoginStatistic(user);
    }

    public void saveReport(String reportType) throws InfraException{
        ReportManagerFactory factory = ReportManagerFactory.getInstancia();
        ReportManagerBase reportManager = factory.create(reportType, userStatisticControl);

        reportManager.saveReport();
    }

    public EventControl.Memento backupEventControl(){
        return eventControl.backup();
    }

    public void restoreEventControl(EventControl.Memento backup){
        eventControl.restore(backup);
    }
}
