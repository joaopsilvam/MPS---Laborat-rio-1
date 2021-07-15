package business.model.events;

import business.model.IEvent;
import business.model.IUser;

import java.util.Date;
import java.util.HashMap;

public class PresentialEvent extends EventBase{

    private String location;

    public PresentialEvent(PresentialEvent presentialEvent){
        super(presentialEvent.getName(), presentialEvent.getDescription(), presentialEvent.getDate());
        this.location = presentialEvent.location;
    }

    public PresentialEvent(){
        super();
        location = "";
    }

    public PresentialEvent(String name, String description, Date date){
        super(name, description, date);
        this.location = "";
    }

    public PresentialEvent(String name, String description, Date date, HashMap<String, IUser> users){
        super(name, description, date, users);
        this.location = "";
    }

    public PresentialEvent(String name, String description, Date date, HashMap<String, IUser> users, String location){
        super(name, description, date, users);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "Location: " + location;
    }

    @Override
    public IEvent copy() {
        return new PresentialEvent(this);
    }
}
