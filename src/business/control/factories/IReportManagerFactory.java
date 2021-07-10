package business.control.factories;

import business.control.UserStatisticControl;
import business.control.reportmanagers.ReportManagerBase;

public interface IReportManagerFactory {
    ReportManagerBase create(UserStatisticControl userStatisticControl);
    
}
