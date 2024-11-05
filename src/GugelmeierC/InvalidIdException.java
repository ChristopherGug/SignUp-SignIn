package GugelmeierC;

public class InvalidIdException extends Exception{

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor for the exception
     */
    public InvalidIdException()
    {
        super();
    }

    /**
     * Invalid Id Exception with a message
     * @param message
     */
    public InvalidIdException(String message)
    {
        super(message);
    }
}
