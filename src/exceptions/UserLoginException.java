package exceptions;

public class UserLoginException extends UserException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserLoginException(){
		super("Login inválido");
	}

	public UserLoginException(String message){
		super(message);
	}
}
