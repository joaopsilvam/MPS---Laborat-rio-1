package infra.factories;

import business.control.UserStatisticControl;
import business.control.factories.IReportManagerFactory;
import business.control.factories.ReportManagerHtmlFactory;
import business.control.factories.ReportManagerPdfFactory;
import business.control.factories.ReportManagerTxtFactory;
import business.control.reportmanagers.ReportManagerBase;
import infra.IReportPersistence;

import java.util.HashMap;

public class ReportPersistenceFactory {

    private HashMap<String, IReportPersistenceFactory> reportPersistenceFactories;

    private static ReportPersistenceFactory instancia;

    public ReportPersistenceFactory() {
        this.reportPersistenceFactories = new HashMap<>();
        this.reportPersistenceFactories.put("pdf", new ReportPersistencePdfFactory());
        this.reportPersistenceFactories.put("html", new ReportPersistenceHtmlFactory());
        this.reportPersistenceFactories.put("txt", new ReportPersistenceTxtFactory());
    }

    public static ReportPersistenceFactory getInstance() {
        if (instancia == null)
            instancia = new ReportPersistenceFactory();
        return instancia;
    }

    public IReportPersistence create(String typeReport , UserStatisticControl userStatisticControl){
        IReportPersistenceFactory factory = this.reportPersistenceFactories.get(typeReport);

        return factory.create();
    }
}
