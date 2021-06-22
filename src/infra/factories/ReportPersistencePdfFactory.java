package infra.factories;

import business.control.UserStatisticControl;
import infra.IReportPersistence;
import infra.ReportPDFPersistence;

public class ReportPersistencePdfFactory implements IReportPersistenceFactory {
    @Override
    public IReportPersistence create(UserStatisticControl userStatisticControl) {
        return new ReportPDFPersistence();
    }
}
