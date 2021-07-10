package business.control.factories;

import business.control.UserStatisticControl;
import business.control.reportmanagers.ReportManagerBase;
import business.control.reportmanagers.ReportManagerPdf;

public class ReportManagerPdfFactory implements IReportManagerFactory {

    public ReportManagerBase create(UserStatisticControl userStatisticControl){
      return new ReportManagerPdf(userStatisticControl);
    }
}
