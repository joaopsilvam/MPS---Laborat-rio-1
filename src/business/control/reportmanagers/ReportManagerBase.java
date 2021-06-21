package business.control.reportmanagers;

import business.model.User;
import business.model.UserStatistic;
import exceptions.InfraException;
import infra.UserStatisticPersistence;
import java.util.Date;
import java.util.List;

public abstract class ReportManagerBase {

    private static final String REPORT_CACHE_FILE = "report_cache.dat";

    private List<UserStatistic> statistics;

    public ReportManagerBase() throws InfraException{
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

        UserStatisticPersistence.saveStatistics(statistics, REPORT_CACHE_FILE);
    }

    private List<UserStatistic> loadFile() throws InfraException {
        return UserStatisticPersistence.loadStatistics(REPORT_CACHE_FILE);
    }

    protected abstract String generateContent(List<UserStatistic> statistics);

    protected abstract String getReportType();
}
