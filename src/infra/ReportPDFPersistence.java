package infra;

import java.io.*;

public class ReportPDFPersistence implements IReportPersistence{
    public void saveFile(byte[] decodedBytes, String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        try {
            arquivo.delete();
            arquivo.createNewFile();

            FileOutputStream fop = new FileOutputStream(arquivo);

            fop.write(decodedBytes);
            fop.flush();
            fop.close();

        } catch(IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }
}
