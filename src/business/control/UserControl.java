package business.control;

import business.control.factories.UserValidatorFactory;
import business.control.validators.IUserValidator;
import business.model.responses.UserListResponse;
import business.model.IUser;
import business.model.responses.UserResponse;
import util.InfraException;
import util.UserException;
import infra.Paths;
import infra.UserPersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserControl {

	private HashMap<String, IUser> users;

	public UserControl() throws InfraException{
		loadData();
	}

	private void loadData() throws InfraException{
		this.users = UserPersistence.loadUsers(Paths.USERS_PATH);
	}

	public void saveData(){
		UserPersistence.saveUsers(this.users, Paths.USERS_PATH);
	}
	
	public List<String> add(IUser IUser) {
		List<String> exceptions = new ArrayList<>();
		IUserValidator validator = UserValidatorFactory.create();

		try{
			avaliableUser(IUser.getLogin());
		}
		catch (UserException e){
			exceptions.add(e.getMessage());
		}

		validator.validate(IUser, exceptions);

		if(exceptions.isEmpty())
			users.put(IUser.getLogin(), IUser);

		return exceptions;
	}

	public UserListResponse readAll() {
		List<IUser> response = new ArrayList<>();

		for(IUser IUser : users.values()){
			response.add(IUser);
		}

		return new UserListResponse(response, new ArrayList<>());
	}

	public UserResponse read(String login) {
		List<String> exceptions = new ArrayList<>();
		IUser IUser = null;

		try{
			hasUser(login);
			IUser = users.get(login);
		}catch (UserException e){
			exceptions.add(e.getMessage());
		}

		return new UserResponse(IUser, exceptions);
	}

	public List<String> delete(String login) {

		List<String> exceptions = new ArrayList<>();

		try{
			hasUser(login);
			users.remove(login);
		}catch (UserException e){
			exceptions.add(e.getMessage());
		}

		return exceptions;
	}

	public String login(IUser IUser) {
		return "O usuário "+ IUser.getLogin()+" fez login!";
	}

	private void avaliableUser(String login) throws UserException{
		if(users.containsKey(login)){
			throw new UserException("Login não disponível");
		}
	}

	private void hasUser(String login) throws UserException{
		if(!users.containsKey(login)){
			throw new UserException("Usuário não existe");
		}
	}
}
