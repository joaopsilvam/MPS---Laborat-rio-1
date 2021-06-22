package infra.factories;

import business.control.UserStatisticControl;
import infra.IReportPersistence;
import infra.ReportTXTPersistence;

public class ReportPersistenceTxtFactory implements IReportPersistenceFactory {
    @Override
    public IReportPersistence create() {
        return new ReportTXTPersistence();
    }
}
