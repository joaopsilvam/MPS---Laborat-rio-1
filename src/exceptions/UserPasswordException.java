package exceptions;

public class UserPasswordException extends UserException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserPasswordException(){
		super("Senha inválido");
	}

}
