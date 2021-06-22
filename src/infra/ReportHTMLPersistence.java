package infra;

import business.control.UserStatisticControl;
import business.model.UserStatistic;

import java.io.*;
import java.util.List;

public class ReportHTMLPersistence implements IReportPersistence{
    public ReportHTMLPersistence(UserStatisticControl userStatisticControl) {

    }

    public void saveFile(String texto, String nomeArquivo) {
        File arquivo = new File(nomeArquivo+".html");
        try {
            arquivo.delete();
            arquivo.createNewFile();

            FileWriter writer = new FileWriter(arquivo);
            writer.write(texto);
            writer.close();

        } catch(IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }
}
