package infra;

import business.model.UserStatistic;
import exceptions.InfraException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserStatisticPersistence {

    public static List<UserStatistic> loadStatistics(String nomeArquivo) throws InfraException {

        List<UserStatistic> statistics = new ArrayList<UserStatistic>();
        try {
            File arquivo = new File(nomeArquivo);
            if (arquivo.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arquivo));
                statistics = (List<UserStatistic>)objInput.readObject();
                objInput.close();
            }
        } catch(IOException erro1) {
            throw new InfraException();
        } catch(ClassNotFoundException erro2) {
            throw new InfraException();
        }

        return(statistics);
    }

    public static void saveStatistics(List<UserStatistic> statistics, String nomeArquivo) {
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
