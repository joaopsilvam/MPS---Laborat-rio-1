package business.control.factories;

import business.control.UserStatisticControl;
import business.control.reportmanagers.ReportManagerBase;
import business.control.reportmanagers.ReportManagerHtml;
import business.control.reportmanagers.ReportManagerPdf;
import business.control.reportmanagers.ReportManagerTxt;
import exceptions.InfraException;

public class ReportManagerFactory {

    public ReportManagerBase create(String reportType, UserStatisticControl userStatisticControl){

        switch (reportType){
            case "txt":
                return new ReportManagerTxt(userStatisticControl);

            case "html":
                return new ReportManagerHtml(userStatisticControl);

            case "pdf":
                return new ReportManagerPdf(userStatisticControl);

            default:
                return null;
        }
    }
}
