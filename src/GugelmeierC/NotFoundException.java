package GugelmeierC;

public class NotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 */
	public NotFoundException()
	{
		super();
	}
	
	/**
	 * Not Found Exception with a message
	 * @param message
	 */
	public NotFoundException(String message)
	{
		super(message);
	}
}
