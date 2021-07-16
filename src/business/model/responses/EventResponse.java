package business.model.responses;

import business.model.IEvent;

import java.util.List;

public class EventResponse implements Response{

    private IEvent event;
    private List<String> errors;

    public EventResponse(IEvent event, List<String> errors){
        this.event = event;
        this.errors = errors;
    }

    public IEvent getEvent() {
        return event;
    }

    public List<String> getErrors() {
        return errors;
    }
}
