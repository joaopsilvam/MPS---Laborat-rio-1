package business.control.reportmanagers;

import business.control.UserStatisticControl;
import business.model.IUser;
import business.model.UserStatistic;

import java.util.Date;
import java.util.List;

public class ReportManagerTxt extends ReportManagerBase{

    public ReportManagerTxt(UserStatisticControl userStatisticControl) {
        super(userStatisticControl);
    }

    @Override
    protected String getReportType() {
        return "txt";
    }

    @Override
    protected String generateContent(List<UserStatistic> statistics){

        final String userLabel = "Usuário: ";
        final String dateLabel = "Data: ";

        StringBuilder builder = new StringBuilder();

        for(UserStatistic statistic : statistics){
            IUser IUser = statistic.getUser();
            Date loginDate = statistic.getLoginDate();

            builder.append(userLabel);
            builder.append(IUser.getLogin());
            builder.append(" - ");
            builder.append(dateLabel);
            builder.append(loginDate);
            builder.append('\n');
        }

        return builder.toString();
    }
}
