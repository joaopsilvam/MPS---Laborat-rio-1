package business.control;

import business.control.factories.UserValidatorFactory;
import business.control.validators.IUserValidator;
import business.model.responses.UserListResponse;
import business.model.User;
import business.model.responses.UserResponse;
import exceptions.InfraException;
import exceptions.UserException;
import infra.Paths;
import infra.UserPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class UserControl {

	private HashMap<String, User> users;

	public UserControl() throws InfraException{
		loadData();
	}

	private void loadData() throws InfraException{
		this.users = UserPersistence.loadUsers(Paths.USERS_PATH);
	}

	public void saveData(){
		UserPersistence.saveUsers(this.users, Paths.USERS_PATH);
	}
	
	public List<String> add(User user) {
		List<String> exceptions = new ArrayList<>();
		IUserValidator validator = UserValidatorFactory.create();

		try{
			avaliableUser(user.getLogin());
		}
		catch (UserException e){
			exceptions.add(e.getMessage());
		}

		validator.validate(user, exceptions);

		if(exceptions.isEmpty())
			users.put(user.getLogin(), user);

		return exceptions;
	}

	public UserListResponse readAll() {
		List<User> response = new ArrayList<>();

		for(User user : users.values()){
			response.add(user);
		}

		return new UserListResponse(response, new ArrayList<>());
	}

	public UserResponse read(String login) {
		List<String> exceptions = new ArrayList<>();
		User user = null;

		try{
			hasUser(login);
			user = users.get(login);
		}catch (UserException e){
			exceptions.add(e.getMessage());
		}

		return new UserResponse(user, exceptions);
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

	public String login(User user) {
		return "O usuário "+user.getLogin()+" fez login!";
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
