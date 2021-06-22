package infra.factories;

import business.control.UserStatisticControl;
import infra.IReportPersistence;
import infra.ReportHTMLPersistence;

public class ReportPersistenceHtmlFactory implements IReportPersistenceFactory {
    @Override
    public IReportPersistence create(UserStatisticControl userStatisticControl) {
        return new ReportHTMLPersistence();
    }
}
