package business.control.reportmanagers;

import business.control.UserStatisticControl;
import business.model.User;
import business.model.UserStatistic;
import exceptions.InfraException;
import infra.UserStatisticPersistence;
import java.util.Date;
import java.util.List;

public abstract class ReportManagerBase {

    private UserStatisticControl userStatisticControl;

    public ReportManagerBase(UserStatisticControl userStatisticControl){
        this.userStatisticControl = userStatisticControl;
    }

    public final void saveReport(){
        String report = generateContent(userStatisticControl.getStatistics());
        System.out.println(report);
    }

    protected abstract String generateContent(List<UserStatistic> statistics);

    protected abstract String getReportType();
}
