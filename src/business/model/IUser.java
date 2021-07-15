package business.model;

import java.io.Serializable;

public interface IUser{

	String getLogin();

	String getPassword();

	void setLogin(String login);

	void setPassword(String password);

	String getDetails();

	IUser copy();
}
