package exceptions;

public class UserException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserException() {
        super("Usuário inválido");
    }

    public UserException(String msg) {
        super(msg);
    }
}
