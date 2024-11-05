package GugelmeierC;

public class InvalidUserDataException extends Exception{

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor for the exception
     */
    public InvalidUserDataException()
    {
        super();
    }

    /**
     * Invalid User Data with an exception
     * @param message
     */
    public InvalidUserDataException(String message)
    {
        super(message);
    }

}
