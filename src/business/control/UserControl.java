package business.control;

import business.control.factories.UserValidatorFactory;
import business.control.validators.IUserValidator;
import business.model.responses.UserListResponse;
import business.model.User;
import business.model.responses.UserResponse;
import exceptions.InfraException;
import exceptions.UserException;
import infra.UserPersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserControl {

	private HashMap<String, User> users;
	private List<IUserValidator> validators;

	public UserControl() throws InfraException{
		UserValidatorFactory userValidatorFactory = new UserValidatorFactory();
		loadData();
		this.validators = userValidatorFactory.create();
	}

	private void loadData() throws InfraException{
		this.users = UserPersistence.loadUsers("usuarios.dat");
	}

	public void saveData(){
		UserPersistence.saveUsers(this.users, "usuarios.dat");
	}
	
	public List<String> add(User user) {
		List<String> exceptions = new ArrayList<>();

		try{
			avaliableUser(user.getLogin());
		}
		catch (UserException e){
			exceptions.add(e.getMessage());
		}

		for(IUserValidator validator : validators){
			try{
				validator.validate(user);
			}
			catch(UserException e){
				exceptions.add(e.getMessage());
			}
		}

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
