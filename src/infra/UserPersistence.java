package infra;

import java.io.*;
import java.util.HashMap;

import business.model.User;

public class UserPersistence{

	public static HashMap<String, User> loadUsers(String nomeArquivo){

		HashMap<String, User> users = new HashMap<String, User>();
		try {
			File arquivo = new File(nomeArquivo);
			if (arquivo.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arquivo));
				users = (HashMap<String, User>)objInput.readObject();
				objInput.close();
			}
		} catch(IOException erro1) {
			System.out.printf("Erro: %s", erro1.getMessage());
		} catch(ClassNotFoundException erro2) {
			System.out.printf("Erro: %s", erro2.getMessage());
		}
		return(users);
	}

	public static void saveUsers(HashMap<String, User> users, String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		try {
			arquivo.delete();
			arquivo.createNewFile();

			ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arquivo));
			objOutput.writeObject(users);
			objOutput.close();

		} catch(IOException erro) {
			System.out.printf("Erro: %s", erro.getMessage());
		}
	}
}
