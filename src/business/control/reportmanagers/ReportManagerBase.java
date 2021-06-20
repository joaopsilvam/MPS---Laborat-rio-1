package business.control.reportmanagers;

import business.model.User;
import business.model.UserStatistic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class ReportManagerBase {

    private List<UserStatistic> statistics;

    public void init(){
        statistics = loadFile();
    }

    public void registerLoginStatistic(User user){
        UserStatistic statistic = new UserStatistic();

        statistic.setLoginDate(new Date());
        statistic.setUser(user);

        statistics.add(statistic);
    }

    public void generate(){
        saveFile(statistics);
    }

    protected abstract List<UserStatistic> loadFile();

    protected abstract void saveFile(List<UserStatistic> statistics);
}
