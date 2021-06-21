package business.control.factories;

import business.control.reportmanagers.ReportManagerBase;
import business.control.reportmanagers.ReportManagerHtml;
import business.control.reportmanagers.ReportManagerPdf;
import business.control.reportmanagers.ReportManagerTxt;
import exceptions.InfraException;

public class ReportManagerFactory {

    public ReportManagerBase create(String reportType) throws InfraException {

        switch (reportType){
            case "txt":
                return new ReportManagerTxt();

            case "html":
                return new ReportManagerHtml();

            case "pdf":
                return new ReportManagerPdf();

            default:
                return null;
        }
    }
}
