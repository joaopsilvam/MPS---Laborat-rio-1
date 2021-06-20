package business.control.reportmanagers;

import business.model.UserStatistic;

import java.util.List;

public class ReportManagerTxt extends ReportManagerBase{

    @Override
    protected String generateContent(List<UserStatistic> statistics) {
        return "";
    }
}
