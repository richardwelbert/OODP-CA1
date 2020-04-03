/****************************************************************************
 * Code adapted from the structure provided by our Lecutrer
 *Title: Week5.2/src/MainWeek5.java
 *Author: Amilcar Aponte
 *Date: 01/04/2020 (last accessed)
 *Availability: https://moodle.cct.ie/course/view.php?id=1505
 ****************************************************************************/

//This class is responsible for the interaction with the user

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;

public class Client {

    public Client(){
        menu();
    }

    //Creates a new instance of the DAO class
    CountryDAO dao = new MySqlCountryDAO();

    //Global variables
    ArrayList<Country> countries;
    Country c;

    //Method to read input from the user
    private String readingFromUser(){

        Scanner sc = new Scanner( System.in );
        String input = sc.nextLine();
        return input;
    }

    //Main menu
    private void menu(){

        System.out.println("Hello! Welcome to the World System! \n" +
                "Please, enter one of the options below:\n\n" +
                "1- List ALL Countries in the World System; \n" +
                "2- Find a Country by Country Code; \n" +
                "3- Find a Country by Country Name; \n" +
                "4- Save a new Country; \n" +
                "5- Exit.\n");
        subMenu();
    }

    //Submenu
    private void subMenu() {

        String input = readingFromUser();
        if(input.equals("1")) {

            listAllCountries();
        }
        else if(input.equals("2")) {

            findByCode();
        }
        else if(input.equals("3")) {

            findByName();
        }
        else if(input.equals("4")) {

            saveNewCountry();
        }
        else if(input.equals("5")) {
            DataSource.getInstance().closing();
            System.out.println("Goodbye!");
            System.exit(0);
        }

    }

    //Method that lists all countries from the database
    private void listAllCountries(){
        System.out.println("Here's a list of ALL Countries!\n");

        //Calling the method in the DAO class to get all the countries in the DB
        countries = dao.getCountries();
        //Printing all countries
        System.out.println(countries);
        System.out.println("Redirecting you to the Main Menu.\n");
        menu();
    }

    //Method that lists the country with an specific code requested by the user
    private void findByCode(){

        System.out.println("Find a Country by Country Code!");
        System.out.println("Please tell me the Country Code: \n" +
                "(It shouldn't be more than 3 characters!) \n");
        String input = readingFromUser();

        if(input.isEmpty()){
            System.out.println("Sorry, you need to type something\n" +
                    "Please try it again.\n\n");
            findByCode();
        }

        //Validation of the code (can't have more than 3 characters)
        if (input.length() <= 3) {

            //Calling the method in the DAO class to find by ID
            c = dao.findCountryById(input);
            if (c == null) {
                System.out.println("Sorry, this value doesn't exist!\n" +
                        "Please try it again!\n");
                findByCode();
            } else {
                System.out.println("Here is the Country requested:");
                //Printing the country requested
                System.out.println(c);
                menu();
            }
        }
        else {
            System.out.println("I'm sorry, this is Code is invalid.\n" +
                    "Please remember the Country Code shouldn't exceed 3 characters!\n" +
                    "Please try it again.\n\n");
            findByCode();
        }
    }

    //Method that lists all countries with the same name requested by the user
    private void findByName(){

        System.out.println("Find a Country by Country Name!");
        System.out.println("Please tell me the Country Name: \n");

        String input = readingFromUser();

        if(input.isEmpty()){
            System.out.println("Sorry, you need to type something\n" +
                    "Please try it again.\n\n");
            findByName();
        }

        //Calling the method in the DAO class to find by Name
        countries = dao.findCountryByName(input);
        if (countries.isEmpty()) {
            System.out.println("Sorry, this value doesn't exist!\n" +
                    "Please try it again!\n");
            findByName();
        }else {
            System.out.println("Here is the Country requested:");
            //Printing all countries with the specific name
            System.out.println(countries);
            menu();
        }
    }

    //Method that creates a new country and saves into the database
    private Country saveNewCountry (){

        Continent con = null;

        System.out.println("Save a New Country! \n");
        System.out.println("Please type a Code for this new Country:" +
                "(It shouldn't be more than 3 characters!)");
        String input = readingFromUser();

        inputIsEmpty(input);

        //Validation of the country code (can't have more than 3 characters)
        if (input.length() > 3){
            System.out.println("I'm sorry, this is Code is invalid.\n" +
                    "Please remember the Country Code shouldn't exceed 3 characters!\n" +
                    "Please try it again.\n\n");
            saveNewCountry();
        }

        System.out.println("Now type a Name for this new Country:");
        String input2 = readingFromUser();

        inputIsEmpty(input2);

        if (!input2.matches("[a-zA-Z_]+")) {
            System.out.println("Sorry, you can only input letters here.\n" +
                    "Please try it again.\n");
            input2 = "";
            saveNewCountry();
        }

        System.out.println("And a Continent that matches those options:\n" +
                "'Asia', 'Europe', 'North America', 'Africa', 'Oceania', 'Antarctica' or 'South America'\n" +
                "Please note the system is case-sensitive.");
        String input3 = readingFromUser();

        inputIsEmpty(input3);

        //Replacing the space with the "_" to match with the Enums
        String contName = input3.replaceAll("\\s+", "_");

        //Validation of the continent. Must match with the Enum class.
        if (enumIsValid(contName)){
            //Assigning the contents of the string into the enum continent
            con = Continent.valueOf(contName);

        } else {
            System.out.println("Sorry, this Continent is not valid.\n" +
                    "Please try it again.\n\n");
            input3 = "";
            saveNewCountry();
        }
        System.out.println("Now type a Surface Area for this new Country:");
        String input4 = readingFromUser();

        inputIsEmpty(input4);

        //Validation of the Surface Area. Must be only numbers.
        if (!input4.matches("[0-9]+")) {
            System.out.println("Sorry, you can only input numbers here.\n" +
                    "Please try it again.\n");
            input4 = "";
            saveNewCountry();
        }

        float area = Float.parseFloat(input4);

        System.out.println("And to finish, type the Country's Head Of State:");
        String input5 = readingFromUser();

        inputIsEmpty(input5);

        //Creating a new object Country
        Country c = new Country.CountryBuilder (input, input2, con, area, input5).build();
        //Saving into the Database
        dao.saveCountry(c);

        menu();
        return c;
    }

    //Code below adapted from the structure that can be found on the link below
    //Author: Simon Forsberg
    //Date: 01/04/2020 (last accessed)
    //Availability: https://codereview.stackexchange.com/questions/42620/validating-an-input-string-against-some-enum-fields

    //Method that validates the continent. Must match with the Enum class.
    private boolean enumIsValid (String enumName){

        //Creating an Enum Set with the Enum values
        EnumSet<Continent> except = EnumSet.of(Continent.Europe, Continent.Africa, Continent.Antarctica, Continent.Asia, Continent.North_America, Continent.Oceania, Continent.South_America);

        boolean valid;
        try {
            //Checking if the input matches the values in the array
            valid = except.contains(Continent.valueOf(enumName));
        } catch (IllegalArgumentException e) { valid = false; }

        //Printing to check if it's valid or not
        //System.out.println(valid ? "valid" : "invalid");

        return valid;
    }

    public void inputIsEmpty(String i){
        if(i.isEmpty()){
            System.out.println("Sorry, you need to type something\n" +
                    "Please try it again.\n\n");
            saveNewCountry();
        }
    }

}
