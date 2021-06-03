package business.model.responses;

import business.model.Event;

import java.util.List;

public class EventListResponse {

    private List<Event> events;
    private List<String> errors;

    public EventListResponse(List<Event> events, List<String> errors){
        this.events = events;
        this.errors = errors;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<String> getErrors() {
        return errors;
    }
}
