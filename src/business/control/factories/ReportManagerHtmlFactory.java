package business.control.factories;

import business.control.UserStatisticControl;
import business.control.reportmanagers.ReportManagerBase;
import business.control.reportmanagers.ReportManagerHtml;

import exceptions.InfraException;

public class ReportManagerHtmlFactory implements IReportManagerFactory {

    public ReportManagerBase create(UserStatisticControl userStatisticControl){
      return new ReportManagerHtml(userStatisticControl);
    }
}
