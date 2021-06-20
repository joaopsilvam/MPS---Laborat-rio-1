package business.control.reportmanagers;

import business.model.User;
import business.model.UserStatistic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class ReportManagerBase {

    private static final String REPORT_CACHE_FILE = "report_cache.dat";

    private List<UserStatistic> statistics;

    public ReportManagerBase(){
        statistics = loadFile();
    }

    public final void registerLoginStatistic(User user){
        UserStatistic statistic = new UserStatistic();

        statistic.setLoginDate(new Date());
        statistic.setUser(user);

        statistics.add(statistic);
    }

    public final void saveReport(){
        String report = generateContent(statistics);

    }

    private List<UserStatistic> loadFile(){
        return new ArrayList<>();
    }

    protected abstract String generateContent(List<UserStatistic> statistics);

    protected abstract String getReportType();
}
