package GugelmeierC;

/**
 * This is a college interface
 * @author Christopher Gugelmeier
 * @Since 1.0
 * @Version 1.0
 */
public interface CollegeInterface {
	//Default College name
	public static final String COLLEGE_NAME = "Durham College";
	//Default College phone number
	public static final String PHONE_NUMBER = "(905)721-2000";
	
	public abstract String getTypeForDisplay();
}
