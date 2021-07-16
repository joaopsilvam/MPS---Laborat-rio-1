package business.model.responses;

import business.model.IEvent;

import java.util.List;

public class EventListResponse {

    private List<IEvent> events;
    private List<String> errors;

    public EventListResponse(List<IEvent> events, List<String> errors){
        this.events = events;
        this.errors = errors;
    }

    public List<IEvent> getEvents() {
        return events;
    }

    public List<String> getErrors() {
        return errors;
    }
}
