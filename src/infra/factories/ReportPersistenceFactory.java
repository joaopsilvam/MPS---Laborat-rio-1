package infra.factories;

import business.control.UserStatisticControl;
import business.control.factories.IReportManagerFactory;
import business.control.factories.ReportManagerHtmlFactory;
import business.control.factories.ReportManagerPdfFactory;
import business.control.factories.ReportManagerTxtFactory;
import business.control.reportmanagers.ReportManagerBase;

import java.util.HashMap;

public class ReportPersistenceFactory {
    public HashMap<String, IReportPersistenceFactory> reportPersistenceFactories = new HashMap<String, IReportPersistenceFactory>();

    private static ReportPersistenceFactory instancia;

    public ReportPersistenceFactory() {
        this.reportPersistenceFactories.put("pdf", new ReportPersistencePdfFactory());
        this.reportPersistenceFactories.put("html", new ReportPersistenceHtmlFactory());
        this.reportPersistenceFactories.put("txt", new ReportPersistenceTxtFactory());
    }

    public static ReportPersistenceFactory getInstancia() {
        if (instancia == null)
            instancia = new ReportPersistenceFactory();
        return instancia;
    }

//    public ReportManagerBase create(String typeReport , UserStatisticControl userStatisticControl){
//        IReportPersistenceFactory factory = this.reportPersistenceFactories.get(typeReport);
//
//        return factory.create(userStatisticControl);
//    }
}
