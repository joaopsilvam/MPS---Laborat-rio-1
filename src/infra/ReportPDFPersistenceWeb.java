package infra;

public class ReportPDFPersistenceWeb implements IReportPersistence{

    @Override
    public void saveFile(String data, String file){
        System.out.println("Salvando:\n" + data + "\n\n" + "No arquivo " + file + ".pdf");
    }
}
