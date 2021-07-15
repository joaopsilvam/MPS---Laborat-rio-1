package business.control.reportmanagers;

import business.control.UserStatisticControl;
import business.model.IUser;
import business.model.UserStatistic;
import java.util.Date;
import java.util.List;

public class ReportManagerHtml extends ReportManagerBase{

    public ReportManagerHtml(UserStatisticControl userStatisticControl) {
        super(userStatisticControl);
    }

    @Override
    protected String generateContent(List<UserStatistic> statistics) {
        StringBuilder builder = new StringBuilder();

        builder.append("<!DOCTYPE html>");
        builder.append("<head><meta charset=\"utf-8\"></meta></head>");
        builder.append("<body>");
        builder.append("<ul>");

        for(UserStatistic statistic : statistics){
            IUser IUser = statistic.getUser();
            Date loginDate = statistic.getLoginDate();

            builder.append("<li>");
            builder.append("Usuário: ");
            builder.append(IUser.getLogin());
            builder.append(" - ");
            builder.append("Data: ");
            builder.append(loginDate);
            builder.append("</li>");
        }

        builder.append("</ul>");
        builder.append("</body>");

        return builder.toString();
    }

    @Override
    protected String getReportType() {
        return "html";
    }
}
