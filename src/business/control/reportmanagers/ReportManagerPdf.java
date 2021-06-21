package business.control.reportmanagers;

import business.control.UserStatisticControl;
import business.model.UserStatistic;
import exceptions.InfraException;

import java.util.List;

public class ReportManagerPdf extends ReportManagerBase{

    public ReportManagerPdf(UserStatisticControl userStatisticControl) {
        super(userStatisticControl);
    }

    @Override
    protected String generateContent(List<UserStatistic> statistics) {
        return "";
    }

    @Override
    protected String getReportType() {
        return "pdf";
    }
}
