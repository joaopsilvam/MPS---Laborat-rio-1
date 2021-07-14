package business.model.news;

import business.model.INews;
import business.model.enums.Phase;

import java.util.Date;

public class SelectiveProcessNews extends NewsBase{

    private Phase phase;

    public SelectiveProcessNews(SelectiveProcessNews selectiveProcessNews){
        super(selectiveProcessNews.getTitle(), selectiveProcessNews.getDescription(), selectiveProcessNews.getDate());
        this.phase = selectiveProcessNews.phase;
    }

    public SelectiveProcessNews(){
        super();
        phase = Phase.DISCLOSURE;
    }

    public SelectiveProcessNews(String title, String description, Date date){
        super(title, description, date);
        this.phase = Phase.DISCLOSURE;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public String getDetails(){
        return super.getDetails() + "Phase: " + phase+"\n";
    }

    public INews copy(){
        return new SelectiveProcessNews(this);
    }
}
