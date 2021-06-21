package business.control.reportmanagers;

import business.model.UserStatistic;
import exceptions.InfraException;

import java.util.List;

public class ReportManagerPdf extends ReportManagerBase{

    public ReportManagerPdf() throws InfraException{}

    @Override
    protected String generateContent(List<UserStatistic> statistics) {
        return "";
    }

    @Override
    protected String getReportType() {
        return "pdf";
    }
}
