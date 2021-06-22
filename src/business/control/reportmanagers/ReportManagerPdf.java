package business.control.reportmanagers;

import business.control.UserStatisticControl;
import business.model.User;
import business.model.UserStatistic;

import java.util.Date;
import java.util.List;

public class ReportManagerPdf extends ReportManagerBase{

    public ReportManagerPdf(UserStatisticControl userStatisticControl) {
        super(userStatisticControl);
    }

    @Override
    protected String generateContent(List<UserStatistic> statistics) {
        final String userLabel = "Usuário: ";
        final String dateLabel = "Data: ";

        StringBuilder builder = new StringBuilder();

        for(UserStatistic statistic : statistics){
            User user = statistic.getUser();
            Date loginDate = statistic.getLoginDate();

            builder.append(userLabel);
            builder.append(user.getLogin());
            builder.append(" - ");
            builder.append(dateLabel);
            builder.append(loginDate);
            builder.append('\n');
        }

        return builder.toString();
    }

    @Override
    protected String getReportType() {
        return "pdf";
    }
}
