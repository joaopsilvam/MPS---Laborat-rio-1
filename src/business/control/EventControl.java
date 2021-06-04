package business.control;

import business.model.Event;
import business.model.responses.EventListResponse;
import business.model.responses.EventResponse;
import exceptions.EventException;
import exceptions.InfraException;
import infra.EventPersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventControl {

    private HashMap<String, Event> events;

    public EventControl() throws InfraException{
        events = new HashMap<>();
        loadData();
    }

    private void loadData() throws InfraException {
        events = EventPersistence.loadEvents("events.dat");
    }

    public void saveData(){
        EventPersistence.saveEvents(events, "events.dat");
    }

    public List<String> add(Event event){
        List<String> errors = new ArrayList<>();
        try{
            avaliableEvent(event.getNome());
            events.put(event.getNome(), event);
        }catch(EventException e){
            errors.add(e.getMessage());
        }
        return errors;
    }

    public EventResponse read(String name){
        List<String> errors = new ArrayList<>();
        Event event = null;

        try{
            hasEvent(name);
            event = events.get(name);
        }catch (EventException e){
            errors.add(e.getMessage());
        }

        return new EventResponse(event, errors);
    }

    public EventListResponse readAll(){
        List<String> errors = new ArrayList<>();
        List<Event> events = new ArrayList<>();

        for(Event event : this.events.values()){
            events.add(event);
        }

        return new EventListResponse(events, errors);
    }

    public List<String> delete(String name){
        List<String> errors = new ArrayList<>();

        try {
            hasEvent(name);
            events.remove(name);
        }catch (EventException e){
            errors.add(name);
        }

        return errors;
    }

    private void avaliableEvent(String name) throws EventException {
        if(events.containsKey(name)){
            throw new EventException("Já existe um evento com este nome");
        }
    }

    private void hasEvent(String name) throws EventException {
        if(!events.containsKey(name)){
            throw new EventException("Esse evento não existe");
        }
    }
}
