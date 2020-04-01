/****************************************************************************
 * Code adapted from the structure provided by our Lecutrer
 *Title: Week5.2/src/DataSource.java and Singleton/src/CalendarEager.java
 *Author: Amilcar Aponte
 *Date: 01/04/2020 (last accessed)
 *Availability: https://moodle.cct.ie/course/view.php?id=1505
 ****************************************************************************/

//This class has the Singleton Pattern implemented.

import java.sql.*;

public class DataSource {

    // Private Static instance of this class
    private static DataSource instance = new DataSource();

    private String db = "jdbc:mysql://52.50.23.197:3306/world";
    private String un = "cctstudent";
    private String pw = "Pass1234!";

    //Global variables
    private Connection conn;
    private Statement stmt;
    private ResultSet rs = null;

    //Private constructor to make sure that no other object can create an instance of it
    private DataSource() {

        try{

            // Get a connection to the database
            conn = DriverManager.getConnection( db, un, pw ) ;

            // Get a statement of the connection
            stmt = conn.createStatement() ;
        }
        catch( SQLException se ){
            System.out.println( "SQL Exception:" ) ;

            // Loop through the SQL Exceptions
            while( se != null ){
                System.out.println( "State  : " + se.getSQLState()  ) ;
                System.out.println( "Message: " + se.getMessage()   ) ;
                System.out.println( "Error  : " + se.getErrorCode() ) ;

                se = se.getNextException() ;
            }
        }
        catch( Exception e ){
            System.out.println( e ) ;
        }
    }

    //Method to save on the database. Receives a query and returns true or false
    public boolean save(String query){

        try {
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            System.out.println("Sorry, this country has duplicate values. Please try it again");
            return false;
        }
    }

    //Method to select entries from the database. Receives a query and returns the Result Set
    public ResultSet select(String query){

        // Execute the query
        try {
            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //Closing the connection with the Database
    public void closing(){

        // Close the result set, statement and the connection
        try {
            rs.close() ;
            stmt.close() ;
            conn.close() ;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Method to access the singleton instance of this class
    public static DataSource getInstance() {
        return instance;
    }

}
