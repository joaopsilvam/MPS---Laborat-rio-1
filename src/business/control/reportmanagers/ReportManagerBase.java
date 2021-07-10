package business.control.reportmanagers;

import business.control.UserStatisticControl;
import business.model.UserStatistic;
import util.InfraException;
import infra.IReportPersistence;
import infra.factories.ReportPersistenceFactory;

import java.util.List;

public abstract class ReportManagerBase {

    private UserStatisticControl userStatisticControl;

    public ReportManagerBase(UserStatisticControl userStatisticControl){
        this.userStatisticControl = userStatisticControl;
    }

    public final void saveReport() throws InfraException{
        String report = generateContent(userStatisticControl.getStatistics());
        ReportPersistenceFactory factory = ReportPersistenceFactory.getInstance();
        IReportPersistence persistence = factory.create(getReportType());

        persistence.saveFile(report, "relatorio");
    }

    protected abstract String generateContent(List<UserStatistic> statistics);

    protected abstract String getReportType();
}
