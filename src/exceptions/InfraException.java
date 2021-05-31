package exceptions;

public class InfraException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InfraException(){
		super("Erro interno");
	}

	public InfraException(String message){
		super(message);
	}
}
