package infra;

import java.io.*;


public class ReportTXTPersistence implements IReportPersistence {
    public void saveFile(String texto, String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
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
