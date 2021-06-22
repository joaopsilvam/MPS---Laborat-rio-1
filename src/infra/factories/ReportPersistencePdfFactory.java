package infra.factories;

import business.configuration.ApplicationConfiguration;
import business.control.UserStatisticControl;
import infra.IReportPersistence;
import infra.ReportPDFPersistence;
import infra.ReportPDFPersistenceWeb;

public class ReportPersistencePdfFactory implements IReportPersistenceFactory {
    @Override
    public IReportPersistence create() {
        String type = ApplicationConfiguration.getPdfPersistence();

        if(type.equals("local")){
            return new ReportPDFPersistence();
        }

        if(type.equals("web")){
            return new ReportPDFPersistenceWeb();
        }

        return null;
    }
}
