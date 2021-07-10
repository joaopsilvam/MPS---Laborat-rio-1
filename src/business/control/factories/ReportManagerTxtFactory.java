package business.control.factories;

import business.control.UserStatisticControl;
import business.control.reportmanagers.ReportManagerBase;
import business.control.reportmanagers.ReportManagerTxt;

public class ReportManagerTxtFactory implements IReportManagerFactory {

    public ReportManagerBase create(UserStatisticControl userStatisticControl){
      return new ReportManagerTxt(userStatisticControl);
    }
}
