package infra;

import business.model.Document;
import exceptions.InfraException;

import java.io.*;
import java.util.HashMap;

public class DocumentPersistence {
    public static HashMap<String, Document> loadDocuments(String nomeArquivo) throws InfraException {

        HashMap<String, Document> documents = new HashMap<String, Document>();
        try {
            File arquivo = new File(nomeArquivo);
            if (arquivo.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arquivo));
                documents = (HashMap<String, Document>)objInput.readObject();
                objInput.close();
            }
        } catch(IOException erro1) {
            throw new InfraException();
        } catch(ClassNotFoundException erro2) {
            throw new InfraException();
        }

        return(documents);
    }

    public static void saveDocuments(HashMap<String, Document> documents, String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        try {
            arquivo.delete();
            arquivo.createNewFile();

            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arquivo));
            objOutput.writeObject(documents);
            objOutput.close();

        } catch(IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }
}
