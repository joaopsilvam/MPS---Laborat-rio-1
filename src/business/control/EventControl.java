package business.control;

import business.model.Event;
import business.model.User;
import business.model.responses.EventListResponse;
import business.model.responses.EventResponse;
import exceptions.EventException;
import exceptions.InfraException;
import infra.EventPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
public class EventControl {

    public class Memento{

        private final HashMap<String, Event> events;

        public Memento(HashMap<String, Event> events){
            this.events = new HashMap<>();

            for(String key : events.keySet()){
                Event event = events.get(key);
                Event newEvent = new Event();
                newEvent.setUsers((HashMap<String, User>) event.getUsers().clone());
                newEvent.setData(event.getData());
                newEvent.setDescricao(event.getDescricao());
                newEvent.setName(event.getName());

                this.events.put(key, newEvent);
            }
        }
    }

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
            avaliableEvent(event.getName());
            events.put(event.getName(), event);
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

    public void restore(EventControl.Memento memento){
        events = memento.events;
        System.out.println(events);
    }

    public EventControl.Memento backup(){
        return new EventControl.Memento(events);
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
