package business.control.reportmanagers;

import business.model.UserStatistic;

import java.util.List;

public class ReportManagerPdf extends ReportManagerBase{

    @Override
    protected String generateContent(List<UserStatistic> statistics) {
        return "";
    }

    @Override
    protected String getReportType() {
        return "pdf";
    }
}
