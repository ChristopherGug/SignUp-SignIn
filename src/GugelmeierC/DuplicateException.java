package GugelmeierC;

public class DuplicateException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor for the exception
	 */
	public DuplicateException()
	{
		super();
	}
	
	/**
	 * Duplicate Exception with a message
	 * @param message
	 */
	public DuplicateException(String message)
	{
		super(message);
	}
}
