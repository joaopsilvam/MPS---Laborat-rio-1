package business.control;

import business.model.ListUserResponse;
import business.model.User;
import business.model.UserResponse;
import exceptions.UserException;
import infra.UserPersistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class UserControl {

	private HashMap<String, User> users;
	private List<IValidator> validators;

	public UserControl(){
		ValidatorFactory validatorFactory = new ValidatorFactory();
		carregarDados();
		this.validators = validatorFactory.create();
	}

	public void carregarDados(){
		this.users = UserPersistence.loadUsers("dados.dat");
	}

	public void gravarDados(){
		UserPersistence.saveUsers(this.users, "dados.dat");
	}
	
	public List<UserException> add(User user) {
		List<UserException> exceptions = new ArrayList<>();

		for(IValidator validator : validators){
			try{
				validator.validate(user);
			}
			catch(UserException e){
				exceptions.add(e);
			}
		}

		if(exceptions.isEmpty())
			users.put(user.getLogin(), user);

		return exceptions;
	}

	public ListUserResponse listAll() {
		List<User> response = new ArrayList<>();

		for(User user : users.values()){
			response.add(user);
		}

		return new ListUserResponse(response);
	}

	public UserResponse read(String login) {
		List<UserException> exceptions = new ArrayList<>();
		User user = null;

		try{
			hasUser(login);
			user = users.get(login);
		}catch (UserException e){
			exceptions.add(e);
		}

		return new UserResponse(user, exceptions);
	}

	public List<UserException> delete(String login) {

		List<UserException> exceptions = new ArrayList<>();

		try{
			hasUser(login);
			users.remove(login);
		}catch (UserException e){
			exceptions.add(e);
		}

		return exceptions;
	}

	private void hasUser(String login) throws UserException{
		if(!users.containsKey(login)){
			throw new UserException();
		}
	}
}
