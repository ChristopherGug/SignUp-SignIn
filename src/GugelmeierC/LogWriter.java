package GugelmeierC;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class LogWriter {
	
	public void write(String message) {
		
		try {
		   	String logFile = "./test_log.log";
		    File f = new File(logFile);
		    PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(f)), true);
		    System.setErr(printStream);
		    System.setOut(printStream);
		    System.out.println("Log started: " + new java.util.Date());
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
