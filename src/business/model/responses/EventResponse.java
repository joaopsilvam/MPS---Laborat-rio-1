package business.model.responses;

import business.model.Event;

import java.util.List;

public class EventResponse implements Response{

    private Event event;
    private List<String> errors;

    public EventResponse(Event event, List<String> errors){
        this.event = event;
        this.errors = errors;
    }

    public Event getEvent() {
        return event;
    }

    public List<String> getErrors() {
        return errors;
    }
}
