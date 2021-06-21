package business.control;

import business.model.User;
import business.model.UserStatistic;
import exceptions.InfraException;
import infra.UserStatisticPersistence;

import java.util.Date;
import java.util.List;

public class UserStatisticControl {

    private static final String REPORT_CACHE_FILE = "report_cache.dat";

    private List<UserStatistic> statistics;

    public UserStatisticControl() throws InfraException{
        statistics = loadFile();
    }

    private List<UserStatistic> loadFile() throws InfraException {
        return UserStatisticPersistence.loadStatistics(REPORT_CACHE_FILE);
    }

    public void saveData(){
        UserStatisticPersistence.saveStatistics(statistics, REPORT_CACHE_FILE);
    }

    public void registerLoginStatistic(User user){
        UserStatistic statistic = new UserStatistic();

        statistic.setLoginDate(new Date());
        statistic.setUser(user);

        statistics.add(statistic);
    }

    public List<UserStatistic> getStatistics(){
        return statistics;
    }
}
