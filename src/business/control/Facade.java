package business.control;

import business.model.Event;
import business.model.User;
import business.model.responses.EventResponse;
import business.model.responses.UserResponse;
import exceptions.EventException;
import exceptions.InfraException;

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

    private Facade() throws InfraException{
        userControl = new UserControl();
        documentControl = new DocumentControl();
        eventControl = new EventControl();
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
}
