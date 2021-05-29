package business.model;

public class User {

	private String login;
	private String pass;

	public String getLogin(){
		return login;
	}

	public String getPassword(){
		return pass;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password){
		this.pass = password;
	}
}
