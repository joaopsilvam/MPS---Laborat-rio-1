package business.control;

import business.model.IUser;
import business.model.UserStatistic;
import util.InfraException;
import infra.Paths;
import infra.UserStatisticPersistence;

import java.util.Date;
import java.util.List;

public class UserStatisticControl {

    private List<UserStatistic> statistics;

    public UserStatisticControl() throws InfraException{
        statistics = loadFile();
    }

    private List<UserStatistic> loadFile() throws InfraException {
        return UserStatisticPersistence.loadStatistics(Paths.REPORT_PATH);
    }

    public void saveData(){
        UserStatisticPersistence.saveStatistics(statistics, Paths.REPORT_PATH);
    }

    public void registerLoginStatistic(IUser IUser){
        UserStatistic statistic = new UserStatistic();

        statistic.setLoginDate(new Date());
        statistic.setUser(IUser);

        statistics.add(statistic);
    }

    public List<UserStatistic> getStatistics(){
        return statistics;
    }
}
