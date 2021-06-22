package infra.factories;

import business.control.UserStatisticControl;
import infra.IReportPersistence;

public interface IReportPersistenceFactory {
    IReportPersistence create(UserStatisticControl userStatisticControl);

}
