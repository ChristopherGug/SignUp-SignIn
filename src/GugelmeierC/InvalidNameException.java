package GugelmeierC;

public class InvalidNameException extends Exception{

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor for the exception
     */
    public InvalidNameException()
    {
        super();
    }

    /**
     * Invalid Name Exception with a message
     * @param message
     */
    public InvalidNameException(String message)
    {
        super(message);
    }

}
