package GugelmeierC.tests;

import java.sql.Connection;
import java.util.Vector;

import GugelmeierC.*;

public class Lab3Tester {

    /** 
     * @param args
     */
    public static void main(String[] args) {

        Connection c = null;
        c = DatabaseConnect.initialize();
        User.initialize(c);
        Student.initialize(c);

        Vector<User> users = new Vector<User>();
        users = User.retrieveAll();
        
        System.out.println(users.size());
        
//        System.out.println("AUTHENTICATION TESTS \n");
//        
//        // test student authenticator
//        System.out.println("*******************************************");
//        System.out.println("Should be Successful - Mike Jones");
//        testAuth(100111111, "password");
//        System.out.println("*******************************************");
//        System.out.println("Should Fail - Wrong PW");
//        testAuth(100999999, "mypassword2");
//        System.out.println("*******************************************");
//        System.out.println("Should Fail - User not found");
//        testAuth(100000000, "mypassword");
//        System.out.println("*******************************************");
//        System.out.println("Should be Successul - Me");
//        testAuth(100852340, "password123");
//        System.out.println("*******************************************");
    }

    /** 
     * @param id
     * @param pw
     */
    public static void testAuth(long id, String pw) {

        try {
            Student stud = Student.authenticate(id, pw);
            System.out.println("Authentication Successful");
            System.out.println(stud.getFirstName() + " " + stud.getLastName());
        } catch (Exception nfe) {
            System.out.println("Authentication Failed: " + nfe.getMessage());
        }

    }

}
