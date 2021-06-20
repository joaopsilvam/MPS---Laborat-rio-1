package business.control.reportmanagers;

import business.model.UserStatistic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class ReportManagerBase {

    private List<UserStatistic> statistics;

    public ReportManagerBase(){
        statistics = new ArrayList<>();
    }

    public void registerLoginStatistic(String userName){
        UserStatistic statistic = new UserStatistic();

        statistic.setLoginDate(new Date());
        statistic.setUserName(userName);

        statistics.add(statistic);
    }

    public String generate(){
        return generateContent(statistics);
    }

    protected abstract String generateContent(List<UserStatistic> statistics);
}
