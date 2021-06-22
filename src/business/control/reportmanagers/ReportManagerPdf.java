package business.control.reportmanagers;

import business.control.UserStatisticControl;
import business.model.User;
import business.model.UserStatistic;
import exceptions.InfraException;

import java.util.Date;
import java.util.List;

public class ReportManagerPdf extends ReportManagerBase{

    public ReportManagerPdf(UserStatisticControl userStatisticControl) {
        super(userStatisticControl);
    }

    @Override
    protected String generateContent(List<UserStatistic> statistics) {
        final String userLabel = "Usuário: ";
        final String dateLabel = "Date: ";

        StringBuffer buffer = new StringBuffer();

        for(UserStatistic statistic : statistics){
            User user = statistic.getUser();
            Date loginDate = statistic.getLoginDate();

            buffer.append(userLabel);
            buffer.append(user.getLogin());
            buffer.append(" - ");
            buffer.append(dateLabel);
            buffer.append(loginDate);
            buffer.append('\n');
        }

        return buffer.toString();
    }

    @Override
    protected String getReportType() {
        return "pdf";
    }
}
