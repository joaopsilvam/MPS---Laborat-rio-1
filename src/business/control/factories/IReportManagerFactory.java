package business.control.factories;

import business.control.UserStatisticControl;
import business.control.reportmanagers.ReportManagerBase;

import exceptions.InfraException;

public interface IReportManagerFactory {
    ReportManagerBase create(UserStatisticControl userStatisticControl);
}
