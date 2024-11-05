package GugelmeierC;

public class InvalidPasswordException extends Exception{

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor for the exception
     */
    public InvalidPasswordException()
    {
        super();
    }

    /**
     * Invalid Password Exception with a message
     * @param message
     */
    public InvalidPasswordException(String message)
    {
        super(message);
    }

}
