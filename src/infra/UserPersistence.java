package infra;

import java.io.*;
import java.util.HashMap;

import business.model.User;
import util.InfraException;

public class UserPersistence{

	public static HashMap<String, User> loadUsers(String nomeArquivo) throws InfraException {

		HashMap<String, User> users = new HashMap<String, User>();
		try {
			File arquivo = new File(nomeArquivo);
			if (arquivo.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arquivo));
				users = (HashMap<String, User>)objInput.readObject();
				objInput.close();
			}
		} catch(IOException erro1) {
			throw new InfraException();
		} catch(ClassNotFoundException erro2) {
			throw new InfraException();
		}

		return(users);
	}

	public static void saveUsers(HashMap<String, User> users, String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		try {

			arquivo.getParentFile().mkdirs();
			arquivo.createNewFile();

			ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arquivo));
			objOutput.writeObject(users);
			objOutput.close();

		} catch(IOException erro) {
			System.out.printf("Erro: %s", erro.getMessage());
		}
	}
}
