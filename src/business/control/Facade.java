package business.control;

import business.configuration.ApplicationConfiguration;
import business.control.factories.ReportManagerFactory;
import business.control.reportmanagers.ReportManagerBase;
import business.model.IEvent;
import business.model.IDocument;
import business.model.INews;
import business.model.IUser;
import business.model.responses.*;
import util.EventException;
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
        this.postControl.saveData();
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
            IEvent event = eventResponse.getEvent();
            IUser IUser = userResponse.getUser();

            try{
                avaliableUserInEvent(userLogin, event);
                HashMap<String, IUser> users = event.getUsers();
                users.put(userLogin, IUser);
            }
            catch (EventException e){
                errors.add(e.getMessage());
            }
        }

        return errors;
    }

    private void avaliableUserInEvent(String userLogin, IEvent event) throws EventException {
        if(event.getUsers().containsKey(userLogin)){
            throw new EventException("Usuário já está cadastrado neste evento");
        }
    }

    public List<String> addUser(IUser IUser) {
        return this.userControl.add(IUser);
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

    public List<String> addDocument(IDocument data) {
        return this.documentControl.add(data);
    }

    public DocumentResponse readDocument(String name) {
        return this.documentControl.read(name);
    }

    public DocumentListReponse readAllDocuments() {
        return this.documentControl.readAll();
    }

    public List<String> deleteDocument(String name) { return this.documentControl.delete(name); }

    public List<String> addEvent(IEvent event) {
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

    public List<String> addPost(INews INews) {
        return this.postControl.add(INews);
    }

    public NewsResponse readPost(String titulo) {
        return this.postControl.read(titulo);
    }

    public NewsListResponse readAllPosts() {
        return this.postControl.readAll();
    }

    public List<String> deletePost(String titulo) {
        return this.postControl.delete(titulo);
    }

    public UserListResponse listAllUsersOnEvent(String nameEvent) {
        EventResponse response = this.eventControl.read(nameEvent);
        IEvent event = response.getEvent();
        List<String> errors = response.getErrors();
        List<IUser> IUsers = new ArrayList<>();

        if(!errors.isEmpty()){
            return new UserListResponse(new ArrayList<>(), errors);
        }

        for(IUser IUser : event.getUsers().values()){
            IUsers.add(IUser);
        }

        return new UserListResponse(IUsers, errors);
    }

    public void login(IUser IUser) throws InfraException{
        this.userControl.login(IUser);
        this.userStatisticControl.registerLoginStatistic(IUser);
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
