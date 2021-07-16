package business.control;

import business.model.IEvent;
import business.model.responses.EventListResponse;
import business.model.responses.EventResponse;
import util.EventException;
import util.InfraException;
import infra.EventPersistence;
import infra.Paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class EventControl {

    public class Memento{

        private final HashMap<String, IEvent> events;

        public Memento(HashMap<String, IEvent> events){
            this.events = new HashMap<>();

            for(String key : events.keySet()){
                IEvent event = events.get(key);
                this.events.put(key, event.copy());
            }
        }
    }

    private HashMap<String, IEvent> events;

    public EventControl() throws InfraException{
        events = new HashMap<>();
        loadData();
    }

    private void loadData() throws InfraException {
        events = EventPersistence.loadEvents(Paths.EVENTS_PATH);
    }

    public void saveData(){
        EventPersistence.saveEvents(events, Paths.EVENTS_PATH);
    }

    public List<String> add(IEvent event){
        List<String> errors = new ArrayList<>();
        try{
            avaliableIEvent(event.getName());
            events.put(event.getName(), event);
        }catch(EventException e){
            errors.add(e.getMessage());
        }
        return errors;
    }

    public EventResponse read(String name){
        List<String> errors = new ArrayList<>();
        IEvent event = null;

        try{
            hasIEvent(name);
            event = events.get(name);
        }catch (EventException e){
            errors.add(e.getMessage());
        }

        return new EventResponse(event, errors);
    }

    public EventListResponse readAll(){
        List<String> errors = new ArrayList<>();
        List<IEvent> events = new ArrayList<>();

        for(IEvent event : this.events.values()){
            events.add(event);
        }

        return new EventListResponse(events, errors);
    }

    public List<String> delete(String name){
        List<String> errors = new ArrayList<>();

        try {
            hasIEvent(name);
            events.remove(name);
        }catch (EventException e){
            errors.add(name);
        }

        return errors;
    }

    public void restore(EventControl.Memento memento){
        events = memento.events;
    }

    public EventControl.Memento backup(){
        return new EventControl.Memento(events);
    }

    private void avaliableIEvent(String name) throws EventException {
        if(events.containsKey(name)){
            throw new EventException("Já existe um evento com este nome");
        }
    }

    private void hasIEvent(String name) throws EventException {
        if(!events.containsKey(name)){
            throw new EventException("Esse evento não existe");
        }
    }
}
