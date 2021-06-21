package infra;

import business.model.UserStatistic;
import exceptions.InfraException;

import java.io.*;
import java.util.HashMap;

public class UserStatisticPersistence {

    public static HashMap<String, UserStatistic> loadStatistics(String nomeArquivo) throws InfraException {

        HashMap<String, UserStatistic> statistics = new HashMap<String, UserStatistic>();
        try {
            File arquivo = new File(nomeArquivo);
            if (arquivo.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arquivo));
                statistics = (HashMap<String, UserStatistic>)objInput.readObject();
                objInput.close();
            }
        } catch(IOException erro1) {
            throw new InfraException();
        } catch(ClassNotFoundException erro2) {
            throw new InfraException();
        }

        return(statistics);
    }

    public static void saveStatistics(HashMap<String, UserStatistic> statistics, String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        try {
            arquivo.delete();
            arquivo.createNewFile();

            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arquivo));
            objOutput.writeObject(statistics);
            objOutput.close();

        } catch(IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }
}
