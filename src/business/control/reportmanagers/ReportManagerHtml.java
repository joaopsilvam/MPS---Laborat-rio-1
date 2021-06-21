package business.control.reportmanagers;

import business.control.UserStatisticControl;
import business.model.User;
import business.model.UserStatistic;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import exceptions.InfraException;

import java.util.Date;
import java.util.List;

public class ReportManagerHtml extends ReportManagerBase{

    public ReportManagerHtml(UserStatisticControl userStatisticControl) {
        super(userStatisticControl);
    }

    @Override
    protected String generateContent(List<UserStatistic> statistics) {
        StringBuffer buffer = new StringBuffer();

        buffer.append("<!DOCTYPE html>");
        buffer.append("<head><meta charset=\"utf-8\"></meta></head>");
        buffer.append("<body>");
        buffer.append("<ul>");

        for(UserStatistic statistic : statistics){
            User user = statistic.getUser();
            Date loginDate = statistic.getLoginDate();

            buffer.append("<li>");
            buffer.append("Usuário: ");
            buffer.append(user.getLogin());
            buffer.append(" - ");
            buffer.append("Data: ");
            buffer.append(loginDate);
            buffer.append("</li>");
        }

        buffer.append("</ul>");
        buffer.append("</body>");

        return buffer.toString();
    }

    @Override
    protected String getReportType() {
        return "html";
    }
}
