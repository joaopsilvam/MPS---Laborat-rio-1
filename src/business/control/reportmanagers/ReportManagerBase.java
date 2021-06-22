package business.control.reportmanagers;

import business.control.UserStatisticControl;
import business.model.User;
import business.model.UserStatistic;
import exceptions.InfraException;
import infra.IReportPersistence;
import infra.UserStatisticPersistence;
import infra.factories.IReportPersistenceFactory;
import infra.factories.ReportPersistenceFactory;

import java.util.Date;
import java.util.List;

public abstract class ReportManagerBase {

    private UserStatisticControl userStatisticControl;

    public ReportManagerBase(UserStatisticControl userStatisticControl){
        this.userStatisticControl = userStatisticControl;
    }

    public final void saveReport() throws InfraException{
        String report = generateContent(userStatisticControl.getStatistics());
        ReportPersistenceFactory factory = ReportPersistenceFactory.getInstance();
        IReportPersistence persistence = factory.create(getReportType(), userStatisticControl);

        persistence.saveFile(report, "relatorio");
    }

    protected abstract String generateContent(List<UserStatistic> statistics);

    protected abstract String getReportType();
}
