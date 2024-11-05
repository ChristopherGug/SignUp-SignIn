package GugelmeierC;

/**
 * This is a faculty user class
 * @author Christopher Gugelmeier
 * @Since 1.0
 * @Version 1.0
 */
public class MyUtil {
	/**
	 * Takes a string and checks if there is any numbers in it
	 * @param string
	 * @return true if it contains a number or false if not
	 * @throws NumberFormatException
	 */
    public static boolean containsNumber(String string) throws NumberFormatException
    {
        double d = 0;
        for (int i = 0; i < string.length(); i++)
        {
            try
            {
                d = Double.parseDouble(string.substring(i));
                return true;
            }
            catch(NumberFormatException ignored)
            {
            }
        }
        return false;
    }

}
