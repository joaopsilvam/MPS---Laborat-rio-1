package business.model;

import java.util.Date;
import java.util.HashMap;

public class OnlineEvent extends EventBase{

    private String link;

    public OnlineEvent(OnlineEvent onlineEvent){
        super(onlineEvent.getName(), onlineEvent.getDescription(), onlineEvent.getDate());
        this.link = onlineEvent.link;
    }

    public OnlineEvent(){
        super();
        link = "";
    }

    public OnlineEvent(String name, String description, Date date){
        super(name, description, date);
        this.link = "";
    }

    public OnlineEvent(String name, String description, Date date, HashMap<String, User> users){
        super(name, description, date, users);
        this.link = "";
    }

    public OnlineEvent(String name, String description, Date date, HashMap<String, User> users, String link){
        super(name, description, date, users);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDetails(){
        return super.getDetails() + "Link: " + link;
    }

    public IEvent copy(){
        return new OnlineEvent(this);
    }
}
